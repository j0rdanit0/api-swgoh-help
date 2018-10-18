package help.swgoh.api;

import help.swgoh.api.image.ImageRequest;
import help.swgoh.api.response.RegistrationResponse;

import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

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
        English( "eng_us", Locale.US ),
        ChineseSimplified( "chs_cn", Locale.SIMPLIFIED_CHINESE ),
        ChineseTraditional( "cht_cn", Locale.TRADITIONAL_CHINESE ),
        French( "fre_fr", Locale.FRANCE ),
        German( "ger_de", Locale.GERMANY ),
        Indonesian( "ind_id", new Locale( "ind" ) ),
        Italian( "ita_it", Locale.ITALY ),
        Japanese( "jpn_jp", Locale.JAPAN ),
        Korean( "kor_kr", Locale.KOREA ),
        Portuguese( "por_br", new Locale( "por" ) ),
        Russian( "rus_ru", new Locale( "rus" ) ),
        Spanish( "spa_xm", new Locale( "spa" ) ),
        Thai( "tha_th", new Locale( "tha" ) ),
        Turkish( "tur_tr", new Locale( "tur" ) ),
        ;

        private final String swgohCode;
        private final Locale locale;

        Language( String swgohCode, Locale locale )
        {
            this.swgohCode = swgohCode;
            this.locale = locale;
        }

        public String getSwgohCode()
        {
            return swgohCode;
        }

        public Locale getLocale()
        {
            return locale;
        }

        public static Optional<Language> fromLocale( Locale locale )
        {
            return Arrays.stream( values() ).filter( language -> language.getLocale().equals( locale ) ).findFirst();
        }
    }

    /**
     * Toggleable fields for the {@link #getPlayers(List, Boolean, Language, PlayerField...)} endpoints.
     *
     * @deprecated as of 4.1.0 in favor of the more versatile {@link SwgohAPIFilter} object.
     */
    @Deprecated
    enum PlayerField
    {
        allyCode, name, level, guildName, guildRefId, stats, roster, arena, updated
    }

    /**
     * Toggleable fields for the {@link #getGuild(int, Boolean, Language, GuildField...)} endpoints.
     *
     * @deprecated as of 4.1.0 in favor of the more versatile {@link SwgohAPIFilter} object.
     */
    @Deprecated
    enum GuildField
    {
        name, id, desc, members, status, required, bannerColor, bannerLogo, message, gp, raid, roster, updated
    }

    /**
     * Toggleable fields for the {@link #getUnits(List, boolean, Boolean, Language, UnitsField...)} endpoints.
     *
     * @deprecated as of 4.1.0 in favor of the more versatile {@link SwgohAPIFilter} object.
     */
    @Deprecated
    enum UnitsField
    {
        player, allyCode, starLevel, level, gearLevel, gear, zetas, type, mods, gp, updated
    }

    /**
     * Toggleable fields for the {@link #getZetaRecommendations(ZetaRecommendationField...)} endpoint.
     *
     * @deprecated as of 4.1.0 in favor of the more versatile {@link SwgohAPIFilter} object.
     */
    @Deprecated
    enum ZetaRecommendationField
    {
        zetas, details, usage, credits, updated
    }

    /**
     * Toggleable fields for the {@link #getSquadRecommendations(SquadRecommendationField...)} endpoint.
     *
     * @deprecated as of 4.1.0 in favor of the more versatile {@link SwgohAPIFilter} object.
     */
    @Deprecated
    enum SquadRecommendationField
    {
        rancor, aat, haat, sith, events, twdefense, lstb, dstb, psummary, gsummary, updated
    }

    /**
     * Toggleable fields for the {@link #getEvents(Boolean, Language, EventField...)} endpoints.
     *
     * @deprecated as of 4.1.0 in favor of the more versatile {@link SwgohAPIFilter} object.
     */
    @Deprecated
    enum EventField
    {
        id, priority, nameKey, summaryKey, descKey, instances, squadType, defensiveSquadType, updated
    }

    /**
     * Toggleable fields for the {@link #getBattles(Boolean, Language, BattleField...)} endpoints.
     *
     * @deprecated as of 4.1.0 in favor of the more versatile {@link SwgohAPIFilter} object.
     */
    @Deprecated
    enum BattleField
    {
        id, nameKey, descriptionKey, campaignType, campaignMapList, updated
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
     *
     * @deprecated as of 4.1.0, use {@link #getPlayers(List, Language, SwgohAPIFilter)} instead.
     */
    @Deprecated
    CompletableFuture<String> getPlayers( List<Integer> allyCodes, Boolean enums, Language language, PlayerField... fields );
    @Deprecated
    default CompletableFuture<String> getPlayers( List<Integer> allyCodes, Language language, PlayerField... fields )
    {
        return getPlayers( allyCodes, null, language, fields );
    }
    @Deprecated
    default CompletableFuture<String> getPlayers( List<Integer> allyCodes, Boolean enums, PlayerField... fields )
    {
        return getPlayers( allyCodes, enums, null, fields );
    }
    @Deprecated
    default CompletableFuture<String> getPlayers( List<Integer> allyCodes, PlayerField... fields )
    {
        return getPlayers( allyCodes, null, null, fields );
    }
    @Deprecated
    default CompletableFuture<String> getPlayer( int allyCode, Boolean enums, Language language, PlayerField... fields )
    {
        return getPlayers( Collections.singletonList( allyCode ), enums, language, fields );
    }
    @Deprecated
    default CompletableFuture<String> getPlayer( int allyCode, Language language, PlayerField... fields )
    {
        return getPlayer( allyCode, null, language, fields );
    }
    @Deprecated
    default CompletableFuture<String> getPlayer( int allyCode, Boolean enums, PlayerField... fields )
    {
        return getPlayer( allyCode, enums, null, fields );
    }
    @Deprecated
    default CompletableFuture<String> getPlayer( int allyCode, PlayerField... fields )
    {
        return getPlayer( allyCode, null, null, fields );
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
     *
     * @deprecated as of 4.1.0, use {@link #getGuild(int, Language, SwgohAPIFilter)} instead.
     */
    @Deprecated
    CompletableFuture<String> getGuild( int allyCode, Boolean enums, Language language, GuildField... fields );
    @Deprecated
    default CompletableFuture<String> getGuild( int allyCode, Language language, GuildField... fields )
    {
        return getGuild( allyCode, null, language, fields );
    }
    @Deprecated
    default CompletableFuture<String> getGuild( int allyCode, Boolean enums, GuildField... fields )
    {
        return getGuild( allyCode, enums, null, fields );
    }
    @Deprecated
    default CompletableFuture<String> getGuild( int allyCode, GuildField... fields )
    {
        return getGuild( allyCode, null, null, fields );
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
     *
     * @deprecated as of 4.1.0, use {@link #getLargeGuild(int, Language, SwgohAPIFilter)} instead.
     */
    @Deprecated
    CompletableFuture<String> getLargeGuild( int allyCode, Boolean enums, Language language, GuildField... fields );
    @Deprecated
    default CompletableFuture<String> getLargeGuild( int allyCode, Language language, GuildField... fields )
    {
        return getLargeGuild( allyCode, null, language, fields );
    }
    @Deprecated
    default CompletableFuture<String> getLargeGuild( int allyCode, Boolean enums, GuildField... fields )
    {
        return getLargeGuild( allyCode, enums, null, fields );
    }
    @Deprecated
    default CompletableFuture<String> getLargeGuild( int allyCode, GuildField... fields )
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
     *
     * @deprecated as of 4.1.0, use {@link #getLargeGuild(int, Language, SwgohAPIFilter)} intead.
     */
    @Deprecated
    CompletableFuture<String> getGuildUnits( int allyCode, boolean includeMods, Boolean enums, Language language, GuildField... fields );
    @Deprecated
    default CompletableFuture<String> getGuildUnits( int allyCode, boolean includeMods, Language language, GuildField... fields )
    {
        return getGuildUnits( allyCode, includeMods, null, language, fields );
    }
    @Deprecated
    default CompletableFuture<String> getGuildUnits( int allyCode, boolean includeMods, Boolean enums, GuildField... fields )
    {
        return getGuildUnits( allyCode, includeMods, enums, null, fields );
    }
    @Deprecated
    default CompletableFuture<String> getGuildUnits( int allyCode, boolean includeMods, GuildField... fields )
    {
        return getGuildUnits( allyCode, includeMods, null, null, fields );
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
     *
     * @deprecated as of 4.1.0, use {@link #getRosters(List, Language, SwgohAPIFilter)} instead.
     */
    @Deprecated
    CompletableFuture<String> getUnits( List<Integer> allyCodes, boolean includeMods, Boolean enums, Language language, UnitsField... fields );
    @Deprecated
    default CompletableFuture<String> getUnits( List<Integer> allyCodes, boolean includeMods, Language language, UnitsField... fields )
    {
        return getUnits( allyCodes, includeMods, null, language, fields );
    }
    @Deprecated
    default CompletableFuture<String> getUnits( List<Integer> allyCodes, boolean includeMods, Boolean enums, UnitsField... fields )
    {
        return getUnits( allyCodes, includeMods, enums, null, fields );
    }
    @Deprecated
    default CompletableFuture<String> getUnits( List<Integer> allyCodes, boolean includeMods, UnitsField... fields )
    {
        return getUnits( allyCodes, includeMods, null, null, fields );
    }
    @Deprecated
    default CompletableFuture<String> getUnits( int allyCode, boolean includeMods, Boolean enums, Language language, UnitsField... fields )
    {
        return getUnits( Collections.singletonList( allyCode ), includeMods, enums, language, fields );
    }
    @Deprecated
    default CompletableFuture<String> getUnits( int allyCode, boolean includeMods, Language language, UnitsField... fields )
    {
        return getUnits( allyCode, includeMods, null, language, fields );
    }
    @Deprecated
    default CompletableFuture<String> getUnits( int allyCode, boolean includeMods, Boolean enums, UnitsField... fields )
    {
        return getUnits( allyCode, includeMods, enums, null, fields );
    }
    @Deprecated
    default CompletableFuture<String> getUnits( int allyCode, boolean includeMods, UnitsField... fields )
    {
        return getUnits( allyCode, includeMods, null, null, fields );
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
     *
     * @deprecated as of 4.1.0, use {@link #getZetaRecommendations(SwgohAPIFilter)} instead.
     */
    @Deprecated
    CompletableFuture<String> getZetaRecommendations( ZetaRecommendationField... fields );

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
     *
     * @deprecated as of 4.1.0, use {@link #getSquadRecommendations(SwgohAPIFilter)} instead.
     */
    @Deprecated
    CompletableFuture<String> getSquadRecommendations( SquadRecommendationField... fields );

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
     *
     * @deprecated as of 4.1.0, use {@link #getEvents(Language, SwgohAPIFilter)} instead.
     */
    @Deprecated
    CompletableFuture<String> getEvents( Boolean enums, Language language, EventField... fields );
    @Deprecated
    default CompletableFuture<String> getEvents( Language language, EventField... fields )
    {
        return getEvents( null, language, fields );
    }
    @Deprecated
    default CompletableFuture<String> getEvents( Boolean enums, EventField... fields )
    {
        return getEvents( enums, null, fields );
    }
    @Deprecated
    default CompletableFuture<String> getEvents( EventField... fields )
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
     *
     * @deprecated as of 4.1.0, use {@link #getBattles(Language, SwgohAPIFilter)} instead.
     */
    @Deprecated
    CompletableFuture<String> getBattles( Boolean enums, Language language, BattleField... fields );
    @Deprecated
    default CompletableFuture<String> getBattles( Language language, BattleField... fields )
    {
        return getBattles( null, language, fields );
    }
    @Deprecated
    default CompletableFuture<String> getBattles( Boolean enums, BattleField... fields )
    {
        return getBattles( enums, null, fields );
    }
    @Deprecated
    default CompletableFuture<String> getBattles( BattleField... fields )
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
    default CompletableFuture<String> getSupportData( Collection collection, Language language )
    {
        return getSupportData( collection, null, language, SwgohAPIFilter.ALL );
    }
    default CompletableFuture<String> getSupportData( Collection collection, SwgohAPIFilter filter )
    {
        return getSupportData( collection, null, null, filter );
    }
    default CompletableFuture<String> getSupportData( Collection collection, Map<String, Object> matchCriteria )
    {
        return getSupportData( collection, matchCriteria, null, SwgohAPIFilter.ALL );
    }
    default CompletableFuture<String> getSupportData( Collection collection )
    {
        return getSupportData( collection, null, null, SwgohAPIFilter.ALL );
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
     *
     * @deprecated as of 4.1.0, use {@link #getSupportData(Collection, Map, Language, SwgohAPIFilter)} instead.
     */
    @Deprecated
    CompletableFuture<String> getSupportData( Collection collection, Map<String, Object> matchCriteria, Boolean enums, Language language, String... fields );
    @Deprecated
    default CompletableFuture<String> getSupportData( Collection collection, Boolean enums, Language language, String... fields )
    {
        return getSupportData( collection, null, enums, language, fields );
    }
    @Deprecated
    default CompletableFuture<String> getSupportData( Collection collection, Map<String, Object> matchCriteria, Language language, String... fields )
    {
        return getSupportData( collection, matchCriteria, null, language, fields );
    }
    @Deprecated
    default CompletableFuture<String> getSupportData( Collection collection, Map<String, Object> matchCriteria, Boolean enums, String... fields )
    {
        return getSupportData( collection, matchCriteria, enums, null, fields );
    }
    @Deprecated
    default CompletableFuture<String> getSupportData( Collection collection, Language language, String... fields )
    {
        return getSupportData( collection, null, null, language, fields );
    }
    @Deprecated
    default CompletableFuture<String> getSupportData( Collection collection, Boolean enums, String... fields )
    {
        return getSupportData( collection, null, enums, null, fields );
    }
    @Deprecated
    default CompletableFuture<String> getSupportData( Collection collection, Map<String, Object> matchCriteria, String... fields )
    {
        return getSupportData( collection, matchCriteria, null, null, fields );
    }
    @Deprecated
    default CompletableFuture<String> getSupportData( Collection collection, String... fields )
    {
        return getSupportData( collection, null, null, null, fields );
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
     * Returns a unit image in PNG format, customized by the options provided by builders
     * {@link help.swgoh.api.image.ToonImageRequestBuilder} or {@link help.swgoh.api.image.ShipImageRequestBuilder}.
     *
     * Once the builder's options are defined, its #build() method can be invoked to construct an implementation of
     * {@link ImageRequest}.
     *
     * @param imageRequest The constructed request that can be used to customize the returned image.
     * @return The customized image in PNG format.
     */
    CompletableFuture<byte[]> getImage( ImageRequest imageRequest );
    CompletableFuture<BufferedImage> getBufferedImage( ImageRequest imageRequest );
}
