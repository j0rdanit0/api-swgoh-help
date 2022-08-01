package help.swgoh.api.models.guild;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Objects;

/**
 * This class represents a guild in SWGOH.
 *
 * @author doenisf
 * @since 4.3.1
 */
public class Guild {

    private String id;
    private String name;
    private String desc;
    private BigInteger members;
    private BigInteger status;
    private BigInteger required;
    private String bannerColor;
    private String bannerLogo;
    private String message;
    private BigInteger gp;
    private Raids raid;
    private GuildMember[] roster;
    private BigInteger updated;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public BigInteger getMembers() {
        return members;
    }

    public void setMembers(BigInteger members) {
        this.members = members;
    }

    public BigInteger getStatus() {
        return status;
    }

    public void setStatus(BigInteger status) {
        this.status = status;
    }

    public BigInteger getRequired() {
        return required;
    }

    public void setRequired(BigInteger required) {
        this.required = required;
    }

    public String getBannerColor() {
        return bannerColor;
    }

    public void setBannerColor(String bannerColor) {
        this.bannerColor = bannerColor;
    }

    public String getBannerLogo() {
        return bannerLogo;
    }

    public void setBannerLogo(String bannerLogo) {
        this.bannerLogo = bannerLogo;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public BigInteger getGp() {
        return gp;
    }

    public void setGp(BigInteger gp) {
        this.gp = gp;
    }

    public Raids getRaid() {
        return raid;
    }

    public void setRaid(Raids raid) {
        this.raid = raid;
    }

    public GuildMember[] getRoster() {
        return roster;
    }

    public void setRoster(GuildMember[] roster) {
        this.roster = roster;
    }

    public BigInteger getUpdated() {
        return updated;
    }

    public void setUpdated(BigInteger updated) {
        this.updated = updated;
    }

    @Override
    public String toString() {
        return "Guild{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", desc='" + desc + '\'' +
                ", members=" + members +
                ", status=" + status +
                ", required=" + required +
                ", bannerColor='" + bannerColor + '\'' +
                ", bannerLogo='" + bannerLogo + '\'' +
                ", message='" + message + '\'' +
                ", gp=" + gp +
                ", raid=" + raid +
                ", roster=" + Arrays.toString(roster) +
                ", updated=" + updated +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Guild guild = (Guild) o;
        return Objects.equals(id, guild.id) && Objects.equals(name, guild.name) && Objects.equals(desc, guild.desc) && Objects.equals(members, guild.members) && Objects.equals(status, guild.status) && Objects.equals(required, guild.required) && Objects.equals(bannerColor, guild.bannerColor) && Objects.equals(bannerLogo, guild.bannerLogo) && Objects.equals(message, guild.message) && Objects.equals(gp, guild.gp) && Objects.equals(raid, guild.raid) && Arrays.equals(roster, guild.roster) && Objects.equals(updated, guild.updated);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(id, name, desc, members, status, required, bannerColor, bannerLogo, message, gp, raid, updated);
        result = 31 * result + Arrays.hashCode(roster);
        return result;
    }

    /**
     * Represents the max unlocked levels for each Raid for the {@link Guild}.
     */
    private static class Raids {
        private String rancor;
        private String aat;
        private String sith_raid;
        private String rancor_challenge;

        public String getRancor() {
            return rancor;
        }

        public void setRancor(String rancor) {
            this.rancor = rancor;
        }

        public String getAat() {
            return aat;
        }

        public void setAat(String aat) {
            this.aat = aat;
        }

        public String getSith_raid() {
            return sith_raid;
        }

        public void setSith_raid(String sith_raid) {
            this.sith_raid = sith_raid;
        }

        public String getRancor_challenge() {
            return rancor_challenge;
        }

        public void setRancor_challenge(String rancor_challenge) {
            this.rancor_challenge = rancor_challenge;
        }
    }
}
