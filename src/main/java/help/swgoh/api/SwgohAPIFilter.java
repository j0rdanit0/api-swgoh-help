package help.swgoh.api;

import java.util.ArrayList;
import java.util.List;

public class SwgohAPIFilter
{
    static final SwgohAPIFilter ALL = new SwgohAPIFilter();

    private List<Object> filters = new ArrayList<>();

    public SwgohAPIFilter( String... elements )
    {
        for ( String element : elements )
        {
            and( element );
        }
    }

    public SwgohAPIFilter( String element, SwgohAPIFilter filter )
    {
        and( element, filter );
    }

    static class InnerFilter
    {
        InnerFilter( String element, SwgohAPIFilter filter )
        {
            this.element = element;
            this.filter = filter;
        }

        String element;
        SwgohAPIFilter filter;
    }

    public SwgohAPIFilter and( String element )
    {
        filters.add( element );
        return this;
    }

    public SwgohAPIFilter and( String element, SwgohAPIFilter filter )
    {
        filters.add( new InnerFilter( element, filter ) );
        return this;
    }

    List<Object> getFilters()
    {
        return filters;
    }
}
