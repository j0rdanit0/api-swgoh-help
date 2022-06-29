package help.swgoh.api.models.player.toon.mod;

import java.util.Objects;

/**
 * This class represents a primary stat of a {@link Mod}.
 *
 * @since 1.0.0
 * @author doenisf
 */
public class PrimaryStat {

    private int unitStat;
    private float value;

    public int getUnitStat() {
        return unitStat;
    }

    public void setUnitStat(int unitStat) {
        this.unitStat = unitStat;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PrimaryStat that = (PrimaryStat) o;
        return unitStat == that.unitStat && Float.compare(that.value, value) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(unitStat, value);
    }

    @Override
    public String toString() {
        return "PrimaryStat{" +
                "unitStat=" + unitStat +
                ", value=" + value +
                '}';
    }
}
