package help.swgoh.api.models.player.toon.mod;

import java.util.Arrays;
import java.util.Objects;

/**
 * This class represents a {@link Mod}.
 *
 * @since 4.3.1
 * @author doenisf
 */
public class Mod {

    private String id;
    private int level;
    private int tier;
    private int slot;
    private int set;
    private int pips;
    private PrimaryStat primaryStat;
    private SecondaryStat[] secondaryStat;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getTier() {
        return tier;
    }

    public void setTier(int tier) {
        this.tier = tier;
    }

    public int getSlot() {
        return slot;
    }

    public void setSlot(int slot) {
        this.slot = slot;
    }

    public int getSet() {
        return set;
    }

    public void setSet(int set) {
        this.set = set;
    }

    public int getPips() {
        return pips;
    }

    public void setPips(int pips) {
        this.pips = pips;
    }

    public PrimaryStat getPrimaryStat() {
        return primaryStat;
    }

    public void setPrimaryStat(PrimaryStat primaryStat) {
        this.primaryStat = primaryStat;
    }

    public SecondaryStat[] getSecondaryStat() {
        return secondaryStat;
    }

    public void setSecondaryStat(SecondaryStat[] secondaryStat) {
        this.secondaryStat = secondaryStat;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Mod mod = (Mod) o;
        return level == mod.level && tier == mod.tier && slot == mod.slot && set == mod.set && pips == mod.pips && Objects.equals(id, mod.id) && Objects.equals(primaryStat, mod.primaryStat) && Arrays.equals(secondaryStat, mod.secondaryStat);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(id, level, tier, slot, set, pips, primaryStat);
        result = 31 * result + Arrays.hashCode(secondaryStat);
        return result;
    }

    @Override
    public String toString() {
        return "Mod{" +
                "id='" + id + '\'' +
                ", level=" + level +
                ", tier=" + tier +
                ", slot=" + slot +
                ", set=" + set +
                ", pips=" + pips +
                ", primaryStat=" + primaryStat +
                ", secondaryStat=" + Arrays.toString(secondaryStat) +
                '}';
    }
}
