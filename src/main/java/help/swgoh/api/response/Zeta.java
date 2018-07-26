package help.swgoh.api.response;

import com.google.gson.annotations.SerializedName;

public class Zeta extends BaseZeta
{
    public @SerializedName( value = "pre", alternate = "preZetaText" ) String preZetaText;
    public String adds;
    public @SerializedName( value = "post", alternate = {"final", "fullText"} ) String fullText;
}
