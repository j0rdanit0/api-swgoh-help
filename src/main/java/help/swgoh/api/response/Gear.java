package help.swgoh.api.response;

import com.google.gson.annotations.SerializedName;

public class Gear
{
    public @SerializedName( value = "id", alternate = "equipmentId" ) String equipmentId;
    public String name;
    public MissionLookup[] lookupMissionList;
    public String mark;
    public MissionLookup[] raidLookupList;
    public MissionLookup[] actionLinkLookupList;

    public class MissionLookup
    {
        public MissionIdentifier missionIdentifier;
        public boolean event;

        public class MissionIdentifier
        {
            public String campaignId;
            public String campaignMapId;
            public String campaignNodeId;
            public int campaignNodeDifficulty;
            public String campaignMissionId;
        }
    }
}
