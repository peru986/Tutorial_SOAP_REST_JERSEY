package random2;

import javax.xml.ws.Endpoint;

public class RandomPublisher
{
    public static void main( String[] args )
    {
        final String url = "http://localhost:8889/rs2";
        System.out.format( "Publicando RandService en %s%n", url );
        Endpoint.publish( url, new RandomServiceImpl() );
    }
}
