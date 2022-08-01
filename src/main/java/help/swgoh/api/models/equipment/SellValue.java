package help.swgoh.api.models.equipment;

import java.util.Objects;

/**
 * Shows the value of an {@link Equipment} when selling it.
 *
 * @since 4.3.1
 * @author doenisf
 */
public class SellValue {

    private Integer currency;
    private Integer quantity;
    private Integer bonusQuantity;

    public Integer getCurrency() {
        return currency;
    }

    public void setCurrency(Integer currency) {
        this.currency = currency;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getBonusQuantity() {
        return bonusQuantity;
    }

    public void setBonusQuantity(Integer bonusQuantity) {
        this.bonusQuantity = bonusQuantity;
    }

    @Override
    public String toString() {
        return "SellValue{" +
                "currency=" + currency +
                ", quantity=" + quantity +
                ", bonusQuantity=" + bonusQuantity +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SellValue sellValue = (SellValue) o;
        return Objects.equals(currency, sellValue.currency) && Objects.equals(quantity, sellValue.quantity) && Objects.equals(bonusQuantity, sellValue.bonusQuantity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(currency, quantity, bonusQuantity);
    }
}
