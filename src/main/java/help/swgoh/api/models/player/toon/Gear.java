package help.swgoh.api.models.player.toon;

import java.math.BigInteger;
import java.util.Objects;

/**
 * This class represents a single piece of gear equipped to a {@link Toon}.
 *
 * @since 3.2.1
 * @author doenisf
 */
public class Gear {

    private String equipmentId;
    private BigInteger slot;
    private String nameKey;

    public String getEquipmentId() {
        return equipmentId;
    }

    public void setEquipmentId(String equipmentId) {
        this.equipmentId = equipmentId;
    }

    public BigInteger getSlot() {
        return slot;
    }

    public void setSlot(BigInteger slot) {
        this.slot = slot;
    }

    public String getNameKey() {
        return nameKey;
    }

    public void setNameKey(String nameKey) {
        this.nameKey = nameKey;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Gear gear = (Gear) o;
        return Objects.equals(equipmentId, gear.equipmentId) && Objects.equals(slot, gear.slot) && Objects.equals(nameKey, gear.nameKey);
    }

    @Override
    public int hashCode() {
        return Objects.hash(equipmentId, slot, nameKey);
    }

    @Override
    public String toString() {
        return "Gear{" +
                "equipmentId='" + equipmentId + '\'' +
                ", slot=" + slot +
                ", nameKey='" + nameKey + '\'' +
                '}';
    }
}
