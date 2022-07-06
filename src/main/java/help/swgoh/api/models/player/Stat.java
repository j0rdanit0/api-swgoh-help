package help.swgoh.api.models.player;

import java.math.BigInteger;
import java.util.Objects;

/**
 * This class represents a stat associated with a {@link Player}.
 *
 * Examples for a stat are the Galactic Power, the best rank in a season and many others.
 *
 * @since 4.3.1
 * @author doenisf
 */
public class Stat {

    private String nameKey;
    private BigInteger value;
    private BigInteger index;

    public String getNameKey() {
        return nameKey;
    }

    public void setNameKey(String nameKey) {
        this.nameKey = nameKey;
    }

    public BigInteger getValue() {
        return value;
    }

    public void setValue(BigInteger value) {
        this.value = value;
    }

    public BigInteger getIndex() {
        return index;
    }

    public void setIndex(BigInteger index) {
        this.index = index;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Stat stat = (Stat) o;
        return Objects.equals(nameKey, stat.nameKey) && Objects.equals(value, stat.value) && Objects.equals(index, stat.index);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nameKey, value, index);
    }

    @Override
    public String toString() {
        return "Stat{" +
                "nameKey='" + nameKey + '\'' +
                ", value=" + value +
                ", index=" + index +
                '}';
    }
}
