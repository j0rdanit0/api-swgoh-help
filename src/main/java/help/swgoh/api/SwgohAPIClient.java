package help.swgoh.api;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import help.swgoh.api.response.Event;
import help.swgoh.api.response.Player;
import help.swgoh.api.response.TB;
import help.swgoh.api.response.Token;
import help.swgoh.api.response.Unit;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;

public class SwgohAPIClient implements SwgohAPI
{
    private static final Gson GSON = new Gson();

    private final String loginCredentials;
    private final String urlBase;

    private String access_token;

    public enum Language
    {
        English( "eng", "ENG_US", "English" ),
        ChineseSimplified( "chi_sim", "CHS_CN", "Chinese" ),
        ChineseTraditional( "chi_tra", "CHT_CN", "Traditional Chinese" ),
        French( "fra", "FRE_FR", "French" ),
        German( "deu", "GER_DE", "German" ),
        Indonesian( "ind", "IND_ID", "Indonesian" ),
        Italian( "ita", "ITA_IT", "Italian" ),
        Japanese( "jpn", "JPN_JP", "Japanese" ),
        Korean( "kor", "KOR_KR", "Korean" ),
        Portuguese( "por", "POR_BR", "Portuguese" ),
        Russian( "rus", "RUS_RU", "Russian" ),
        Spanish( "spa", "SPA_XM", "Spanish" ),
        Thai( "tha", "THA_TH", "Thai" ),
        Turkish( "tur", "TUR_TR", "Turkish" ),
        ;

        private final String tesseractCode;
        private final String displayName;
        private final String swgohCode;

        Language( String tesseractCode, String swgohCode, String displayName )
        {
            this.tesseractCode = tesseractCode;
            this.swgohCode = swgohCode;
            this.displayName = displayName;
        }

        public String getTesseractCode()
        {
            return tesseractCode;
        }

        public String getDisplayName()
        {
            return displayName;
        }

        public String getSwgohCode()
        {
            return swgohCode;
        }
    }

    public enum DataCriteria
    {
        EVENTS, UNITS, ARENA, GEAR, MOD_SETS, MOD_STATS, SKILLS, SKILL_TYPES, TB, ZETAS, ZETA_ABILITIES, ZETA_RECOMMENDATIONS, BATTLES;

        @Override
        public String toString()
        {
            return name().toLowerCase().replaceAll( "_", "-" );
        }
    }

    public enum API
    {
        signin( "/auth/signin" ),
        data( "/swgoh/data/" ),
        player( "/swgoh/player/" ),
        guild( "/swgoh/guild/" ),
        ;

        private final String path;

        API( String path )
        {
            this.path = path;
        }

        private String constructUrl( String urlBase, String dataCriteria, Language language )
        {
            String lang = language != null ? "/"+language.getSwgohCode() : "";
            String criteria = dataCriteria != null ? dataCriteria : "";
            return urlBase + path + criteria + lang;
        }

        public URL getUrl( String urlBase, String criteria, Language language ) throws MalformedURLException
        {
            return new URL( constructUrl( urlBase, criteria, language ) );
        }
    }

    public SwgohAPIClient( SwgohAPISettings settings )
    {
        urlBase = String.format( "%s://%s%s", "http" + (settings.isUsesSSL() ? "s" : ""), settings.getHost(), settings.getPort() );
        loginCredentials = "username=" + settings.getUsername() +
                           "&password=" + settings.getPassword() +
                           "&grant_type=password" +
                           "&client_id=" + settings.getClientId() +
                           "&client_secret=" + settings.getClientSecret();
    }

    @Override
    public Player getPlayer( int allyCode ) throws IOException
    {
        return getPlayer( allyCode, null );
    }

    @Override
    public Player getPlayer( int allyCode, Language language ) throws IOException
    {
        return callApi( API.player.getUrl( urlBase, allyCode+"", language ), Player.class );
    }

    @Override
    public String getPlayerJSON( int allyCode ) throws IOException
    {
        return getPlayerJSON( allyCode, null );
    }

    @Override
    public String getPlayerJSON( int allyCode, Language language ) throws IOException
    {
        return callApi( API.player.getUrl( urlBase, allyCode+"", language ) );
    }

    @Override
    public List<Player> getGuild( int allyCode ) throws IOException
    {
        return getGuild( allyCode, null );
    }

    @Override
    public List<Player> getGuild( int allyCode, Language language ) throws IOException
    {
        return callApi( API.guild.getUrl( urlBase, allyCode+"", language ), new TypeToken<List<Player>>(){}.getType() );
    }

    @Override
    public String getGuildJSON( int allyCode ) throws IOException
    {
        return getGuildJSON( allyCode, null );
    }

    @Override
    public String getGuildJSON( int allyCode, Language language ) throws IOException
    {
        return callApi( API.guild.getUrl( urlBase, allyCode+"", language ) );
    }

