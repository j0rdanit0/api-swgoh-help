package help.swgoh.api.response;

import com.google.gson.annotations.SerializedName;

public enum SwgohRosterType
{
    @SerializedName( value = "1", alternate = "Toon" ) Toon,
    @SerializedName( value = "2", alternate = "Ship" ) Ship
}
