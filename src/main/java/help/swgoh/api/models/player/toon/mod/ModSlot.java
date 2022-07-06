package help.swgoh.api.models.player.toon.mod;

import com.google.gson.annotations.SerializedName;

/**
 * Enumeration to map the int values that describe the slot of a {@link Mod} to a {@code java.lang.String}.
 *
 * @since 4.3.1
 * @author doenisf
 */
public enum ModSlot {

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
