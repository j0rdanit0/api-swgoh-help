package help.swgoh.api.response;

import com.google.gson.annotations.SerializedName;

public class Player
{
    public String name;
    public int level;
    public int allyCode;
    public String guildName;
    public int gpFull;
    public int gpChar;
    public int gpShip;
    public Collectible[] roster;
    public Arena arena;
    public long updated;
    public String _id;

    public Player(){}

    public class Arena
    {
        public @SerializedName( "char" ) SubArena character;
        public SubArena ship;

        public Arena(){}

        public class SubArena
        {
            public int rank;
            public ArenaSquadMember[] squad;

            public SubArena(){}

            public class ArenaSquadMember
            {
                public String id;
                public String name;
                public String type;

                public ArenaSquadMember(){}
            }
        }
    }
}
