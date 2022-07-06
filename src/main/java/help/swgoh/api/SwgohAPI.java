package help.swgoh.api;

import help.swgoh.api.models.event.Events;
import help.swgoh.api.models.guild.Guild;
import help.swgoh.api.models.player.Player;
import help.swgoh.api.response.RegistrationResponse;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * Interface that exposes the public methods of {@link SwgohAPIClient}.
 *
 * Due to the extreme versatility of this API and the available options that are given that modify the response structures,
 * all of the responses returned by each endpoint will be raw JSON in the form of a {@code java.lang.String} for now.
 * The consumer of this API client is then free to parse the data into whatever form they choose.
 *
 * @since 1.0.0
 */
public interface SwgohAPI
{
    /**
     * All game-supported languages that can be used to get localized data from the API responses.
     */
    enum Language
    {
        English( "eng_us", Locale.US, "English", "\uD83C\uDDFA\uD83C\uDDF8" ),
        ChineseSimplified( "chs_cn", Locale.SIMPLIFIED_CHINESE, "简体中文", "\uD83C\uDDE8\uD83C\uDDF3" ),
        ChineseTraditional( "cht_cn", Locale.TRADITIONAL_CHINESE, "繁體中文", "\uD83C\uDDF9\uD83C\uDDFC" ),
        French( "fre_fr", Locale.FRANCE, "Français", "\uD83C\uDDEB\uD83C\uDDF7" ),
        German( "ger_de", Locale.GERMANY, "Deutsch", "\uD83C\uDDE9\uD83C\uDDEA" ),
        Indonesian( "ind_id", new Locale( "ind" ), "Bahasa Indonesia", "\uD83C\uDDEE\uD83C\uDDE9" ),
        Italian( "ita_it", Locale.ITALY, "Italiano", "\uD83C\uDDEE\uD83C\uDDF9" ),
        Japanese( "jpn_jp", Locale.JAPAN, "日本語", "\uD83C\uDDEF\uD83C\uDDF5" ),
        Korean( "kor_kr", Locale.KOREA, "한국어", "\uD83C\uDDF0\uD83C\uDDF7" ),
        Portuguese( "por_br", new Locale( "por" ), "Português", "\uD83C\uDDE7\uD83C\uDDF7" ),
        Russian( "rus_ru", new Locale( "rus" ), "русский", "\uD83C\uDDF7\uD83C\uDDFA" ),
        Spanish( "spa_xm", new Locale( "spa" ), "Español", "\uD83C\uDDEA\uD83C\uDDF8" ),
        Thai( "tha_th", new Locale( "tha" ), "ไทย", "\uD83C\uDDF9\uD83C\uDDED" ),
        Turkish( "tur_tr", new Locale( "tur" ), "Türkçe", "\uD83C\uDDF9\uD83C\uDDF7" ),
        ;

        private final String swgohCode;
        private final Locale locale;
        private final String displayName;
        private final String unicode;

        Language( String swgohCode, Locale locale, String displayName, String unicode )
        {
            this.swgohCode = swgohCode;
            this.locale = locale;
            this.displayName = displayName;
            this.unicode = unicode;
        }

        public String getSwgohCode()
        {
            return swgohCode;
        }

        public Locale getLocale()
        {
            return locale;
        }

        public String getDisplayName()
        {
            return displayName;
        }

        public String getUnicode()
        {
            return unicode;
        }

        public static Optional<Language> fromSwgohCode( String swgohCode )
        {
            return Arrays.stream( values() ).filter( language -> language.getSwgohCode().equalsIgnoreCase( swgohCode ) ).findFirst();
        }

        public static Optional<Language> fromLocale( Locale locale )
        {
            return Arrays.stream( values() ).filter( language -> language.getLocale().equals( locale ) ).findFirst();
        }

        public static Optional<Language> fromDisplayName( String displayName )
        {
            return Arrays.stream( values() ).filter( language -> language.getDisplayName().equalsIgnoreCase( displayName ) ).findFirst();
        }

        public static Optional<Language> fromUnicode( String unicode )
        {
            return Arrays.stream( values() ).filter( language -> language.getUnicode().equals( unicode ) ).findFirst();
        }
    }

