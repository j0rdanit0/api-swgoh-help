package help.swgoh.api.response;

public class Guild
{
    public String name;
    public String desc;
    public int members;
    public int required;
    public String bannerColor;
    public String bannerLogo;
    public String message;
    public int gp;
    public Raid raid;
    public long updated;
    public Player[] roster;

    public class Raid
    {
        public String rancor;
        public String aat;
        public String sith_raid;
    }
}
