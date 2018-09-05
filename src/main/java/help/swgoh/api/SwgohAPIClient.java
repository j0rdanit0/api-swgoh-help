package help.swgoh.api;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public class SwgohAPIClient implements SwgohAPI
{
    private final String loginCredentials;
    private final String defaultLanguage;
    private final Boolean defaultEnums;

    SwgohAPIClient( SwgohAPISettings settings )
    {
        loginCredentials = "username=" + settings.getUsername() +
                           "&password=" + settings.getPassword() +
                           "&grant_type=password" +
                           "&client_id=abc" +
                           "&client_secret=123";
        defaultLanguage = settings.getDefaultLanguage() == null ? null : settings.getDefaultLanguage().getSwgohCode();
        defaultEnums = settings.getDefaultEnums();
    }

    public enum API
    {
        signin( "/auth/signin" ),
        player( "/swgoh/player" ),
        guild( "/swgoh/guild" ),
        units( "/swgoh/units" ),
        data( "/swgoh/data" ),
        zetas( "/swgoh/zetas" ),
        squads( "/swgoh/squads" ),
        events( "/swgoh/events" ),
        battles( "/swgoh/battles" ),
        ;

        private static final String URL_BASE = "https://api.swgoh.help";
        private static final Gson GSON = new Gson();

        private static final SwgohAPIToken TOKEN = new SwgohAPIToken();
        private volatile long expiredMillis;

        private final String path;

        API( String path )
        {
            this.path = path;
        }

        public CompletableFuture<String> call( String loginCredentials, Map<String, Object> payload )
        {
            return CompletableFuture.supplyAsync( () -> {
                try
                {
                    return getJson( loginCredentials, payload );
                }
                catch ( Exception exception )
                {
                    throw new SwgohAPIException( "Unable to complete request.", exception );
                }
            } );
        }

        private String getJson( String loginCredentials, Map<String, Object> payload ) throws IOException
        {
            try ( BufferedReader br = new BufferedReader( new InputStreamReader( getAuthorizedConnection( loginCredentials, payload ).getInputStream() ) ) )
            {
                StringBuilder sb = new StringBuilder();
                String line;
                while ( ( line = br.readLine() ) != null )
                {
                    sb.append( line ).append( "\n" );
                }
                return sb.toString();
            }
        }

        private HttpURLConnection getAuthorizedConnection( String loginCredentials, Map<String, Object> payload ) throws IOException
        {
            if ( TOKEN.access_token == null || System.currentTimeMillis() > (expiredMillis - 10) )
            {
                synchronized ( TOKEN )
                {
                    if ( TOKEN.access_token == null || System.currentTimeMillis() > (expiredMillis - 10) )
                    {
                        login( loginCredentials );
                    }
                }
            }

            byte[] postData = GSON.toJson( payload ).getBytes( StandardCharsets.UTF_8 );
            HttpURLConnection connection = createConnection( this, postData );
            connection.setRequestProperty( "Authorization", "Bearer " + TOKEN.access_token );
            connection.setRequestProperty( "Content-Type", "application/json" );

            try( DataOutputStream outputStream = new DataOutputStream( connection.getOutputStream() ) )
            {
                outputStream.write( postData );
            }

            return connection;
        }

        private HttpURLConnection createConnection( API api, byte[] postData ) throws IOException
        {
            HttpURLConnection connection = (HttpURLConnection) api.getUrl().openConnection();
            connection.setRequestMethod( "POST" );

            connection.setDoOutput( true );
            connection.setInstanceFollowRedirects( false );
            connection.setUseCaches( false );
            connection.setRequestProperty( "Content-Type", "application/x-www-form-urlencoded" );
            connection.setRequestProperty( "charset", "utf-8" );
            connection.setRequestProperty( "Content-Length", postData.length+"" );

            return connection;
        }

        private void login( String loginCredentials )
        {
            try
            {
                SwgohAPIToken token = fetchToken( loginCredentials );
                expiredMillis = System.currentTimeMillis() + (token.expires_in * 1000);
                TOKEN.access_token = token.access_token;
            }
            catch ( Throwable exception )
            {
                throw new SwgohAPIException( "Unable to authorize with API.", exception );
            }
        }

        private SwgohAPIToken fetchToken( String loginCredentials ) throws IOException
        {
            byte[] postData = loginCredentials.getBytes( StandardCharsets.UTF_8 );
            HttpURLConnection connection = createConnection( API.signin, postData );

            try( DataOutputStream outputStream = new DataOutputStream( connection.getOutputStream() ) )
            {
                outputStream.write( postData );
            }

            return GSON.fromJson( new InputStreamReader( connection.getInputStream() ), SwgohAPIToken.class );
        }

        private URL getUrl() throws MalformedURLException
        {
            return new URL( URL_BASE + path );
        }
    }

    @Override
    public CompletableFuture<String> getPlayers( int[] allyCodes, Boolean enums, Language language, PlayerField... fields )
    {
        Map<String, Object> payload = new HashMap<>();
        payload.put( "allycodes", allyCodes );
        payload.put( "enums", enums == null ? defaultEnums : enums );
        payload.put( "language", language == null ? defaultLanguage : language.getSwgohCode() );

        createProjection( payload, fields );

        return API.player.call( loginCredentials, payload );
    }

    @Override
    public CompletableFuture<String> getGuild( int allyCode, Boolean enums, Language language, GuildField... fields )
    {
        Map<String, Object> payload = new HashMap<>();
        payload.put( "allycode", allyCode );
        payload.put( "enums", enums == null ? defaultEnums : enums );
        payload.put( "language", language == null ? defaultLanguage : language.getSwgohCode() );

        createProjection( payload, fields );

        return API.guild.call( loginCredentials, payload );
    }

    @Override
    public CompletableFuture<String> getLargeGuild( int allyCode, Boolean enums, Language language, GuildField... fields )
    {
        Map<String, Object> payload = new HashMap<>();
        payload.put( "allycode", allyCode );
        payload.put( "roster", true );
        payload.put( "enums", enums == null ? defaultEnums : enums );
        payload.put( "language", language == null ? defaultLanguage : language.getSwgohCode() );

        createProjection( payload, fields );

        return API.guild.call( loginCredentials, payload );
    }

    @Override
    public CompletableFuture<String> getGuildUnits( int allyCode, boolean includeMods, Boolean enums, Language language, GuildField... fields )
    {
        Map<String, Object> payload = new HashMap<>();
        payload.put( "allycode", allyCode );
        payload.put( "roster", true );
        payload.put( "units", true );
        payload.put( "mods", includeMods );
        payload.put( "enums", enums == null ? defaultEnums : enums );
        payload.put( "language", language == null ? defaultLanguage : language.getSwgohCode() );

        createProjection( payload, fields );

        return API.guild.call( loginCredentials, payload );
    }

    @Override
    public CompletableFuture<String> getUnits( int[] allyCodes, boolean includeMods, Boolean enums, Language language, UnitsField... fields )
    {
        Map<String, Object> payload = new HashMap<>();
        payload.put( "allycode", allyCodes );
        payload.put( "mods", includeMods );
        payload.put( "enums", enums == null ? defaultEnums : enums );
        payload.put( "language", language == null ? defaultLanguage : language.getSwgohCode() );

        createProjection( payload, fields );

        return API.units.call( loginCredentials, payload );
    }

    @Override
    public CompletableFuture<String> getZetaRecommendations( ZetaRecommendationField... fields )
    {
        Map<String, Object> payload = new HashMap<>();

        createProjection( payload, fields );

        return API.zetas.call( loginCredentials, payload );
    }

    @Override
    public CompletableFuture<String> getSquadRecommendations( SquadRecommendationField... fields )
    {
        Map<String, Object> payload = new HashMap<>();

        createProjection( payload, fields );

        return API.squads.call( loginCredentials, payload );
    }

    @Override
    public CompletableFuture<String> getEvents( Boolean enums, Language language, EventField... fields )
    {
        Map<String, Object> payload = new HashMap<>();
        payload.put( "enums", enums == null ? defaultEnums : enums );
        payload.put( "language", language == null ? defaultLanguage : language.getSwgohCode() );

        createProjection( payload, fields );

        return API.events.call( loginCredentials, payload );
    }

    @Override
    public CompletableFuture<String> getBattles( Boolean enums, Language language, BattleField... fields )
    {
        Map<String, Object> payload = new HashMap<>();
        payload.put( "enums", enums == null ? defaultEnums : enums );
        payload.put( "language", language == null ? defaultLanguage : language.getSwgohCode() );

        createProjection( payload, fields );

        return API.battles.call( loginCredentials, payload );
    }

    @Override
    public CompletableFuture<String> getSupportData( Collection collection, Map<String, Object> matchCriteria, Boolean enums, Language language, String... fields )
    {
        Map<String, Object> payload = new HashMap<>();
        payload.put( "collection", collection.name() );
        payload.put( "enums", enums == null ? defaultEnums : enums );
        payload.put( "language", language == null ? defaultLanguage : language.getSwgohCode() );

        if ( matchCriteria != null )
        {
            payload.put( "match", matchCriteria );
        }

        createProjection( payload, fields );

        return API.data.call( loginCredentials, payload );
    }

    private void createProjection( Map<String, Object> payload, Enum... fields )
    {
        if ( fields != null && fields.length > 0 )
        {
            List<String> stringFields = new ArrayList<>();
            for ( Enum field : fields )
            {
                stringFields.add( field.name() );
            }

            createProjection( payload, stringFields.toArray( new String[]{} ) );
        }
    }

    private void createProjection( Map<String, Object> payload, String... fields )
    {
        if ( fields != null && fields.length > 0 )
        {
            Map<String, Integer> fieldsMap = new HashMap<>();
            for ( String field : fields )
            {
                fieldsMap.put( field, 1 );
            }
            payload.put( "project", fieldsMap );
        }
    }
}