    /**
     * Complete list of possible data collections that can be queried from the {@link #getSupportData(Collection, Map, Language, SwgohAPIFilter)} endpoints.
     */
    enum Collection
    {
        abilityList, battleEnvironmentsList, battleTargetingRuleList, categoryList, challengeList, challengeStyleList,
        effectList, environmentCollectionList, equipmentList, eventSamplingList, guildExchangeItemList, guildRaidList,
        helpEntryList, materialList, playerTitleList, powerUpBundleList, raidConfigList, recipeList, requirementList,
        skillList, starterGuildList, statModList, statModSetList, statProgressionList, tableList, targetingSetList,
        territoryBattleDefinitionList, territoryWarDefinitionList, unitsList, unlockAnnouncementDefinitionList,
        warDefinitionList, xpTableList
    }

    /**
     * Returns a list of player objects which are identified by the given ally code(s).
     *
     * Cache sync:
     *   Registered user: 4 hours
     *   Verified user: 2 hours
     *   Patreon user: 1 hour
     *
     * https://api.swgoh.help/swgoh
     *
     * @param allyCodes Ally code(s) of the players to request.
     * @param language Optional language to return translated names. If no language specified, no translations will be applied.
     * @param filter Optional projection of response fields you want returned. If no fields are specified, all fields will be returned.
     * @return player data by individual or group of ally codes.
     */
    CompletableFuture<String> getPlayers( List<Integer> allyCodes, Language language, SwgohAPIFilter filter );
    default CompletableFuture<String> getPlayers( List<Integer> allyCodes, SwgohAPIFilter filter )
    {
        return getPlayers( allyCodes, null, filter );
    }
    default CompletableFuture<String> getPlayers( List<Integer> allyCodes )
    {
        return getPlayers( allyCodes, null, SwgohAPIFilter.ALL );
    }
    default CompletableFuture<String> getPlayer( int allyCode, Language language, SwgohAPIFilter filter )
    {
        return getPlayers( Collections.singletonList( allyCode ), language, filter );
    }
    default CompletableFuture<String> getPlayer( int allyCode, Language language )
    {
        return getPlayer( allyCode, language, SwgohAPIFilter.ALL );
    }
    default CompletableFuture<String> getPlayer( int allyCode, SwgohAPIFilter filter )
    {
        return getPlayer( allyCode, null, filter );
    }
    default CompletableFuture<String> getPlayer( int allyCode )
    {
        return getPlayer( allyCode, null, SwgohAPIFilter.ALL );
    }

    /**
     * Returns a guild object that contains only basic information for each guild member.
     *
     * For a full array of player profiles in the response, see {@link #getLargeGuild(int, Language, SwgohAPIFilter)}
     *
     * Cache sync:
     *   Registered user: 6 hours
     *   Verified user: 4 hours
     *   Patreon user: 3 hours
     *
     * https://api.swgoh.help/swgoh
     *
     * @param allyCode Ally code of any guild member in guild to request.
     * @param language Optional language to return translated names. If no language specified, no translations will be applied.
     * @param filter Optional projection of response fields you want returned. If no fields are specified, all fields will be returned.
     * @return guild data by individual ally code of guild member.
     */
    CompletableFuture<String> getGuild( int allyCode, Language language, SwgohAPIFilter filter );
    default CompletableFuture<String> getGuild( int allyCode, Language language )
    {
        return getGuild( allyCode, language, SwgohAPIFilter.ALL );
    }
    default CompletableFuture<String> getGuild( int allyCode, SwgohAPIFilter filter )
    {
        return getGuild( allyCode, null, filter );
    }
    default CompletableFuture<String> getGuild( int allyCode )
    {
        return getGuild( allyCode, SwgohAPIFilter.ALL );
    }

