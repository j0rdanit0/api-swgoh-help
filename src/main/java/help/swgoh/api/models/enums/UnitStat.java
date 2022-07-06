package help.swgoh.api.models.enums;

import com.google.gson.annotations.SerializedName;

/**
 * Enumeration to map the {@code int} unitStat values of the responses to a more readable {@code java.lang.String}.
 *
 * @see <a href="http://api.swgoh.gg/stat-definitions/">Mapping list of the API</a>
 * @since 4.3.1
 * @author doenisf
 */
public enum UnitStat {


    @SerializedName("0")NONE(0, ""),
    @SerializedName("1")HEALTH(1, "Max Health"),
    @SerializedName("2")STRENGTH(2, "Strength"),
    @SerializedName("3")AGILITY(3, "Agility"),
    @SerializedName("4")TACTICS(4, "Tactics"),
    @SerializedName("5")SPEED(5, "Speed"),
    @SerializedName("6")PHYSICAL_DAMAGE(6, "Physical Damage"),
    @SerializedName("7")SPECIAL_DAMAGE(7, "Special Damage"),
    @SerializedName("8")ARMOR(8, "Armor"),
    @SerializedName("9")RESISTANCE(9, "Resistance"),
    @SerializedName("10")ARMOR_PENETRATION(10, "Armor Penetration"),
    @SerializedName("11")RESISTANCE_PENETRATION(10, "Resistancec Penetration"),
    @SerializedName("12")DODGE_CHANCE(12, "Dodge Chance"),
    @SerializedName("13")DEFLECTION_CHANCEC(13,"Deflection Chance"),
    @SerializedName("14")PHYSICAL_CRIT_CHANCE(14,"Physical Critical Chance"),
    @SerializedName("15")SPECIAL_CRIT_CHANCE(15,"Special Critical Chance"),
    @SerializedName("16")CRIT_DAMAGE(16, "Critical Damage"),
    @SerializedName("17")POTENCY(17, "Potency"),
    @SerializedName("18")TENACITY(18, "Tenacity"),
    @SerializedName("19")DODGE(19,"Dodge Percent Additive"),
    @SerializedName("20")DEFLECTION(20,"Deflection Percent Additive"),
    @SerializedName("21")PHYSICAL_CRIT_CHANCE_ADD(21,"Physical Critical Percent Additive"),
    @SerializedName("22")SPECIAL_CRIT_CHANCE_ADD(22,"Special Critical Percent Additive"),
    @SerializedName("23")ARMOR_ADD(23,"Armor Percent Additive"),
    @SerializedName("24")RESISTANCE_ADD(24,"Resistance Percent Additive"),
    @SerializedName("25")ARMOR_PENETRATION_ADD(25,"Armor Penetration Percent Additive"),
    @SerializedName("26")RESISTANCE_PENETRATION_ADD(26,"Resistance Penetration Percent Additive"),
    @SerializedName("27")HEALTH_STEAL(27,"Health Steal"),
    @SerializedName("28")PROTECTION(28, "Protection"),
    @SerializedName("29")PROTECTION_IGNORE(29,"Protection Ignore"),
    @SerializedName("30")HEALTH_REGEN(30,"Health Regen"),
    @SerializedName("31")PHYSICAL_DAMAGE_ADD(31,"Physical Damage Percent Additive"),
    @SerializedName("32")SPECIAL_DAMAGE_ADD(32,"Special Damage Percent Additive"),
    @SerializedName("33")PHYSICAL_ACCURACY_ADD(33,"Physical Accuracy Percent Additive"),
    @SerializedName("34")SPECIAL_ACCURACY_ADD(34,"Special Accuracy Percent Additive"),
    @SerializedName("35")PHYSICAL_CRIT_ACCURACY_ADD(35,"Physical Critical Accuracy Percent Additive"),
    @SerializedName("36")SPECIAL_CRIT_ACCURACY_ADD(36,"Special Critical Accuracy Percent Additive"),
    @SerializedName("37")PHYSICAL_ACCURACY(37,"Physical Accuracy"),
    @SerializedName("38")SPECIAL_ACCURACY(38,"Special Accuracy"),
    @SerializedName("39")PHYSICAL_CRIT_AVOIDANCE(39,"Physical Critical Avoidance"),
    @SerializedName("40")SPECIAL_CRIT_AVOIDANCE(40,"Special Critical Avoidance"),
    @SerializedName("41")OFFENSE(41, "Offense"),
    @SerializedName("42")DEFENSE(42, "Defense"),
    @SerializedName("43")DEFENSE_PENETRATION(43,"Defense Penetration"),
    @SerializedName("44")EVASION(44,"Evasion"),
    @SerializedName("45")CRIT_CHACNE(45,"Critical Chance"),
    @SerializedName("46")ACCURACY(46,"Accuracy"),
    @SerializedName("47")CRIT_AVOIDANCE(47,"Critical Avoidance"),
    @SerializedName("48")OFFENCE_ADD(48, "Offense Percent Additive"),
    @SerializedName("49")DEFENSE_ADD(49, "Defense Percent Additive"),
    @SerializedName("50")DEFENSE_PENETRATION_ADD(50,"Defense Penetration Percent Additive"),
    @SerializedName("51")EVASION_ADD(51,"Evasion Percent Additive"),
    @SerializedName("52")ACCURACY_ADDD(52,"Accuracy Percent Additive"),
    @SerializedName("53")CRIT_CHANCE_ADD(53,"Critical Chance Percent Additive"),
    @SerializedName("54")CRIT_AVOIDANCE_ADD(54, "Critical Avoidance Percent Additive"),
    @SerializedName("55")HEALTH_ADD(55, "Health Percent Additive"),
    @SerializedName("56")PROTECTION_ADD(56, "Protection Percent Additive"),
    @SerializedName("57")SPEED_ADD(57,"Speed Percent Additive"),
    @SerializedName("58")COUNTER_ATTACK(58,"Counter Attack"),
    @SerializedName("59")TAUNT(59,"Taunt"),
    @SerializedName("60")TARGET_DEFENSE_PENETRATION(60,"Target Defense Penetration"),
    @SerializedName("61")MASTERY(61,"Mastery")
    ;


    private final int unitStat;
    private final String name;

    UnitStat(int unitStat, String name) {
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
