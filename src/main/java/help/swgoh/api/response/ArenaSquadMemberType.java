package help.swgoh.api.response;

import com.google.gson.annotations.SerializedName;

public enum ArenaSquadMemberType
{
    None, Unit, Leader, @SerializedName( value = "Capital Ship", alternate = "Capital_Ship" )Capital_Ship, Support, Reinforcement
}