    /**
     * Returns a guild object that contains a full array of player profiles for each guild member.
     * Note: Large reply object.
     *
     * For a basic view of the player roster, see {@link #getGuild(int, Language, SwgohAPIFilter)}
     *
     * Cache sync:
     *   Registered user: 6 hours
     *   Verified user: 4 hours
     *   Patreon user: 3 hours
     *
     * https://api.swgoh.help/swgoh
     *
     * @param allyCode Ally code of any guild member in guild to request.
     * @param language Optional language to return translated names. If no language specified, no translations will be applied.
     * @param filter Optional projection of response fields you want returned. If no fields are specified, all fields will be returned.
     * @return guild data by individual ally code of guild member.
     */
    CompletableFuture<String> getLargeGuild( int allyCode, Language language, SwgohAPIFilter filter );
    default CompletableFuture<String> getLargeGuild( int allyCode, SwgohAPIFilter filter )
    {
        return getLargeGuild( allyCode, null, filter );
    }
    default CompletableFuture<String> getLargeGuild( int allyCode, Language language )
    {
        return getLargeGuild( allyCode, language, SwgohAPIFilter.ALL );
    }
    default CompletableFuture<String> getLargeGuild( int allyCode )
    {
        return getLargeGuild( allyCode, null, SwgohAPIFilter.ALL );
    }

    /**
     * Returns a list of roster information for each player specified.
     * Note: Potentially large reply object.
     *
     * For a basic view of the player roster, see {@link #getGuild(int, Language, SwgohAPIFilter)}
     *
     * Cache sync:
     *   Registered user: 6 hours
     *   Verified user: 4 hours
     *   Patreon user: 3 hours
     *
     * https://api.swgoh.help/swgoh
     *
     * @param allyCodes Ally codes of any players to request.
     * @param language Optional language to return translated names. If no language specified, no translations will be applied.
     * @param filter Optional projection of response fields you want returned. If no fields are specified, all fields will be returned.
     * @return roster data for all ally codes requested.
     */
    CompletableFuture<String> getRosters( List<Integer> allyCodes, Language language, SwgohAPIFilter filter );
    default CompletableFuture<String> getRosters( List<Integer> allyCodes, Language language )
    {
        return getRosters( allyCodes, language, SwgohAPIFilter.ALL );
    }
    default CompletableFuture<String> getRosters( List<Integer> allyCodes, SwgohAPIFilter filter )
    {
        return getRosters( allyCodes, null, filter );
    }
    default CompletableFuture<String> getRosters( List<Integer> allyCodes )
    {
        return getRosters( allyCodes, null, SwgohAPIFilter.ALL );
    }
    default CompletableFuture<String> getRoster( int allyCode, Language language, SwgohAPIFilter filter )
    {
        return getRosters( Collections.singletonList( allyCode ), language, filter );
    }
    default CompletableFuture<String> getRoster( int allyCode, Language language )
    {
        return getRoster( allyCode, language, SwgohAPIFilter.ALL );
    }
    default CompletableFuture<String> getRoster( int allyCode, SwgohAPIFilter filter )
    {
        return getRoster( allyCode, null, filter );
    }
    default CompletableFuture<String> getRoster( int allyCode )
    {
        return getRoster( allyCode, null, SwgohAPIFilter.ALL );
    }

    /**
     * Returns the current zeta list, rated by their various uses throughout the game.
     * This dataset is an aggregated summary of rankings collected through a grouping of krakens, whales, dolphins and free-to-players.
     * Note: This dataset is in the care of a third party and does not have available translations.
     *
     * Cache sync: 7 days
     *
     * https://api.swgoh.help/swgoh
     *
     * @param filter Optional projection of response fields you want returned. If no fields are specified, all fields will be returned.
     * @return current zeta list, rated by their various uses throughout the game.
     */
    CompletableFuture<String> getZetaRecommendations( SwgohAPIFilter filter );
    default CompletableFuture<String> getZetaRecommandations()
    {
        return getZetaRecommendations( SwgohAPIFilter.ALL );
    }

    /**
     * Returns the current list of recommended squads, organized around their various uses throughout the game.
     * This dataset is provided by swgoh.help home-site and additions or changes can be requested in their Discord server. https://discord.gg/kau4XTB
     * Note: This dataset is in the care of a third party and does not have available translations.
     *
     * Cache sync: 7 days
     *
     * https://api.swgoh.help/swgoh
     *
     * @param filter Optional projection of response fields you want returned. If no fields are specified, all fields will be returned.
     * @return current list of recommended squads, organized around their various uses throughout the game.
     */
    CompletableFuture<String> getSquadRecommendations( SwgohAPIFilter filter );
    default CompletableFuture<String> getSquadRecommendations()
    {
        return getSquadRecommendations( SwgohAPIFilter.ALL );
    }

