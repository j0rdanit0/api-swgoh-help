package help.swgoh.api.response;

public class SwgohGuild
{
    public String _id;
    public String name;
    public String desc;
    public short members;
    public short required;
    public String bannerColor;
    public String bannerLogo;
    public String message;
    public int gp;
    public Raid raid;
    public GuildMember[] roster;
    public long updated;

    public class Raid
    {
        public String rancor;
        public String aat;
        public String sith_raid;
    }

    public class GuildMember
    {
        public String name;
        public short level;
        public int allyCode;
        public int gp;
        public GuildMemberType memberType;
    }

    public enum GuildMemberType
    {
        NONE, PENDING, GUILDMEMBER, GUILDOFFICER, GUILDLEADER
    }
}
