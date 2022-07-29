package help.swgoh.api.models.player.toon.mod;

import com.google.gson.annotations.SerializedName;

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
    private Tier tier;
    private ModSlot slot;
    private ModSet set;
    private int pips;
    private ModStat primaryStat;
    private ModStat[] secondaryStat;

    private enum Tier {

        @SerializedName("1")GREY(1,"Grey",'E'),
        @SerializedName("2")GREEN(2,"Green",'D'),
        @SerializedName("3")BLUE(3,"Blue",'C'),
        @SerializedName("4")PURPLE(4,"Purple",'B'),
        @SerializedName("5")GOLD(5,"Gold",'A')
                ;

        private final int tier;
        private final String color;
        private final char grade;

        Tier(int tier, String color, char grade) {
            this.tier = tier;
            this.color = color;
            this.grade = grade;
        }

        public int getTier() {
            return tier;
        }

        public String getColor() {
            return color;
        }

        public char getGrade() {
            return grade;
        }

        @Override
        public String toString() {
            return "Tier{" +
                    "tier=" + tier +
                    ", color='" + color + '\'' +
                    ", grade=" + grade +
                    '}';
        }
    }

    private enum ModSet {

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

    private enum ModSlot {

        @SerializedName("1")SENDER(1, "Sender", "Square"),
        @SerializedName("2")REVIEVER(2,"Reciever", "Arrow"),
        @SerializedName("3")PROCESSOR(3, "Processor", "Diamond"),
        @SerializedName("4")HOLO_ARRAY(4, "Holo array", "Triangle"),
        @SerializedName("5")DATA_BUS(5, "Data bus" , "Circle"),
        @SerializedName("6")MULTIPLEXER(6, "Multiplexer", "Cross");

        private final int slot;
        private final String name;
        private final String simpleName;

        ModSlot(int slot, String name, String simpleName) {
            this.slot = slot;
            this.name = name;
            this.simpleName = simpleName;
        }

        public int getSlot() {
            return slot;
        }

        public String getName() {
            return name;
        }

        public String getSimpleName() {
            return simpleName;
        }

        @Override
        public String toString() {
            return "ModSlot{" +
                    "slot=" + slot +
                    ", name='" + name + '\'' +
                    ", simpleName='" + simpleName + '\'' +
                    '}';
        }
    }


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

    public Tier getTier() {
        return tier;
    }

    public void setTier(Tier tier) {
        this.tier = tier;
    }

    public ModSlot getSlot() {
        return slot;
    }

    public void setSlot(ModSlot slot) {
        this.slot = slot;
    }

    public ModSet getSet() {
        return set;
    }

    public void setSet(ModSet set) {
        this.set = set;
    }

    public int getPips() {
        return pips;
    }

    public void setPips(int pips) {
        this.pips = pips;
    }

    public ModStat getPrimaryStat() {
        return primaryStat;
    }

    public void setPrimaryStat(ModStat primaryStat) {
        this.primaryStat = primaryStat;
    }

    public ModStat[] getSecondaryStat() {
        return secondaryStat;
    }

    public void setSecondaryStat(ModStat[] secondaryStat) {
        this.secondaryStat = secondaryStat;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Mod mod = (Mod) o;
        return level == mod.level && pips == mod.pips && Objects.equals(id, mod.id) && tier == mod.tier && slot == mod.slot && set == mod.set && Objects.equals(primaryStat, mod.primaryStat) && Arrays.equals(secondaryStat, mod.secondaryStat);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(id, level, tier, slot, set, pips, primaryStat);
        result = 31 * result + Arrays.hashCode(secondaryStat);
        return result;
    }
}
