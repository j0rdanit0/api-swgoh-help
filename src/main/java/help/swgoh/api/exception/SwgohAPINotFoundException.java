package help.swgoh.api.exception;

/**
 * Thrown by the API client when a web call produces the HTTP status 404.
 *
 * Can be used for custom handling of when the specified resource was not found.
 *
 * @since 4.2.1
 */
public class SwgohAPINotFoundException extends SwgohAPIException
{
    public SwgohAPINotFoundException( Throwable cause )
    {
        super( cause );
    }
}
