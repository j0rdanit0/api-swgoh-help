package help.swgoh.api.image;

import java.awt.*;

/**
 * This builder's purpose is to compile together the elements of a {@link ShipImageRequest} object, which can be used
 * to satisfy the {@link ImageRequest} interface.
 *
 * @since 3.2.0
 */
public class ShipImageRequestBuilder
{
    private ShipImageRequest request = new ShipImageRequest();

    public ShipImageRequestBuilder()
    {}

    public ShipImageRequestBuilder( String baseId )
    {
        withBaseId( baseId );
    }

    /**
     * Defines the baseId to be used in the image request.
     *
     * @param baseId  The unique identifier of a ship.
     * @return The builder instance.
     */
    public ShipImageRequestBuilder withBaseId( String baseId )
    {
        request.setBaseId( baseId );
        return this;
    }

    /**
     * Defines the level to be used in the image request.
     *
     * @param level  The ship's level.
     * @return The builder instance.
     */
    public ShipImageRequestBuilder withLevel( Integer level )
    {
        request.setLevel( level );
        return this;
    }

    /**
     * Defines the number of stars to be used in the image request.
     *
     * @param stars  The ship's number of stars.
     * @return The builder instance.
     */
    public ShipImageRequestBuilder withStars( Integer stars )
    {
        request.setRarity( stars );
        return this;
    }

    /**
     * Defines the background color to be used in the image request.
     *
     * @param backgroundColor  The background color of the image.
     * @return The builder instance.
     */
    public ShipImageRequestBuilder withBackgroundColor( Color backgroundColor )
    {
        request.setBackgroundColor( backgroundColor );
        return this;
    }

    /**
     * Adds a pilot to the image request.
     *
     * @param baseId  The unique identifier of the pilot.
     * @param level  The pilot's level.
     * @param gear  The pilot's gear.
     * @param stars  The pilot's number of stars.
     * @param zetas  The pilot's number of zetas.
     * @return The builder instance.
     */
    public ShipImageRequestBuilder addPilot( String baseId, Integer level, Integer gear, Integer stars, Integer zetas )
    {
        ShipImageRequest.Pilot pilot = new ShipImageRequest.Pilot();
        pilot.baseId = baseId;
        pilot.level = level;
        pilot.gear = gear;
        pilot.rarity = stars;
        pilot.zetas = zetas;
        request.getPilots().add( pilot );
        return this;
    }

    /**
     * Validate the {@link ShipImageRequest} object and return it.
     *
     * @return The {@link ShipImageRequest} with the configuration specified by this builder.
     * @throws help.swgoh.api.exception.SwgohAPIException if the validation of the request object fails.
     * @see ShipImageRequest#validate()
     */
    public ShipImageRequest build()
    {
        request.validate();
        return request;
    }
}
