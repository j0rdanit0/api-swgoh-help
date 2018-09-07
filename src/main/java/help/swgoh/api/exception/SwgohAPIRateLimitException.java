package help.swgoh.api.exception;

/**
 * Thrown by the API client when a web call produces the HTTP status 429.
 *
 * Can be used for custom handling of the rate limit scenario.
 *
 * @since 3.1.3
 */
public class SwgohAPIRateLimitException extends SwgohAPIException
{
    public SwgohAPIRateLimitException( Throwable cause )
    {
        super( cause );
    }
}
