package help.swgoh.api.response;

import com.google.gson.annotations.SerializedName;

public class Mod
{
    public @SerializedName( value = "id", alternate = "mod_uid" ) String id;
    public String slot;
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
    public String characterName;
}