    /**
     * Returns the current event schedule.
     *
     * Cache sync: 4 hours
     *
     * https://api.swgoh.help/swgoh
     *
     * @param language Optional language to return translated names. If no language specified, no translations will be applied.
     * @param filter Optional projection of response fields you want returned. If no fields are specified, all fields will be returned.
     * @return current event schedule.
     */
    CompletableFuture<String> getEvents( Language language, SwgohAPIFilter filter );
    default CompletableFuture<String> getEvents( Language language )
    {
        return getEvents( language, SwgohAPIFilter.ALL );
    }
    default CompletableFuture<String> getEvents( SwgohAPIFilter filter )
    {
        return getEvents( null, filter );
    }
    default CompletableFuture<String> getEvents()
    {
        return getEvents( null, SwgohAPIFilter.ALL );
    }

    /**
     * Returns the current list of all campaigns, nodes and battle data.
     *
     * Cache sync: 4 hours
     *
     * https://api.swgoh.help/swgoh
     *
     * @param language Optional language to return translated names. If no language specified, no translations will be applied.
     * @param filter Optional projection of response fields you want returned. If no fields are specified, all fields will be returned.
     * @return current list of all campaigns, nodes and battle data.
     */
    CompletableFuture<String> getBattles( Language language, SwgohAPIFilter filter );
    default CompletableFuture<String> getBattles( Language language )
    {
        return getBattles( language, SwgohAPIFilter.ALL );
    }
    default CompletableFuture<String> getBattles( SwgohAPIFilter filter )
    {
        return getBattles( null, filter );
    }
    default CompletableFuture<String> getBattles()
    {
        return getBattles( null, SwgohAPIFilter.ALL );
    }

    /**
     * Returns game data listings for organizing your tools and understanding relationships between data.
     *
     * The match criteria should be the {@code $match}'s body, according to mongodb specifications.
     * https://docs.mongodb.com/manual/reference/operator/aggregation/match/
     *
     * Cache sync: on game updates
     *
     * https://api.swgoh.help/swgoh
     *
     * @param collection Name of the collection to access.
     * @param matchCriteria Optionally include match criteria to filter down response.
     * @param language Optional language to return translated names. If no language specified, no translations will be applied.
     * @param filter Optional projection of response fields you want returned. If no fields are specified, all fields will be returned.
     * @return game data listings for organizing your tools and understanding relationships between data.
     */
    CompletableFuture<String> getSupportData( Collection collection, Map<String, Object> matchCriteria, Language language, SwgohAPIFilter filter );
    default CompletableFuture<String> getSupportData( Collection collection, Language language, SwgohAPIFilter filter )
    {
        return getSupportData( collection, null, language, filter );
    }
    default CompletableFuture<String> getSupportData( Collection collection, Map<String, Object> matchCriteria, Language language )
    {
        return getSupportData( collection, matchCriteria, language, SwgohAPIFilter.ALL );
    }
    default CompletableFuture<String> getSupportData( Collection collection, Map<String, Object> matchCriteria, SwgohAPIFilter filter )
    {
        return getSupportData( collection, matchCriteria, null, filter );
    }
    default CompletableFuture<String> getSupportData( Collection collection, SwgohAPIFilter filter )
    {
        return getSupportData( collection, null, null, filter );
    }
    default CompletableFuture<String> getSupportData( Collection collection, Map<String, Object> matchCriteria )
    {
        return getSupportData( collection, matchCriteria, null, SwgohAPIFilter.ALL );
    }

    /**
     * This registry is a single-sourced discord-to-ally code registration for all patreon-tier users.
     *
     * Add or update registrations from the pool for all patreon-tiered tools to access.
     *
     * https://api.swgoh.help/patreon
     *
     * @param allyCodeDiscordIdMappings Map of ally code/discord ID associations
     * @return details about the success of the registration
     */
    CompletableFuture<RegistrationResponse> register( Map<Integer, String> allyCodeDiscordIdMappings );
    default CompletableFuture<RegistrationResponse> register( int allyCode, String discordId )
    {
        return register( Collections.singletonMap( allyCode, discordId ) );
    }

