package help.swgoh.api.models.player.toon.mod;

import java.util.Objects;

/**
 * This class represents a secondary stat of a {@link Mod}.
 *
 * @since 1.0.0
 * @author doenisf
 */
public class SecondaryStat {

    private int unitStat;
    private float value;
    private int roll;

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

    public int getRoll() {
        return roll;
    }

    public void setRoll(int roll) {
        this.roll = roll;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SecondaryStat that = (SecondaryStat) o;
        return unitStat == that.unitStat && Float.compare(that.value, value) == 0 && roll == that.roll;
    }

    @Override
    public int hashCode() {
        return Objects.hash(unitStat, value, roll);
    }

    @Override
    public String toString() {
        return "SecondaryStat{" +
                "unitStat=" + unitStat +
                ", value=" + value +
                ", roll=" + roll +
                '}';
    }
}
