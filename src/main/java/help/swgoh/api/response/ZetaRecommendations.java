package help.swgoh.api.response;

import java.util.Map;

public class ZetaRecommendations
{
    public String request;
    public String sort;
    public ZetaRecommendation[] zetas;
    public ZetaDetail[] details;
    public ZetaUsage[] usage;
    public Credit[] credits;
    public long updated;
    public String _id;

    public class ZetaRecommendation extends BaseZeta
    {
        public SkillType type;
        public float pvp;
        public float tw;
        public float tb;
        public float pit;
        public float tank;
        public float sith;
        public float versa;
    }

    public class ZetaDetail extends BaseZeta
    {
        public String Faction;
        public SkillType Type;
        public int MinLevel;
        public String Description;
    }

    public class ZetaUsage
    {
        public String[] version;
        public String[] url;
        public Map<String, String> params;
        public Map<String, String> request;
        public Map<String, String> sort;
        public String[] examples;
        public String[] note;
    }

    public class Credit
    {
        public String Name;
        public String Discord;
        public String Link;
        public String Credit;
    }
}
