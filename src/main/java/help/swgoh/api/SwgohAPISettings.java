package help.swgoh.api;

public class SwgohAPISettings
{
    private String username;
    private String password;
    private boolean usesSSL = true;
    private String host = "apiv2.swgoh.help";
    private int port = 0;

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

    public boolean isUsesSSL()
    {
        return usesSSL;
    }

    public void setUsesSSL( boolean usesSSL )
    {
        this.usesSSL = usesSSL;
    }

    public String getHost()
    {
        return host;
    }

    public void setHost( String host )
    {
        this.host = host;
    }

    public String getPort()
    {
        return port == 0 ? "" : ":"+port;
    }

    public void setPort( int port )
    {
        this.port = port;
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
        if ( host == null || "".equals( host ) )
        {
            throw new SwgohAPIException( "host is required." );
        }
        if ( port < 0 )
        {
            throw new SwgohAPIException( "port cannot be negative." );
        }
    }
}
