package help.swgoh.api.models.player.toon.mod;

import com.google.gson.annotations.SerializedName;

/**
 * Enumeration to map the int values that describe the stat of a {@link Mod} to a {@code java.lang.String}.
 *
 * @since 4.3.1
 * @author doenisf
 */
public enum ModStat {


    @SerializedName("1")HEALTH(1, "Health"),
    @SerializedName("5")SPEED(5, "Speed"),
    @SerializedName("16")CRIT_DAMAGE(16, "Critical Damage"),
    @SerializedName("17")POTENCY(17, "Potency"),
    @SerializedName("18")TENACITY(18, "Tenacity"),
    @SerializedName("28")PROTECTION(28, "Protection"),
    @SerializedName("41")OFFENSE(41, "Offense"),
    @SerializedName("42")DEFENSE(42, "Defense"),
    @SerializedName("48")OFFENCE_PERCENT(48, "%-Offense"),
    @SerializedName("49")DEFENSE_PERCENT(49, "%-Defense"),
    @SerializedName("53")CRIT_CHANCE(53,"Critical Chance"),
    @SerializedName("54")CRIT_AVOIDANCE(54, "Critical Avoidance"),
    @SerializedName("55")HEALTH_PERCENT(55, "%-Health"),
    @SerializedName("56")PROTECTION_PERCENT(56, "%-Protection");


    private final int unitStat;
    private final String name;

    ModStat(int unitStat, String name) {
        this.unitStat = unitStat;
        this.name = name;
    }

    public int getUnitStat() {
        return unitStat;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "ModStat{" +
                "unitStat=" + unitStat +
                ", name='" + name + '\'' +
                '}';
    }
}
