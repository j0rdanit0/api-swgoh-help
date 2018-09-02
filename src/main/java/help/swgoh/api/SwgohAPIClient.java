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

public class SwgohAPIClient implements SwgohAPI
{
    private final String loginCredentials;

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

        private volatile String access_token;
        private volatile long expiredMillis;

        private final String path;

        API( String path )
        {
            this.path = path;
        }

        public String call( String loginCredentials, Map<String, Object> payload ) throws IOException
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
            if ( access_token == null || System.currentTimeMillis() > (expiredMillis - 10) )
            {
                login( loginCredentials );
            }

            byte[] postData = GSON.toJson( payload ).getBytes( StandardCharsets.UTF_8 );
            HttpURLConnection connection = createConnection( this, postData );
            connection.setRequestProperty( "Authorization", "Bearer " + access_token );
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
                access_token = token.access_token;
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

    SwgohAPIClient( SwgohAPISettings settings )
    {
        loginCredentials = "username=" + settings.getUsername() +
                           "&password=" + settings.getPassword() +
                           "&grant_type=password" +
                           "&client_id=abc" +
                           "&client_secret=123";
    }

    @Override
    public String getPlayers( int[] allyCodes, Boolean enums, Language language, PlayerField... fields ) throws IOException
    {
        Map<String, Object> payload = new HashMap<>();
        payload.put( "allycodes", allyCodes );

        if ( enums != null )
        {
            payload.put( "enums", enums );
        }

        if ( language != null )
        {
            payload.put( "language", language.getSwgohCode() );
        }

        createProjection( payload, fields );

        return API.player.call( loginCredentials, payload );
    }

    @Override
    public String getGuild( int allyCode, Boolean enums, Language language, GuildField... fields ) throws IOException
    {
        Map<String, Object> payload = new HashMap<>();
        payload.put( "allycode", allyCode );

        if ( enums != null )
        {
            payload.put( "enums", enums );
        }

        if ( language != null )
        {
            payload.put( "language", language.getSwgohCode() );
        }

        createProjection( payload, fields );

        return API.guild.call( loginCredentials, payload );
    }

    @Override
    public String getLargeGuild( int allyCode, Boolean enums, Language language, GuildField... fields ) throws IOException
    {
        Map<String, Object> payload = new HashMap<>();
        payload.put( "allycode", allyCode );
        payload.put( "roster", true );

        if ( enums != null )
        {
            payload.put( "enums", enums );
        }

        if ( language != null )
        {
            payload.put( "language", language.getSwgohCode() );
        }

        createProjection( payload, fields );

        return API.guild.call( loginCredentials, payload );
    }

    @Override
    public String getGuildUnits( int allyCode, boolean includeMods, Boolean enums, Language language, GuildField... fields ) throws IOException
    {
        Map<String, Object> payload = new HashMap<>();
        payload.put( "allycode", allyCode );
        payload.put( "roster", true );
        payload.put( "units", true );
        payload.put( "mods", includeMods );

        if ( enums != null )
        {
            payload.put( "enums", enums );
        }

        if ( language != null )
        {
            payload.put( "language", language.getSwgohCode() );
        }

        createProjection( payload, fields );

        return API.guild.call( loginCredentials, payload );
    }

    @Override
    public String getUnits( int[] allyCodes, boolean includeMods, Boolean enums, Language language, UnitsField... fields ) throws IOException
    {
        Map<String, Object> payload = new HashMap<>();
        payload.put( "allycode", allyCodes );
        payload.put( "mods", includeMods );

        if ( enums != null )
        {
            payload.put( "enums", enums );
        }

        if ( language != null )
        {
            payload.put( "language", language.getSwgohCode() );
        }

        createProjection( payload, fields );

        return API.units.call( loginCredentials, payload );
    }

    @Override
    public String getZetaRecommendations( ZetaRecommendationField... fields ) throws IOException
    {
        Map<String, Object> payload = new HashMap<>();

        createProjection( payload, fields );

        return API.zetas.call( loginCredentials, payload );
    }

    @Override
    public String getSquadRecommendations( SquadRecommendationField... fields ) throws IOException
    {
        Map<String, Object> payload = new HashMap<>();

        createProjection( payload, fields );

        return API.squads.call( loginCredentials, payload );
    }

    @Override
    public String getEvents( Boolean enums, Language language, EventField... fields ) throws IOException
    {
        Map<String, Object> payload = new HashMap<>();

        if ( enums != null )
        {
            payload.put( "enums", enums );
        }

        if ( language != null )
        {
            payload.put( "language", language.getSwgohCode() );
        }

        createProjection( payload, fields );

        return API.events.call( loginCredentials, payload );
    }

    @Override
    public String getBattles( Boolean enums, Language language, BattleField... fields ) throws IOException
    {
        Map<String, Object> payload = new HashMap<>();

        if ( enums != null )
        {
            payload.put( "enums", enums );
        }

        if ( language != null )
        {
            payload.put( "language", language.getSwgohCode() );
        }

        createProjection( payload, fields );

        return API.battles.call( loginCredentials, payload );
    }

    @Override
    public String getSupportData( Collection collection, Map<String, Object> matchCriteria, Boolean enums, Language language, String... fields ) throws IOException
    {
        Map<String, Object> payload = new HashMap<>();
        payload.put( "collection", collection.name() );

        if ( enums != null )
        {
            payload.put( "enums", enums );
        }

        if ( language != null )
        {
            payload.put( "language", language.getSwgohCode() );
        }

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
