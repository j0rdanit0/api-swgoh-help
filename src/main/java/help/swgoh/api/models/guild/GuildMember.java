package help.swgoh.api.models.guild;

import java.math.BigInteger;
import java.util.Objects;

/**
 * The representation of a {@link help.swgoh.api.models.player.Player} as a member of a {@link Guild}.
 *
 * @since 4.3.1
 * @author doenisf
 */
public class GuildMember {

    private String id;
    private int guildMemberLevel;
    private String name;
    private BigInteger level;
    private BigInteger allyCode;
    private BigInteger gp;
    private BigInteger gpChar;
    private BigInteger gpShip;
    private BigInteger updated;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getGuildMemberLevel() {
        return guildMemberLevel;
    }

    public void setGuildMemberLevel(int guildMemberLevel) {
        this.guildMemberLevel = guildMemberLevel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigInteger getLevel() {
        return level;
    }

    public void setLevel(BigInteger level) {
        this.level = level;
    }

    public BigInteger getAllyCode() {
        return allyCode;
    }

    public void setAllyCode(BigInteger allyCode) {
        this.allyCode = allyCode;
    }

    public BigInteger getGp() {
        return gp;
    }

    public void setGp(BigInteger gp) {
        this.gp = gp;
    }

    public BigInteger getGpChar() {
        return gpChar;
    }

    public void setGpChar(BigInteger gpChar) {
        this.gpChar = gpChar;
    }

    public BigInteger getGpShip() {
        return gpShip;
    }

    public void setGpShip(BigInteger gpShip) {
        this.gpShip = gpShip;
    }

    public BigInteger getUpdated() {
        return updated;
    }

    public void setUpdated(BigInteger updated) {
        this.updated = updated;
    }

    @Override
    public String toString() {
        return "GuildMember{" +
                "id='" + id + '\'' +
                ", guildMemberLevel=" + guildMemberLevel +
                ", name='" + name + '\'' +
                ", level=" + level +
                ", allyCode=" + allyCode +
                ", gp=" + gp +
                ", gpChar=" + gpChar +
                ", gpShip=" + gpShip +
                ", updated=" + updated +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GuildMember that = (GuildMember) o;
        return guildMemberLevel == that.guildMemberLevel && Objects.equals(id, that.id) && Objects.equals(name, that.name) && Objects.equals(level, that.level) && Objects.equals(allyCode, that.allyCode) && Objects.equals(gp, that.gp) && Objects.equals(gpChar, that.gpChar) && Objects.equals(gpShip, that.gpShip) && Objects.equals(updated, that.updated);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, guildMemberLevel, name, level, allyCode, gp, gpChar, gpShip, updated);
    }
}
