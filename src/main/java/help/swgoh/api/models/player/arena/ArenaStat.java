package help.swgoh.api.models.player.arena;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Objects;

/**
 * This class represents a {@link help.swgoh.api.models.player.Player}s current rank and squad/fleet
 * in the arena/fleet arena.
 *
 * @since 4.3.1
 * @author doenisf
 */
public class ArenaStat {

    private BigInteger rank;
    private ArenaToon[] squad;

    public BigInteger getRank() {
        return rank;
    }

    public void setRank(BigInteger rank) {
        this.rank = rank;
    }

    public ArenaToon[] getSquad() {
        return squad;
    }

    public void setSquad(ArenaToon[] squad) {
        this.squad = squad;
    }

    @Override
    public String toString() {
        return "ArenaStat{" +
                "rank=" + rank +
                ", squad=" + Arrays.toString(squad) +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArenaStat arenaStat = (ArenaStat) o;
        return Objects.equals(rank, arenaStat.rank) && Arrays.equals(squad, arenaStat.squad);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(rank);
        result = 31 * result + Arrays.hashCode(squad);
        return result;
    }
}
