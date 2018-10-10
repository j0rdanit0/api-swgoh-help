package help.swgoh.api.exception;

/**
 * Thrown by the API client when a web call produces the HTTP status 409.
 *
 * Can be used for custom handling of the duplicate request scenario.
 *
 * @since 4.0.0
 */
public class SwgohAPIDuplicateRequestException extends SwgohAPIException
{
    public SwgohAPIDuplicateRequestException( Throwable cause )
    {
        super( cause );
    }
}
