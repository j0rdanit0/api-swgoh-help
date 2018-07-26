package help.swgoh.api.response;

import com.google.gson.annotations.SerializedName;

public class BaseZeta
{
    public @SerializedName( value = "unit", alternate = {"unitName", "toon", "Toon"} ) String unitName;
    public @SerializedName( value = "name", alternate = {"zeta", "zetaName", "Name"} ) String zetaName;
}
