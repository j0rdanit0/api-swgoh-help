package help.swgoh.api.response;

import com.google.gson.annotations.SerializedName;

public class RegistrationResponse
{
    public GetResponse[] get;
    public PutResponse[] put;
    public DeleteResponse del;

    public class PutResponse
    {
        public @SerializedName( "ok" ) int successCount;
        public @SerializedName( "nModified" ) int modifiedCount;
        public @SerializedName( "n" ) int total;
        public UpsertReport[] upserted;

        public class UpsertReport
        {
            public int index;
            public String _id;
        }
    }

    public class GetResponse
    {
        public int allyCode;
        public String discordId;
    }

    public class DeleteResponse
    {
        public @SerializedName( "ok" ) int success;
        public @SerializedName( "n" ) int deletedCount;
    }
}
