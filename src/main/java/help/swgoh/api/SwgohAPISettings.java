package help.swgoh.api;

public class SwgohAPISettings
{
    private String username;
    private String password;
    private SwgohAPI.Language defaultLanguage;
    private Boolean defaultEnums;

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
        if ( username == null || "".equals( username ) )
        {
            throw new SwgohAPIException( "username is required." );
        }
        if ( password == null || "".equals( password ) )
        {
            throw new SwgohAPIException( "password is required." );
        }
    }
}
