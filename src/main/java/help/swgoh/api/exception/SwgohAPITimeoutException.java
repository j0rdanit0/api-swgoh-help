package help.swgoh.api.exception;

/**
 * Thrown by the API client when a web call produces the HTTP status 504.
 *
 * Can be used for custom handling of the gateway timeout scenario.
 *
 * @since 4.0.0
 */
public class SwgohAPITimeoutException extends SwgohAPIException
{
    public SwgohAPITimeoutException( Throwable cause )
    {
        super( cause );
    }
}
