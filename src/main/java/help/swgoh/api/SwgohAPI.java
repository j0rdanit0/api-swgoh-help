package help.swgoh.api;

import help.swgoh.api.response.Event;
import help.swgoh.api.response.Player;
import help.swgoh.api.response.TB;
import help.swgoh.api.response.Unit;

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

//    Arena getArena() throws IOException;
//    Arena getArena( SwgohHelp.Language language ) throws IOException;
//
//    List<Gear> getGear() throws IOException;
//    List<Gear> getGear( SwgohHelp.Language language ) throws IOException;
//
//    List<ModSet> getModSets() throws IOException;
//    List<ModSet> getModSets( SwgohHelp.Language language ) throws IOException;
//
//    List<ModStat> getModStats() throws IOException;
//    List<ModStat> getModStats( SwgohHelp.Language language ) throws IOException;
//
//    List<Skill> getSkills() throws IOException;
//    List<Skill> getSkills( SwgohHelp.Language language ) throws IOException;
//
//    List<SkillType> getSkillTypes() throws IOException;
//    List<SkillType> getSkillTypes( SwgohHelp.Language language ) throws IOException;

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
     * Makes an http connection to the swgoh.help API for a list of Territory Battles in the default language.
     *
     * @return JSON string of the list of public information for each Territory Battle
     * @throws IOException if http connection fails.
     * @see #getTBs()
     * @see #getTBs(SwgohAPIClient.Language)
     * @see #getTBsJSON(SwgohAPIClient.Language)
     */
    String getTBsJSON() throws IOException;

    /**
     * Makes an http connection to the swgoh.help API for a list of Territory Battles in the specified language.
     *
     * @param language  The {@link SwgohAPIClient.Language} that the result should be displayed in.
     * @return JSON string of the list of public information for each Territory Battle
     * @throws IOException if http connection fails.
     * @see #getTBs()
     * @see #getTBs(SwgohAPIClient.Language)
     * @see #getTBsJSON()
     */
    String getTBsJSON( SwgohAPIClient.Language language ) throws IOException;

//    List<Zeta> getZetas() throws IOException;
//    List<Zeta> getZetas( SwgohHelp.Language language ) throws IOException;
//
//    List<ZetaAbility> getZetaAbilities() throws IOException;
//    List<ZetaAbility> getZetaAbilities( SwgohHelp.Language language ) throws IOException;
//
//    List<ZetaRecommendation> getZetaRecommendations() throws IOException;
//    List<ZetaRecommendation> getZetaRecommendations( SwgohHelp.Language language ) throws IOException;
//
//    List<Battle> getBattles() throws IOException;
//    List<Battle> getBattles( SwgohHelp.Language language ) throws IOException;
}
