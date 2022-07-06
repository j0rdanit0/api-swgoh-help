package help.swgoh.api;

import help.swgoh.api.exception.SwgohAPIException;

/**
 * This builder's purpose is to compile together the elements of a {@link SwgohAPISettings} object, which will be used
 * to create an instance of the {@link SwgohAPI} interface.
 *
 * @since 1.0.0
 */
public class SwgohAPIBuilder
{
    private final SwgohAPISettings settings = new SwgohAPISettings();

    /**
     * Defines the base URL upon which all request URLs are built.
     *
     * This field is optional. If not defined, the default is https://api.swgoh.help
     *
     * @param urlBase  The base URL upon which all request URLs are built
     * @return The builder instance.
     */
    public SwgohAPIBuilder withUrlBase( String urlBase )
    {
        settings.setUrlBase( urlBase );
        return this;
    }

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
     * Defines the default language to use if none is specified at the time of the request.
     *
     * @param language  The language to use for all API requests, unless overridden at the call level.
     * @return The builder instance.
     */
    public SwgohAPIBuilder withDefaultLanguage( SwgohAPI.Language language )
    {
        settings.setDefaultLanguage( language );
        return this;
    }

    /**
     * Defines the default behavior for the enums parameter if none is specified at the time of the request.
     *
     * @param enums  The default value of the enums parameter to use for all API requests, unless overridden at the call level.
     * @return The builder instance.
     */
    public SwgohAPIBuilder withDefaultEnums( boolean enums )
    {
        settings.setDefaultEnums( enums );
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

    /**
     * Validate the {@link SwgohAPISettings} object and construct a new {@link ExtendedSwgohAPIClient} instance with it.
     *
     * @return The new client with the configuration specified by this builder.
     * @throws SwgohAPIException if the validation of the settings object fails.
     * @see SwgohAPISettings#validate()
     */
    @Deprecated
    public ExtendedSwgohAPI buildExtended()
    {
        settings.validate();
        return new ExtendedSwgohAPIClient(settings);
    }
}
