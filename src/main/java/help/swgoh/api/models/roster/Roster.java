package help.swgoh.api.models.roster;

/**
 * This class represents the response to the getRoster(int allyCode) method.
 *
 * @since 4.3.1
 * @author doenisf
 */
public class Roster {

    private RosterToon[] toons;

    public RosterToon[] getToons() {
        return toons;
    }

    public void setToons(RosterToon[] toons) {
        this.toons = toons;
    }
}
