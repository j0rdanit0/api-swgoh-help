package help.swgoh.api.models.event;

import java.util.Objects;

/**
 * Further identifies an {@link EventInstance}.
 *
 * @since 4.3.1
 * @author doenisf
 */
public class CampaingElementIdentifier {
    private String campaignId;
    private String campaingMapId;
    private String campaignNodeId;
    private Integer campaignNodeDifficulty;
    private String campaingMissionId;

    public String getCampaignId() {
        return campaignId;
    }

    public void setCampaignId(String campaignId) {
        this.campaignId = campaignId;
    }

    public String getCampaingMapId() {
        return campaingMapId;
    }

    public void setCampaingMapId(String campaingMapId) {
        this.campaingMapId = campaingMapId;
    }

    public String getCampaignNodeId() {
        return campaignNodeId;
    }

    public void setCampaignNodeId(String campaignNodeId) {
        this.campaignNodeId = campaignNodeId;
    }

    public Integer getCampaignNodeDifficulty() {
        return campaignNodeDifficulty;
    }

    public void setCampaignNodeDifficulty(Integer campaignNodeDifficulty) {
        this.campaignNodeDifficulty = campaignNodeDifficulty;
    }

    public String getCampaingMissionId() {
        return campaingMissionId;
    }

    public void setCampaingMissionId(String campaingMissionId) {
        this.campaingMissionId = campaingMissionId;
    }

    @Override
    public String toString() {
        return "CampaingElementIdentifier{" +
                "campaignId='" + campaignId + '\'' +
                ", campaingMapId='" + campaingMapId + '\'' +
                ", campaignNodeId='" + campaignNodeId + '\'' +
                ", campaignNodeDifficulty=" + campaignNodeDifficulty +
                ", campaingMissionId='" + campaingMissionId + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CampaingElementIdentifier that = (CampaingElementIdentifier) o;
        return Objects.equals(campaignId, that.campaignId) && Objects.equals(campaingMapId, that.campaingMapId) && Objects.equals(campaignNodeId, that.campaignNodeId) && Objects.equals(campaignNodeDifficulty, that.campaignNodeDifficulty) && Objects.equals(campaingMissionId, that.campaingMissionId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(campaignId, campaingMapId, campaignNodeId, campaignNodeDifficulty, campaingMissionId);
    }
}
