package help.swgoh.api.models.equipment;

import java.util.Arrays;
import java.util.Objects;

public class Equipment {

    private String id;
    private String nameKey;
    private String iconKey;
    private Integer requiredLevel;
    private EquipmentStats equipmentStat;
    private String recipeId;
    private Integer tier;
    private SellValue sellValue;
    private MissionLookup[] lookupMissionList;
    private String mark;
    private Long obtainableTime;
    private MissionLookup[] raidLookupList;
    private Integer type;
    private Integer requiredRarity;
    private boolean findFlowDisabled;
    private Long updated;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNameKey() {
        return nameKey;
    }

    public void setNameKey(String nameKey) {
        this.nameKey = nameKey;
    }

    public String getIconKey() {
        return iconKey;
    }

    public void setIconKey(String iconKey) {
        this.iconKey = iconKey;
    }

    public Integer getRequiredLevel() {
        return requiredLevel;
    }

    public void setRequiredLevel(Integer requiredLevel) {
        this.requiredLevel = requiredLevel;
    }

    public EquipmentStats getEquipmentStat() {
        return equipmentStat;
    }

    public void setEquipmentStat(EquipmentStats equipmentStat) {
        this.equipmentStat = equipmentStat;
    }

    public String getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(String recipeId) {
        this.recipeId = recipeId;
    }

    public Integer getTier() {
        return tier;
    }

    public void setTier(Integer tier) {
        this.tier = tier;
    }

    public SellValue getSellValue() {
        return sellValue;
    }

    public void setSellValue(SellValue sellValue) {
        this.sellValue = sellValue;
    }

    public MissionLookup[] getLookupMissionList() {
        return lookupMissionList;
    }

    public void setLookupMissionList(MissionLookup[] lookupMissionList) {
        this.lookupMissionList = lookupMissionList;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public Long getObtainableTime() {
        return obtainableTime;
    }

    public void setObtainableTime(Long obtainableTime) {
        this.obtainableTime = obtainableTime;
    }

    public MissionLookup[] getRaidLookupList() {
        return raidLookupList;
    }

    public void setRaidLookupList(MissionLookup[] raidLookupList) {
        this.raidLookupList = raidLookupList;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getRequiredRarity() {
        return requiredRarity;
    }

    public void setRequiredRarity(Integer requiredRarity) {
        this.requiredRarity = requiredRarity;
    }

    public boolean isFindFlowDisabled() {
        return findFlowDisabled;
    }

    public void setFindFlowDisabled(boolean findFlowDisabled) {
        this.findFlowDisabled = findFlowDisabled;
    }

    public Long getUpdated() {
        return updated;
    }

    public void setUpdated(Long updated) {
        this.updated = updated;
    }

    @Override
    public String toString() {
        return "Equipment{" +
                "id='" + id + '\'' +
                ", nameKey='" + nameKey + '\'' +
                ", iconKey='" + iconKey + '\'' +
                ", requiredLevel=" + requiredLevel +
                ", equipmentStat=" + equipmentStat +
                ", recipeId='" + recipeId + '\'' +
                ", tier=" + tier +
                ", sellValue=" + sellValue +
                ", lookupMissionList=" + Arrays.toString(lookupMissionList) +
                ", mark='" + mark + '\'' +
                ", obtainableTime=" + obtainableTime +
                ", raidLookupList=" + Arrays.toString(raidLookupList) +
                ", type=" + type +
                ", requiredRarity=" + requiredRarity +
                ", findFlowDisabled=" + findFlowDisabled +
                ", updated=" + updated +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Equipment equipment = (Equipment) o;
        return findFlowDisabled == equipment.findFlowDisabled && Objects.equals(id, equipment.id) && Objects.equals(nameKey, equipment.nameKey) && Objects.equals(iconKey, equipment.iconKey) && Objects.equals(requiredLevel, equipment.requiredLevel) && Objects.equals(equipmentStat, equipment.equipmentStat) && Objects.equals(recipeId, equipment.recipeId) && Objects.equals(tier, equipment.tier) && Objects.equals(sellValue, equipment.sellValue) && Arrays.equals(lookupMissionList, equipment.lookupMissionList) && Objects.equals(mark, equipment.mark) && Objects.equals(obtainableTime, equipment.obtainableTime) && Arrays.equals(raidLookupList, equipment.raidLookupList) && Objects.equals(type, equipment.type) && Objects.equals(requiredRarity, equipment.requiredRarity) && Objects.equals(updated, equipment.updated);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(id, nameKey, iconKey, requiredLevel, equipmentStat, recipeId, tier, sellValue, mark, obtainableTime, type, requiredRarity, findFlowDisabled, updated);
        result = 31 * result + Arrays.hashCode(lookupMissionList);
        result = 31 * result + Arrays.hashCode(raidLookupList);
        return result;
    }
}
