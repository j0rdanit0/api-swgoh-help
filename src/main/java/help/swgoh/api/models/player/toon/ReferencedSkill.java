package help.swgoh.api.models.player.toon;

import java.math.BigInteger;
import java.util.Objects;

/**
 * This class represents the skills of a ship that are connected to a {@link CrewMember}.
 *
 * @since 4.3.1
 * @author doenisf
 */
public class ReferencedSkill {

    private String skillId;
    private BigInteger requiredTier;
    private BigInteger requiredRarity;
    private BigInteger requiredRelicTier;

    public String getSkillId() {
        return skillId;
    }

    public void setSkillId(String skillId) {
        this.skillId = skillId;
    }

    public BigInteger getRequiredTier() {
        return requiredTier;
    }

    public void setRequiredTier(BigInteger requiredTier) {
        this.requiredTier = requiredTier;
    }

    public BigInteger getRequiredRarity() {
        return requiredRarity;
    }

    public void setRequiredRarity(BigInteger requiredRarity) {
        this.requiredRarity = requiredRarity;
    }

    public BigInteger getRequiredRelicTier() {
        return requiredRelicTier;
    }

    public void setRequiredRelicTier(BigInteger requiredRelicTier) {
        this.requiredRelicTier = requiredRelicTier;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReferencedSkill that = (ReferencedSkill) o;
        return Objects.equals(skillId, that.skillId) && Objects.equals(requiredTier, that.requiredTier) && Objects.equals(requiredRarity, that.requiredRarity) && Objects.equals(requiredRelicTier, that.requiredRelicTier);
    }

    @Override
    public int hashCode() {
        return Objects.hash(skillId, requiredTier, requiredRarity, requiredRelicTier);
    }

    @Override
    public String toString() {
        return "ReferencedSkill{" +
                "skillId='" + skillId + '\'' +
                ", requiredTier=" + requiredTier +
                ", requiredRarity=" + requiredRarity +
                ", requiredRelicTier=" + requiredRelicTier +
                '}';
    }
}
