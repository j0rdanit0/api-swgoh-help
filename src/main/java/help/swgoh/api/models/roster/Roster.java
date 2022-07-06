package help.swgoh.api.models.roster;

import java.util.Arrays;

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

    @Override
    public String toString() {
        return "Roster{" +
                "toons=" + Arrays.toString(toons) +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Roster roster = (Roster) o;
        return Arrays.equals(toons, roster.toons);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(toons);
    }
}