    @Override
    public List<Event> getEvents() throws IOException
    {
        return getEvents( null );
    }

    @Override
    public List<Event> getEvents( Language language ) throws IOException
    {
        return fetchData( DataCriteria.EVENTS, language, new TypeToken<List<Event>>(){}.getType() );
    }

    @Override
    public String getEventsJSON() throws IOException
    {
        return getEventsJSON( null );
    }

    @Override
    public String getEventsJSON( Language language ) throws IOException
    {
        return fetchData( DataCriteria.EVENTS, language );
    }

    @Override
    public Map<String, Unit> getUnits() throws IOException
    {
        return getUnits( null );
    }

    @Override
    public Map<String, Unit> getUnits( Language language ) throws IOException
    {
        return fetchData( DataCriteria.UNITS, language, new TypeToken<Map<String, Unit>>(){}.getType() );
    }

    @Override
    public String getUnitsJSON() throws IOException
    {
        return getUnitsJSON( null );
    }

    @Override
    public String getUnitsJSON( Language language ) throws IOException
    {
        return fetchData( DataCriteria.UNITS, language );
    }

    @Override
    public List<TB> getTBs() throws IOException
    {
        return getTBs( null );
    }

    @Override
    public List<TB> getTBs( Language language ) throws IOException
    {
        return fetchData( DataCriteria.TB, language, new TypeToken<List<TB>>(){}.getType() );
    }

    @Override
    public String getTBsJSON() throws IOException
    {
        return getTBsJSON( null );
    }

    @Override
    public String getTBsJSON( Language language ) throws IOException
    {
        return fetchData( DataCriteria.TB, language );
    }

    private void login()
    {
        try
        {
            access_token = fetchToken().access_token;
        }
        catch ( Throwable exception )
        {
            throw new SwgohAPIException( exception );
        }
    }

    private <T> T fetchData( DataCriteria criteria, Language language, Class<? extends T> response ) throws IOException
    {
        return callApi( API.data.getUrl( urlBase, criteria.toString(), language ), response );
    }

    private <T> T fetchData( DataCriteria criteria, Language language, Type responseType ) throws IOException
    {
        return callApi( API.data.getUrl( urlBase, criteria.toString(), language ), responseType );
    }

    private String fetchData( DataCriteria criteria, Language language ) throws IOException
    {
        return callApi( API.data.getUrl( urlBase, criteria.toString(), language ) );
    }

    private Token fetchToken() throws IOException
    {
        byte[] postData = loginCredentials.getBytes( StandardCharsets.UTF_8 );

        return callApi( API.signin.getUrl( urlBase, null, null ), postData, Token.class );
    }

    private <T> T callApi( URL url, byte[] postData, Class<? extends T> response ) throws IOException
    {
        return GSON.fromJson( new InputStreamReader( createConnection( url, postData ).getInputStream() ), response );
    }

    private <T> T callApi( URL url, Class<? extends T> response ) throws IOException
    {
        return GSON.fromJson( new InputStreamReader( getAuthorizedConnection( url ).getInputStream() ), response );
    }

    private <T> T callApi( URL url, Type responseType ) throws IOException
    {
        return GSON.fromJson( new InputStreamReader( getAuthorizedConnection( url ).getInputStream() ), responseType );
    }

    private String callApi( URL url ) throws IOException
    {
        try ( BufferedReader br = new BufferedReader( new InputStreamReader( getAuthorizedConnection( url ).getInputStream() ) ) )
        {
            StringBuilder sb = new StringBuilder();
            String line;
            while ( ( line = br.readLine() ) != null )
            {
                sb.append( line + "\n" );
            }
            return sb.toString();
        }
    }

    private HttpURLConnection getAuthorizedConnection( URL url ) throws IOException
    {
        if ( access_token == null )
        {
            login();
        }

        HttpURLConnection connection = createConnection( url );
        connection.setRequestProperty( "Authorization", "Bearer " + access_token );

        return connection;
    }

    private HttpURLConnection createConnection( URL url ) throws IOException
    {
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod( "POST" );

        return connection;
    }

    private HttpURLConnection createConnection( URL url, byte[] postData ) throws IOException
    {
        HttpURLConnection connection = createConnection( url );

        connection.setDoOutput( true );
        connection.setInstanceFollowRedirects( false );
        connection.setUseCaches( false );
        connection.setRequestProperty( "Content-Type", "application/x-www-form-urlencoded" );
        connection.setRequestProperty( "charset", "utf-8" );
        connection.setRequestProperty( "Content-Length", postData.length+"" );

        try( DataOutputStream outputStream = new DataOutputStream( connection.getOutputStream() ) )
        {
            outputStream.write( postData );
        }

        return connection;
    }
}
