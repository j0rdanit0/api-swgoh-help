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
    /**
     * Makes an http connection to the swgoh.help API for a player object in the default language.
     *
     * For pulling all the members of a guild, use {@link #getGuild(int)}
     *
     * @param allyCode  The number that uniquely identifies a player, found in the player's game profile.
     * @return The player, specified by the allyCode.
     * @throws IOException if http connection fails.
     * @see #getPlayer(int, SwgohAPIClient.Language)
     * @see #getPlayerJSON(int)
     * @see #getPlayerJSON(int, SwgohAPIClient.Language)
     * @see #getGuild(int)
     */
    Player getPlayer( int allyCode ) throws IOException;

    /**
     * Makes an http connection to the swgoh.help API for a player object in the specified language.
     *
     * For pulling all the members of a guild, use {@link #getGuild(int, SwgohAPIClient.Language)}
     *
     * @param allyCode  The number that uniquely identifies a player, found in the player's game profile.
     * @param language  The {@link SwgohAPIClient.Language} that the result should be displayed in.
     * @return The player, specified by the allyCode.
     * @throws IOException if http connection fails.
     * @see #getPlayer(int)
     * @see #getPlayerJSON(int)
     * @see #getPlayerJSON(int, SwgohAPIClient.Language)
     * @see #getGuild(int, SwgohAPIClient.Language)
     */
    Player getPlayer( int allyCode, SwgohAPIClient.Language language ) throws IOException;

    /**
     * Makes an http connection to the swgoh.help API for a player JSON in the default language.
     *
     * For pulling all the members of a guild, use {@link #getGuildJSON(int)}
     *
     * @param allyCode  The number that uniquely identifies a player, found in the player's game profile.
     * @return JSON string of the player, specified by the allyCode.
     * @throws IOException if http connection fails.
     * @see #getPlayer(int)
     * @see #getPlayer(int, SwgohAPIClient.Language)
     * @see #getPlayerJSON(int, SwgohAPIClient.Language)
     * @see #getGuildJSON(int)
     */
    String getPlayerJSON( int allyCode ) throws IOException;

    /**
     * Makes an http connection to the swgoh.help API for a player JSON in the specified language.
     *
     * For pulling all the members of a guild, use {@link #getGuildJSON(int, SwgohAPIClient.Language)}
     *
     * @param allyCode  The number that uniquely identifies a player, found in the player's game profile.
     * @param language  The {@link SwgohAPIClient.Language} that the result should be displayed in.
     * @return JSON string of the player, specified by the allyCode.
     * @throws IOException if http connection fails.
     * @see #getPlayer(int)
     * @see #getPlayer(int, SwgohAPIClient.Language)
     * @see #getPlayerJSON(int)
     * @see #getGuildJSON(int, SwgohAPIClient.Language)
     */
    String getPlayerJSON( int allyCode, SwgohAPIClient.Language language ) throws IOException;

    /**
     * Makes an http connection to the swgoh.help API for a list of player objects in the default language.
     *
     * The list of player objects that is returned belongs to the guild of the player that is identified by the given
     * allyCode.
     *
     * @param allyCode  The number that uniquely identifies a player, found in the player's game profile.
     * @return List of players belonging to the same guild as the player identified by the allyCode
     * @throws IOException if http connection fails.
     * @see #getGuild(int, SwgohAPIClient.Language)
     * @see #getGuildJSON(int)
     * @see #getGuildJSON(int, SwgohAPIClient.Language)
     * @see #getPlayer(int)
     */
    List<Player> getGuild( int allyCode ) throws IOException;

    /**
     * Makes an http connection to the swgoh.help API for a JSON string of player objects in the specified language.
     *
     * The list of player objects that is returned belongs to the guild of the player that is identified by the given
     * allyCode.
     *
     * @param allyCode  The number that uniquely identifies a player, found in the player's game profile.
     * @param language  The {@link SwgohAPIClient.Language} that the result should be displayed in.
     * @return List of players belonging to the same guild as the player identified by the allyCode
     * @throws IOException if http connection fails.
     * @see #getGuild(int)
     * @see #getGuildJSON(int)
     * @see #getGuildJSON(int, SwgohAPIClient.Language)
     * @see #getPlayer(int, SwgohAPIClient.Language)
     */
    List<Player> getGuild( int allyCode, SwgohAPIClient.Language language ) throws IOException;

    /**
     * Makes an http connection to the swgoh.help API for a JSON string of player objects in the default language.
     *
     * The JSON string of player objects that is returned belongs to the guild of the player that is identified by the
     * given allyCode.
     *
     * @param allyCode  The number that uniquely identifies a player, found in the player's game profile.
     * @return JSON string of the list of players belonging to the same guild as the player identified by the allyCode
     * @throws IOException if http connection fails.
     * @see #getGuild(int)
     * @see #getGuild(int, SwgohAPIClient.Language)
     * @see #getGuildJSON(int, SwgohAPIClient.Language)
     * @see #getPlayerJSON(int)
     */
    String getGuildJSON( int allyCode ) throws IOException;

    /**
     * Makes an http connection to the swgoh.help API for a JSON string of player objects in the specified language.
     *
     * The JSON string of player objects that is returned belongs to the guild of the player that is identified by the
     * given allyCode.
     *
     * @param allyCode  The number that uniquely identifies a player, found in the player's game profile.
     * @param language  The {@link SwgohAPIClient.Language} that the result should be displayed in.
     * @return JSON string of the list of players belonging to the same guild as the player identified by the allyCode
     * @throws IOException if http connection fails.
     * @see #getGuild(int)
     * @see #getGuild(int, SwgohAPIClient.Language)
     * @see #getGuildJSON(int)
     * @see #getPlayerJSON(int, SwgohAPIClient.Language)
     */
    String getGuildJSON( int allyCode, SwgohAPIClient.Language language ) throws IOException;

    /**
     * Makes an http connection to the swgoh.help API for a list of game events in the default language.
     *
     * @return List of events
     * @throws IOException if http connection fails.
     * @see #getEvents(SwgohAPIClient.Language)
     * @see #getEventsJSON()
     * @see #getEventsJSON(SwgohAPIClient.Language)
     */
    List<Event> getEvents() throws IOException;

    /**
     * Makes an http connection to the swgoh.help API for a list of game events in the specified language.
     *
     * @param language  The {@link SwgohAPIClient.Language} that the result should be displayed in.
     * @return List of events
     * @throws IOException if http connection fails.
     * @see #getEvents()
     * @see #getEventsJSON()
     * @see #getEventsJSON(SwgohAPIClient.Language)
     */
    List<Event> getEvents( SwgohAPIClient.Language language ) throws IOException;

    /**
     * Makes an http connection to the swgoh.help API for a JSON string of game events in the default language.
     *
     * @return JSON string of the list of events
     * @throws IOException if http connection fails.
     * @see #getEvents()
     * @see #getEvents(SwgohAPIClient.Language)
     * @see #getEventsJSON(SwgohAPIClient.Language)
     */
    String getEventsJSON() throws IOException;

    /**
     * Makes an http connection to the swgoh.help API for a JSON string of game events in the specified language.
     *
     * @param language  The {@link SwgohAPIClient.Language} that the result should be displayed in.
     * @return JSON string of the list of events
     * @throws IOException if http connection fails.
     * @see #getEvents()
     * @see #getEvents(SwgohAPIClient.Language)
     * @see #getEventsJSON()
     */
    String getEventsJSON( SwgohAPIClient.Language language ) throws IOException;

    /**
     * Makes an http connection to the swgoh.help API for a map of game units in the default language.
     *
     * In the {@link Map} response, the {@link String} key is the unit's ID and the {@link Unit} value is the rest of
     * the information for the unit.
     *
     * @return The game's playable characters and ships
     * @throws IOException if http connection fails.
     * @see #getUnits(SwgohAPIClient.Language)
     * @see #getUnitsJSON()
     * @see #getUnitsJSON(SwgohAPIClient.Language)
     */
    Map<String, Unit> getUnits() throws IOException;

    /**
     * Makes an http connection to the swgoh.help API for a list of game units in the specified language.
     *
     * In the {@link Map} response, the {@link String} key is the unit's ID and the {@link Unit} value is the rest of
     * the information for the unit.
     *
     * @param language  The {@link SwgohAPIClient.Language} that the result should be displayed in.
     * @return The game's playable characters and ships
     * @throws IOException if http connection fails.
     * @see #getUnits()
     * @see #getUnitsJSON()
     * @see #getUnitsJSON(SwgohAPIClient.Language)
     */
    Map<String, Unit> getUnits( SwgohAPIClient.Language language ) throws IOException;

    /**
     * Makes an http connection to the swgoh.help API for a JSON string of game units in the default language.
     *
     * @return JSON string of the game's playable characters and ships
     * @throws IOException if http connection fails.
     * @see #getUnits()
     * @see #getUnits(SwgohAPIClient.Language)
     * @see #getUnitsJSON(SwgohAPIClient.Language)
     */
    String getUnitsJSON() throws IOException;

    /**
     * Makes an http connection to the swgoh.help API for a JSON string of game units in the specified language.
     *
     * @param language  The {@link SwgohAPIClient.Language} that the result should be displayed in.
     * @return JSON string of the game's playable characters and ships
     * @throws IOException if http connection fails.
     * @see #getUnits()
     * @see #getUnits(SwgohAPIClient.Language)
     * @see #getUnitsJSON()
     */
    String getUnitsJSON( SwgohAPIClient.Language language ) throws IOException;

    /**
     * Makes an http connection to the swgoh.help API for a JSON string of arena types in the default language.
     *
     * @return List of arena types
     * @throws IOException if http connection fails.
     * @see #getArenaSquadMemberTypes(SwgohAPIClient.Language)
     * @see #getArenaSquadMemberTypesJSON()
     * @see #getArenaSquadMemberTypesJSON(SwgohAPIClient.Language)
     */
    List<ArenaSquadMemberType> getArenaSquadMemberTypes() throws IOException;

    /**
     * Makes an http connection to the swgoh.help API for a JSON string of arena types in the specified language.
     *
     * @param language  The {@link SwgohAPIClient.Language} that the result should be displayed in.
     * @return List of arena types
     * @throws IOException if http connection fails.
     * @see #getArenaSquadMemberTypes()
     * @see #getArenaSquadMemberTypesJSON()
     * @see #getArenaSquadMemberTypesJSON(SwgohAPIClient.Language)
     */
    List<ArenaSquadMemberType> getArenaSquadMemberTypes( SwgohAPIClient.Language language ) throws IOException;

    /**
     * Makes an http connection to the swgoh.help API for a JSON string of arena types in the default language.
     *
     * @return JSON string of arena types
     * @throws IOException if http connection fails.
     * @see #getArenaSquadMemberTypes()
     * @see #getArenaSquadMemberTypes(SwgohAPIClient.Language)
     * @see #getArenaSquadMemberTypesJSON(SwgohAPIClient.Language)
     */
    String getArenaSquadMemberTypesJSON() throws IOException;

    /**
     * Makes an http connection to the swgoh.help API for a JSON string of arena types in the specified language.
     *
     * @param language  The {@link SwgohAPIClient.Language} that the result should be displayed in.
     * @return JSON string of arena types
     * @throws IOException if http connection fails.
     * @see #getArenaSquadMemberTypes()
     * @see #getArenaSquadMemberTypes(SwgohAPIClient.Language)
     * @see #getArenaSquadMemberTypesJSON()
     */
    String getArenaSquadMemberTypesJSON( SwgohAPIClient.Language language ) throws IOException;

    /**
     * Makes an http connection to the swgoh.help API for a map of gear pieces in the default language.
     *
     * In the {@link Map} response, the {@link String} key is the gear's equipmentId and the {@link Gear} value is the
     * rest of the information for the gear piece.
     *
     * @return All the gear in the game, and where to find them.
     * @throws IOException if http connection fails.
     * @see #getGear(SwgohAPIClient.Language)
     * @see #getGearJSON()
     * @see #getGearJSON(SwgohAPIClient.Language)
     */
    Map<String,Gear> getGear() throws IOException;

    /**
     * Makes an http connection to the swgoh.help API for a map of gear pieces in the specified language.
     *
     * In the {@link Map} response, the {@link String} key is the gear's equipmentId and the {@link Gear} value is the
     * rest of the information for the gear piece.
     *
     * @param language  The {@link SwgohAPIClient.Language} that the result should be displayed in.
     * @return All the gear in the game, and where to find them.
     * @throws IOException if http connection fails.
     * @see #getGear()
     * @see #getGearJSON()
     * @see #getGearJSON(SwgohAPIClient.Language)
     */
    Map<String,Gear> getGear( SwgohAPIClient.Language language ) throws IOException;

    /**
     * Makes an http connection to the swgoh.help API for a JSON string of a map of gear pieces in the default language.
     *
     * In the {@link Map} response, the {@link String} key is the gear's equipmentId and the {@link Gear} value is the
     * rest of the information for the gear piece.
     *
     * @return JSON string of all the gear in the game, and where to find them.
     * @throws IOException if http connection fails.
     * @see #getGear()
     * @see #getGear(SwgohAPIClient.Language)
     * @see #getGearJSON(SwgohAPIClient.Language)
     */
    String getGearJSON() throws IOException;

    /**
     * Makes an http connection to the swgoh.help API for a JSON string of a map of gear pieces in the specified
     * language.
     *
     * In the {@link Map} response, the {@link String} key is the gear's equipmentId and the {@link Gear} value is the
     * rest of the information for the gear piece.
     *
     * @param language  The {@link SwgohAPIClient.Language} that the result should be displayed in.
     * @return JSON string of all the gear in the game, and where to find them.
     * @throws IOException if http connection fails.
     * @see #getGear()
     * @see #getGear(SwgohAPIClient.Language)
     * @see #getGearJSON()
     */
    String getGearJSON( SwgohAPIClient.Language language ) throws IOException;

    /**
     * Makes an http connection to the swgoh.help API for a list of all mod sets in the default language.
     *
     * @return All the mod sets in the game.
     * @throws IOException if http connection fails.
     * @see #getModSets(SwgohAPIClient.Language)
     * @see #getModSetsJSON()
     * @see #getModSetsJSON(SwgohAPIClient.Language)
     */
    List<ModSet> getModSets() throws IOException;

    /**
     * Makes an http connection to the swgoh.help API for a list of all mod sets in the specified language.
     *
     * @param language  The {@link SwgohAPIClient.Language} that the result should be displayed in.
     * @return All the mod sets in the game.
     * @throws IOException if http connection fails.
     * @see #getModSets()
     * @see #getModSetsJSON()
     * @see #getModSetsJSON(SwgohAPIClient.Language)
     */
    List<ModSet> getModSets( SwgohAPIClient.Language language ) throws IOException;

    /**
     * Makes an http connection to the swgoh.help API for a JSON string of a list of all mod sets in the default
     * language.
     *
     * @return JSON string of all the mod sets in the game.
     * @throws IOException if http connection fails.
     * @see #getModSets()
     * @see #getModSets(SwgohAPIClient.Language)
     * @see #getModSetsJSON(SwgohAPIClient.Language)
     */
    String getModSetsJSON() throws IOException;

    /**
     * Makes an http connection to the swgoh.help API for a JSON string of a list of all mod sets in the specified
     * language.
     *
     * @param language  The {@link SwgohAPIClient.Language} that the result should be displayed in.
     * @return JSON string of all the mod sets in the game.
     * @throws IOException if http connection fails.
     * @see #getModSets()
     * @see #getModSets(SwgohAPIClient.Language)
     * @see #getModSetsJSON()
     */
    String getModSetsJSON( SwgohAPIClient.Language language ) throws IOException;

    /**
     * Makes an http connection to the swgoh.help API for a list of all mod stat fields in the default language.
     *
     * @return All the mod stat fields in the game.
     * @throws IOException if http connection fails.
     * @see #getModStatFields(SwgohAPIClient.Language)
     * @see #getModStatFieldsJSON()
     * @see #getModStatFieldsJSON(SwgohAPIClient.Language)
     */
    List<String> getModStatFields() throws IOException;

    /**
     * Makes an http connection to the swgoh.help API for a list of all mod stat fields in the specified language.
     *
     * @param language  The {@link SwgohAPIClient.Language} that the result should be displayed in.
     * @return All the mod stat fields in the game.
     * @throws IOException if http connection fails.
     * @see #getModStatFields()
     * @see #getModStatFieldsJSON()
     * @see #getModStatFieldsJSON(SwgohAPIClient.Language)
     */
    List<String> getModStatFields( SwgohAPIClient.Language language ) throws IOException;

    /**
     * Makes an http connection to the swgoh.help API for a JSON string of a list of all mod stat fields in the default
     * language.
     *
     * @return JSON string of all the mod stat fields in the game.
     * @throws IOException if http connection fails.
     * @see #getModStatFields()
     * @see #getModStatFields(SwgohAPIClient.Language)
     * @see #getModStatFieldsJSON(SwgohAPIClient.Language)
     */
    String getModStatFieldsJSON() throws IOException;

    /**
     * Makes an http connection to the swgoh.help API for a JSON string of a list of all mod stat fields in the
     * specified language.
     *
     * @param language  The {@link SwgohAPIClient.Language} that the result should be displayed in.
     * @return JSON string of all the mod stat fields in the game.
     * @throws IOException if http connection fails.
     * @see #getModStatFields()
     * @see #getModStatFields(SwgohAPIClient.Language)
     * @see #getModStatFieldsJSON()
     */
    String getModStatFieldsJSON( SwgohAPIClient.Language language ) throws IOException;

    /**
     * Makes an http connection to the swgoh.help API for a list of all unit abilities in the default language.
     *
     * @return All the unit abilities in the game.
     * @throws IOException if http connection fails.
     * @see #getSkills(SwgohAPIClient.Language)
     * @see #getSkillsJSON()
     * @see #getSkillsJSON(SwgohAPIClient.Language)
     */
    Map<String, BaseSkill> getSkills() throws IOException;

    /**
     * Makes an http connection to the swgoh.help API for a list of all unit abilities in the specified language.
     *
     * @param language  The {@link SwgohAPIClient.Language} that the result should be displayed in.
     * @return All the unit abilities in the game.
     * @throws IOException if http connection fails.
     * @see #getSkills()
     * @see #getSkillsJSON()
     * @see #getSkillsJSON(SwgohAPIClient.Language)
     */
    Map<String, BaseSkill> getSkills( SwgohAPIClient.Language language ) throws IOException;

    /**
     * Makes an http connection to the swgoh.help API for a JSON string of a list of all unit abilities in the default
     * language.
     *
     * @return JSON string of all the unit abilities in the game.
     * @throws IOException if http connection fails.
     * @see #getSkills()
     * @see #getSkills(SwgohAPIClient.Language)
     * @see #getSkillsJSON(SwgohAPIClient.Language)
     */
    String getSkillsJSON() throws IOException;

    /**
     * Makes an http connection to the swgoh.help API for a JSON string of a list of all unit abilities in the specified
     * language.
     *
     * @param language  The {@link SwgohAPIClient.Language} that the result should be displayed in.
     * @return JSON string of all the unit abilities in the game.
     * @throws IOException if http connection fails.
     * @see #getSkills()
     * @see #getSkills(SwgohAPIClient.Language)
     * @see #getSkillsJSON()
     */
    String getSkillsJSON( SwgohAPIClient.Language language ) throws IOException;

    /**
     * Makes an http connection to the swgoh.help API for a map of all skill types in the default language.
     *
     * @return All the skill types in the game.
     * @throws IOException if http connection fails.
     * @see #getSkillTypes(SwgohAPIClient.Language)
     * @see #getSkillTypesJSON()
     * @see #getSkillTypesJSON(SwgohAPIClient.Language)
     */
    Map<String, BaseSkill.Type> getSkillTypes() throws IOException;

    /**
     * Makes an http connection to the swgoh.help API for a map of all skill types in the specified
     * language.
     *
     * @param language  The {@link SwgohAPIClient.Language} that the result should be displayed in.
     * @return All the skill types in the game.
     * @throws IOException if http connection fails.
     * @see #getSkillTypes()
     * @see #getSkillTypesJSON()
     * @see #getSkillTypesJSON(SwgohAPIClient.Language)
     */
    Map<String, BaseSkill.Type> getSkillTypes( SwgohAPIClient.Language language ) throws IOException;

    /**
     * Makes an http connection to the swgoh.help API for a JSON string of a map of all skill types in the default
     * language.
     *
     * @return JSON string of all the skill types in the game.
     * @throws IOException if http connection fails.
     * @see #getSkillTypes()
     * @see #getSkillTypes(SwgohAPIClient.Language)
     * @see #getSkillTypesJSON(SwgohAPIClient.Language)
     */
    String getSkillTypesJSON() throws IOException;

    /**
     * Makes an http connection to the swgoh.help API for a JSON string of a map of all skill types in the specified
     * language.
     *
     * @param language  The {@link SwgohAPIClient.Language} that the result should be displayed in.
     * @return JSON string of all the skill types in the game.
     * @throws IOException if http connection fails.
     * @see #getSkillTypes()
     * @see #getSkillTypes(SwgohAPIClient.Language)
     * @see #getSkillTypesJSON()
     */
    String getSkillTypesJSON( SwgohAPIClient.Language language ) throws IOException;

    /**
     * Makes an http connection to the swgoh.help API for a list of Territory Battles in the default language.
     *
     * @return List of public information for each Territory Battle
     * @throws IOException if http connection fails.
     * @see #getTBs(SwgohAPIClient.Language)
     * @see #getTBsJSON()
     * @see #getTBsJSON(SwgohAPIClient.Language)
     */
    List<TB> getTBs() throws IOException;

    /**
     * Makes an http connection to the swgoh.help API for a list of Territory Battles in the specified language.
     *
     * @param language  The {@link SwgohAPIClient.Language} that the result should be displayed in.
     * @return List of public information for each Territory Battle
     * @throws IOException if http connection fails.
     * @see #getTBs()
     * @see #getTBsJSON()
     * @see #getTBsJSON(SwgohAPIClient.Language)
     */
    List<TB> getTBs( SwgohAPIClient.Language language ) throws IOException;

    /**
     * Makes an http connection to the swgoh.help API for a JSON string of the list of Territory Battles in the default
     * language.
     *
     * @return JSON string of the list of public information for each Territory Battle
     * @throws IOException if http connection fails.
     * @see #getTBs()
     * @see #getTBs(SwgohAPIClient.Language)
     * @see #getTBsJSON(SwgohAPIClient.Language)
     */
    String getTBsJSON() throws IOException;

    /**
     * Makes an http connection to the swgoh.help API for a JSON string of the list of Territory Battles in the
     * specified language.
     *
     * @param language  The {@link SwgohAPIClient.Language} that the result should be displayed in.
     * @return JSON string of the list of public information for each Territory Battle
     * @throws IOException if http connection fails.
     * @see #getTBs()
     * @see #getTBs(SwgohAPIClient.Language)
     * @see #getTBsJSON()
     */
    String getTBsJSON( SwgohAPIClient.Language language ) throws IOException;

    /**
     * Makes an http connection to the swgoh.help API for a map of all zetas in the default language.
     *
     * @return All zetas in the game
     * @throws IOException if http connection fails.
     * @see #getZetas(SwgohAPIClient.Language)
     * @see #getZetasJSON()
     * @see #getZetasJSON(SwgohAPIClient.Language)
     */
    Map<String, Zeta> getZetas() throws IOException;

    /**
     * Makes an http connection to the swgoh.help API for a map of all zetas in the specified language.
     *
     * @param language  The {@link SwgohAPIClient.Language} that the result should be displayed in.
     * @return All zetas in the game
     * @throws IOException if http connection fails.
     * @see #getZetas()
     * @see #getZetasJSON()
     * @see #getZetasJSON(SwgohAPIClient.Language)
     */
    Map<String, Zeta> getZetas( SwgohAPIClient.Language language ) throws IOException;

    /**
     * Makes an http connection to the swgoh.help API for a JSON string of the map of all zetas in the default language.
     *
     * @return JSON string of all zetas in the game
     * @throws IOException if http connection fails.
     * @see #getZetas()
     * @see #getZetas(SwgohAPIClient.Language)
     * @see #getZetasJSON(SwgohAPIClient.Language)
     */
    String getZetasJSON() throws IOException;

    /**
     * Makes an http connection to the swgoh.help API for a JSON string of the map of all zetas in the specified
     * language.
     *
     * @param language  The {@link SwgohAPIClient.Language} that the result should be displayed in.
     * @return JSON string of all zetas in the game
     * @throws IOException if http connection fails.
     * @see #getZetas()
     * @see #getZetas(SwgohAPIClient.Language)
     * @see #getZetasJSON()
     */
    String getZetasJSON( SwgohAPIClient.Language language ) throws IOException;

    /**
     * Makes an http connection to the swgoh.help API for a list of all zetas, including the unit, in the default
     * language.
     *
     * @return List of all zetas, including the unit
     * @throws IOException if http connection fails.
     * @see #getZetasWithUnit(SwgohAPIClient.Language)
     * @see #getZetasWithUnitJSON()
     * @see #getZetasWithUnitJSON(SwgohAPIClient.Language)
     */
    List<Zeta> getZetasWithUnit() throws IOException;

    /**
     * Makes an http connection to the swgoh.help API for a list of all zetas, including the unit, in the specified
     * language.
     *
     * @param language  The {@link SwgohAPIClient.Language} that the result should be displayed in.
     * @return List of all zetas, including the unit
     * @throws IOException if http connection fails.
     * @see #getZetasWithUnit()
     * @see #getZetasWithUnitJSON()
     * @see #getZetasWithUnitJSON(SwgohAPIClient.Language)
     */
    List<Zeta> getZetasWithUnit( SwgohAPIClient.Language language ) throws IOException;

    /**
     * Makes an http connection to the swgoh.help API for a JSON string of the list of all zetas, including the unit, in
     * the default
     * language.
     *
     * @return JSON string of the list of all zetas, including the unit
     * @throws IOException if http connection fails.
     * @see #getZetasWithUnit()
     * @see #getZetasWithUnit(SwgohAPIClient.Language)
     * @see #getZetasWithUnitJSON(SwgohAPIClient.Language)
     */
    String getZetasWithUnitJSON() throws IOException;

    /**
     * Makes an http connection to the swgoh.help API for a JSON string of the list of all zetas, including the unit, in
     * the specified language.
     *
     * @param language  The {@link SwgohAPIClient.Language} that the result should be displayed in.
     * @return JSON string of the list of all zetas, including the unit
     * @throws IOException if http connection fails.
     * @see #getZetasWithUnit()
     * @see #getZetasWithUnit(SwgohAPIClient.Language)
     * @see #getZetasWithUnitJSON()
     */
    String getZetasWithUnitJSON( SwgohAPIClient.Language language ) throws IOException;

    /**
     * Makes an http connection to the swgoh.help API for a list of battles in the default language.
     *
     * @return list of battles
     * @throws IOException if http connection fails.
     * @see #getBattles(SwgohAPIClient.Language)
     * @see #getBattlesJSON()
     * @see #getBattlesJSON(SwgohAPIClient.Language)
     */
    List<Battle> getBattles() throws IOException;

    /**
     * Makes an http connection to the swgoh.help API for a list of battles in the specified language.
     *
     * @param language  The {@link SwgohAPIClient.Language} that the result should be displayed in.
     * @return list of battles
     * @throws IOException if http connection fails.
     * @see #getBattles()
     * @see #getBattlesJSON()
     * @see #getBattlesJSON(SwgohAPIClient.Language)
     */
    List<Battle> getBattles( SwgohAPIClient.Language language ) throws IOException;

    /**
     * Makes an http connection to the swgoh.help API for a JSON string of the list of battles in the default language.
     *
     * @return JSON string of the list of battles
     * @throws IOException if http connection fails.
     * @see #getBattles()
     * @see #getBattles(SwgohAPIClient.Language)
     * @see #getBattlesJSON(SwgohAPIClient.Language)
     */
    String getBattlesJSON() throws IOException;

    /**
     * Makes an http connection to the swgoh.help API for a JSON string of the list of battles in the specified
     * language.
     *
     * @param language  The {@link SwgohAPIClient.Language} that the result should be displayed in.
     * @return JSON string of the list of battles
     * @throws IOException if http connection fails.
     * @see #getBattles()
     * @see #getBattles(SwgohAPIClient.Language)
     * @see #getBattlesJSON()
     */
    String getBattlesJSON( SwgohAPIClient.Language language ) throws IOException;
}
