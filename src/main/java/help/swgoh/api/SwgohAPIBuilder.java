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
