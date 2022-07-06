package help.swgoh.api.models.player.toon.mod;

/**
 * Enumeration to map the int values that describe the set of a {@link Mod} to a {@code java.lang.String}.
 *
 * @since 4.3.1
 * @author doenisf
 */
public enum ModSet {

    HEALTH(1, "Health"),
    OFFENSE(2, "Offense"),
    DEFENSE(3, "Defense"),
    SPEED(4, "Speed"),
    CIRT_CHANCE(5, "Critical Chance"),
    CRIT_DAMAGE(6, "Critical Damage"),
    POTENCY(7, "Potency"),
    TENACITY(8, "Tenacity"),
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
