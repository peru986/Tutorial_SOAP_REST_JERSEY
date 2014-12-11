package predictions;

import java.util.concurrent.ConcurrentHashMap;

public class DataStore
{
    private static final ConcurrentHashMap<String, String> map;

    static {
        map = new ConcurrentHashMap<>();
        map.put( "moe", "MoeMoeMoe" );
        map.put( "curly", "CurlyCurlyCurly" );
        map.put( "larry", "LarryLarryLarry" );
    }

    public static String get( String key )
    {
        return map.get( key );
    }
}
