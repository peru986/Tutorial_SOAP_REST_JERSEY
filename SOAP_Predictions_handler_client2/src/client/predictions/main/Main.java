package client.predictions.main;

import client.predictions.Prediction;
import client.predictions.VerbosityException_Exception;
import java.util.List;

public class Main
{
    public static void main( String[] args )
    {
        Client client = new Client( "moe", "MoeMoeMoe" );

        for ( int id = 1; id < 30; ++id ) {
            Prediction p = client.getOne( id );
            if ( p != null ) {
                client.print( p );
            }
        }

        try {
            String s = client.create( "Marcela", "Serrano" );
            System.out.format( "%n%s%n", s );
        }
        catch (VerbosityException_Exception ex) {
            System.out.format( "%nExcepción al crear el cliente%n" );
        }

        try {
            String s = client.edit( 2, "Vega", "Mar es azul" );
            System.out.format( "%n%s%n", s );
        }
        catch (VerbosityException_Exception ex) {
            System.out.format( "%nExcepción al editar el cliente%n" );
        }

        String s = client.delete( 4 );
        System.out.format( "%n%s%n", s );

        List<Prediction> list = client.getAll();
        System.out.format( "%nTamaño de la lista es %d%n", list.size() );
    }
}
