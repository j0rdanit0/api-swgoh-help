package help.swgoh.api.models.player.toon.mod;

import com.google.gson.annotations.SerializedName;

/**
 * Enumeration to map the int values that describe the set of a {@link Mod} to a {@code java.lang.String}.
 *
 * @since 4.3.1
 * @author doenisf
 */
public enum ModSet {

    @SerializedName("1")HEALTH(1, "Health"),
    @SerializedName("2")OFFENSE(2, "Offense"),
    @SerializedName("3")DEFENSE(3, "Defense"),
    @SerializedName("4")SPEED(4, "Speed"),
    @SerializedName("5")CIRT_CHANCE(5, "Critical Chance"),
    @SerializedName("6")CRIT_DAMAGE(6, "Critical Damage"),
    @SerializedName("7")POTENCY(7, "Potency"),
    @SerializedName("8")TENACITY(8, "Tenacity"),
    ;

    private final int set;
    private final String name;

    ModSet(int set, String name) {
        this.set = set;
        this.name = name;
    }

    public int getSet() {
        return set;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "ModSet{" +
                "set=" + set +
                ", name='" + name + '\'' +
                '}';
    }
}
