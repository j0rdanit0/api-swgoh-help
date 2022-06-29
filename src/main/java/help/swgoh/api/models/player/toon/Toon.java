package help.swgoh.api.models.player.toon;

import help.swgoh.api.models.player.toon.mod.Mod;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Objects;

/**
 * This class represents a single toon of a {@link help.swgoh.api.models.player.Player}.
 *
 * @author doenisf
 * @since 4.3.1
 */
public class Toon {

    private String id;
    private String defId;
    private String nameKey;
    private BigInteger rarity;
    private BigInteger level;
    private BigInteger xp;
    private BigInteger gear;
    private Gear[] equipped;
    private BigInteger combatType;
    private Skill[] skills;
    private Mod[] mods;
    private CrewMember[] crew;
    private BigInteger gp;
    private PrimaryUnitStat primaryUnitStat;
    private Relic relic;

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

    public String getNameKey() {
        return nameKey;
    }

    public void setNameKey(String nameKey) {
        this.nameKey = nameKey;
    }

    public BigInteger getRarity() {
        return rarity;
    }

    public void setRarity(BigInteger rarity) {
        this.rarity = rarity;
    }

    public BigInteger getLevel() {
        return level;
    }

    public void setLevel(BigInteger level) {
        this.level = level;
    }

    public BigInteger getXp() {
        return xp;
    }

    public void setXp(BigInteger xp) {
        this.xp = xp;
    }

    public BigInteger getGear() {
        return gear;
    }

    public void setGear(BigInteger gear) {
        this.gear = gear;
    }

    public Gear[] getEquipped() {
        return equipped;
    }

    public void setEquipped(Gear[] equipped) {
        this.equipped = equipped;
    }

    public BigInteger getCombatType() {
        return combatType;
    }

    public void setCombatType(BigInteger combatType) {
        this.combatType = combatType;
    }

    public Skill[] getSkills() {
        return skills;
    }

    public void setSkills(Skill[] skills) {
        this.skills = skills;
    }

    public Mod[] getMods() {
        return mods;
    }

    public void setMods(Mod[] mods) {
        this.mods = mods;
    }

    public CrewMember[] getCrew() {
        return crew;
    }

    public void setCrew(CrewMember[] crew) {
        this.crew = crew;
    }

    public BigInteger getGp() {
        return gp;
    }

    public void setGp(BigInteger gp) {
        this.gp = gp;
    }

    public PrimaryUnitStat getPrimaryUnitStat() {
        return primaryUnitStat;
    }

    public void setPrimaryUnitStat(PrimaryUnitStat primaryUnitStat) {
        this.primaryUnitStat = primaryUnitStat;
    }

    public Relic getRelic() {
        return relic;
    }

    public void setRelic(Relic relic) {
        this.relic = relic;
    }

    /**
     * Represents the relic level of the {@link Toon}.
     */
    private static class Relic {
        int currentTier;

        public int getCurrentTier() {
            return currentTier;
        }

        public void setCurrentTier(int currentTier) {
            this.currentTier = currentTier;
        }
    }

    /**
     * Currently not known what it is used for.
     */
    // TODO: implement when needed.
    private static class PrimaryUnitStat {

    }

    @Override
    public String toString() {
        return "Toon{" +
                "id='" + id + '\'' +
                ", defId='" + defId + '\'' +
                ", nameKey='" + nameKey + '\'' +
                ", rarity=" + rarity +
                ", level=" + level +
                ", xp=" + xp +
                ", gear=" + gear +
                ", equipped=" + Arrays.toString(equipped) +
                ", combatType=" + combatType +
                ", skills=" + Arrays.toString(skills) +
                ", mods=" + Arrays.toString(mods) +
                ", crew=" + Arrays.toString(crew) +
                ", gp=" + gp +
                ", primaryUnitStat=" + primaryUnitStat +
                ", relic=" + relic +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Toon toon = (Toon) o;
        return Objects.equals(id, toon.id) && Objects.equals(defId, toon.defId) && Objects.equals(nameKey, toon.nameKey) && Objects.equals(rarity, toon.rarity) && Objects.equals(level, toon.level) && Objects.equals(xp, toon.xp) && Objects.equals(gear, toon.gear) && Arrays.equals(equipped, toon.equipped) && Objects.equals(combatType, toon.combatType) && Arrays.equals(skills, toon.skills) && Arrays.equals(mods, toon.mods) && Arrays.equals(crew, toon.crew) && Objects.equals(gp, toon.gp) && Objects.equals(primaryUnitStat, toon.primaryUnitStat) && Objects.equals(relic, toon.relic);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(id, defId, nameKey, rarity, level, xp, gear, combatType, gp, primaryUnitStat, relic);
        result = 31 * result + Arrays.hashCode(equipped);
        result = 31 * result + Arrays.hashCode(skills);
        result = 31 * result + Arrays.hashCode(mods);
        result = 31 * result + Arrays.hashCode(crew);
        return result;
    }
}
