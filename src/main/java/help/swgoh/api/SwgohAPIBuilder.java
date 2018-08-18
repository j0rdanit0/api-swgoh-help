package help.swgoh.api;

/**
 * This builder's sole purpose is to compile together the elements of a {@link SwgohAPISettings} object, which will be used
 * to create an instance of the {@link SwgohAPI} interface.
 *
 * @since 1.0.0
 */
public class SwgohAPIBuilder
{
    private final SwgohAPISettings settings = new SwgohAPISettings();

    /**
     * Defines the username to be sent with the login request.
     *
     * @param username  The username to be sent with the login request
     * @return The builder instance.
     */
    public SwgohAPIBuilder withUsername( String username )
    {
        settings.setUsername( username );
        return this;
    }

    /**
     * Defines the password to be sent with the login request.
     *
     * @param password  The password to be sent with the login request
     * @return The builder instance.
     */
    public SwgohAPIBuilder withPassword( String password )
    {
        settings.setPassword( password );
        return this;
    }

    /**
     * Defines whether the http connections should include SSL or not. (Optional)
     *
     * A value of false will result in http being used, and a value of true will result in https being used.
     *
     * There is a default value defined in {@link SwgohAPISettings#usesSSL}
     *
     * @param usesSSL  The flag to turn http into https or not.
     * @return The builder instance.
     */
    public SwgohAPIBuilder withSSL( boolean usesSSL )
    {
        settings.setUsesSSL( usesSSL );
        return this;
    }

    /**
     * Defines the host for the http connections. (Optional)
     *
     * There is a default value defined in {@link SwgohAPISettings#host}
     *
     * @param host  The domain part of the URL.
     * @return The builder instance.
     */
    public SwgohAPIBuilder withHost( String host )
    {
        settings.setHost( host );
        return this;
    }

    /**
     * Defines the port to be used in the http connection. (Optional)
     *
     * To not specify a port, pass in the value 0.
     *
     * There is a default value defined in {@link SwgohAPISettings#port}
     *
     * @param port  The non-negative port.
     * @return The builder instance.
     */
    public SwgohAPIBuilder withPort( int port )
    {
        settings.setPort( port );
        return this;
    }

    /**
     * Validate the {@link SwgohAPISettings} object and construct a new {@link SwgohAPIClient} instance with it.
     *
     * @return The new client with the configuration specified by this builder.
     * @throws SwgohAPIException if the validation of the settings object fails.
     * @see SwgohAPISettings#validate()
     */
    public SwgohAPI build()
    {
        settings.validate();
        return new SwgohAPIClient( settings );
    }
}
