package help.swgoh.api;

import java.io.IOException;
import java.util.Map;

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
        English( "eng_us" ),
        ChineseSimplified( "chs_cn" ),
        ChineseTraditional( "cht_cn" ),
        French( "fre_fr" ),
        German( "ger_de" ),
        Indonesian( "ind_id" ),
        Italian( "ita_it" ),
        Japanese( "jpn_jp" ),
        Korean( "kor_kr" ),
        Portuguese( "por_br" ),
        Russian( "rus_ru" ),
        Spanish( "spa_xm" ),
        Thai( "tha_th" ),
        Turkish( "tur_tr" ),
        ;

        private final String swgohCode;

        Language( String swgohCode )
        {
            this.swgohCode = swgohCode;
        }

        public String getSwgohCode()
        {
            return swgohCode;
        }
    }

    /**
     * Toggleable fields for the {@link #getPlayers(int[], Boolean, Language, PlayerField...)} endpoints.
     */
    enum PlayerField
    {
        allyCode, name, level, guildName, stats, roster, arena, updated
    }

    /**
     * Toggleable fields for the {@link #getGuild(int, Boolean, Language, GuildField...)} endpoints.
     */
    enum GuildField
    {
        name, desc, members, status, required, bannerColor, bannerLogo, message, gp, raid, roster, updated
    }

    /**
     * Toggleable fields for the {@link #getUnits(int[], boolean, Boolean, Language, UnitsField...)} endpoints.
     */
    enum UnitsField
    {
        player, allyCode, starLevel, level, gearLevel, gear, zetas, type, mods, updated
    }

    /**
     * Toggleable fields for the {@link #getZetaRecommendations(ZetaRecommendationField...)} endpoint.
     */
    enum ZetaRecommendationField
    {
        zetas, details, usage, credits, updated
    }

    /**
     * Toggleable fields for the {@link #getSquadRecommendations(SquadRecommendationField...)} endpoint.
     */
    enum SquadRecommendationField
    {
        rancor, aat, haat, sith, events, twdefense, lstb, dstb, psummary, gsummary, updated
    }

    /**
     * Toggleable fields for the {@link #getEvents(Boolean, Language, EventField...)} endpoints.
     */
    enum EventField
    {
        id, priority, nameKey, summaryKey, descKey, instances, squadType, defensiveSquadType
    }

    /**
     * Toggleable fields for the {@link #getBattles(Boolean, Language, BattleField...)} endpoints.
     */
    enum BattleField
    {
        id, nameKey, descriptionKey, campaignType, campaignMapList
    }

    /**
     * Complete list of possible data collections that can be queried from the {@link #getSupportData(Collection, Map, Boolean, Language, String...)} endpoints.
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
     * @param enums Optionally return enumerated items as their string equivalents.
     * @param language Optional language to return translated names. If no language specified, no translations will be applied.
     * @param fields Optional projection of {@link PlayerField}s you want returned. If no fields are specified, all fields will be returned.
     * @return player data by individual or group of ally codes.
     * @throws IOException if the API connection fails.
     */
    String getPlayers( int[] allyCodes, Boolean enums, Language language, PlayerField... fields ) throws IOException;
    default String getPlayers( int[] allyCodes, Language language, PlayerField... fields ) throws IOException
    {
        return getPlayers( allyCodes, null, language, fields );
    }
    default String getPlayers( int[] allyCodes, Boolean enums, PlayerField... fields ) throws IOException
    {
        return getPlayers( allyCodes, enums, null, fields );
    }
    default String getPlayers( int[] allyCodes, PlayerField... fields ) throws IOException
    {
        return getPlayers( allyCodes, null, null, fields );
    }
    default String getPlayer( int allyCode, Boolean enums, Language language, PlayerField... fields ) throws IOException
    {
        return getPlayers( new int[]{allyCode}, enums, language, fields );
    }
    default String getPlayer( int allyCode, Language language, PlayerField... fields ) throws IOException
    {
        return getPlayer( allyCode, null, language, fields );
    }
    default String getPlayer( int allyCode, Boolean enums, PlayerField... fields ) throws IOException
    {
        return getPlayer( allyCode, enums, null, fields );
    }
    default String getPlayer( int allyCode, PlayerField... fields ) throws IOException
    {
        return getPlayer( allyCode, null, null, fields );
    }

    /**
     * Returns a guild object that contains only basic information for each guild member.
     *
     * For a full array of player profiles in the response, see {@link #getLargeGuild(int, Boolean, Language, GuildField...)}
     * For a guild-wide units report instead of an array of player profiles, see {@link #getGuildUnits(int, boolean, Boolean, Language, GuildField...)}
     *
     * Cache sync:
     *   Registered user: 6 hours
     *   Verified user: 4 hours
     *   Patreon user: 3 hours
     *
     * https://api.swgoh.help/swgoh
     *
     * @param allyCode Ally code of any guild member in guild to request.
     * @param enums Optionally return enumerated items as their string equivalents.
     * @param language Optional language to return translated names. If no language specified, no translations will be applied.
     * @param fields Optional projection of {@link GuildField}s you want returned. If no fields are specified, all fields will be returned.
     * @return guild data by individual ally code of guild member.
     * @throws IOException if the API connection fails.
     */
    String getGuild( int allyCode, Boolean enums, Language language, GuildField... fields ) throws IOException;
    default String getGuild( int allyCode, Language language, GuildField... fields ) throws IOException
    {
        return getGuild( allyCode, null, language, fields );
    }
    default String getGuild( int allyCode, Boolean enums, GuildField... fields ) throws IOException
    {
        return getGuild( allyCode, enums, null, fields );
    }
    default String getGuild( int allyCode, GuildField... fields ) throws IOException
    {
        return getGuild( allyCode, null, null, fields );
    }

    /**
     * Returns a guild object that contains a full array of player profiles for each guild member.
     * Note: Large reply object.
     *
     * For a basic view of the player roster, see {@link #getGuild(int, Boolean, Language, GuildField...)}
     * For a guild-wide units report instead of an array of player profiles, see {@link #getGuildUnits(int, boolean, Boolean, Language, GuildField...)}
     *
     * Cache sync:
     *   Registered user: 6 hours
     *   Verified user: 4 hours
     *   Patreon user: 3 hours
     *
     * https://api.swgoh.help/swgoh
     *
     * @param allyCode Ally code of any guild member in guild to request.
     * @param enums Optionally return enumerated items as their string equivalents.
     * @param language Optional language to return translated names. If no language specified, no translations will be applied.
     * @param fields Optional projection of {@link GuildField}s you want returned. If no fields are specified, all fields will be returned.
     * @return guild data by individual ally code of guild member.
     * @throws IOException if the API connection fails.
     */
    String getLargeGuild( int allyCode, Boolean enums, Language language, GuildField... fields ) throws IOException;
    default String getLargeGuild( int allyCode, Language language, GuildField... fields ) throws IOException
    {
        return getLargeGuild( allyCode, null, language, fields );
    }
    default String getLargeGuild( int allyCode, Boolean enums, GuildField... fields ) throws IOException
    {
        return getLargeGuild( allyCode, enums, null, fields );
    }
    default String getLargeGuild( int allyCode, GuildField... fields ) throws IOException
    {
        return getLargeGuild( allyCode, null, null, fields );
    }

    /**
     * Returns a guild object that contains a units report for each guild member.
     * Note: Large reply object.
     *
     * For a basic view of the player roster, see {@link #getGuild(int, Boolean, Language, GuildField...)}
     * For a full array of player profiles in the response, see {@link #getLargeGuild(int, Boolean, Language, GuildField...)}
     *
     * Cache sync:
     *   Registered user: 6 hours
     *   Verified user: 4 hours
     *   Patreon user: 3 hours
     *
     * https://api.swgoh.help/swgoh
     *
     * @param allyCode Ally code of any guild member in guild to request.
     * @param includeMods Optionally include unit mods in units report. Note: Large reply object
     * @param enums Optionally return enumerated items as their string equivalents.
     * @param language Optional language to return translated names. If no language specified, no translations will be applied.
     * @param fields Optional projection of {@link GuildField}s you want returned. If no fields are specified, all fields will be returned.
     * @return guild data by individual ally code of guild member.
     * @throws IOException if the API connection fails.
     */
    String getGuildUnits( int allyCode, boolean includeMods, Boolean enums, Language language, GuildField... fields ) throws IOException;
    default String getGuildUnits( int allyCode, boolean includeMods, Language language, GuildField... fields ) throws IOException
    {
        return getGuildUnits( allyCode, includeMods, null, language, fields );
    }
    default String getGuildUnits( int allyCode, boolean includeMods, Boolean enums, GuildField... fields ) throws IOException
    {
        return getGuildUnits( allyCode, includeMods, enums, null, fields );
    }
    default String getGuildUnits( int allyCode, boolean includeMods, GuildField... fields ) throws IOException
    {
        return getGuildUnits( allyCode, includeMods, null, null, fields );
    }

    /**
     * Returns a list of unit details organized by unit instead of by player, similar to the swgoh.gg /units API.
     *
     * Cache sync:
     *   Registered user: 4 hours
     *   Verified user: 2 hours
     *   Patreon user: 1 hour
     *
     * https://api.swgoh.help/swgoh
     *
     * @param allyCodes Ally code(s) of the players to request.
     * @param includeMods Optionally include unit-mods in report. Note: Large reply object
     * @param enums Optionally return enumerated items as their string equivalents.
     * @param language (in conjunction with mods) Optionally converts mod stat values to text.
     * @param fields Optional projection of {@link UnitsField}s you want returned. If no fields are specified, all fields will be returned.
     * @return player profiles organized by units for individual or group of ally codes.
     * @throws IOException if the API connection fails.
     */
    String getUnits( int[] allyCodes, boolean includeMods, Boolean enums, Language language, UnitsField... fields ) throws IOException;
    default String getUnits( int[] allyCodes, boolean includeMods, Language language, UnitsField... fields ) throws IOException
    {
        return getUnits( allyCodes, includeMods, null, language, fields );
    }
    default String getUnits( int[] allyCodes, boolean includeMods, Boolean enums, UnitsField... fields ) throws IOException
    {
        return getUnits( allyCodes, includeMods, enums, null, fields );
    }
    default String getUnits( int[] allyCodes, boolean includeMods, UnitsField... fields ) throws IOException
    {
        return getUnits( allyCodes, includeMods, null, null, fields );
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
     * @param fields Optional projection of {@link ZetaRecommendationField}s you want returned. If no fields are specified, all fields will be returned.
     * @return current zeta list, rated by their various uses throughout the game.
     * @throws IOException if the API connection fails.
     */
    String getZetaRecommendations( ZetaRecommendationField... fields ) throws IOException;

    /**
     * Returns the current list of recommended squads, organized around their various uses throughout the game.
     * This dataset is provided by swgoh.help home-site and additions or changes can be requested in their Discord server. https://discord.gg/kau4XTB
     * Note: This dataset is in the care of a third party and does not have available translations.
     *
     * Cache sync: 7 days
     *
     * https://api.swgoh.help/swgoh
     *
     * @param fields Optional projection of {@link SquadRecommendationField}s you want returned. If no fields are specified, all fields will be returned.
     * @return current list of recommended squads, organized around their various uses throughout the game.
     * @throws IOException if the API connection fails.
     */
    String getSquadRecommendations( SquadRecommendationField... fields ) throws IOException;

    /**
     * Returns the current event schedule.
     *
     * Cache sync: 4 hours
     *
     * https://api.swgoh.help/swgoh
     *
     * @param enums Optionally return enumerated items as their string equivalents.
     * @param language Optional language to return translated names. If no language specified, no translations will be applied.
     * @param fields Optional projection of {@link EventField}s you want returned. If no fields are specified, all fields will be returned.
     * @return current event schedule.
     * @throws IOException if the API connection fails.
     */
    String getEvents( Boolean enums, Language language, EventField... fields ) throws IOException;
    default String getEvents( Language language, EventField... fields ) throws IOException
    {
        return getEvents( null, language, fields );
    }
    default String getEvents( Boolean enums, EventField... fields ) throws IOException
    {
        return getEvents( enums, null, fields );
    }
    default String getEvents( EventField... fields ) throws IOException
    {
        return getEvents( null, null, fields );
    }

    /**
     * Returns the current list of all campaigns, nodes and battle data.
     *
     * Cache sync: 4 hours
     *
     * https://api.swgoh.help/swgoh
     *
     * @param enums Optionally return enumerated items as their string equivalents.
     * @param language Optional language to return translated names. If no language specified, no translations will be applied.
     * @param fields Optional projection of {@link BattleField}s you want returned. If no fields are specified, all fields will be returned.
     * @return current list of all campaigns, nodes and battle data.
     * @throws IOException if the API connection fails.
     */
    String getBattles( Boolean enums, Language language, BattleField... fields ) throws IOException;
    default String getBattles( Language language, BattleField... fields ) throws IOException
    {
        return getBattles( null, language, fields );
    }
    default String getBattles( Boolean enums, BattleField... fields ) throws IOException
    {
        return getBattles( enums, null, fields );
    }
    default String getBattles( BattleField... fields ) throws IOException
    {
        return getBattles( null, null, fields );
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
     * @param enums Optionally return enumerated items as their string equivalents.
     * @param language Optional language to return translated names. If no language specified, no translations will be applied.
     * @param fields Optional projection of fields you want returned. If no fields are specified, all fields will be returned.
     * @return game data listings for organizing your tools and understanding relationships between data.
     * @throws IOException if the API connection fails.
     */
    String getSupportData( Collection collection, Map<String, Object> matchCriteria, Boolean enums, Language language, String... fields ) throws IOException;
    default String getSupportData( Collection collection, Boolean enums, Language language, String... fields ) throws IOException
    {
        return getSupportData( collection, null, enums, language, fields );
    }
    default String getSupportData( Collection collection, Map<String, Object> matchCriteria, Language language, String... fields ) throws IOException
    {
        return getSupportData( collection, matchCriteria, null, language, fields );
    }
    default String getSupportData( Collection collection, Map<String, Object> matchCriteria, Boolean enums, String... fields ) throws IOException
    {
        return getSupportData( collection, matchCriteria, enums, null, fields );
    }
    default String getSupportData( Collection collection, Language language, String... fields ) throws IOException
    {
        return getSupportData( collection, null, null, language, fields );
    }
    default String getSupportData( Collection collection, Boolean enums, String... fields ) throws IOException
    {
        return getSupportData( collection, null, enums, null, fields );
    }
    default String getSupportData( Collection collection, Map<String, Object> matchCriteria, String... fields ) throws IOException
    {
        return getSupportData( collection, matchCriteria, null, null, fields );
    }
    default String getSupportData( Collection collection, String... fields ) throws IOException
    {
        return getSupportData( collection, null, null, null, fields );
    }
}
