package help.swgoh.api;

import help.swgoh.api.response.Event;
import help.swgoh.api.response.Player;
import help.swgoh.api.response.TB;
import help.swgoh.api.response.Unit;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Interface that exposes the public methods of {@link SwgohAPIConnector}.
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
     * @return {@link Player}
     * @throws IOException if http connection fails.
     * @see #getPlayer(int, SwgohAPIConnector.Language)
     * @see #getPlayerJSON(int)
     * @see #getPlayerJSON(int, SwgohAPIConnector.Language)
     * @see #getGuild(int)
     */
    Player getPlayer( int allyCode ) throws IOException;

    /**
     * Makes an http connection to the swgoh.help API for a player object in the specified language.
     *
     * For pulling all the members of a guild, use {@link #getGuild(int, SwgohAPIConnector.Language)}
     *
     * @param allyCode  The number that uniquely identifies a player, found in the player's game profile.
     * @param language  The {@link SwgohAPIConnector.Language} that the result should be displayed in.
     * @return {@link Player}
     * @throws IOException if http connection fails.
     * @see #getPlayer(int)
     * @see #getPlayerJSON(int)
     * @see #getPlayerJSON(int, SwgohAPIConnector.Language)
     * @see #getGuild(int, SwgohAPIConnector.Language)
     */
    Player getPlayer( int allyCode, SwgohAPIConnector.Language language ) throws IOException;

    /**
     * Makes an http connection to the swgoh.help API for a player JSON in the default language.
     *
     * For pulling all the members of a guild, use {@link #getGuildJSON(int)}
     *
     * @param allyCode  The number that uniquely identifies a player, found in the player's game profile.
     * @return JSON string
     * @throws IOException if http connection fails.
     * @see #getPlayer(int)
     * @see #getPlayer(int, SwgohAPIConnector.Language)
     * @see #getPlayerJSON(int, SwgohAPIConnector.Language)
     * @see #getGuildJSON(int)
     */
    String getPlayerJSON( int allyCode ) throws IOException;

    /**
     * Makes an http connection to the swgoh.help API for a player JSON in the specified language.
     *
     * For pulling all the members of a guild, use {@link #getGuildJSON(int, SwgohAPIConnector.Language)}
     *
     * @param allyCode  The number that uniquely identifies a player, found in the player's game profile.
     * @param language  The {@link SwgohAPIConnector.Language} that the result should be displayed in.
     * @return JSON string
     * @throws IOException if http connection fails.
     * @see #getPlayer(int)
     * @see #getPlayer(int, SwgohAPIConnector.Language)
     * @see #getPlayerJSON(int)
     * @see #getGuildJSON(int, SwgohAPIConnector.Language)
     */
    String getPlayerJSON( int allyCode, SwgohAPIConnector.Language language ) throws IOException;

    /**
     * Makes an http connection to the swgoh.help API for a list of player objects in the default language.
     *
     * The list of player objects that is returned belongs to the guild of the player that is identified by the given
     * allyCode.
     *
     * @param allyCode  The number that uniquely identifies a player, found in the player's game profile.
     * @return List of {@link Player}
     * @throws IOException if http connection fails.
     * @see #getGuild(int, SwgohAPIConnector.Language)
     * @see #getGuildJSON(int)
     * @see #getGuildJSON(int, SwgohAPIConnector.Language)
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
     * @param language  The {@link SwgohAPIConnector.Language} that the result should be displayed in.
     * @return List of {@link Player}
     * @throws IOException if http connection fails.
     * @see #getGuild(int)
     * @see #getGuildJSON(int)
     * @see #getGuildJSON(int, SwgohAPIConnector.Language)
     * @see #getPlayer(int, SwgohAPIConnector.Language)
     */
    List<Player> getGuild( int allyCode, SwgohAPIConnector.Language language ) throws IOException;

    /**
     * Makes an http connection to the swgoh.help API for a JSON string of player objects in the default language.
     *
     * The JSON string of player objects that is returned belongs to the guild of the player that is identified by the
     * given allyCode.
     *
     * @param allyCode  The number that uniquely identifies a player, found in the player's game profile.
     * @return JSON string
     * @throws IOException if http connection fails.
     * @see #getGuild(int)
     * @see #getGuild(int, SwgohAPIConnector.Language)
     * @see #getGuildJSON(int, SwgohAPIConnector.Language)
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
     * @param language  The {@link SwgohAPIConnector.Language} that the result should be displayed in.
     * @return JSON string
     * @throws IOException if http connection fails.
     * @see #getGuild(int)
     * @see #getGuild(int, SwgohAPIConnector.Language)
     * @see #getGuildJSON(int)
     * @see #getPlayerJSON(int, SwgohAPIConnector.Language)
     */
    String getGuildJSON( int allyCode, SwgohAPIConnector.Language language ) throws IOException;

    /**
     * Makes an http connection to the swgoh.help API for a list of game events in the default language.
     *
     * @return List of {@link Event}
     * @throws IOException if http connection fails.
     * @see #getEvents(SwgohAPIConnector.Language)
     * @see #getEventsJSON()
     * @see #getEventsJSON(SwgohAPIConnector.Language)
     */
    List<Event> getEvents() throws IOException;

    /**
     * Makes an http connection to the swgoh.help API for a list of game events in the specified language.
     *
     * @param language  The {@link SwgohAPIConnector.Language} that the result should be displayed in.
     * @return List of {@link Event}
     * @throws IOException if http connection fails.
     * @see #getEvents()
     * @see #getEventsJSON()
     * @see #getEventsJSON(SwgohAPIConnector.Language)
     */
    List<Event> getEvents( SwgohAPIConnector.Language language ) throws IOException;

    /**
     * Makes an http connection to the swgoh.help API for a JSON string of game events in the default language.
     *
     * @return JSON string
     * @throws IOException if http connection fails.
     * @see #getEvents()
     * @see #getEvents(SwgohAPIConnector.Language)
     * @see #getEventsJSON(SwgohAPIConnector.Language)
     */
    String getEventsJSON() throws IOException;

    /**
     * Makes an http connection to the swgoh.help API for a JSON string of game events in the specified language.
     *
     * @param language  The {@link SwgohAPIConnector.Language} that the result should be displayed in.
     * @return JSON string
     * @throws IOException if http connection fails.
     * @see #getEvents()
     * @see #getEvents(SwgohAPIConnector.Language)
     * @see #getEventsJSON()
     */
    String getEventsJSON( SwgohAPIConnector.Language language ) throws IOException;

    /**
     * Makes an http connection to the swgoh.help API for a map of game units in the default language.
     *
     * In the {@link Map<String,Unit>} response, the key is the unit's ID and the value is the rest of the information
     * for the unit.
     *
     * @return JSON string
     * @throws IOException if http connection fails.
     * @see #getUnits(SwgohAPIConnector.Language)
     * @see #getUnitsJSON()
     * @see #getUnitsJSON(SwgohAPIConnector.Language)
     */
    Map<String, Unit> getUnits() throws IOException;

    /**
     * Makes an http connection to the swgoh.help API for a list of game units in the specified language.
     *
     * In the {@link Map<String,Unit>} response, the key is the unit's ID and the value is the rest of the information
     * for the unit.
     *
     * @param language  The {@link SwgohAPIConnector.Language} that the result should be displayed in.
     * @return JSON string
     * @throws IOException if http connection fails.
     * @see #getUnits()
     * @see #getUnitsJSON()
     * @see #getUnitsJSON(SwgohAPIConnector.Language)
     */
    Map<String, Unit> getUnits( SwgohAPIConnector.Language language ) throws IOException;

    /**
     * Makes an http connection to the swgoh.help API for a JSON string of game units in the default language.
     *
     * @return JSON string
     * @throws IOException if http connection fails.
     * @see #getUnits()
     * @see #getUnits(SwgohAPIConnector.Language)
     * @see #getUnitsJSON(SwgohAPIConnector.Language)
     */
    String getUnitsJSON() throws IOException;

    /**
     * Makes an http connection to the swgoh.help API for a JSON string of game units in the specified language.
     *
     * @param language  The {@link SwgohAPIConnector.Language} that the result should be displayed in.
     * @return JSON string
     * @throws IOException if http connection fails.
     * @see #getUnits()
     * @see #getUnits(SwgohAPIConnector.Language)
     * @see #getUnitsJSON()
     */
    String getUnitsJSON( SwgohAPIConnector.Language language ) throws IOException;

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
     * @return List of {@link TB}
     * @throws IOException if http connection fails.
     * @see #getTB(SwgohAPIConnector.Language)
     */
    List<TB> getTB() throws IOException;

    /**
     * Makes an http connection to the swgoh.help API for a list of Territory Battles in the specified language.
     *
     * @param language  The {@link SwgohAPIConnector.Language} that the result should be displayed in.
     * @return List of {@link TB}
     * @throws IOException if http connection fails.
     * @see #getTB()
     */
    List<TB> getTB( SwgohAPIConnector.Language language ) throws IOException;

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
