package help.swgoh.api.response;

import com.google.gson.annotations.SerializedName;

public final class RegistrationResponse
{
    public GetResponse[] get;
    public PutResponse[] put;
    public DeleteResponse del;

    public final static class PutResponse
    {
        public @SerializedName( "ok" ) int successCount;
        public @SerializedName( "nModified" ) int modifiedCount;
        public @SerializedName( "n" ) int total;
        public UpsertReport[] upserted;

        public final static class UpsertReport
        {
            public int index;
            public String _id;
        }
    }

    public final static class GetResponse
    {
        public int allyCode;
        public String discordId;
    }

    public final static class DeleteResponse
    {
        public @SerializedName( "ok" ) int success;
        public @SerializedName( "n" ) int deletedCount;
    }
}
