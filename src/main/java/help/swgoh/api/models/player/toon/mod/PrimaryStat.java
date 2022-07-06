package help.swgoh.api.models.player.toon.mod;

import java.util.Objects;

/**
 * This class represents a primary stat of a {@link Mod}.
 *
 * @since 4.3.1
 * @author doenisf
 */
public class PrimaryStat {

    private ModStat unitStat;
    private float value;

    public ModStat getUnitStat() {
        return unitStat;
    }

    public void setUnitStat(ModStat unitStat) {
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
        return Float.compare(that.value, value) == 0 && unitStat == that.unitStat;
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
