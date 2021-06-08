package help.swgoh.api;

import help.swgoh.api.exception.SwgohAPIException;

public class SwgohAPISettings
{
    private String urlBase = "https://api.swgoh.help";
    private String username;
    private String password;
    private SwgohAPI.Language defaultLanguage;
    private Boolean defaultEnums;

    public String getUrlBase()
    {
        return urlBase;
    }

    public void setUrlBase( String urlBase )
    {
        this.urlBase = urlBase;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername( String username )
    {
        this.username = username;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword( String password )
    {
        this.password = password;
    }

    public SwgohAPI.Language getDefaultLanguage()
    {
        return defaultLanguage;
    }

    public void setDefaultLanguage( SwgohAPI.Language defaultLanguage )
    {
        this.defaultLanguage = defaultLanguage;
    }

    public Boolean getDefaultEnums()
    {
        return defaultEnums;
    }

    public void setDefaultEnums( Boolean defaultEnums )
    {
        this.defaultEnums = defaultEnums;
    }

    public void validate()
    {
        if ( urlBase == null || "".equals( urlBase ) )
        {
            throw new SwgohAPIException( "urlBase is required." );
        }
        boolean isUsernameUsed = ( username != null && !"".equals( username ) );
        boolean isPasswordUsed = ( password != null && !"".equals( password ) );
        if ( isUsernameUsed || isPasswordUsed )
        {
            if ( !isUsernameUsed )
            {
                throw new SwgohAPIException( "username is required." );
            }
            if ( !isPasswordUsed )
            {
                throw new SwgohAPIException( "password is required." );
            }
        }

        try
        {
            SwgohAPIClient.login( urlBase, username, password );
        }
        catch ( Exception exception )
        {
            throw new SwgohAPIException( "urlBase, username, or password are invalid or the host is inaccessible.", exception );
        }
    }
}
