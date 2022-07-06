package help.swgoh.api.models.player.toon;

import java.math.BigInteger;
import java.util.Objects;

/**
 * This class represents a single skill/ability of a {@link Toon}.
 *
 * @since 4.3.1
 * @author doenisf
 */
public class Skill {

    private String id;
    private BigInteger tier;
    private String nameKey;
    private boolean isZeta;
    private BigInteger tiers;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public BigInteger getTier() {
        return tier;
    }

    public void setTier(BigInteger tier) {
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

    public BigInteger getTiers() {
        return tiers;
    }

    public void setTiers(BigInteger tiers) {
        this.tiers = tiers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Skill skill = (Skill) o;
        return isZeta == skill.isZeta && Objects.equals(id, skill.id) && Objects.equals(tier, skill.tier) && Objects.equals(nameKey, skill.nameKey) && Objects.equals(tiers, skill.tiers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, tier, nameKey, isZeta, tiers);
    }

    @Override
    public String toString() {
        return "Skill{" +
                "id='" + id + '\'' +
                ", tier=" + tier +
                ", nameKey='" + nameKey + '\'' +
                ", isZeta=" + isZeta +
                ", tiers=" + tiers +
                '}';
    }
}
