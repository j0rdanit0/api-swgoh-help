package help.swgoh.api.image;

/**
 * This interface serves only a single purpose: to provide a more generic interface to all of the -image methods in
 * {@link help.swgoh.api.SwgohAPI}.
 *
 * Implementations such as {@link ToonImageRequest} and {@link ShipImageRequest} should be used for instantiation. They
 * can easily be constructed using their respective builders, {@link ToonImageRequestBuilder} and
 * {@link ShipImageRequestBuilder}.
 *
 * @since 3.1.5
 */
public interface ImageRequest
{
}
