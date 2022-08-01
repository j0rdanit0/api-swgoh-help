package help.swgoh.api.models.player.toon;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Objects;


/**
 * The represenation of a crew membar in a {@link Toon} in a{@link help.swgoh.api.models.player.PlayerRoster}.
 *
 * @since 4.3.1
 * @author doenisf
 */
public class CrewMember {

    private String unitId;
    private BigInteger slot;
    private ReferencedSkill[] skillReferenceList;
    private String skilllessCrewAbilityId;
    private BigInteger gp;
    private float cp;

    public String getUnitId() {
        return unitId;
    }

    public void setUnitId(String unitId) {
        this.unitId = unitId;
    }

    public BigInteger getSlot() {
        return slot;
    }

    public void setSlot(BigInteger slot) {
        this.slot = slot;
    }

    public ReferencedSkill[] getSkillReferenceList() {
        return skillReferenceList;
    }

    public void setSkillReferenceList(ReferencedSkill[] skillReferenceList) {
        this.skillReferenceList = skillReferenceList;
    }

    public String getSkilllessCrewAbilityId() {
        return skilllessCrewAbilityId;
    }

    public void setSkilllessCrewAbilityId(String skilllessCrewAbilityId) {
        this.skilllessCrewAbilityId = skilllessCrewAbilityId;
    }

    public BigInteger getGp() {
        return gp;
    }

    public void setGp(BigInteger gp) {
        this.gp = gp;
    }

    public float getCp() {
        return cp;
    }

    public void setCp(float cp) {
        this.cp = cp;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CrewMember that = (CrewMember) o;
        return Float.compare(that.cp, cp) == 0 && Objects.equals(unitId, that.unitId) && Objects.equals(slot, that.slot) && Arrays.equals(skillReferenceList, that.skillReferenceList) && Objects.equals(skilllessCrewAbilityId, that.skilllessCrewAbilityId) && Objects.equals(gp, that.gp);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(unitId, slot, skilllessCrewAbilityId, gp, cp);
        result = 31 * result + Arrays.hashCode(skillReferenceList);
        return result;
    }

    @Override
    public String toString() {
        return "CrewMember{" +
                "unitId='" + unitId + '\'' +
                ", slot=" + slot +
                ", skillReferenceList=" + Arrays.toString(skillReferenceList) +
                ", skilllessCrewAbilityId='" + skilllessCrewAbilityId + '\'' +
                ", gp=" + gp +
                ", cp=" + cp +
                '}';
    }
}
