package help.swgoh.api.response;

public class SwgohRosterUnit
{
    public String id;
    public String defId;
    public SwgohRosterType type;
    public short rarity;
    public short level;
    public float gp;
    public int xp;
    public short gear;
    public Equipment[] equipped;
    public SwgohSkill[] skills;
    public Crew[] crew;
    public SwgohPlayerMod[] mods;

    public class Crew
    {
        public String unitId;
        public short slot;
        public SkillReference[] skillReferenceList;

        public class SkillReference
        {
            public String skillId;
        }
    }

    public class Equipment
    {
        public String equipmentId;
        public short slot;
    }
}
