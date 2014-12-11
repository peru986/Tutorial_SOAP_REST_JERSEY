package random;

import java.util.Random;
import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService( serviceName = "RandomWebService" )
public class RandomService
{
    private static final int MAX_RANDS = 16;

    @WebMethod  // opcional
    public int next()
    {
        return new Random().nextInt();
    }

    @WebMethod
    public int[] nextN( final int n )
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
