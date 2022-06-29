package help.swgoh.api.models.roster;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Objects;

/**
 * Represents a single toon in the {@link Roster} class.
 *
 * @since 1.0.0
 * @author doenisf
 */
public class RosterToon {
    private BigInteger updated;
    private String player;
    private int allyCode;
    private int type;
    private int gp;
    private int starLevel;
    private int level;
    private int gearLevel;
    private String[] gear;
    private RosterZeta[] zetas;
    private RosterMod[] mods;

    public BigInteger getUpdated() {
        return updated;
    }

    public void setUpdated(BigInteger updated) {
        this.updated = updated;
    }

    public String getPlayer() {
        return player;
    }

    public void setPlayer(String player) {
        this.player = player;
    }

    public int getAllyCode() {
        return allyCode;
    }

    public void setAllyCode(int allyCode) {
        this.allyCode = allyCode;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getGp() {
        return gp;
    }

    public void setGp(int gp) {
        this.gp = gp;
    }

    public int getStarLevel() {
        return starLevel;
    }

    public void setStarLevel(int starLevel) {
        this.starLevel = starLevel;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getGearLevel() {
        return gearLevel;
    }

    public void setGearLevel(int gearLevel) {
        this.gearLevel = gearLevel;
    }

    public String[] getGear() {
        return gear;
    }

    public void setGear(String[] gear) {
        this.gear = gear;
    }

    public RosterZeta[] getZetas() {
        return zetas;
    }

    public void setZetas(RosterZeta[] zetas) {
        this.zetas = zetas;
    }

    public RosterMod[] getMods() {
        return mods;
    }

    public void setMods(RosterMod[] mods) {
        this.mods = mods;
    }

    @Override
    public String toString() {
        return "RosterToon{" +
                "updated=" + updated +
                ", player='" + player + '\'' +
                ", allyCode=" + allyCode +
                ", type=" + type +
                ", gp=" + gp +
                ", starLevel=" + starLevel +
                ", level=" + level +
                ", gear=" + Arrays.toString(gear) +
                ", zetas=" + Arrays.toString(zetas) +
                ", mods=" + Arrays.toString(mods) +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RosterToon that = (RosterToon) o;
        return allyCode == that.allyCode && type == that.type && gp == that.gp && starLevel == that.starLevel && level == that.level && Objects.equals(updated, that.updated) && Objects.equals(player, that.player) && Arrays.equals(gear, that.gear) && Arrays.equals(zetas, that.zetas) && Arrays.equals(mods, that.mods);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(updated, player, allyCode, type, gp, starLevel, level);
        result = 31 * result + Arrays.hashCode(gear);
        result = 31 * result + Arrays.hashCode(zetas);
        result = 31 * result + Arrays.hashCode(mods);
        return result;
    }
}
