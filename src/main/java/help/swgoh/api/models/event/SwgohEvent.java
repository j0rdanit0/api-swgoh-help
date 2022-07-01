package help.swgoh.api.models.event;

import java.util.Arrays;
import java.util.Objects;

/**
 * Represents one SWGOH event that is listed in {@link Events}.
 *
 * @since 4.3.1
 * @author doenisf
 */
public class SwgohEvent {
    private String id;
    private String nameKey;
    private String descKey;
    private String summaryKey;
    private String image;
    private Integer squadType;
    private EventInstance[] instanceList;

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

    public String getDescKey() {
        return descKey;
    }

    public void setDescKey(String descKey) {
        this.descKey = descKey;
    }

    public String getSummaryKey() {
        return summaryKey;
    }

    public void setSummaryKey(String summaryKey) {
        this.summaryKey = summaryKey;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getSquadType() {
        return squadType;
    }

    public void setSquadType(Integer squadType) {
        this.squadType = squadType;
    }

    public EventInstance[] getInstanceList() {
        return instanceList;
    }

    public void setInstanceList(EventInstance[] instanceList) {
        this.instanceList = instanceList;
    }

    @Override
    public String toString() {
        return "SwgohEvent{" +
                "id='" + id + '\'' +
                ", nameKey='" + nameKey + '\'' +
                ", descKey='" + descKey + '\'' +
                ", summaryKey='" + summaryKey + '\'' +
                ", image='" + image + '\'' +
                ", squadType=" + squadType +
                ", instanceList=" + Arrays.toString(instanceList) +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SwgohEvent that = (SwgohEvent) o;
        return Objects.equals(id, that.id) && Objects.equals(nameKey, that.nameKey) && Objects.equals(descKey, that.descKey) && Objects.equals(summaryKey, that.summaryKey) && Objects.equals(image, that.image) && Objects.equals(squadType, that.squadType) && Arrays.equals(instanceList, that.instanceList);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(id, nameKey, descKey, summaryKey, image, squadType);
        result = 31 * result + Arrays.hashCode(instanceList);
        return result;
    }
}
