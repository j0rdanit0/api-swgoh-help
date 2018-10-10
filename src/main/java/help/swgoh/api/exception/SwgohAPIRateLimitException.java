package help.swgoh.api.exception;

/**
 * Thrown by the API client when a web call produces the HTTP status 429 (API rate limit) or 502 (CG rate limit).
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
