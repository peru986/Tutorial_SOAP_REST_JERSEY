package client.random.main;

import client.random.RandomService;
import client.random.RandomWebService;
import java.util.List;

public class RandomClient
{
    public static void main( String[] args )
    {
        RandomWebService service = new RandomWebService();
        RandomService port = service.getRandomServicePort();

        System.out.format( "Llamada a next: %d%n", port.next() );

        System.out.format( "Llamada a nextN(8)%n" );
        List<Integer> nums = port.nextN( 8 );
        nums.forEach( n -> System.out.format( "%16d%n", n ) );
    }
}
