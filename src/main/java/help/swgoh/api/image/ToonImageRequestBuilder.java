package help.swgoh.api.image;

import java.awt.*;

/**
 * This builder's purpose is to compile together the elements of a {@link ToonImageRequest} object, which can be used
 * to satisfy the {@link ImageRequest} interface.
 *
 * @since 3.2.0
 */
public class ToonImageRequestBuilder
{
    private ToonImageRequest request = new ToonImageRequest();

    public ToonImageRequestBuilder()
    {}

    public ToonImageRequestBuilder( String baseId )
    {
        withBaseId( baseId );
    }

    /**
     * Defines the baseId to be used in the image request.
     *
     * @param baseId  The unique identifier of a toon.
     * @return The builder instance.
     */
    public ToonImageRequestBuilder withBaseId( String baseId )
    {
        request.setBaseId( baseId );
        return this;
    }

    /**
     * Defines the level to be used in the image request.
     *
     * @param level  The toon's level.
     * @return The builder instance.
     */
    public ToonImageRequestBuilder withLevel( Integer level )
    {
        request.setLevel( level );
        return this;
    }

    /**
     * Defines the number of stars to be used in the image request.
     *
     * @param stars  The toon's number of stars.
     * @return The builder instance.
     */
    public ToonImageRequestBuilder withStars( Integer stars )
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
    public ToonImageRequestBuilder withBackgroundColor( Color backgroundColor )
    {
        request.setBackgroundColor( backgroundColor );
        return this;
    }

    /**
     * Defines the gear to be used in the image request.
     *
     * @param gear  The toon's gear.
     * @param displayRomanNumeral  Whether or not to display the gear in roman numerals.
     * @return The builder instance.
     */
    public ToonImageRequestBuilder withGear( ToonImageRequest.Gear gear, boolean displayRomanNumeral )
    {
        request.setGear( gear );
        request.setDisplayRomanNumeral( displayRomanNumeral );
        return this;
    }
    public ToonImageRequestBuilder withGear( ToonImageRequest.Gear gear )
    {
        return withGear( gear, request.isDisplayRomanNumeral() );
    }
    public ToonImageRequestBuilder withGear( Integer gear, boolean displayRomanNumeral )
    {
        return withGear( ToonImageRequest.Gear.get( gear ), ToonImageRequest.Gear.get( gear ) != null && displayRomanNumeral );
    }
    public ToonImageRequestBuilder withGear( Integer gear )
    {
        return withGear( gear, request.isDisplayRomanNumeral() );
    }

    /**
     * Defines whether the gear should be display in roman numerals in the image request.
     *
     * @param displayRomanNumeral  Whether or not to display the gear in roman numerals.
     * @return The builder instance.
     */
    public ToonImageRequestBuilder withRomanNumeralDisplay( boolean displayRomanNumeral )
    {
        request.setDisplayRomanNumeral( displayRomanNumeral );
        return this;
    }

    /**
     * Defines the number of zetas to be used in the image request.
     *
     * @param zetas  The toon's number of zetas.
     * @return The builder instance.
     */
    public ToonImageRequestBuilder withZetas( Integer zetas )
    {
        request.setZetas( zetas );
        return this;
    }

    /**
     * Validate the {@link ToonImageRequest} object and return it.
     *
     * @return The {@link ToonImageRequest} with the configuration specified by this builder.
     * @throws help.swgoh.api.exception.SwgohAPIException if the validation of the request object fails.
     * @see ToonImageRequest#validate()
     */
    public ToonImageRequest build()
    {
        request.validate();
        return request;
    }
}
