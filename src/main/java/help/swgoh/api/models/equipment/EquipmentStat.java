package help.swgoh.api.models.equipment;

import help.swgoh.api.models.enums.UnitStat;

import java.math.BigDecimal;
import java.util.Objects;

public class EquipmentStat {

    private UnitStat unitStatId;
    private BigDecimal statDecimalValue;
    private BigDecimal unscaledDecimalValue;
    private Integer uiDisplayOverrideValue;
    private Integer scalar;

    public UnitStat getUnitStatId() {
        return unitStatId;
    }

    public void setUnitStatId(UnitStat unitStatId) {
        this.unitStatId = unitStatId;
    }

    public BigDecimal getStatDecimalValue() {
        return statDecimalValue;
    }

    public void setStatDecimalValue(BigDecimal statDecimalValue) {
        this.statDecimalValue = statDecimalValue;
    }

    public BigDecimal getUnscaledDecimalValue() {
        return unscaledDecimalValue;
    }

    public void setUnscaledDecimalValue(BigDecimal unscaledDecimalValue) {
        this.unscaledDecimalValue = unscaledDecimalValue;
    }

    public Integer getUiDisplayOverrideValue() {
        return uiDisplayOverrideValue;
    }

    public void setUiDisplayOverrideValue(Integer uiDisplayOverrideValue) {
        this.uiDisplayOverrideValue = uiDisplayOverrideValue;
    }

    public Integer getScalar() {
        return scalar;
    }

    public void setScalar(Integer scalar) {
        this.scalar = scalar;
    }

    @Override
    public String toString() {
        return "EquipmentStat{" +
                "unitStatId=" + unitStatId +
                ", statDecimalValue=" + statDecimalValue +
                ", unscaledDecimalValue=" + unscaledDecimalValue +
                ", uiDisplayOverrideValue=" + uiDisplayOverrideValue +
                ", scalar=" + scalar +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EquipmentStat that = (EquipmentStat) o;
        return unitStatId == that.unitStatId && Objects.equals(statDecimalValue, that.statDecimalValue) && Objects.equals(unscaledDecimalValue, that.unscaledDecimalValue) && Objects.equals(uiDisplayOverrideValue, that.uiDisplayOverrideValue) && Objects.equals(scalar, that.scalar);
    }

    @Override
    public int hashCode() {
        return Objects.hash(unitStatId, statDecimalValue, unscaledDecimalValue, uiDisplayOverrideValue, scalar);
    }
}
