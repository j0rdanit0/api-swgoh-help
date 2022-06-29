package help.swgoh.api.models.roster;

import java.util.Arrays;
import java.util.Objects;

/**
 * Represents a mod of a {@link RosterToon}.
 *
 * @since 1.0.0
 * @author doenisf
 */
public class RosterMod {

    private String id;
    private int set;
    private int level;
    private int pips;
    private int tier;
    private float[][] stat;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getSet() {
        return set;
    }

    public void setSet(int set) {
        this.set = set;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getPips() {
        return pips;
    }

    public void setPips(int pips) {
        this.pips = pips;
    }

    public int getTier() {
        return tier;
    }

    public void setTier(int tier) {
        this.tier = tier;
    }

    public float[][] getStat() {
        return stat;
    }

    public void setStat(float[][] stat) {
        this.stat = stat;
    }

    @Override
    public String toString() {
        return "RosterMod{" +
                "id='" + id + '\'' +
                ", set=" + set +
                ", level=" + level +
                ", pips=" + pips +
                ", tier=" + tier +
                ", stat=" + Arrays.deepToString(stat) +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RosterMod rosterMod = (RosterMod) o;
        return set == rosterMod.set && level == rosterMod.level && pips == rosterMod.pips && tier == rosterMod.tier && Objects.equals(id, rosterMod.id) && Arrays.deepEquals(stat, rosterMod.stat);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(id, set, level, pips, tier);
        result = 31 * result + Arrays.deepHashCode(stat);
        return result;
    }
}
