package help.swgoh.api.response;

import com.google.gson.annotations.SerializedName;

public class ModSet
{
    public String name;
    public @SerializedName( value = "count", alternate = "setCompletionAmount" ) int setCompletionAmount;
    public Bonus bonus;

    public class Bonus
    {
        public int unitStatId;
        public int statValueDecimal;
        public int unscaledDecimalValue;
    }
}
