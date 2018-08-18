package help.swgoh.api;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import help.swgoh.api.response.*;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SwgohAPIClient implements SwgohAPI
{
    private static final Gson GSON = new Gson();

    private final String loginCredentials;
    private final String urlBase;

    private String access_token;

    public enum API
    {
        signin( "/auth/signin" ),
        player( "/swgoh/player" ),
        guild( "/swgoh/guild" ),
        units( "/swgoh/units" ),
        data( "/swgoh/data" ),
        ;

        private final String path;

        API( String path )
        {
            this.path = path;
        }

        public URL getUrl( String urlBase ) throws MalformedURLException
        {
            return new URL( urlBase + path );
        }
    }

    SwgohAPIClient( SwgohAPISettings settings )
    {
        urlBase = String.format( "%s://%s%s", "http" + (settings.isUsesSSL() ? "s" : ""), settings.getHost(), settings.getPort() );
        loginCredentials = "username=" + settings.getUsername() +
                           "&password=" + settings.getPassword() +
                           "&grant_type=password" +
                           "&client_id=abc" +
                           "&client_secret=123";
    }

    @Override
    public SwgohPlayer getPlayer( int[] allyCodes, PlayerField... fields ) throws IOException
    {
        return getPlayer( allyCodes, null, fields );
    }

    @Override
    public SwgohPlayer getPlayer( int[] allyCodes, Language language, PlayerField... fields ) throws IOException
    {
        return GSON.fromJson( getPlayerJSON( allyCodes, language, fields ), SwgohPlayer.class );
    }

    @Override
    public String getPlayerJSON( int[] allyCodes, PlayerField... fields ) throws IOException
    {
        return getPlayerJSON( allyCodes, null, fields );
    }

    @Override
    public String getPlayerJSON( int[] allyCodes, Language language, PlayerField... fields ) throws IOException
    {
        Map<String, Object> payload = new HashMap<>();
        payload.put( "allycodes", allyCodes );
        if ( language != null )
        {
            payload.put( "language", language.getSwgohCode() );
        }
        if ( fields != null && fields.length > 0 )
        {
            Map<String, Integer> fieldsMap = new HashMap<>();
            for ( PlayerField field : fields )
            {
                fieldsMap.put( field.name(), 1 );
            }
            payload.put( "project", fieldsMap );
        }

        return callApi( API.player.getUrl( urlBase ), payload );
    }

    @Override
    public SwgohGuild getGuild( int allyCode, GuildField... fields ) throws IOException
    {
        return getGuild( allyCode, null, fields );
    }

    @Override
    public SwgohGuild getGuild( int allyCode, Language language, GuildField... fields ) throws IOException
    {
        return GSON.fromJson( getGuildJSON( allyCode, language, fields), SwgohGuild.class );
    }

    @Override
    public String getGuildJSON( int allyCode, GuildField... fields ) throws IOException
    {
        return getGuildJSON( allyCode, null, fields );
    }

    @Override
    public String getGuildJSON( int allyCode, Language language, GuildField... fields ) throws IOException
    {
        Map<String, Object> payload = new HashMap<>();
        payload.put( "allycode", allyCode );
        if ( language != null )
        {
            payload.put( "language", language.getSwgohCode() );
        }
        if ( fields != null && fields.length > 0 )
        {
            Map<String, Integer> fieldsMap = new HashMap<>();
            for ( GuildField field : fields )
            {
                fieldsMap.put( field.name(), 1 );
            }
            payload.put( "project", fieldsMap );
        }

        return callApi( API.guild.getUrl( urlBase ), payload );
    }

    @Override
    public Map<String, List<SwgohPlayerUnit>> getUnits( int[] allyCodes ) throws IOException
    {
        return getUnits( allyCodes, true );
    }

    @Override
    public Map<String, List<SwgohPlayerUnit>> getUnits( int[] allyCodes, boolean includeMods ) throws IOException
    {
        return GSON.fromJson( getUnitsJSON( allyCodes, includeMods ), new TypeToken<Map<String, List<SwgohPlayerUnit>>>(){}.getType() );
    }

    @Override
    public String getUnitsJSON( int[] allyCodes ) throws IOException
    {
        return getUnitsJSON( allyCodes, true );
    }

    @Override
    public String getUnitsJSON( int[] allyCodes, boolean includeMods ) throws IOException
    {
        Map<String, Object> payload = new HashMap<>();
        payload.put( "allycode", allyCodes );
        payload.put( "mods", includeMods );
        return callApi( API.units.getUrl( urlBase ), payload );
    }

    @Override
    public String getSupportData( Collection collection, String... fields ) throws IOException
    {
        return getSupportData( collection, null, null, fields );
    }

    @Override
    public String getSupportData( Collection collection, Language language, String... fields ) throws IOException
    {
        return getSupportData( collection, language, null, fields );
    }

    @Override
    public String getSupportData( Collection collection, Map<String, String> matchCriteria, String... fields ) throws IOException
    {
        return getSupportData( collection, null, matchCriteria, fields );
    }

    @Override
    public String getSupportData( Collection collection, Language language, Map<String, String> matchCriteria, String... fields ) throws IOException
    {
        Map<String, Object> payload = new HashMap<>();
        payload.put( "collection", collection.name() );
        if ( language != null )
        {
            payload.put( "language", language.getSwgohCode() );
        }
        if ( matchCriteria != null )
        {
            payload.put( "match", matchCriteria );
        }
        if ( fields != null && fields.length > 0 )
        {
            Map<String, Integer> fieldsMap = new HashMap<>();
            for ( String field : fields )
            {
                fieldsMap.put( field, 1 );
            }
            payload.put( "project", fieldsMap );
        }

        return callApi( API.data.getUrl( urlBase ), payload );
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

    private Token fetchToken() throws IOException
    {
        byte[] postData = loginCredentials.getBytes( StandardCharsets.UTF_8 );
        URL url = API.signin.getUrl( urlBase );
        HttpURLConnection connection = createConnection( url, postData );

        try( DataOutputStream outputStream = new DataOutputStream( connection.getOutputStream() ) )
        {
            outputStream.write( postData );
        }

        return GSON.fromJson( new InputStreamReader( connection.getInputStream() ), Token.class );
    }

    private String callApi( URL url, Map<String, Object> payload ) throws IOException
    {
        try ( BufferedReader br = new BufferedReader( new InputStreamReader( getAuthorizedConnection( url, payload ).getInputStream() ) ) )
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

    private HttpURLConnection getAuthorizedConnection( URL url, Map<String, Object> payload ) throws IOException
    {
        if ( access_token == null )
        {
            login();
        }

        byte[] postData = GSON.toJson( payload ).getBytes( StandardCharsets.UTF_8 );
        HttpURLConnection connection = createConnection( url, postData );
        connection.setRequestProperty( "Authorization", "Bearer " + access_token );
        connection.setRequestProperty( "Content-Type", "application/json" );

        try( DataOutputStream outputStream = new DataOutputStream( connection.getOutputStream() ) )
        {
            outputStream.write( postData );
        }

        return connection;
    }

    private HttpURLConnection createConnection( URL url, byte[] postData ) throws IOException
    {
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod( "POST" );

        connection.setDoOutput( true );
        connection.setInstanceFollowRedirects( false );
        connection.setUseCaches( false );
        connection.setRequestProperty( "Content-Type", "application/x-www-form-urlencoded" );
        connection.setRequestProperty( "charset", "utf-8" );
        connection.setRequestProperty( "Content-Length", postData.length+"" );

        return connection;
    }
}
