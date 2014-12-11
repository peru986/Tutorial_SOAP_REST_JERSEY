package client.predictions.main;

import client.predictions.Prediction;
import java.util.List;

public class Main
{
    public static void main( String[] args )
    {
        Client client = new Client( "moe", "MoeMoeMoe" );

        for ( int id = 1; id < 32; ++id ) {
            Prediction p = client.getOne( id );
            if ( p != null ) {
                client.print( p );
            }
        }

        String s = client.create( "Marcela", "Serrano" );
        System.out.format( "%n%s%n", s );

        s = client.edit( 2, "Vega", "Mar es azul" );
        System.out.format( "%n%s%n", s );

        s = client.delete( 4 );
        System.out.format( "%n%s%n", s );

        List<Prediction> list = client.getAll();
        System.out.format( "%nTamaño de la lista es %d%n", list.size() );
    }
}
