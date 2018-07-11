package help.swgoh.api;

public class SwgohAPIException extends RuntimeException
{
    public SwgohAPIException()
    {
        this( "Exception has occurred." );
    }

    public SwgohAPIException( String errorMessage )
    {
        super( "! " + errorMessage );
    }

    public SwgohAPIException( Throwable cause )
    {
        super( cause );
    }
}
