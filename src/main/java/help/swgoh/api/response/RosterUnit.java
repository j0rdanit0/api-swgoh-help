package help.swgoh.api.response;

public class RosterUnit
{
    public String id;
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

    public class Mod
    {
        public String id;
        public ModSlot slot;
        public int setId;
        public String set;
        public int level;
        public int pips;
        public String primaryBonusType;
        public String primaryBonusValue;
        public String secondaryType_1;
        public String secondaryValue_1;
        public String secondaryType_2;
        public String secondaryValue_2;
        public String secondaryType_3;
        public String secondaryValue_3;
        public String secondaryType_4;
        public String secondaryValue_4;
    }

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

    public class Skill extends BaseSkill
    {
        public int tier;
        public Type type;
    }

    public enum RosterType
    {
        Char, Ship
    }

    public enum ModSlot
    {
        Arrow, Circle, Cross, Diamond, Square, Triangle
    }
}
