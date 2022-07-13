package help.swgoh.api.models.equipment;

import java.util.Objects;

public class MissionIdentifier {

    private String campaignId;
    private String campaignMapId;
    private String campaignNodeId;
    private String campaignNodeDifficulty;
    private String campaignMissionId;

    public String getCampaignId() {
        return campaignId;
    }

    public void setCampaignId(String campaignId) {
        this.campaignId = campaignId;
    }

    public String getCampaignMapId() {
        return campaignMapId;
    }

    public void setCampaignMapId(String campaignMapId) {
        this.campaignMapId = campaignMapId;
    }

    public String getCampaignNodeId() {
        return campaignNodeId;
    }

    public void setCampaignNodeId(String campaignNodeId) {
        this.campaignNodeId = campaignNodeId;
    }

    public String getCampaignNodeDifficulty() {
        return campaignNodeDifficulty;
    }

    public void setCampaignNodeDifficulty(String campaignNodeDifficulty) {
        this.campaignNodeDifficulty = campaignNodeDifficulty;
    }

    public String getCampaignMissionId() {
        return campaignMissionId;
    }

    public void setCampaignMissionId(String campaignMissionId) {
        this.campaignMissionId = campaignMissionId;
    }

    @Override
    public String toString() {
        return "MissionIdentifier{" +
                "campaignId='" + campaignId + '\'' +
                ", campaignMapId='" + campaignMapId + '\'' +
                ", campaignNodeId='" + campaignNodeId + '\'' +
                ", campaignNodeDifficulty='" + campaignNodeDifficulty + '\'' +
                ", campaignMissionId='" + campaignMissionId + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MissionIdentifier that = (MissionIdentifier) o;
        return Objects.equals(campaignId, that.campaignId) && Objects.equals(campaignMapId, that.campaignMapId) && Objects.equals(campaignNodeId, that.campaignNodeId) && Objects.equals(campaignNodeDifficulty, that.campaignNodeDifficulty) && Objects.equals(campaignMissionId, that.campaignMissionId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(campaignId, campaignMapId, campaignNodeId, campaignNodeDifficulty, campaignMissionId);
    }
}
