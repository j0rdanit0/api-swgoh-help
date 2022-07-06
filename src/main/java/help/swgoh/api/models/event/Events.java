package help.swgoh.api.models.event;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Objects;

/**
 * Represents the response to the getEvent-Method and lists all events.
 *
 * @since 4.3.1
 * @author doenisf
 */
public class Events {
    private String id;
    private SwgohEvent[] events;
    private BigInteger updated;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public SwgohEvent[] getEvents() {
        return events;
    }

    public void setEvents(SwgohEvent[] events) {
        this.events = events;
    }

    public BigInteger getUpdated() {
        return updated;
    }

    public void setUpdated(BigInteger updated) {
        this.updated = updated;
    }

    @Override
    public String toString() {
        return "Events{" +
                "id='" + id + '\'' +
                ", events=" + Arrays.toString(events) +
                ", updated=" + updated +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Events events1 = (Events) o;
        return Objects.equals(id, events1.id) && Arrays.equals(events, events1.events) && Objects.equals(updated, events1.updated);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(id, updated);
        result = 31 * result + Arrays.hashCode(events);
        return result;
    }
}
