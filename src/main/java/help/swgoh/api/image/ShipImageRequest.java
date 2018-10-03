package help.swgoh.api.image;

import help.swgoh.api.exception.SwgohAPIException;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ShipImageRequest implements ImageRequest
{
    private String baseId;
    private Integer level;
    private Integer rarity;
    private Color backgroundColor;
    private List<Pilot> pilots = new ArrayList<>();

    public static class Pilot
    {
        public String baseId;
        public Integer level;
        public Integer gear;
        public Integer rarity;
        public Integer zetas;
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

    public List<Pilot> getPilots()
    {
        return pilots;
    }

    public void setPilots( List<Pilot> pilots )
    {
        this.pilots = pilots;
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

        int index = 0;
        for ( Pilot pilot : pilots )
        {
            if ( pilot == null )
            {
                throw new SwgohAPIException( "pilot at index [" + index + "] is null." );
            }
            else
            {
                if ( pilot.baseId == null )
                {
                    throw new SwgohAPIException( "pilot at index [" + index + "] needs a baseId." );
                }
                if ( pilot.level != null && pilot.level < 1 )
                {
                    throw new SwgohAPIException( "pilot at index [" + index + "] has an invalid level." );
                }
                if ( pilot.gear != null && pilot.gear < 1 )
                {
                    throw new SwgohAPIException( "pilot at index [" + index + "] has an invalid gear." );
                }
                if ( pilot.rarity != null && pilot.rarity < 1 )
                {
                    throw new SwgohAPIException( "pilot at index [" + index + "] has an invalid rarity." );
                }
                if ( pilot.zetas != null && pilot.zetas < 1 )
                {
                    throw new SwgohAPIException( "pilot at index [" + index + "] has an invalid zetas." );
                }
            }

            ++index;
        }
    }
}