    /**
     * This registry is a single-sourced discord-to-ally code registration for all patreon-tier users.
     *
     * Delete registrations from the pool that all patreon-tiered tools can access.
     *
     * Each ally code that is passed in will have its single associated Discord ID removed.
     *
     * Each Discord ID that is passed in will have all of its associated ally codes removed.
     *
     * https://api.swgoh.help/patreon
     *
     * @param allyCodes List of ally codes to unregister
     * @param discordIds List of Discord IDs to unregister
     * @return details about the success of the unregistration
     */
    CompletableFuture<RegistrationResponse> unregister( List<Integer> allyCodes, List<String> discordIds );
    default CompletableFuture<RegistrationResponse> unregisterAllyCodes( List<Integer> allyCodes )
    {
        return unregister( allyCodes, null );
    }
    default CompletableFuture<RegistrationResponse> unregisterAllyCode( int allyCode )
    {
        return unregister( Collections.singletonList( allyCode ), null );
    }
    default CompletableFuture<RegistrationResponse> unregisterDiscordIds( List<String> discordIds )
    {
        return unregister( null, discordIds );
    }
    default CompletableFuture<RegistrationResponse> unregisterDiscordId( String discordId )
    {
        return unregister( null, Collections.singletonList( discordId ) );
    }

    /**
     * This registry is a single-sourced discord-to-ally code registration for all patreon-tier users.
     *
     * Fetch registrations to the pool for for the specified ally code(s).
     *
     * https://api.swgoh.help/patreon
     *
     * @param allyCodes List of ally codes to search
     * @return registered pairing of ally code / discord ID, if any.
     */
    CompletableFuture<RegistrationResponse> getRegistrationByAllyCodes( List<Integer> allyCodes );
    default CompletableFuture<RegistrationResponse> getRegistrationByAllyCode( int allyCode )
    {
        return getRegistrationByAllyCodes( Collections.singletonList( allyCode ) );
    }

    /**
     * This registry is a single-sourced discord-to-ally code registration for all patreon-tier users.
     *
     * Fetch registrations to the pool for for the specified discord ID(s).
     *
     * The discord "snowflake" ID format is specified here: https://discordapp.com/developers/docs/reference#snowflakes
     *
     * https://api.swgoh.help/patreon
     *
     * @param discordIds List of discord IDs to search
     * @return registered pairing of ally code / discord ID, if any.
     */
    CompletableFuture<RegistrationResponse> getRegistrationByDiscordIds( List<String> discordIds );
    default CompletableFuture<RegistrationResponse> getRegistrationByDiscordId( String discordId )
    {
        return getRegistrationByDiscordIds( Collections.singletonList( discordId ) );
    }


    /**
     * Returns a list of player objects which are identified by the given ally code(s).
     *
     * Cache sync:
     *   Registered user: 4 hours
     *   Verified user: 2 hours
     *   Patreon user: 1 hour
     *
     * https://api.swgoh.help/swgoh
     *
     * @param allyCodes Ally code(s) of the players to request.
     * @param language Optional language to return translated names. If no language specified, no translations will be applied.
     * @param filter Optional projection of response fields you want returned. If no fields are specified, all fields will be returned.
     * @return player data by individual or group of ally codes.
     */
    List<Player> getFullPlayers(List<Integer> allyCodes, Language language, SwgohAPIFilter filter) throws ExecutionException, InterruptedException;
    default List<Player> getFullPlayers(List<Integer> allyCodes, SwgohAPIFilter filter ) throws ExecutionException, InterruptedException {
        return getFullPlayers( allyCodes, null, filter );
    }
    default List<Player> getFullPlayers( List<Integer> allyCodes ) throws ExecutionException, InterruptedException {
        return getFullPlayers( allyCodes, null, SwgohAPIFilter.ALL );
    }
    default Player getFullPlayer( int allyCode, Language language, SwgohAPIFilter filter ) throws ExecutionException, InterruptedException {
        return getFullPlayers( Collections.singletonList( allyCode ), language, filter ).get(0);
    }
    default Player getFullPlayer( int allyCode, Language language ) throws ExecutionException, InterruptedException {
        return getFullPlayer( allyCode, language, SwgohAPIFilter.ALL );
    }
    default Player getFullPlayer( int allyCode, SwgohAPIFilter filter ) throws ExecutionException, InterruptedException {
        return getFullPlayer( allyCode, null, filter );
    }
    default Player getFullPlayer( int allyCode ) throws ExecutionException, InterruptedException {
        return getFullPlayer( allyCode, null, SwgohAPIFilter.ALL );
    }

