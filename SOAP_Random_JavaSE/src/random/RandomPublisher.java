package random;

import javax.xml.ws.Endpoint;

public class RandomPublisher
{
    public static void main( String[] args )
    {
        final String url = "http://localhost:8888/rs";
        System.out.format( "Publicando RandService en %s%n", url );
        Endpoint.publish( url, new RandomService() );
    }
}
