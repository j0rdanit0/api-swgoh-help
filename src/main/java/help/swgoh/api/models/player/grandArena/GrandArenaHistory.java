package help.swgoh.api.models.player.grandArena;


import java.math.BigInteger;
import java.util.Objects;

/**
 * This class represents a {@link help.swgoh.api.models.player.Player}s stats for one season of grand arena.
 *
 * @since 4.3.1
 * @author doenisf
 */
public class GrandArenaHistory {

    private String seasonId;
    private String eventInstanceId;
    private String league;
    private BigInteger wins;
    private BigInteger losses;
    private boolean eliteDivision;
    private BigInteger seasonPoints;
    private BigInteger division;
    private BigInteger joinTime;
    private BigInteger endTime;
    private boolean remove;
    private BigInteger rank;

    public String getSeasonId() {
        return seasonId;
    }

    public void setSeasonId(String seasonId) {
        this.seasonId = seasonId;
    }

    public String getEventInstanceId() {
        return eventInstanceId;
    }

    public void setEventInstanceId(String eventInstanceId) {
        this.eventInstanceId = eventInstanceId;
    }

    public String getLeague() {
        return league;
    }

    public void setLeague(String league) {
        this.league = league;
    }

    public BigInteger getWins() {
        return wins;
    }

    public void setWins(BigInteger wins) {
        this.wins = wins;
    }

    public BigInteger getLosses() {
        return losses;
    }

    public void setLosses(BigInteger losses) {
        this.losses = losses;
    }

    public boolean isEliteDivision() {
        return eliteDivision;
    }

    public void setEliteDivision(boolean eliteDivision) {
        this.eliteDivision = eliteDivision;
    }

    public BigInteger getSeasonPoints() {
        return seasonPoints;
    }

    public void setSeasonPoints(BigInteger seasonPoints) {
        this.seasonPoints = seasonPoints;
    }

    public BigInteger getDivision() {
        return division;
    }

    public void setDivision(BigInteger division) {
        this.division = division;
    }

    public BigInteger getJoinTime() {
        return joinTime;
    }

    public void setJoinTime(BigInteger joinTime) {
        this.joinTime = joinTime;
    }

    public BigInteger getEndTime() {
        return endTime;
    }

    public void setEndTime(BigInteger endTime) {
        this.endTime = endTime;
    }

    public boolean isRemove() {
        return remove;
    }

    public void setRemove(boolean remove) {
        this.remove = remove;
    }

    public BigInteger getRank() {
        return rank;
    }

    public void setRank(BigInteger rank) {
        this.rank = rank;
    }

    @Override
    public String toString() {
        return "GrandArenaHistory{" +
                "seasonId='" + seasonId + '\'' +
                ", eventInstanceId='" + eventInstanceId + '\'' +
                ", league='" + league + '\'' +
                ", wins=" + wins +
                ", losses=" + losses +
                ", eliteDivision=" + eliteDivision +
                ", seasonPoints=" + seasonPoints +
                ", division=" + division +
                ", joinTime=" + joinTime +
                ", endTime=" + endTime +
                ", remove=" + remove +
                ", rank=" + rank +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GrandArenaHistory that = (GrandArenaHistory) o;
        return eliteDivision == that.eliteDivision && remove == that.remove && Objects.equals(seasonId, that.seasonId) && Objects.equals(eventInstanceId, that.eventInstanceId) && Objects.equals(league, that.league) && Objects.equals(wins, that.wins) && Objects.equals(losses, that.losses) && Objects.equals(seasonPoints, that.seasonPoints) && Objects.equals(division, that.division) && Objects.equals(joinTime, that.joinTime) && Objects.equals(endTime, that.endTime) && Objects.equals(rank, that.rank);
    }

    @Override
    public int hashCode() {
        return Objects.hash(seasonId, eventInstanceId, league, wins, losses, eliteDivision, seasonPoints, division, joinTime, endTime, remove, rank);
    }
}
