package help.swgoh.api.models.roster;


import java.util.Objects;

/**
 * Represents a Zeta ability of a {@link RosterToon}.
 *
 * @since 1.0.0
 * @author doenisf
 */
public class RosterZeta {
    private String id;
    private int tier;
    private String nameKey;
    private boolean isZeta;
    private int tiers;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getTier() {
        return tier;
    }

    public void setTier(int tier) {
        this.tier = tier;
    }

    public String getNameKey() {
        return nameKey;
    }

    public void setNameKey(String nameKey) {
        this.nameKey = nameKey;
    }

    public boolean isZeta() {
        return isZeta;
    }

    public void setZeta(boolean zeta) {
        isZeta = zeta;
    }

    public int getTiers() {
        return tiers;
    }

    public void setTiers(int tiers) {
        this.tiers = tiers;
    }

    @Override
    public String toString() {
        return "RosterZeta{" +
                "id='" + id + '\'' +
                ", tier=" + tier +
                ", name='" + nameKey + '\'' +
                ", isZeta=" + isZeta +
                ", tiers=" + tiers +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RosterZeta that = (RosterZeta) o;
        return tier == that.tier && isZeta == that.isZeta && tiers == that.tiers && Objects.equals(id, that.id) && Objects.equals(nameKey, that.nameKey);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, tier, nameKey, isZeta, tiers);
    }
}
