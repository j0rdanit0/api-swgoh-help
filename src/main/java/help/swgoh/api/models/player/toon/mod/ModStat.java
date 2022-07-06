package help.swgoh.api.models.player.toon.mod;

/**
 * Enumeration to map the int values that describe the stat of a {@link Mod} to a {@code java.lang.String}.
 *
 * @since 4.3.1
 * @author doenisf
 */
public enum ModStat {

    HEALTH(1, "Health"),
    SPEED(5, "Speed"),
    CRIT_DAMAGE(16, "Critical Damage"),
    POTENCY(17, "Potency"),
    TENACITY(18, "Tenacity"),
    PROTECTION(28, "Protection"),
    OFFENSE(41, "Offense"),
    DEFENSE(42, "Defense"),
    OFFENCE_PERCENT(48, "%-Offense"),
    DEFENSE_PERCENT(49, "%-Defense"),
    CRIT_CHANCE(53,"Critical Chance"),
    CRIT_AVOIDANCE(54, "Critical Avoidance"),
    HEALTH_PERCENT(55, "%-Health"),
    PROTECTION_PERCENT(56, "%-Protection");


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
