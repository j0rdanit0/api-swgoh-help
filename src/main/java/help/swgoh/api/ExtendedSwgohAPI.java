package help.swgoh.api;

import help.swgoh.api.models.event.Events;
import help.swgoh.api.models.guild.Guild;
import help.swgoh.api.models.player.Player;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Interface that exposes the public methods of {@link ExtendedSwgohAPIClient}.
 *
 * An extention to the {@link SwgohAPI}. Offers the same functionality but adds methods that return actual objects instead
 * of just raw JSON as a {@code java.lang.String}.
 *
 * @since 4.3.1
 * @author doenisf
 */
public interface ExtendedSwgohAPI extends SwgohAPI {

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
    /*
    List<Roster> getFullRosters( List<Integer> allyCodes, Language language, SwgohAPIFilter filter ) throws ExecutionException, InterruptedException;
    default List<Roster> getFullRosters( List<Integer> allyCodes, Language language ) throws ExecutionException, InterruptedException {
        return getFullRosters( allyCodes, language, SwgohAPIFilter.ALL );
    }
    default List<Roster> getFullRosters( List<Integer> allyCodes, SwgohAPIFilter filter ) throws ExecutionException, InterruptedException {
        return getFullRosters( allyCodes, null, filter );
    }
    default List<Roster> getFullRosters( List<Integer> allyCodes ) throws ExecutionException, InterruptedException {
        return getFullRosters( allyCodes, null, SwgohAPIFilter.ALL );
    }
    default Roster getFullRoster(int allyCode, Language language, SwgohAPIFilter filter ) throws ExecutionException, InterruptedException {
        return getFullRosters( Collections.singletonList( allyCode ), language, filter ).get(0);
    }
    default Roster getFullRoster( int allyCode, Language language ) throws ExecutionException, InterruptedException {
        return getFullRoster( allyCode, language, SwgohAPIFilter.ALL );
    }
    default Roster getFullRoster( int allyCode, SwgohAPIFilter filter ) throws ExecutionException, InterruptedException {
        return getFullRoster( allyCode, null, filter );
    }
    default Roster getFullRoster( int allyCode ) throws ExecutionException, InterruptedException {
        return getFullRoster( allyCode, null, SwgohAPIFilter.ALL );
    }

     */

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
    Events getFullEvents( Language language, SwgohAPIFilter filter ) throws ExecutionException, InterruptedException;
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
