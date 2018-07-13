package help.swgoh.api.response;

public class Battle
{
    public String id;
    public String name;
    public BattleTiers[] mapList;

    public class BattleTiers
    {
        public TieredBattle[] easy;
        public TieredBattle[] hard;

        public class TieredBattle
        {
            public String id;
            public String desc;
            public Object[] rewards;
        }
    }
}
