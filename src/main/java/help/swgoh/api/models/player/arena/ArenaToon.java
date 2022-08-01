package help.swgoh.api.models.player.arena;

import java.math.BigInteger;
import java.util.Objects;

/**
 * This class represents a toon used in the {@link ArenaStat} squad of a {@link help.swgoh.api.models.player.Player}.
 *
 * @since 4.3.1
 * @author doenisf
 */
public class ArenaToon {

    private String id;
    private String defId;
    private BigInteger squadUnitType;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDefId() {
        return defId;
    }

    public void setDefId(String defId) {
        this.defId = defId;
    }

    public BigInteger getSquadUnitType() {
        return squadUnitType;
    }

    public void setSquadUnitType(BigInteger squadUnitType) {
        this.squadUnitType = squadUnitType;
    }

    @Override
    public String toString() {
        return "ArenaToon{" +
                "id='" + id + '\'' +
                ", defId='" + defId + '\'' +
                ", squadUnitType=" + squadUnitType +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArenaToon arenaToon = (ArenaToon) o;
        return Objects.equals(id, arenaToon.id) && Objects.equals(defId, arenaToon.defId) && Objects.equals(squadUnitType, arenaToon.squadUnitType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, defId, squadUnitType);
    }
}
