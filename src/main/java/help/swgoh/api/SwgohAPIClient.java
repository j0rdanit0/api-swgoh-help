package help.swgoh.api;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import help.swgoh.api.response.*;

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
        data( "/swgoh/data/%s" ),
        player( "/swgoh/player/%s" ),
        playerGG( "/swgoh/player/%s/gg" ),
        playerMods( "/swgoh/player/%s/mods" ),
        playerZetas( "/swgoh/player/%s/zetas" ),
        guild( "/swgoh/guild/%s" ),
        guildGG( "/swgoh/guild/%s/gg" ),
        guildDetails( "/swgoh/guild/%s/details" ),
        guildRoster( "/swgoh/guild/%s/roster" ),
        stats( "/swgoh/stats" ),
        ;

        private final String path;

        API( String path )
        {
            this.path = path;
        }

        private String constructUrl( String urlBase, String dataCriteria, Language language )
        {
            String lang = language != null ? "?lang="+language.getSwgohCode() : "";
            String criteria = dataCriteria != null ? dataCriteria : "";
            return urlBase + String.format( path, criteria ) + lang;
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
    public String getPlayerGGJSON( int allyCode ) throws IOException
    {
        return callApi( API.playerGG.getUrl( urlBase, allyCode+"", null ) );
    }

    @Override
    public PlayerMods getPlayerMods( int allyCode ) throws IOException
    {
        return getPlayerMods( allyCode, null );
    }

    @Override
    public PlayerMods getPlayerMods( int allyCode, Language language ) throws IOException
    {
        return callApi( API.playerMods.getUrl( urlBase, allyCode+"", language ), PlayerMods.class );
    }

    @Override
    public String getPlayerModsJSON( int allyCode ) throws IOException
    {
        return getPlayerModsJSON( allyCode, null );
    }

    @Override
    public String getPlayerModsJSON( int allyCode, Language language ) throws IOException
    {
        return callApi( API.playerMods.getUrl( urlBase, allyCode+"", language ) );
    }

    @Override
    public PlayerZetas getPlayerZetas( int allyCode ) throws IOException
    {
        return getPlayerZetas( allyCode, null );
    }

    @Override
    public PlayerZetas getPlayerZetas( int allyCode, Language language ) throws IOException
    {
        return callApi( API.playerZetas.getUrl( urlBase, allyCode+"", language ), PlayerZetas.class );
    }

    @Override
    public String getPlayerZetasJSON( int allyCode ) throws IOException
    {
        return getPlayerZetasJSON( allyCode, null );
    }

    @Override
    public String getPlayerZetasJSON( int allyCode, Language language ) throws IOException
    {
        return callApi( API.playerZetas.getUrl( urlBase, allyCode+"", language ) );
    }

    @Override
    public Guild getGuild( int allyCode ) throws IOException
    {
        return getGuild( allyCode, null );
    }

    @Override
    public Guild getGuild( int allyCode, Language language ) throws IOException
    {
        return callApi( API.guild.getUrl( urlBase, allyCode+"", language ), Guild.class );
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
    public String getGuildGGJSON( int allyCode ) throws IOException
    {
        return callApi( API.guildGG.getUrl( urlBase, allyCode+"", null ) );
    }

    @Override
    public Guild getGuildDetails( int allyCode ) throws IOException
    {
        return getGuildDetails( allyCode, null );
    }

    @Override
    public Guild getGuildDetails( int allyCode, Language language ) throws IOException
    {
        return callApi( API.guildDetails.getUrl( urlBase, allyCode+"", language ), Guild.class );
    }

    @Override
    public String getGuildDetailsJSON( int allyCode ) throws IOException
    {
        return this.getGuildDetailsJSON( allyCode, null );
    }

    @Override
    public String getGuildDetailsJSON( int allyCode, Language language ) throws IOException
    {
        return callApi( API.guildDetails.getUrl( urlBase, allyCode+"", language ) );
    }

    @Override
    public List<Player> getGuildRoster( int allyCode ) throws IOException
    {
        return getGuildRoster( allyCode, null );
    }

    @Override
    public List<Player> getGuildRoster( int allyCode, Language language ) throws IOException
    {
        return callApi( API.guildRoster.getUrl( urlBase, allyCode+"", language ), new TypeToken<List<Player>>(){}.getType() );
    }

    @Override
    public String getGuildRosterJSON( int allyCode ) throws IOException
    {
        return getGuildRosterJSON( allyCode, null );
    }

    @Override
    public String getGuildRosterJSON( int allyCode, Language language ) throws IOException
    {
        return callApi( API.guildRoster.getUrl( urlBase, allyCode+"", language ) );
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
    public List<ArenaSquadMemberType> getArenaSquadMemberTypes() throws IOException
    {
        return getArenaSquadMemberTypes( null );
    }

    @Override
    public List<ArenaSquadMemberType> getArenaSquadMemberTypes( Language language ) throws IOException
    {
        return fetchData( DataCriteria.ARENA, language, new TypeToken<List<ArenaSquadMemberType>>(){}.getType() );
    }

    @Override
    public String getArenaSquadMemberTypesJSON() throws IOException
    {
        return getArenaSquadMemberTypesJSON( null );
    }

    @Override
    public String getArenaSquadMemberTypesJSON( Language language ) throws IOException
    {
        return fetchData( DataCriteria.ARENA, language );
    }

    @Override
    public Map<String, Gear> getGear() throws IOException
    {
        return getGear( null );
    }

    @Override
    public Map<String, Gear> getGear( Language language ) throws IOException
    {
        return fetchData( DataCriteria.GEAR, language, new TypeToken<Map<String, Gear>>(){}.getType() );
    }

    @Override
    public String getGearJSON() throws IOException
    {
        return getGearJSON( null );
    }

    @Override
    public String getGearJSON( Language language ) throws IOException
    {
        return fetchData( DataCriteria.GEAR, language );
    }

    @Override
    public List<ModSet> getModSets() throws IOException
    {
        return getModSets( null );
    }

    @Override
    public List<ModSet> getModSets( Language language ) throws IOException
    {
        return fetchData( DataCriteria.MOD_SETS, language, new TypeToken<List<ModSet>>(){}.getType() );
    }

    @Override
    public String getModSetsJSON() throws IOException
    {
        return getModSetsJSON( null );
    }

    @Override
    public String getModSetsJSON( Language language ) throws IOException
    {
        return fetchData( DataCriteria.MOD_SETS, language );
    }

    @Override
    public List<String> getModStatFields() throws IOException
    {
        return getModStatFields( null );
    }

    @Override
    public List<String> getModStatFields( Language language ) throws IOException
    {
        return fetchData( DataCriteria.MOD_STATS, language, new TypeToken<List<String>>(){}.getType() );
    }

    @Override
    public String getModStatFieldsJSON() throws IOException
    {
        return getModStatFieldsJSON( null );
    }

    @Override
    public String getModStatFieldsJSON( Language language ) throws IOException
    {
        return fetchData( DataCriteria.MOD_STATS, language );
    }

    @Override
    public Map<String, BaseSkill> getSkills() throws IOException
    {
        return getSkills( null );
    }

    @Override
    public Map<String, BaseSkill> getSkills( Language language ) throws IOException
    {
        return fetchData( DataCriteria.SKILLS, language, new TypeToken<Map<String, BaseSkill>>(){}.getType() );
    }

    @Override
    public String getSkillsJSON() throws IOException
    {
        return getSkillsJSON( null );
    }

    @Override
    public String getSkillsJSON( Language language ) throws IOException
    {
        return fetchData( DataCriteria.SKILLS, language );
    }

    @Override
    public Map<String, SkillType> getSkillTypes() throws IOException
    {
        return getSkillTypes( null );
    }

    @Override
    public Map<String, SkillType> getSkillTypes( Language language ) throws IOException
    {
        return fetchData( DataCriteria.SKILL_TYPES, language, new TypeToken<Map<String, SkillType>>(){}.getType() );
    }

    @Override
    public String getSkillTypesJSON() throws IOException
    {
        return getSkillTypesJSON( null );
    }

    @Override
    public String getSkillTypesJSON( Language language ) throws IOException
    {
        return fetchData( DataCriteria.SKILL_TYPES, language );
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

    @Override
    public Map<String, Zeta> getZetas() throws IOException
    {
        return getZetas( null );
    }

    @Override
    public Map<String, Zeta> getZetas( Language language ) throws IOException
    {
        return fetchData( DataCriteria.ZETAS, language, new TypeToken<Map<String, Zeta>>(){}.getType() );
    }

    @Override
    public String getZetasJSON() throws IOException
    {
        return getZetasJSON( null );
    }

    @Override
    public String getZetasJSON( Language language ) throws IOException
    {
        return fetchData( DataCriteria.ZETAS, language );
    }

    @Override
    public List<Zeta> getZetasWithUnit() throws IOException
    {
        return getZetasWithUnit( null );
    }

    @Override
    public List<Zeta> getZetasWithUnit( Language language ) throws IOException
    {
        return fetchData( DataCriteria.ZETA_ABILITIES, language, new TypeToken<List<Zeta>>(){}.getType() );
    }

    @Override
    public String getZetasWithUnitJSON() throws IOException
    {
        return getZetasWithUnitJSON( null );
    }

    @Override
    public String getZetasWithUnitJSON( Language language ) throws IOException
    {
        return fetchData( DataCriteria.ZETA_ABILITIES, language );
    }

    @Override
    public ZetaRecommendations getZetaRecommendations() throws IOException
    {
        return getZetaRecommendations( null );
    }

    @Override
    public ZetaRecommendations getZetaRecommendations( Language language ) throws IOException
    {
        return fetchData( DataCriteria.ZETA_RECOMMENDATIONS, language, ZetaRecommendations.class );
    }

    @Override
    public String getZetaRecommendationsJSON() throws IOException
    {
        return getZetaRecommendationsJSON( null );
    }

    @Override
    public String getZetaRecommendationsJSON( Language language ) throws IOException
    {
        return fetchData( DataCriteria.ZETA_RECOMMENDATIONS, null );
    }

    @Override
    public List<Battle> getBattles() throws IOException
    {
        return getBattles( null );
    }

    @Override
    public List<Battle> getBattles( Language language ) throws IOException
    {
        return fetchData( DataCriteria.BATTLES, language, new TypeToken<List<Battle>>(){}.getType() );
    }

    @Override
    public String getBattlesJSON() throws IOException
    {
        return getBattlesJSON( null );
    }

    @Override
    public String getBattlesJSON( Language language ) throws IOException
    {
        return fetchData( DataCriteria.BATTLES, language );
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
