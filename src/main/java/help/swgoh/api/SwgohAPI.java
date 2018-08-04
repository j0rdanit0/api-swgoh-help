package help.swgoh.api;

import help.swgoh.api.response.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Interface that exposes the public methods of {@link SwgohAPIClient}.
 *
 * @since 1.0.0
 */
public interface SwgohAPI
{
    enum Language
    {
        English( "eng_us", "English" ),
        ChineseSimplified( "chs_cn", "Chinese" ),
        ChineseTraditional( "cht_cn", "Traditional Chinese" ),
        French( "fre_fr", "French" ),
        German( "ger_de", "German" ),
        Indonesian( "ind_id", "Indonesian" ),
        Italian( "ita_it", "Italian" ),
        Japanese( "jpn_jp", "Japanese" ),
        Korean( "kor_kr", "Korean" ),
        Portuguese( "por_br", "Portuguese" ),
        Russian( "rus_ru", "Russian" ),
        Spanish( "spa_xm", "Spanish" ),
        Thai( "tha_th", "Thai" ),
        Turkish( "tur_tr", "Turkish" ),
        ;

        private final String displayName;
        private final String swgohCode;

        Language( String swgohCode, String displayName )
        {
            this.swgohCode = swgohCode;
            this.displayName = displayName;
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
    
    /**
     * Makes an http connection to the swgoh.help API for a player object in the default language.
     *
     * For pulling all the members of a guild, use {@link #getGuildRoster(int)}
     *
     * @param allyCode  The number that uniquely identifies a player, found in the player's game profile.
     * @return The player, specified by the allyCode.
     * @throws IOException if http connection fails.
     * @see #getPlayer(int, Language)
     * @see #getPlayerJSON(int)
     * @see #getPlayerJSON(int, Language)
     */
    Player getPlayer( int allyCode ) throws IOException;

    /**
     * Makes an http connection to the swgoh.help API for a player object in the specified language.
     *
     * For pulling all the members of a guild, use {@link #getGuildRoster(int, Language)}
     *
     * @param allyCode  The number that uniquely identifies a player, found in the player's game profile.
     * @param language  The {@link Language} that the result should be displayed in.
     * @return The player, specified by the allyCode.
     * @throws IOException if http connection fails.
     * @see #getPlayer(int)
     * @see #getPlayerJSON(int)
     * @see #getPlayerJSON(int, Language)
     */
    Player getPlayer( int allyCode, Language language ) throws IOException;

    /**
     * Makes an http connection to the swgoh.help API for a player JSON in the default language.
     *
     * For pulling all the members of a guild, use {@link #getGuildRosterJSON(int)}
     *
     * @param allyCode  The number that uniquely identifies a player, found in the player's game profile.
     * @return JSON string of the player, specified by the allyCode.
     * @throws IOException if http connection fails.
     * @see #getPlayer(int)
     * @see #getPlayer(int, Language)
     * @see #getPlayerJSON(int, Language)
     */
    String getPlayerJSON( int allyCode ) throws IOException;

    /**
     * Makes an http connection to the swgoh.help API for a player JSON in the specified language.
     *
     * For pulling all the members of a guild, use {@link #getGuildRosterJSON(int, Language)}
     *
     * @param allyCode  The number that uniquely identifies a player, found in the player's game profile.
     * @param language  The {@link Language} that the result should be displayed in.
     * @return JSON string of the player, specified by the allyCode.
     * @throws IOException if http connection fails.
     * @see #getPlayer(int)
     * @see #getPlayer(int, Language)
     * @see #getPlayerJSON(int)
     */
    String getPlayerJSON( int allyCode, Language language ) throws IOException;

    /**
     * Makes an http connection to the swgoh.help API for a JSON string of a player roster formatted like the swgoh.gg
     * /units response.
     *
     * For pulling all the members of a guild, use {@link #getGuildGGJSON(int)}
     *
     * @param allyCode  The number that uniquely identifies a player, found in the player's game profile.
     * @return The player roster, specified by the allyCode.
     * @throws IOException if http connection fails.
     */
    String getPlayerGGJSON( int allyCode ) throws IOException;

    /**
     * Makes an http connection to the swgoh.help API for a list of player mods in the default language.
     *
     * @param allyCode  The number that uniquely identifies a player, found in the player's game profile.
     * @return The player's mods, specified by the allyCode.
     * @throws IOException if http connection fails.
     * @see #getPlayerMods(int, Language)
     * @see #getPlayerModsJSON(int)
     * @see #getPlayerModsJSON(int, Language)
     */
    PlayerMods getPlayerMods( int allyCode ) throws IOException;

    /**
     * Makes an http connection to the swgoh.help API for a list of player mods in the specified language.
     *
     * @param allyCode  The number that uniquely identifies a player, found in the player's game profile.
     * @param language  The {@link Language} that the result should be displayed in.
     * @return The player's mods, specified by the allyCode.
     * @throws IOException if http connection fails.
     * @see #getPlayerMods(int)
     * @see #getPlayerModsJSON(int)
     * @see #getPlayerModsJSON(int, Language)
     */
    PlayerMods getPlayerMods( int allyCode, Language language ) throws IOException;

    /**
     * Makes an http connection to the swgoh.help API for a JSON string of a list of player mods in the default
     * language.
     *
     * @param allyCode  The number that uniquely identifies a player, found in the player's game profile.
     * @return The player's mods, specified by the allyCode.
     * @throws IOException if http connection fails.
     * @see #getPlayerMods(int)
     * @see #getPlayerMods(int, Language)
     * @see #getPlayerModsJSON(int, Language)
     */
    String getPlayerModsJSON( int allyCode ) throws IOException;

    /**
     * Makes an http connection to the swgoh.help API for a JSON string of a list of player mods in the specified
     * language.
     *
     * @param allyCode  The number that uniquely identifies a player, found in the player's game profile.
     * @param language  The {@link Language} that the result should be displayed in.
     * @return The player's mods, specified by the allyCode.
     * @throws IOException if http connection fails.
     * @see #getPlayerMods(int)
     * @see #getPlayerMods(int, Language)
     * @see #getPlayerModsJSON(int)
     */
    String getPlayerModsJSON( int allyCode, Language language ) throws IOException;

    /**
     * Makes an http connection to the swgoh.help API for a list of player zetas in the default language.
     *
     * @param allyCode  The number that uniquely identifies a player, found in the player's game profile.
     * @return The player's zetas, specified by the allyCode.
     * @throws IOException if http connection fails.
     * @see #getPlayerZetas(int, Language)
     * @see #getPlayerZetasJSON(int)
     * @see #getPlayerZetasJSON(int, Language)
     */
    PlayerZetas getPlayerZetas( int allyCode ) throws IOException;

    /**
     * Makes an http connection to the swgoh.help API for a list of player zetas in the specified language.
     *
     * @param allyCode  The number that uniquely identifies a player, found in the player's game profile.
     * @param language  The {@link Language} that the result should be displayed in.
     * @return The player's zetas, specified by the allyCode.
     * @throws IOException if http connection fails.
     * @see #getPlayerZetas(int)
     * @see #getPlayerZetasJSON(int)
     * @see #getPlayerZetasJSON(int, Language)
     */
    PlayerZetas getPlayerZetas( int allyCode, Language language ) throws IOException;

    /**
     * Makes an http connection to the swgoh.help API for a JSON string of a list of player zetas in the default
     * language.
     *
     * @param allyCode  The number that uniquely identifies a player, found in the player's game profile.
     * @return The player's zetas, specified by the allyCode.
     * @throws IOException if http connection fails.
     * @see #getPlayerZetas(int)
     * @see #getPlayerZetas(int, Language)
     * @see #getPlayerZetasJSON(int, Language)
     */
    String getPlayerZetasJSON( int allyCode ) throws IOException;

    /**
     * Makes an http connection to the swgoh.help API for a JSON string of a list of player zetas in the specified
     * language.
     *
     * @param allyCode  The number that uniquely identifies a player, found in the player's game profile.
     * @param language  The {@link Language} that the result should be displayed in.
     * @return The player's zetas, specified by the allyCode.
     * @throws IOException if http connection fails.
     * @see #getPlayerZetas(int)
     * @see #getPlayerZetas(int, Language)
     * @see #getPlayerZetasJSON(int)
     */
    String getPlayerZetasJSON( int allyCode, Language language ) throws IOException;

    /**
     * Makes an http connection to the swgoh.help API for a full guild object in the default language.
     *
     * @param allyCode  The number that uniquely identifies a player, found in the player's game profile.
     * @return The full guild object, specified by the allyCode.
     * @throws IOException if http connection fails.
     * @see #getGuild(int, Language)
     * @see #getGuildJSON(int)
     * @see #getGuildJSON(int, Language)
     */
    Guild getGuild( int allyCode ) throws IOException;

    /**
     * Makes an http connection to the swgoh.help API for a full guild object in the specified language.
     *
     * @param allyCode  The number that uniquely identifies a player, found in the player's game profile.
     * @param language  The {@link Language} that the result should be displayed in.
     * @return The full guild object, specified by the allyCode.
     * @throws IOException if http connection fails.
     * @see #getGuild(int)
     * @see #getGuildJSON(int)
     * @see #getGuildJSON(int, Language)
     */
    Guild getGuild( int allyCode, Language language ) throws IOException;

    /**
     * Makes an http connection to the swgoh.help API for a JSON string of a full guild object in the default language.
     *
     * @param allyCode  The number that uniquely identifies a player, found in the player's game profile.
     * @return The full guild object, specified by the allyCode.
     * @throws IOException if http connection fails.
     * @see #getGuild(int)
     * @see #getGuild(int, Language)
     * @see #getGuildJSON(int, Language)
     */
    String getGuildJSON( int allyCode ) throws IOException;

    /**
     * Makes an http connection to the swgoh.help API for a JSON string of a full guild object in the specified
     * language.
     *
     * @param allyCode  The number that uniquely identifies a player, found in the player's game profile.
     * @param language  The {@link Language} that the result should be displayed in.
     * @return The full guild object, specified by the allyCode.
     * @throws IOException if http connection fails.
     * @see #getGuild(int)
     * @see #getGuild(int, Language)
     * @see #getGuildJSON(int)
     */
    String getGuildJSON( int allyCode, Language language ) throws IOException;

    /**
     * Makes an http connection to the swgoh.help API for a JSON formatted response similar to the swgoh.gg guild /units
     * API.
     *
     * @param allyCode  The number that uniquely identifies a player, found in the player's game profile.
     * @return The guild units, specified by the allyCode.
     * @throws IOException if http connection fails.
     */
    String getGuildGGJSON( int allyCode ) throws IOException;

    /**
     * Makes an http connection to the swgoh.help API for guild details in the default language.
     *
     * If you want to include players' roster information, use {@link #getGuild(int)}
     *
     * @param allyCode  The number that uniquely identifies a player, found in the player's game profile.
     * @return The guild details, specified by the allyCode.
     * @throws IOException if http connection fails.
     * @see #getGuildDetails(int, Language)
     * @see #getGuildDetailsJSON(int)
     * @see #getGuildDetailsJSON(int, Language)
     */
    Guild getGuildDetails( int allyCode ) throws IOException;

    /**
     * Makes an http connection to the swgoh.help API for guild details in the specified language.
     *
     * If you want to include players' roster information, use {@link #getGuild(int, Language)}
     *
     * @param allyCode  The number that uniquely identifies a player, found in the player's game profile.
     * @param language  The {@link Language} that the result should be displayed in.
     * @return The guild details, specified by the allyCode.
     * @throws IOException if http connection fails.
     * @see #getGuildDetails(int)
     * @see #getGuildDetailsJSON(int)
     * @see #getGuildDetailsJSON(int, Language)
     */
    Guild getGuildDetails( int allyCode, Language language ) throws IOException;

    /**
     * Makes an http connection to the swgoh.help API for a JSON string of guild details in the default language.
     *
     * If you want to include players' roster information, use {@link #getGuildJSON(int)}
     *
     * @param allyCode  The number that uniquely identifies a player, found in the player's game profile.
     * @return The guild details, specified by the allyCode.
     * @throws IOException if http connection fails.
     * @see #getGuildDetails(int)
     * @see #getGuildDetails(int, Language)
     * @see #getGuildDetailsJSON(int, Language)
     */
    String getGuildDetailsJSON( int allyCode ) throws IOException;

    /**
     * Makes an http connection to the swgoh.help API for a JSON string of guild details in the specified language.
     *
     * If you want to include players' roster information, use {@link #getGuildJSON(int, Language)}
     *
     * @param allyCode  The number that uniquely identifies a player, found in the player's game profile.
     * @param language  The {@link Language} that the result should be displayed in.
     * @return The guild details, specified by the allyCode.
     * @throws IOException if http connection fails.
     * @see #getGuildDetails(int)
     * @see #getGuildDetails(int, Language)
     * @see #getGuildDetailsJSON(int)
     */
    String getGuildDetailsJSON( int allyCode, Language language ) throws IOException;

    /**
     * Makes an http connection to the swgoh.help API for a list of player objects in the default language.
     *
     * The list of player objects that is returned belongs to the guild of the player that is identified by the given
     * allyCode.
     *
     * @param allyCode  The number that uniquely identifies a player, found in the player's game profile.
     * @return List of players belonging to the same guild as the player identified by the allyCode
     * @throws IOException if http connection fails.
     * @see #getGuildRoster(int, Language)
     * @see #getGuildRosterJSON(int)
     * @see #getGuildRosterJSON(int, Language)
     */
    List<Player> getGuildRoster( int allyCode ) throws IOException;

    /**
     * Makes an http connection to the swgoh.help API for a JSON string of player objects in the specified language.
     *
     * The list of player objects that is returned belongs to the guild of the player that is identified by the given
     * allyCode.
     *
     * @param allyCode  The number that uniquely identifies a player, found in the player's game profile.
     * @param language  The {@link Language} that the result should be displayed in.
     * @return List of players belonging to the same guild as the player identified by the allyCode
     * @throws IOException if http connection fails.
     * @see #getGuildRoster(int)
     * @see #getGuildRosterJSON(int)
     * @see #getGuildRosterJSON(int, Language)
     */
    List<Player> getGuildRoster( int allyCode, Language language ) throws IOException;

    /**
     * Makes an http connection to the swgoh.help API for a JSON string of player objects in the default language.
     *
     * The JSON string of player objects that is returned belongs to the guild of the player that is identified by the
     * given allyCode.
     *
     * @param allyCode  The number that uniquely identifies a player, found in the player's game profile.
     * @return JSON string of the list of players belonging to the same guild as the player identified by the allyCode
     * @throws IOException if http connection fails.
     * @see #getGuildRoster(int)
     * @see #getGuildRoster(int, Language)
     * @see #getGuildRosterJSON(int, Language)
     */
    String getGuildRosterJSON( int allyCode ) throws IOException;

    /**
     * Makes an http connection to the swgoh.help API for a JSON string of player objects in the specified language.
     *
     * The JSON string of player objects that is returned belongs to the guild of the player that is identified by the
     * given allyCode.
     *
     * @param allyCode  The number that uniquely identifies a player, found in the player's game profile.
     * @param language  The {@link Language} that the result should be displayed in.
     * @return JSON string of the list of players belonging to the same guild as the player identified by the allyCode
     * @throws IOException if http connection fails.
     * @see #getGuildRoster(int)
     * @see #getGuildRoster(int, Language)
     * @see #getGuildRosterJSON(int)
     */
    String getGuildRosterJSON( int allyCode, Language language ) throws IOException;

    /**
     * Makes an http connection to the swgoh.help API for a list of game events in the default language.
     *
     * @return List of events
     * @throws IOException if http connection fails.
     * @see #getEvents(Language)
     * @see #getEventsJSON()
     * @see #getEventsJSON(Language)
     */
    List<Event> getEvents() throws IOException;

    /**
     * Makes an http connection to the swgoh.help API for a list of game events in the specified language.
     *
     * @param language  The {@link Language} that the result should be displayed in.
     * @return List of events
     * @throws IOException if http connection fails.
     * @see #getEvents()
     * @see #getEventsJSON()
     * @see #getEventsJSON(Language)
     */
    List<Event> getEvents( Language language ) throws IOException;

    /**
     * Makes an http connection to the swgoh.help API for a JSON string of game events in the default language.
     *
     * @return JSON string of the list of events
     * @throws IOException if http connection fails.
     * @see #getEvents()
     * @see #getEvents(Language)
     * @see #getEventsJSON(Language)
     */
    String getEventsJSON() throws IOException;

    /**
     * Makes an http connection to the swgoh.help API for a JSON string of game events in the specified language.
     *
     * @param language  The {@link Language} that the result should be displayed in.
     * @return JSON string of the list of events
     * @throws IOException if http connection fails.
     * @see #getEvents()
     * @see #getEvents(Language)
     * @see #getEventsJSON()
     */
    String getEventsJSON( Language language ) throws IOException;

    /**
     * Makes an http connection to the swgoh.help API for a map of game units in the default language.
     *
     * In the {@link Map} response, the {@link String} key is the unit's ID and the {@link Unit} value is the rest of
     * the information for the unit.
     *
     * @return The game's playable characters and ships
     * @throws IOException if http connection fails.
     * @see #getUnits(Language)
     * @see #getUnitsJSON()
     * @see #getUnitsJSON(Language)
     */
    Map<String, Unit> getUnits() throws IOException;

    /**
     * Makes an http connection to the swgoh.help API for a list of game units in the specified language.
     *
     * In the {@link Map} response, the {@link String} key is the unit's ID and the {@link Unit} value is the rest of
     * the information for the unit.
     *
     * @param language  The {@link Language} that the result should be displayed in.
     * @return The game's playable characters and ships
     * @throws IOException if http connection fails.
     * @see #getUnits()
     * @see #getUnitsJSON()
     * @see #getUnitsJSON(Language)
     */
    Map<String, Unit> getUnits( Language language ) throws IOException;

    /**
     * Makes an http connection to the swgoh.help API for a JSON string of game units in the default language.
     *
     * @return JSON string of the game's playable characters and ships
     * @throws IOException if http connection fails.
     * @see #getUnits()
     * @see #getUnits(Language)
     * @see #getUnitsJSON(Language)
     */
    String getUnitsJSON() throws IOException;

    /**
     * Makes an http connection to the swgoh.help API for a JSON string of game units in the specified language.
     *
     * @param language  The {@link Language} that the result should be displayed in.
     * @return JSON string of the game's playable characters and ships
     * @throws IOException if http connection fails.
     * @see #getUnits()
     * @see #getUnits(Language)
     * @see #getUnitsJSON()
     */
    String getUnitsJSON( Language language ) throws IOException;

    /**
     * Makes an http connection to the swgoh.help API for a JSON string of arena types in the default language.
     *
     * @return List of arena types
     * @throws IOException if http connection fails.
     * @see #getArenaSquadMemberTypes(Language)
     * @see #getArenaSquadMemberTypesJSON()
     * @see #getArenaSquadMemberTypesJSON(Language)
     */
    List<ArenaSquadMemberType> getArenaSquadMemberTypes() throws IOException;

    /**
     * Makes an http connection to the swgoh.help API for a JSON string of arena types in the specified language.
     *
     * @param language  The {@link Language} that the result should be displayed in.
     * @return List of arena types
     * @throws IOException if http connection fails.
     * @see #getArenaSquadMemberTypes()
     * @see #getArenaSquadMemberTypesJSON()
     * @see #getArenaSquadMemberTypesJSON(Language)
     */
    List<ArenaSquadMemberType> getArenaSquadMemberTypes( Language language ) throws IOException;

    /**
     * Makes an http connection to the swgoh.help API for a JSON string of arena types in the default language.
     *
     * @return JSON string of arena types
     * @throws IOException if http connection fails.
     * @see #getArenaSquadMemberTypes()
     * @see #getArenaSquadMemberTypes(Language)
     * @see #getArenaSquadMemberTypesJSON(Language)
     */
    String getArenaSquadMemberTypesJSON() throws IOException;

    /**
     * Makes an http connection to the swgoh.help API for a JSON string of arena types in the specified language.
     *
     * @param language  The {@link Language} that the result should be displayed in.
     * @return JSON string of arena types
     * @throws IOException if http connection fails.
     * @see #getArenaSquadMemberTypes()
     * @see #getArenaSquadMemberTypes(Language)
     * @see #getArenaSquadMemberTypesJSON()
     */
    String getArenaSquadMemberTypesJSON( Language language ) throws IOException;

    /**
     * Makes an http connection to the swgoh.help API for a map of gear pieces in the default language.
     *
     * In the {@link Map} response, the {@link String} key is the gear's equipmentId and the {@link Gear} value is the
     * rest of the information for the gear piece.
     *
     * @return All the gear in the game, and where to find them.
     * @throws IOException if http connection fails.
     * @see #getGear(Language)
     * @see #getGearJSON()
     * @see #getGearJSON(Language)
     */
    Map<String,Gear> getGear() throws IOException;

    /**
     * Makes an http connection to the swgoh.help API for a map of gear pieces in the specified language.
     *
     * In the {@link Map} response, the {@link String} key is the gear's equipmentId and the {@link Gear} value is the
     * rest of the information for the gear piece.
     *
     * @param language  The {@link Language} that the result should be displayed in.
     * @return All the gear in the game, and where to find them.
     * @throws IOException if http connection fails.
     * @see #getGear()
     * @see #getGearJSON()
     * @see #getGearJSON(Language)
     */
    Map<String,Gear> getGear( Language language ) throws IOException;

    /**
     * Makes an http connection to the swgoh.help API for a JSON string of a map of gear pieces in the default language.
     *
     * In the {@link Map} response, the {@link String} key is the gear's equipmentId and the {@link Gear} value is the
     * rest of the information for the gear piece.
     *
     * @return JSON string of all the gear in the game, and where to find them.
     * @throws IOException if http connection fails.
     * @see #getGear()
     * @see #getGear(Language)
     * @see #getGearJSON(Language)
     */
    String getGearJSON() throws IOException;

    /**
     * Makes an http connection to the swgoh.help API for a JSON string of a map of gear pieces in the specified
     * language.
     *
     * In the {@link Map} response, the {@link String} key is the gear's equipmentId and the {@link Gear} value is the
     * rest of the information for the gear piece.
     *
     * @param language  The {@link Language} that the result should be displayed in.
     * @return JSON string of all the gear in the game, and where to find them.
     * @throws IOException if http connection fails.
     * @see #getGear()
     * @see #getGear(Language)
     * @see #getGearJSON()
     */
    String getGearJSON( Language language ) throws IOException;

    /**
     * Makes an http connection to the swgoh.help API for a list of all mod sets in the default language.
     *
     * @return All the mod sets in the game.
     * @throws IOException if http connection fails.
     * @see #getModSets(Language)
     * @see #getModSetsJSON()
     * @see #getModSetsJSON(Language)
     */
    List<ModSet> getModSets() throws IOException;

    /**
     * Makes an http connection to the swgoh.help API for a list of all mod sets in the specified language.
     *
     * @param language  The {@link Language} that the result should be displayed in.
     * @return All the mod sets in the game.
     * @throws IOException if http connection fails.
     * @see #getModSets()
     * @see #getModSetsJSON()
     * @see #getModSetsJSON(Language)
     */
    List<ModSet> getModSets( Language language ) throws IOException;

    /**
     * Makes an http connection to the swgoh.help API for a JSON string of a list of all mod sets in the default
     * language.
     *
     * @return JSON string of all the mod sets in the game.
     * @throws IOException if http connection fails.
     * @see #getModSets()
     * @see #getModSets(Language)
     * @see #getModSetsJSON(Language)
     */
    String getModSetsJSON() throws IOException;

    /**
     * Makes an http connection to the swgoh.help API for a JSON string of a list of all mod sets in the specified
     * language.
     *
     * @param language  The {@link Language} that the result should be displayed in.
     * @return JSON string of all the mod sets in the game.
     * @throws IOException if http connection fails.
     * @see #getModSets()
     * @see #getModSets(Language)
     * @see #getModSetsJSON()
     */
    String getModSetsJSON( Language language ) throws IOException;

    /**
     * Makes an http connection to the swgoh.help API for a list of all mod stat fields in the default language.
     *
     * @return All the mod stat fields in the game.
     * @throws IOException if http connection fails.
     * @see #getModStatFields(Language)
     * @see #getModStatFieldsJSON()
     * @see #getModStatFieldsJSON(Language)
     */
    List<String> getModStatFields() throws IOException;

    /**
     * Makes an http connection to the swgoh.help API for a list of all mod stat fields in the specified language.
     *
     * @param language  The {@link Language} that the result should be displayed in.
     * @return All the mod stat fields in the game.
     * @throws IOException if http connection fails.
     * @see #getModStatFields()
     * @see #getModStatFieldsJSON()
     * @see #getModStatFieldsJSON(Language)
     */
    List<String> getModStatFields( Language language ) throws IOException;

    /**
     * Makes an http connection to the swgoh.help API for a JSON string of a list of all mod stat fields in the default
     * language.
     *
     * @return JSON string of all the mod stat fields in the game.
     * @throws IOException if http connection fails.
     * @see #getModStatFields()
     * @see #getModStatFields(Language)
     * @see #getModStatFieldsJSON(Language)
     */
    String getModStatFieldsJSON() throws IOException;

    /**
     * Makes an http connection to the swgoh.help API for a JSON string of a list of all mod stat fields in the
     * specified language.
     *
     * @param language  The {@link Language} that the result should be displayed in.
     * @return JSON string of all the mod stat fields in the game.
     * @throws IOException if http connection fails.
     * @see #getModStatFields()
     * @see #getModStatFields(Language)
     * @see #getModStatFieldsJSON()
     */
    String getModStatFieldsJSON( Language language ) throws IOException;

    /**
     * Makes an http connection to the swgoh.help API for a list of all unit abilities in the default language.
     *
     * @return All the unit abilities in the game.
     * @throws IOException if http connection fails.
     * @see #getSkills(Language)
     * @see #getSkillsJSON()
     * @see #getSkillsJSON(Language)
     */
    Map<String, BaseSkill> getSkills() throws IOException;

    /**
     * Makes an http connection to the swgoh.help API for a list of all unit abilities in the specified language.
     *
     * @param language  The {@link Language} that the result should be displayed in.
     * @return All the unit abilities in the game.
     * @throws IOException if http connection fails.
     * @see #getSkills()
     * @see #getSkillsJSON()
     * @see #getSkillsJSON(Language)
     */
    Map<String, BaseSkill> getSkills( Language language ) throws IOException;

    /**
     * Makes an http connection to the swgoh.help API for a JSON string of a list of all unit abilities in the default
     * language.
     *
     * @return JSON string of all the unit abilities in the game.
     * @throws IOException if http connection fails.
     * @see #getSkills()
     * @see #getSkills(Language)
     * @see #getSkillsJSON(Language)
     */
    String getSkillsJSON() throws IOException;

    /**
     * Makes an http connection to the swgoh.help API for a JSON string of a list of all unit abilities in the specified
     * language.
     *
     * @param language  The {@link Language} that the result should be displayed in.
     * @return JSON string of all the unit abilities in the game.
     * @throws IOException if http connection fails.
     * @see #getSkills()
     * @see #getSkills(Language)
     * @see #getSkillsJSON()
     */
    String getSkillsJSON( Language language ) throws IOException;

    /**
     * Makes an http connection to the swgoh.help API for a map of all skill types in the default language.
     *
     * @return All the skill types in the game.
     * @throws IOException if http connection fails.
     * @see #getSkillTypes(Language)
     * @see #getSkillTypesJSON()
     * @see #getSkillTypesJSON(Language)
     */
    Map<String, SkillType> getSkillTypes() throws IOException;

    /**
     * Makes an http connection to the swgoh.help API for a map of all skill types in the specified
     * language.
     *
     * @param language  The {@link Language} that the result should be displayed in.
     * @return All the skill types in the game.
     * @throws IOException if http connection fails.
     * @see #getSkillTypes()
     * @see #getSkillTypesJSON()
     * @see #getSkillTypesJSON(Language)
     */
    Map<String, SkillType> getSkillTypes( Language language ) throws IOException;

    /**
     * Makes an http connection to the swgoh.help API for a JSON string of a map of all skill types in the default
     * language.
     *
     * @return JSON string of all the skill types in the game.
     * @throws IOException if http connection fails.
     * @see #getSkillTypes()
     * @see #getSkillTypes(Language)
     * @see #getSkillTypesJSON(Language)
     */
    String getSkillTypesJSON() throws IOException;

    /**
     * Makes an http connection to the swgoh.help API for a JSON string of a map of all skill types in the specified
     * language.
     *
     * @param language  The {@link Language} that the result should be displayed in.
     * @return JSON string of all the skill types in the game.
     * @throws IOException if http connection fails.
     * @see #getSkillTypes()
     * @see #getSkillTypes(Language)
     * @see #getSkillTypesJSON()
     */
    String getSkillTypesJSON( Language language ) throws IOException;

    /**
     * Makes an http connection to the swgoh.help API for a list of Territory Battles in the default language.
     *
     * @return List of public information for each Territory Battle
     * @throws IOException if http connection fails.
     * @see #getTBs(Language)
     * @see #getTBsJSON()
     * @see #getTBsJSON(Language)
     */
    List<TB> getTBs() throws IOException;

    /**
     * Makes an http connection to the swgoh.help API for a list of Territory Battles in the specified language.
     *
     * @param language  The {@link Language} that the result should be displayed in.
     * @return List of public information for each Territory Battle
     * @throws IOException if http connection fails.
     * @see #getTBs()
     * @see #getTBsJSON()
     * @see #getTBsJSON(Language)
     */
    List<TB> getTBs( Language language ) throws IOException;

    /**
     * Makes an http connection to the swgoh.help API for a JSON string of the list of Territory Battles in the default
     * language.
     *
     * @return JSON string of the list of public information for each Territory Battle
     * @throws IOException if http connection fails.
     * @see #getTBs()
     * @see #getTBs(Language)
     * @see #getTBsJSON(Language)
     */
    String getTBsJSON() throws IOException;

    /**
     * Makes an http connection to the swgoh.help API for a JSON string of the list of Territory Battles in the
     * specified language.
     *
     * @param language  The {@link Language} that the result should be displayed in.
     * @return JSON string of the list of public information for each Territory Battle
     * @throws IOException if http connection fails.
     * @see #getTBs()
     * @see #getTBs(Language)
     * @see #getTBsJSON()
     */
    String getTBsJSON( Language language ) throws IOException;

    /**
     * Makes an http connection to the swgoh.help API for a map of all zetas in the default language.
     *
     * @return All zetas in the game
     * @throws IOException if http connection fails.
     * @see #getZetas(Language)
     * @see #getZetasJSON()
     * @see #getZetasJSON(Language)
     */
    Map<String, Zeta> getZetas() throws IOException;

    /**
     * Makes an http connection to the swgoh.help API for a map of all zetas in the specified language.
     *
     * @param language  The {@link Language} that the result should be displayed in.
     * @return All zetas in the game
     * @throws IOException if http connection fails.
     * @see #getZetas()
     * @see #getZetasJSON()
     * @see #getZetasJSON(Language)
     */
    Map<String, Zeta> getZetas( Language language ) throws IOException;

    /**
     * Makes an http connection to the swgoh.help API for a JSON string of the map of all zetas in the default language.
     *
     * @return JSON string of all zetas in the game
     * @throws IOException if http connection fails.
     * @see #getZetas()
     * @see #getZetas(Language)
     * @see #getZetasJSON(Language)
     */
    String getZetasJSON() throws IOException;

    /**
     * Makes an http connection to the swgoh.help API for a JSON string of the map of all zetas in the specified
     * language.
     *
     * @param language  The {@link Language} that the result should be displayed in.
     * @return JSON string of all zetas in the game
     * @throws IOException if http connection fails.
     * @see #getZetas()
     * @see #getZetas(Language)
     * @see #getZetasJSON()
     */
    String getZetasJSON( Language language ) throws IOException;

    /**
     * Makes an http connection to the swgoh.help API for a list of all zetas, including the unit, in the default
     * language.
     *
     * @return List of all zetas, including the unit
     * @throws IOException if http connection fails.
     * @see #getZetasWithUnit(Language)
     * @see #getZetasWithUnitJSON()
     * @see #getZetasWithUnitJSON(Language)
     */
    List<Zeta> getZetasWithUnit() throws IOException;

    /**
     * Makes an http connection to the swgoh.help API for a list of all zetas, including the unit, in the specified
     * language.
     *
     * @param language  The {@link Language} that the result should be displayed in.
     * @return List of all zetas, including the unit
     * @throws IOException if http connection fails.
     * @see #getZetasWithUnit()
     * @see #getZetasWithUnitJSON()
     * @see #getZetasWithUnitJSON(Language)
     */
    List<Zeta> getZetasWithUnit( Language language ) throws IOException;

    /**
     * Makes an http connection to the swgoh.help API for a JSON string of the list of all zetas, including the unit, in
     * the default
     * language.
     *
     * @return JSON string of the list of all zetas, including the unit
     * @throws IOException if http connection fails.
     * @see #getZetasWithUnit()
     * @see #getZetasWithUnit(Language)
     * @see #getZetasWithUnitJSON(Language)
     */
    String getZetasWithUnitJSON() throws IOException;

    /**
     * Makes an http connection to the swgoh.help API for a JSON string of the list of all zetas, including the unit, in
     * the specified language.
     *
     * @param language  The {@link Language} that the result should be displayed in.
     * @return JSON string of the list of all zetas, including the unit
     * @throws IOException if http connection fails.
     * @see #getZetasWithUnit()
     * @see #getZetasWithUnit(Language)
     * @see #getZetasWithUnitJSON()
     */
    String getZetasWithUnitJSON( Language language ) throws IOException;

    /**
     * Makes an http connection to the swgoh.help API for a list of zeta recommendations in the default language.
     *
     * @return List of zeta recommendations
     * @throws IOException if http connection fails.
     * @see #getZetaRecommendations(Language)
     * @see #getZetaRecommendationsJSON()
     * @see #getZetaRecommendationsJSON(Language)
     */
    ZetaRecommendations getZetaRecommendations() throws IOException;

    /**
     * Makes an http connection to the swgoh.help API for a list of zeta recommendations in the specified language.
     *
     * @param language  The {@link Language} that the result should be displayed in.
     * @return List of zeta recommendations
     * @throws IOException if http connection fails.
     * @see #getZetaRecommendations()
     * @see #getZetaRecommendationsJSON()
     * @see #getZetaRecommendationsJSON(Language)
     */
    ZetaRecommendations getZetaRecommendations( Language language ) throws IOException;

    /**
     * Makes an http connection to the swgoh.help API for a JSON string of a list of zeta recommendations in the default
     * language.
     *
     * @return List of zeta recommendations
     * @throws IOException if http connection fails.
     * @see #getZetaRecommendations()
     * @see #getZetaRecommendations(Language)
     * @see #getZetaRecommendationsJSON(Language)
     */
    String getZetaRecommendationsJSON() throws IOException;

    /**
     * Makes an http connection to the swgoh.help API for a JSON string of a list of zeta recommendations in the
     * specified language.
     *
     * @param language  The {@link Language} that the result should be displayed in.
     * @return List of zeta recommendations
     * @throws IOException if http connection fails.
     * @see #getZetaRecommendations()
     * @see #getZetaRecommendations(Language)
     * @see #getZetaRecommendationsJSON()
     */
    String getZetaRecommendationsJSON( Language language ) throws IOException;

    /**
     * Makes an http connection to the swgoh.help API for a list of battles in the default language.
     *
     * @return list of battles
     * @throws IOException if http connection fails.
     * @see #getBattles(Language)
     * @see #getBattlesJSON()
     * @see #getBattlesJSON(Language)
     */
    List<Battle> getBattles() throws IOException;

    /**
     * Makes an http connection to the swgoh.help API for a list of battles in the specified language.
     *
     * @param language  The {@link Language} that the result should be displayed in.
     * @return list of battles
     * @throws IOException if http connection fails.
     * @see #getBattles()
     * @see #getBattlesJSON()
     * @see #getBattlesJSON(Language)
     */
    List<Battle> getBattles( Language language ) throws IOException;

    /**
     * Makes an http connection to the swgoh.help API for a JSON string of the list of battles in the default language.
     *
     * @return JSON string of the list of battles
     * @throws IOException if http connection fails.
     * @see #getBattles()
     * @see #getBattles(Language)
     * @see #getBattlesJSON(Language)
     */
    String getBattlesJSON() throws IOException;

    /**
     * Makes an http connection to the swgoh.help API for a JSON string of the list of battles in the specified
     * language.
     *
     * @param language  The {@link Language} that the result should be displayed in.
     * @return JSON string of the list of battles
     * @throws IOException if http connection fails.
     * @see #getBattles()
     * @see #getBattles(Language)
     * @see #getBattlesJSON()
     */
    String getBattlesJSON( Language language ) throws IOException;
}