    /**
     * Returns a guild object that contains only basic information for each guild member.
     *
     * For a full array of player profiles in the response, see {@link #getFullLargeGuild(int, Language, SwgohAPIFilter)}
     *
     * Cache sync:
     *   Registered user: 6 hours
     *   Verified user: 4 hours
     *   Patreon user: 3 hours
     *
     * https://api.swgoh.help/swgoh
     *
     * @param allyCode Ally code of any guild member in guild to request.
     * @param language Optional language to return translated names. If no language specified, no translations will be applied.
     * @param filter Optional projection of response fields you want returned. If no fields are specified, all fields will be returned.
     * @return guild data by individual ally code of guild member.
     */
    List<Guild> getFullGuild(int allyCode, Language language, SwgohAPIFilter filter ) throws ExecutionException, InterruptedException;
    default List<Guild> getFullGuild( int allyCode, Language language ) throws ExecutionException, InterruptedException {
        return getFullGuild( allyCode, language, SwgohAPIFilter.ALL );
    }
    default List<Guild> getFullGuild( int allyCode, SwgohAPIFilter filter ) throws ExecutionException, InterruptedException {
        return getFullGuild( allyCode, null, filter );
    }
    default List<Guild> getFullGuild( int allyCode ) throws ExecutionException, InterruptedException {
        return getFullGuild( allyCode, SwgohAPIFilter.ALL );
    }

    /**
     * Returns a guild object that contains a full array of player profiles for each guild member.
     * Note: Large reply object.
     *
     * For a basic view of the player roster, see {@link #getGuild(int, Language, SwgohAPIFilter)}
     *
     * Cache sync:
     *   Registered user: 6 hours
     *   Verified user: 4 hours
     *   Patreon user: 3 hours
     *
     * https://api.swgoh.help/swgoh
     *
     * @param allyCode Ally code of any guild member in guild to request.
     * @param language Optional language to return translated names. If no language specified, no translations will be applied.
     * @param filter Optional projection of response fields you want returned. If no fields are specified, all fields will be returned.
     * @return guild data by individual ally code of guild member.
     */
    List<Guild> getFullLargeGuild( int allyCode, Language language, SwgohAPIFilter filter ) throws ExecutionException, InterruptedException;
    default List<Guild> getFullLargeGuild( int allyCode, SwgohAPIFilter filter ) throws ExecutionException, InterruptedException {
        return getFullLargeGuild( allyCode, null, filter );
    }
    default List<Guild> getFullLargeGuild( int allyCode, Language language ) throws ExecutionException, InterruptedException {
        return getFullLargeGuild( allyCode, language, SwgohAPIFilter.ALL );
    }
    default List<Guild> getFullLargeGuild( int allyCode ) throws ExecutionException, InterruptedException {
        return getFullLargeGuild( allyCode, null, SwgohAPIFilter.ALL );
    }

    /**
     * Returns the current event schedule.
     *
     * Cache sync: 4 hours
     *
     * https://api.swgoh.help/swgoh
     *
     * @param language Optional language to return translated names. If no language specified, no translations will be applied.
     * @param filter Optional projection of response fields you want returned. If no fields are specified, all fields will be returned.
     * @return current event schedule.
     */
    Events getFullEvents(Language language, SwgohAPIFilter filter ) throws ExecutionException, InterruptedException;
    default Events getFullEvents(Language language ) throws ExecutionException, InterruptedException {
        return getFullEvents( language, SwgohAPIFilter.ALL );
    }
    default Events getFullEvents( SwgohAPIFilter filter ) throws ExecutionException, InterruptedException {
        return getFullEvents( null, filter );
    }
    default Events getFullEvents() throws ExecutionException, InterruptedException {
        return getFullEvents( null, SwgohAPIFilter.ALL );
    }
}
