package help.swgoh.api.models.event;

import java.math.BigInteger;
import java.util.Objects;

/**
 * Represents one instance of a {@link SwgohEvent}.
 *
 * @since 4.3.1
 * @author doenisf
 */
public class EventInstance {
    private String id;
    private BigInteger startTime;
    private BigInteger endTime;
    private BigInteger displayStartTime;
    private BigInteger displayEndTime;
    private boolean timeLimited;
    private CampaingElementIdentifier campaingElementIdentifier;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public BigInteger getStartTime() {
        return startTime;
    }

    public void setStartTime(BigInteger startTime) {
        this.startTime = startTime;
    }

    public BigInteger getEndTime() {
        return endTime;
    }

    public void setEndTime(BigInteger endTime) {
        this.endTime = endTime;
    }

    public BigInteger getDisplayStartTime() {
        return displayStartTime;
    }

    public void setDisplayStartTime(BigInteger displayStartTime) {
        this.displayStartTime = displayStartTime;
    }

    public BigInteger getDisplayEndTime() {
        return displayEndTime;
    }

    public void setDisplayEndTime(BigInteger displayEndTime) {
        this.displayEndTime = displayEndTime;
    }

    public boolean isTimeLimited() {
        return timeLimited;
    }

    public void setTimeLimited(boolean timeLimited) {
        this.timeLimited = timeLimited;
    }

    public CampaingElementIdentifier getCampaingElementIdentifier() {
        return campaingElementIdentifier;
    }

    public void setCampaingElementIdentifier(CampaingElementIdentifier campaingElementIdentifier) {
        this.campaingElementIdentifier = campaingElementIdentifier;
    }

    @Override
    public String toString() {
        return "EventInstance{" +
                "id='" + id + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", displayStartTime=" + displayStartTime +
                ", displayEndTime=" + displayEndTime +
                ", timeLimited=" + timeLimited +
                ", campaingElementIdentifier=" + campaingElementIdentifier +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EventInstance that = (EventInstance) o;
        return timeLimited == that.timeLimited && Objects.equals(id, that.id) && Objects.equals(startTime, that.startTime) && Objects.equals(endTime, that.endTime) && Objects.equals(displayStartTime, that.displayStartTime) && Objects.equals(displayEndTime, that.displayEndTime) && Objects.equals(campaingElementIdentifier, that.campaingElementIdentifier);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, startTime, endTime, displayStartTime, displayEndTime, timeLimited, campaingElementIdentifier);
    }
}
