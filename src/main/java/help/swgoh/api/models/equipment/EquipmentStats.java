package help.swgoh.api.models.equipment;

import java.util.Arrays;

public class EquipmentStats {

    private EquipmentStat[] statList;

    public EquipmentStat[] getStatList() {
        return statList;
    }

    public void setStatList(EquipmentStat[] statList) {
        this.statList = statList;
    }

    @Override
    public String toString() {
        return "EquipmentStats{" +
                "statList=" + Arrays.toString(statList) +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EquipmentStats that = (EquipmentStats) o;
        return Arrays.equals(statList, that.statList);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(statList);
    }
}
