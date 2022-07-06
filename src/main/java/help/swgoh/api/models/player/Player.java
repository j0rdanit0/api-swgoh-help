package help.swgoh.api.models.player;

import help.swgoh.api.models.player.arena.PlayerArenaStats;
import help.swgoh.api.models.player.grandArena.GrandArenaHistory;
import help.swgoh.api.models.player.toon.Toon;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Objects;

/**
 * This class represents all data associated with a player in SWGOH.
 *
 * @author doenisf
 * @since 4.3.1
 */
public class Player {

    private BigInteger allyCode;
    private String id;
    private String name;
    private BigInteger level;
    private Titles titles;
    private String guildRefId;
    private String guildName;
    private String guildBannerColor;
    private String guildBannerLogo;
    private String guildTypeId;
    private Stat[] stats;
    private Toon[] roster;
    private PlayerRoster playerRoster;
    private PlayerArenaStats arena;
    private BigInteger lastActivity;
    private BigInteger poUTCOffsetMinutes;
    private Portraits portraits;
    private GrandArenaHistory[] grandArena;
    private BigInteger grandArenaLifeTime;
    private BigInteger updated;


    public BigInteger getAllyCode() {
        return allyCode;
    }

    public void setAllyCode(BigInteger allyCode) {
        this.allyCode = allyCode;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigInteger getLevel() {
        return level;
    }

    public void setLevel(BigInteger level) {
        this.level = level;
    }

    public Titles getTitles() {
        return titles;
    }

    public void setTitles(Titles titles) {
        this.titles = titles;
    }

    public String getGuildRefId() {
        return guildRefId;
    }

    public void setGuildRefId(String guildRefId) {
        this.guildRefId = guildRefId;
    }

    public String getGuildName() {
        return guildName;
    }

    public void setGuildName(String guildName) {
        this.guildName = guildName;
    }

    public String getGuildBannerColor() {
        return guildBannerColor;
    }

    public void setGuildBannerColor(String guildBannerColor) {
        this.guildBannerColor = guildBannerColor;
    }

    public String getGuildBannerLogo() {
        return guildBannerLogo;
    }

    public void setGetGuildBannerLogo(String guildBannerLogo) {
        this.guildBannerLogo = guildBannerLogo;
    }

    public void setGuildBannerLogo(String guildBannerLogo) {
        this.guildBannerLogo = guildBannerLogo;
    }

    public String getGuildTypeId() {
        return guildTypeId;
    }

    public void setGuildTypeId(String guildTypeId) {
        this.guildTypeId = guildTypeId;
    }

    public Stat[] getStats() {
        return stats;
    }

    public void setStats(Stat[] stats) {
        this.stats = stats;
    }

    public Toon[] getRoster() {
        return roster;
    }

    public void setRoster(Toon[] roster) {
        this.roster = roster;
    }

    public PlayerRoster getPlayerRoster() {
        initPlayerRoster(roster);
        return playerRoster;
    }

    public void setPlayerRoster(PlayerRoster playerRoster) {
        this.playerRoster = playerRoster;
    }

    public PlayerArenaStats getArena() {
        return arena;
    }

    public void setArena(PlayerArenaStats arena) {
        this.arena = arena;
    }

    public BigInteger getLastActivity() {
        return lastActivity;
    }

    public void setLastActivity(BigInteger lastActivity) {
        this.lastActivity = lastActivity;
    }

    public BigInteger getPoUTCOffsetMinutes() {
        return poUTCOffsetMinutes;
    }

    public void setPoUTCOffsetMinutes(BigInteger poUTCOffsetMinutes) {
        this.poUTCOffsetMinutes = poUTCOffsetMinutes;
    }

    public Portraits getPortraits() {
        return portraits;
    }

    public void setPortraits(Portraits portraits) {
        this.portraits = portraits;
    }

    public GrandArenaHistory[] getGrandArena() {
        return grandArena;
    }

    public void setGrandArena(GrandArenaHistory[] grandArena) {
        this.grandArena = grandArena;
    }

    public BigInteger getGrandArenaLifeTime() {
        return grandArenaLifeTime;
    }

    public void setGrandArenaLifeTime(BigInteger grandArenaLifeTime) {
        this.grandArenaLifeTime = grandArenaLifeTime;
    }

    public BigInteger getUpdated() {
        return updated;
    }

    public void setUpdated(BigInteger updated) {
        this.updated = updated;
    }

    /* Supporting methods */
    private void initPlayerRoster(Toon[] toons) {
        this.playerRoster = new PlayerRoster();
        playerRoster.setToons(toons);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return Objects.equals(allyCode, player.allyCode) && Objects.equals(id, player.id) && Objects.equals(name, player.name) && Objects.equals(level, player.level) && Objects.equals(titles, player.titles) && Objects.equals(guildRefId, player.guildRefId) && Objects.equals(guildName, player.guildName) && Objects.equals(guildBannerColor, player.guildBannerColor) && Objects.equals(guildBannerLogo, player.guildBannerLogo) && Objects.equals(guildTypeId, player.guildTypeId) && Arrays.equals(stats, player.stats) && Arrays.equals(roster, player.roster) && Objects.equals(playerRoster, player.playerRoster) && Objects.equals(arena, player.arena) && Objects.equals(lastActivity, player.lastActivity) && Objects.equals(poUTCOffsetMinutes, player.poUTCOffsetMinutes) && Objects.equals(portraits, player.portraits) && Arrays.equals(grandArena, player.grandArena) && Objects.equals(grandArenaLifeTime, player.grandArenaLifeTime) && Objects.equals(updated, player.updated);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(allyCode, id, name, level, titles, guildRefId, guildName, guildBannerColor, guildBannerLogo, guildTypeId, playerRoster, arena, lastActivity, poUTCOffsetMinutes, portraits, grandArenaLifeTime, updated);
        result = 31 * result + Arrays.hashCode(stats);
        result = 31 * result + Arrays.hashCode(roster);
        result = 31 * result + Arrays.hashCode(grandArena);
        return result;
    }

    @Override
    public String toString() {
        return "Player{" +
                "allyCode=" + allyCode +
                ", id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", level=" + level +
                ", titles=" + titles +
                ", guildRefId='" + guildRefId + '\'' +
                ", guildName='" + guildName + '\'' +
                ", guildBannerColor='" + guildBannerColor + '\'' +
                ", guildBannerLogo='" + guildBannerLogo + '\'' +
                ", guildTypeId='" + guildTypeId + '\'' +
                ", stats=" + Arrays.toString(stats) +
                ", roster=" + Arrays.toString(roster) +
                ", playerRoster=" + playerRoster +
                ", arena=" + arena +
                ", lastActivity=" + lastActivity +
                ", poUTCOffsetMinutes=" + poUTCOffsetMinutes +
                ", portraits=" + portraits +
                ", grandArena=" + Arrays.toString(grandArena) +
                ", grandArenaLifeTime=" + grandArenaLifeTime +
                ", updated=" + updated +
                '}';
    }
}
