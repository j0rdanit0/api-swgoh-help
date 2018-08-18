package help.swgoh.api;

import help.swgoh.api.response.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Interface that exposes the public methods of {@link SwgohAPIClient}.
 *
 * @since 1.0.0
 */
public interface SwgohAPI
{
    enum Language
    {
        English( "eng_us" ),
        ChineseSimplified( "chs_cn" ),
        ChineseTraditional( "cht_cn" ),
        French( "fre_fr" ),
        German( "ger_de" ),
        Indonesian( "ind_id" ),
        Italian( "ita_it" ),
        Japanese( "jpn_jp" ),
        Korean( "kor_kr" ),
        Portuguese( "por_br" ),
        Russian( "rus_ru" ),
        Spanish( "spa_xm" ),
        Thai( "tha_th" ),
        Turkish( "tur_tr" ),
        ;

        private final String swgohCode;

        Language( String swgohCode )
        {
            this.swgohCode = swgohCode;
        }

        public String getSwgohCode()
        {
            return swgohCode;
        }
    }

    enum PlayerField
    {
        allyCode, arena, gpChar, gpFull, gpShip, guildName, level, name, roster, updated
    }

    enum GuildField
    {
        bannerColor, bannerLogo, desc, gp, members, message, name, raid, required, roster, updated
    }

    enum Collection
    {
        abilityList, battleEnvironmentsList, battleTargetingRuleList, categoryList, challengeList, challengeStyleList,
        effectList, environmentCollectionList, equipmentList, eventSamplingList, guildExchangeItemList, guildRaidList,
        helpEntryList, materialList, playerTitleList, powerUpBundleList, raidConfigList, recipeList, requirementList,
        skillList, starterGuildList, statModList, statModSetList, statProgressionList, tableList, targetingSetList,
        territoryBattleDefinitionList, territoryWarDefinitionList, unitsList, unlockAnnouncementDefinitionList,
        warDefinitionList, xpTableList
    }

    SwgohPlayer getPlayer( int allyCode, PlayerField... fields ) throws IOException;
    SwgohPlayer getPlayer( int allyCode, Language language, PlayerField... fields ) throws IOException;
    String getPlayerJSON( int allyCode, PlayerField... fields ) throws IOException;
    String getPlayerJSON( int allyCode, Language language, PlayerField... fields ) throws IOException;

    List<SwgohPlayer> getPlayers( int[] allyCodes, PlayerField... fields ) throws IOException;
    List<SwgohPlayer> getPlayers( int[] allyCodes, Language language, PlayerField... fields ) throws IOException;
    String getPlayersJSON( int[] allyCodes, PlayerField... fields ) throws IOException;
    String getPlayersJSON( int[] allyCodes, Language language, PlayerField... fields ) throws IOException;

    SwgohGuild getGuild( int allyCode, GuildField... fields ) throws IOException;
    SwgohGuild getGuild( int allyCode, Language language, GuildField... fields ) throws IOException;
    String getGuildJSON( int allyCode, GuildField... fields ) throws IOException;
    String getGuildJSON( int allyCode, Language language, GuildField... fields ) throws IOException;

    Map<String, List<SwgohPlayerUnit>> getUnits( int[] allyCodes ) throws IOException;
    Map<String, List<SwgohPlayerUnit>> getUnits( int[] allyCodes, boolean includeMods ) throws IOException;
    String getUnitsJSON( int[] allyCodes ) throws IOException;
    String getUnitsJSON( int[] allyCodes, boolean includeMods ) throws IOException;

    String getSupportData( Collection collection, String... fields ) throws IOException;
    String getSupportData( Collection collection, Language language, String... fields ) throws IOException;
    String getSupportData( Collection collection, Map<String, String> matchCriteria, String... fields ) throws IOException;
    String getSupportData( Collection collection, Map<String, String> matchCriteria, Language language, String... fields ) throws IOException;
}
