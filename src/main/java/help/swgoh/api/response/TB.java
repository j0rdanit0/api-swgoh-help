package help.swgoh.api.response;

public class TB
{
    public String id;
    public String name;
    public String description;
    public int roundCount;
    public ConflictZoneDefinition[] conflictZoneDefinitionList;
    public ZoneDefinitionWrapper[] strikeZoneDefinitionList;
    public ReconZoneDefinition[] reconZoneDefinitionList;
    public DynamicDescription[] dynamicDescriptionList;
    public ZoneDefinitionWrapper[] covertZoneDefinitionList;
    public IdName[] statCategoryList;

    public TB(){}

    public class ZoneDefinitionWrapper
    {
        public ZoneDefinition zoneDefinition;

        public ZoneDefinitionWrapper(){}

        public class VictoryPointReward
        {
            public int galacticScoreRequirement;

            public VictoryPointReward(){}
        }
    }

    public class ZoneDefinition
    {
        public String zoneId;
        public String name;
        public String description;
        public String linkedConflictId;

        public ZoneDefinition(){}
    }

    public class ConflictZoneDefinition extends ZoneDefinitionWrapper
    {
        public int combatType;
        public ZoneDefinitionWrapper.VictoryPointReward[] victoryPointRewardsList;

        public ConflictZoneDefinition(){}
    }

    public class ReconZoneDefinition extends ZoneDefinitionWrapper
    {
        public String goalDesc;
        public int unitRarity;
        public String rewardDesc;
        public String subTitle;

        public ReconZoneDefinition(){}
    }

    public class DynamicDescription
    {
        public String text;

        public DynamicDescription(){}
    }

    public class IdName
    {
        public String id;
        public String name;

        public IdName(){}
    }
}
