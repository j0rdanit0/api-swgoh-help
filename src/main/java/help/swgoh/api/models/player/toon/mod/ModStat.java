package help.swgoh.api.models.player.toon.mod;

import help.swgoh.api.models.enums.UnitStat;

import java.util.Objects;

/**
 * This class represents one stat of a {@link Mod}.
 *
 * @since 4.3.1
 * @author doenisf
 */
public class ModStat {

    private UnitStat unitStat;
    private float value;
    private int roll;

    public UnitStat getUnitStat() {
        return unitStat;
    }

    public void setUnitStat(UnitStat unitStat) {
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
    public String toString() {
        return "ModStat{" +
                "unitStat=" + unitStat +
                ", value=" + value +
                ", roll=" + roll +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ModStat that = (ModStat) o;
        return Float.compare(that.value, value) == 0 && roll == that.roll && unitStat == that.unitStat;
    }

    @Override
    public int hashCode() {
        return Objects.hash(unitStat, value, roll);
    }
}
