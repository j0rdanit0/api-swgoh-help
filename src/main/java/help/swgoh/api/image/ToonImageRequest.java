package help.swgoh.api.image;

import help.swgoh.api.exception.SwgohAPIException;

import java.awt.*;

public class ToonImageRequest implements ImageRequest
{
    private String baseId;
    private Integer level;
    private Integer rarity;
    private Color backgroundColor;
    private Gear gear;
    private boolean displayRomanNumeral;
    private Integer zetas;

    public enum Gear
    {
        I, II, III, IV, V, VI, VII, VIII, IX, X, XI, XII;

        public static Gear get( Integer gear )
        {
            Gear result = null;

            if ( gear > 0 && gear <= values().length )
            {
                int ordinal = gear - 1;
                result = values()[ordinal];
            }

            return result;
        }
    }

    public String getBaseId()
    {
        return baseId;
    }

    public void setBaseId( String baseId )
    {
        this.baseId = baseId;
    }

    public Integer getLevel()
    {
        return level;
    }

    public void setLevel( Integer level )
    {
        this.level = level;
    }

    public Integer getRarity()
    {
        return rarity;
    }

    public void setRarity( Integer rarity )
    {
        this.rarity = rarity;
    }

    public Color getBackgroundColor()
    {
        return backgroundColor;
    }

    public void setBackgroundColor( Color backgroundColor )
    {
        this.backgroundColor = backgroundColor;
    }

    public Gear getGear()
    {
        return gear;
    }

    public void setGear( Gear gear )
    {
        this.gear = gear;
    }

    public boolean isDisplayRomanNumeral()
    {
        return displayRomanNumeral;
    }

    public void setDisplayRomanNumeral( boolean displayRomanNumeral )
    {
        this.displayRomanNumeral = displayRomanNumeral;
    }

    public Integer getZetas()
    {
        return zetas;
    }

    public void setZetas( Integer zetas )
    {
        this.zetas = zetas;
    }

    public void validate()
    {
        if ( baseId == null )
        {
            throw new SwgohAPIException( "baseId is required." );
        }
        if ( level != null && level < 1 )
        {
            throw new SwgohAPIException( "invalid level." );
        }
        if ( rarity != null && rarity < 1 )
        {
            throw new SwgohAPIException( "invalid rarity." );
        }
        if ( zetas != null && zetas < 1 )
        {
            throw new SwgohAPIException( "invalid zetas." );
        }
    }
}
