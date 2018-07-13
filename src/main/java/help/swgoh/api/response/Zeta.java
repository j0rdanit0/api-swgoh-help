package help.swgoh.api.response;

import com.google.gson.annotations.SerializedName;

public class Zeta
{
    public @SerializedName( value = "unit", alternate = "unitName" ) String unitName;
    public @SerializedName( value = "name", alternate = {"zeta", "zetaName"} ) String zetaName;
    public @SerializedName( value = "pre", alternate = "preZetaText" ) String preZetaText;
    public String adds;
    public @SerializedName( value = "post", alternate = {"final", "fullText"} ) String fullText;
}
