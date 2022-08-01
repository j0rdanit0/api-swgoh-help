package help.swgoh.api.models.player;

import java.util.Arrays;
import java.util.Objects;

/**
 * This class represtens the titles selected and unlocked by a {@link Player}.
 *
 * @since 4.3.1
 * @author doenisf
 */
public class Titles {

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
        Titles titles = (Titles) o;
        return Objects.equals(selected, titles.selected) && Arrays.equals(unlocked, titles.unlocked);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(selected);
        result = 31 * result + Arrays.hashCode(unlocked);
        return result;
    }

    @Override
    public String toString() {
        return "Titles{" +
                "selected='" + selected + '\'' +
                ", unlocked=" + Arrays.toString(unlocked) +
                '}';
    }
}
