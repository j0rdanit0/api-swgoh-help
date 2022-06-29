package help.swgoh.api.models.player;

import java.util.Arrays;
import java.util.Objects;

/**
 * This class represtens the portraits selected and unlocked by a {@link Player}.
 *
 * @since 4.3.1
 * @author doenisf
 */
public class Portraits {

    private String selected;
    private String[] unlocked;

    public String getSelected() {
        return selected;
    }

    public void setSelected(String selected) {
        this.selected = selected;
    }

    public String[] getUnlocked() {
        return unlocked;
    }

    public void setUnlocked(String[] unlocked) {
        this.unlocked = unlocked;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Portraits portraits = (Portraits) o;
        return Objects.equals(selected, portraits.selected) && Arrays.equals(unlocked, portraits.unlocked);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(selected);
        result = 31 * result + Arrays.hashCode(unlocked);
        return result;
    }

    @Override
    public String toString() {
        return "Portraits{" +
                "selected='" + selected + '\'' +
                ", unlocked=" + Arrays.toString(unlocked) +
                '}';
    }
}
