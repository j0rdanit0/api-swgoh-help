package help.swgoh.api.response;

public class Unit
{
    public String name;
    public int alignment;
    public String type;
    public Crew[] crew;
    public String[] tags;

    public class Crew
    {
        public String unitId;
        public int slot;
        public SkillReference[] skillReferenceList;

        public class SkillReference
        {
            public String skillId;
            public int requiredTier;
            public int requiredRarity;
        }
    }
}
