package help.swgoh.api;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import help.swgoh.api.exception.SwgohAPIDuplicateRequestException;
import help.swgoh.api.exception.SwgohAPIException;
import help.swgoh.api.exception.SwgohAPINotFoundException;
import help.swgoh.api.exception.SwgohAPIRateLimitException;
import help.swgoh.api.exception.SwgohAPITimeoutException;
import help.swgoh.api.models.equipment.Equipment;
import help.swgoh.api.models.event.Events;
import help.swgoh.api.models.guild.Guild;
import help.swgoh.api.models.player.Player;
import help.swgoh.api.models.player.PlayerRoster;
import help.swgoh.api.response.RegistrationResponse;

import java.io.*;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.concurrent.*;
import java.util.zip.GZIPInputStream;

public class SwgohAPIClient implements SwgohAPI {
    private static final Gson GSON = new Gson();
    private static final SwgohAPIToken TOKEN = new SwgohAPIToken();
    private static final ScheduledExecutorService TOKEN_REFRESHER_SERVICE = new ScheduledThreadPoolExecutor(1);

    private final String urlBase;
    private final String username;
    private final String password;
    private final String defaultLanguage;
    private final Boolean defaultEnums;

    SwgohAPIClient(SwgohAPISettings settings) {
        urlBase = settings.getUrlBase();
        username = settings.getUsername();
        password = settings.getPassword();
        defaultLanguage = settings.getDefaultLanguage() == null ? null : settings.getDefaultLanguage().getSwgohCode();
        defaultEnums = settings.getDefaultEnums();
    }

    public enum API {
        signin("/auth/signin"),
        players("/swgoh/players"),
        guilds("/swgoh/guilds"),
        roster("/swgoh/roster"),
        zetas("/swgoh/zetas"),
        squads("/swgoh/squads"),
        events("/swgoh/events"),
        battles("/swgoh/battles"),
        data("/swgoh/data"),
        registration("/registration"),
        ;

        private final String path;

        API(String path) {
            this.path = path;
        }

        public String getPath() {
            return path;
        }
    }

    public CompletableFuture<String> call(API api, String username, String password, Map<String, Object> payload) {
        return CompletableFuture.supplyAsync(() -> getJson(api, username, password, payload));
    }

    public <T> CompletableFuture<T> call(API api, String username, String password, Map<String, Object> payload, Class<T> resultType) {
        return CompletableFuture.supplyAsync(() -> GSON.fromJson(getJson(api, username, password, payload), resultType));
    }

    public <T> CompletableFuture<T> call(API api, String username, String password, Map<String, Object> payload, Type resultType) {
        return CompletableFuture.supplyAsync(() -> GSON.fromJson(getJson(api, username, password, payload), resultType));
    }

