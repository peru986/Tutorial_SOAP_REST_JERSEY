package random2;

import java.util.Random;
import javax.jws.WebService;

@WebService( endpointInterface = "random2.RandomService" )
public class RandomServiceImpl implements RandomService
{
    private static final int MAX_RANDS = 16;

    @Override
    public int next1()
    {
        return new Random().nextInt();
    }

    @Override
    public int[] nextN( int n )
    {
        final int k = n > MAX_RANDS ? MAX_RANDS : Math.abs( n );
        final int[] rands = new int[ k ];

        final Random r = new Random();
        for ( int i = 0; i < k; i++ ) {
            rands[i] = r.nextInt();
        }

        return rands;
    }
}
