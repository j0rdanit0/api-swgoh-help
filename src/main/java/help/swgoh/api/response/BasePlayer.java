package help.swgoh.api.response;

import com.google.gson.annotations.SerializedName;

public class BasePlayer
{
    public String name;
    public int allyCode;
    public long updated;
    public MemberType memberType;

    public enum MemberType
    {
        @SerializedName( "0" )
        None(0),
        @SerializedName( "1" )
        Pending(1),
        @SerializedName( "2" )
        Member(2),
        @SerializedName( "3" )
        Officer(3),
        @SerializedName( "4" )
        Leader(4)
        ;

        private final int code;

        MemberType( int code )
        {
            this.code = code;
        }

        public static MemberType get( int code )
        {
            MemberType result = None;

            for ( MemberType memberType : values() )
            {
                if ( memberType.getCode() == code )
                {
                    result = memberType;
                    break;
                }
            }

            return result;
        }

        public int getCode()
        {
            return code;
        }
    }
}
