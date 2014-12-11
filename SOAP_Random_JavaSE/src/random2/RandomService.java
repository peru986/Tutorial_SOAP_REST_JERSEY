package random2;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public interface RandomService
{
    @WebMethod
    public int next1();

    @WebMethod
    public int[] nextN( final int n );
}
