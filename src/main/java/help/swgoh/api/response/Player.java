package help.swgoh.api.response;

import com.google.gson.annotations.SerializedName;

public class Player extends BasePlayer
{
    public int level;
    public String guildName;
    public int gpFull;
    public int gpChar;
    public int gpShip;
    public RosterUnit[] roster;
    public Arena arena;

    public class Arena
    {
        public @SerializedName( value = "char", alternate = "character" ) SubArena character;
        public SubArena ship;

        public class SubArena
        {
            public int rank;
            public ArenaSquadMember[] squad;

            public class ArenaSquadMember
            {
                public String id;
                public String name;
                public ArenaSquadMemberType type;
            }
        }
    }
}
