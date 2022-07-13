package help.swgoh.api.models.equipment;

import java.util.Objects;

public class MissionLookup {

    private MissionIdentifier missionIdentifier;
    private boolean event;

    public MissionIdentifier getMissionIdentifier() {
        return missionIdentifier;
    }

    public void setMissionIdentifier(MissionIdentifier missionIdentifier) {
        this.missionIdentifier = missionIdentifier;
    }

    public boolean isEvent() {
        return event;
    }

    public void setEvent(boolean event) {
        this.event = event;
    }

    @Override
    public String toString() {
        return "MissionLookup{" +
                "missionIdentifier=" + missionIdentifier +
                ", event=" + event +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MissionLookup that = (MissionLookup) o;
        return event == that.event && Objects.equals(missionIdentifier, that.missionIdentifier);
    }

    @Override
    public int hashCode() {
        return Objects.hash(missionIdentifier, event);
    }
}