    private String getJson(API api, String username, String password, Map<String, Object> payload) {
        try {
            try (BufferedReader br = new BufferedReader(getInputReader(getAuthorizedConnection(api, username, password, payload)))) {
                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = br.readLine()) != null) {
                    sb.append(line).append("\n");
                }
                return sb.toString();
            }
        } catch (FileNotFoundException exception) {
            throw new SwgohAPINotFoundException(exception);
        } catch (IOException ioException) {
            if (ioException.getMessage() != null) {
                if (ioException.getMessage().contains("429") || ioException.getMessage().contains("502")) {
                    throw new SwgohAPIRateLimitException(ioException);
                } else if (ioException.getMessage().contains("409")) {
                    throw new SwgohAPIDuplicateRequestException(ioException);
                } else if (ioException.getMessage().contains("504")) {
                    throw new SwgohAPITimeoutException(ioException);
                }
            }

            throw new SwgohAPIException("Unable to complete request.", ioException);
        }
    }

    private HttpURLConnection getAuthorizedConnection(API api, String username, String password, Map<String, Object> payload) throws IOException {
        if (TOKEN.access_token == null) {
            synchronized (TOKEN) {
                if (TOKEN.access_token == null) {
                    login(urlBase, username, password);
                }
            }
        }

        byte[] postData = GSON.toJson(payload).getBytes(StandardCharsets.UTF_8);
        HttpURLConnection connection = createConnection(urlBase, api, postData);
        connection.setRequestProperty("Authorization", "Bearer " + TOKEN.access_token);
        connection.setRequestProperty("Content-Type", "application/json");

        try (DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream())) {
            outputStream.write(postData);
        }

        return connection;
    }

    private static HttpURLConnection createConnection(String urlBase, API api, byte[] postData) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) getUrl(urlBase, api).openConnection();
        connection.setRequestMethod("POST");

        connection.setDoOutput(true);
        connection.setInstanceFollowRedirects(false);
        connection.setUseCaches(false);
        connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        connection.setRequestProperty("charset", "utf-8");
        connection.setRequestProperty("Content-Length", postData.length + "");
        connection.setRequestProperty("Accept-Encoding", "gzip");

        return connection;
    }

    public static void login(String urlBase, String username, String password) {
        try {
            long requestStartMillis = System.currentTimeMillis();
            SwgohAPIToken token = fetchToken(urlBase, username, password);
            TOKEN.access_token = token.access_token;
            long requestMillis = System.currentTimeMillis() - requestStartMillis;

            TOKEN_REFRESHER_SERVICE.schedule(
                    () -> TOKEN.access_token = null,
                    Math.max(0, (token.expires_in * 1000) - requestMillis - 50),
                    TimeUnit.MILLISECONDS
            );
        } catch (Throwable exception) {
            throw new SwgohAPIException("Unable to authorize with API.", exception);
        }
    }

    private static SwgohAPIToken fetchToken(String urlBase, String username, String password) throws IOException {
        String loginCredentials = "username=" + username +
                "&password=" + password +
                "&grant_type=password" +
                "&client_id=abc" +
                "&client_secret=123";

        byte[] postData = loginCredentials.getBytes(StandardCharsets.UTF_8);
        HttpURLConnection connection = createConnection(urlBase, API.signin, postData);

        try (DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream())) {
            outputStream.write(postData);
        }

        return GSON.fromJson(getInputReader(connection), SwgohAPIToken.class);
    }

    private static Reader getInputReader(HttpURLConnection connection) throws IOException {
        Reader reader;
        if ("gzip".equals(connection.getContentEncoding())) {
            reader = new InputStreamReader(new GZIPInputStream(connection.getInputStream()));
        } else {
            reader = new InputStreamReader(connection.getInputStream());
        }

        return reader;
    }

    private static URL getUrl(String urlBase, API api) throws MalformedURLException {
        return new URL(urlBase + api.getPath());
    }

    @Override
    public CompletableFuture<String> getPlayers(List<Integer> allyCodes, Language language, SwgohAPIFilter filter) {
        Map<String, Object> payload = new HashMap<>();
        payload.put("allycodes", allyCodes);
        payload.put("enums", defaultEnums);
        payload.put("language", language == null ? defaultLanguage : language.getSwgohCode());

        createProjection(payload, filter);

        return call(API.players, username, password, payload);
    }

    @Override
    public CompletableFuture<String> getGuild(int allyCode, Language language, SwgohAPIFilter filter) {
        Map<String, Object> payload = new HashMap<>();
        payload.put("allycode", allyCode);
        payload.put("enums", defaultEnums);
        payload.put("language", language == null ? defaultLanguage : language.getSwgohCode());

        createProjection(payload, filter);

        return call(API.guilds, username, password, payload);
    }

    @Override
    public CompletableFuture<String> getLargeGuild(int allyCode, Language language, SwgohAPIFilter filter) {
        Map<String, Object> payload = new HashMap<>();
        payload.put("allycode", allyCode);
        payload.put("roster", true);
        payload.put("enums", defaultEnums);
        payload.put("language", language == null ? defaultLanguage : language.getSwgohCode());

        createProjection(payload, filter);

        return call(API.guilds, username, password, payload);
    }

    @Override
    public CompletableFuture<String> getRosters(List<Integer> allyCodes, Language language, SwgohAPIFilter filter) {
        Map<String, Object> payload = new HashMap<>();
        payload.put("allycode", allyCodes);
        payload.put("enums", defaultEnums);
        payload.put("language", language == null ? defaultLanguage : language.getSwgohCode());

        createProjection(payload, filter);

        return call(API.roster, username, password, payload);
    }

    @Override
    public CompletableFuture<String> getZetaRecommendations(SwgohAPIFilter filter) {
        Map<String, Object> payload = new HashMap<>();

        createProjection(payload, filter);

        return call(API.zetas, username, password, payload);
    }

    @Override
    public CompletableFuture<String> getSquadRecommendations(SwgohAPIFilter filter) {
        Map<String, Object> payload = new HashMap<>();

        createProjection(payload, filter);

        return call(API.squads, username, password, payload);
    }

    @Override
    public CompletableFuture<String> getEvents(Language language, SwgohAPIFilter filter) {
        Map<String, Object> payload = new HashMap<>();
        payload.put("enums", defaultEnums);
        payload.put("language", language == null ? defaultLanguage : language.getSwgohCode());

        createProjection(payload, filter);

        return call(API.events, username, password, payload);
    }

    @Override
    public CompletableFuture<String> getBattles(Language language, SwgohAPIFilter filter) {
        Map<String, Object> payload = new HashMap<>();
        payload.put("enums", defaultEnums);
        payload.put("language", language == null ? defaultLanguage : language.getSwgohCode());

        createProjection(payload, filter);

        return call(API.battles, username, password, payload);
    }

    @Override
    public CompletableFuture<String> getSupportData(Collection collection, Map<String, Object> matchCriteria, Language language, SwgohAPIFilter filter) {
        Map<String, Object> payload = new HashMap<>();
        payload.put("collection", collection.name());
        payload.put("enums", defaultEnums);
        payload.put("language", language == null ? defaultLanguage : language.getSwgohCode());

        if (matchCriteria != null) {
            payload.put("match", matchCriteria);
        }

        createProjection(payload, filter);

        return call(API.data, username, password, payload);
    }

    @Override
    public CompletableFuture<RegistrationResponse> register(Map<Integer, String> allyCodeDiscordIdMappings) {
        Map<String, Object> payload = new HashMap<>();

        List<List<Object>> mappings = new ArrayList<>();
        for (Integer allyCode : allyCodeDiscordIdMappings.keySet()) {
            List<Object> mapping = new ArrayList<>();
            mapping.add(allyCode);
            mapping.add(allyCodeDiscordIdMappings.get(allyCode));
            mappings.add(mapping);
        }

        payload.put("put", mappings);

        return call(API.registration, username, password, payload, RegistrationResponse.class);
    }

    @Override
    public CompletableFuture<RegistrationResponse> unregister(List<Integer> allyCodes, List<String> discordIds) {
        Map<String, Object> payload = new HashMap<>();

        List<Object> allyCodesOrDiscordIds = new ArrayList<>();
        if (allyCodes != null) {
            allyCodesOrDiscordIds.addAll(allyCodes);
        }

        if (discordIds != null) {
            allyCodesOrDiscordIds.addAll(discordIds);
        }

        payload.put("del", allyCodesOrDiscordIds);

        return call(API.registration, username, password, payload, RegistrationResponse.class);
    }

    @Override
    public CompletableFuture<RegistrationResponse> getRegistrationByAllyCodes(List<Integer> allyCodes) {
        Map<String, Object> payload = new HashMap<>();
        payload.put("get", allyCodes);

        return call(API.registration, username, password, payload, RegistrationResponse.class);
    }

    @Override
    public CompletableFuture<RegistrationResponse> getRegistrationByDiscordIds(List<String> discordIds) {
        Map<String, Object> payload = new HashMap<>();
        payload.put("get", discordIds);

        return call(API.registration, username, password, payload, RegistrationResponse.class);
    }

    private void createProjection(Map<String, Object> payload, SwgohAPIFilter filter) {
        if (filter != null && filter.getFilters().size() > 0) {
            payload.put("project", convertInnerFilters(filter));
        }
    }

    private Map<String, Object> convertInnerFilters(SwgohAPIFilter filter) {
        Map<String, Object> fieldsMap = new HashMap<>();
        for (Object element : filter.getFilters()) {
            if (element instanceof SwgohAPIFilter.InnerFilter) {
                fieldsMap.put(((SwgohAPIFilter.InnerFilter) element).element, convertInnerFilters(((SwgohAPIFilter.InnerFilter) element).filter));
            } else {
                fieldsMap.put((String) element, 1);
            }
        }

        return fieldsMap;
    }

    private void createProjection(Map<String, Object> payload, String... fields) {
        if (fields != null && fields.length > 0) {
            Map<String, Integer> fieldsMap = new HashMap<>();
            for (String field : fields) {
                fieldsMap.put(field, 1);
            }
            payload.put("project", fieldsMap);
        }
    }

    public CompletableFuture<List<Player>> getFullPlayers(List<Integer> allyCodes, Language language, SwgohAPIFilter filter) {
        Map<String, Object> payload = new HashMap<>();
        payload.put("allycodes", allyCodes);
        payload.put("enums", defaultEnums);
        payload.put("language", language == null ? defaultLanguage : language.getSwgohCode());

        createProjection(payload, filter);
        return call(API.players, username, password, payload, new TypeToken<List<Player>>() {
        }.getType());
    }


    @Override
    public CompletableFuture<List<Guild>> getFullGuild(int allyCode, Language language, SwgohAPIFilter filter) {
        Map<String, Object> payload = new HashMap<>();
        payload.put("allycode", allyCode);
        payload.put("enums", defaultEnums);
        payload.put("language", language == null ? defaultLanguage : language.getSwgohCode());

        createProjection(payload, filter);

        return call(API.guilds, username, password, payload, new TypeToken<List<Guild>>() {
        }.getType());
    }

    @Override
    public CompletableFuture<List<Guild>> getFullLargeGuild(int allyCode, Language language, SwgohAPIFilter filter) {
        Map<String, Object> payload = new HashMap<>();
        payload.put("allycode", allyCode);
        payload.put("enums", defaultEnums);
        payload.put("language", language == null ? defaultLanguage : language.getSwgohCode());

        createProjection(payload, filter);

        return call(API.guilds, username, password, payload, new TypeToken<List<Guild>>() {
        }.getType());
    }

    @Override
    public CompletableFuture<Events> getFullEvents(Language language, SwgohAPIFilter filter) {
        Map<String, Object> payload = new HashMap<>();
        payload.put("enums", defaultEnums);
        payload.put("language", language == null ? defaultLanguage : language.getSwgohCode());

        createProjection(payload, filter);

        return call(API.events, username, password, payload, Events.class);
    }

    @Override
    public List<PlayerRoster> getImprovedRosters(List<Integer> allyCodes, Language language, SwgohAPIFilter filter) throws ExecutionException, InterruptedException {
        filter.and("roster");

        String responseString = getPlayers(allyCodes, language, filter).get();
        System.out.println(responseString);

        Player[] players = GSON.fromJson(initReader(responseString), Player[].class);
        List<PlayerRoster> rosters = new ArrayList<>();
        for (Player player : players) {
            rosters.add(player.getPlayerRoster());
        }
        return rosters;
    }

    @Override
    public CompletableFuture<List<Equipment>> getEquipment(Map<String, Object> matchCriteria, Language language, SwgohAPIFilter filter) {
        Map<String, Object> payload = new HashMap<>();
        payload.put("collection", Collection.equipmentList);
        payload.put("enums", defaultEnums);
        payload.put("language", language == null ? defaultLanguage : language.getSwgohCode());

        if (matchCriteria != null) {
            payload.put("match", matchCriteria);
        }

        createProjection(payload, filter);

        return call(API.data, username, password, payload, new TypeToken<List<Equipment>>(){}.getType());
    }

    /* Supporting methods */

    private JsonReader initReader(String input) {

        JsonReader jsonReader = new JsonReader(new StringReader(input));
        jsonReader.setLenient(true);

        return jsonReader;
    }
}
