package help.swgoh.api.response;

public class BaseSkill
{
    public String name;
    public boolean isZeta;

    public enum Type
    {
        Basic, Contract, Hardware, Leader, Special, Unique
    }
}
