package help.swgoh.api.response;

public class RosterUnit
{
    public String id;
    public String defId;
    public String name;
    public RosterType type;
    public int rarity;
    public int level;
    public float gp;
    public int xp;
    public int gear;
    public Equipment[] equipped;
    public Skill[] skills;
    public Crew[] crew;
    public Mod[] mods;

    public class Crew
    {
        public String unitId;
        public int slot;
        public SkillReference[] skillReferenceList;
        public int cp;
        public float gp;

        public class SkillReference
        {
            public String skillId;
            public int requiredTier;
            public int requiredRarity;
        }
    }

    public class Equipment
    {
        public String equipmentId;
        public int slot;
    }

    public enum RosterType
    {
        Char, Ship
    }
}
