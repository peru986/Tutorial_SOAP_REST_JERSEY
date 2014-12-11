package client.predictions.main;

import client.predictions.Prediction;
import client.predictions.PredictionsSoapHandler;
import client.predictions.PredictionsSoapHandlerService;
import client.predictions.VerbosityException_Exception;
import client.predictions.handler.ClientHandlerResolver;
import java.util.List;

public class Client
{
    final PredictionsSoapHandler port;

    public Client( String name, String key )
    {
        PredictionsSoapHandlerService service = new PredictionsSoapHandlerService();
        service.setHandlerResolver( new ClientHandlerResolver( name, key ) );
        port = service.getPredictionsSoapHandlerPort();
    }

    Prediction getOne( int id )
    {
        return port.getOne( id );
    }

    String create( String who, String what ) throws VerbosityException_Exception
    {
        return port.create( who, what );
    }

    String edit( int id, String who, String what ) throws VerbosityException_Exception
    {
        return port.edit( id, who, what );
    }

    String delete( int id )
    {
        return port.delete( id );
    }

    List<Prediction> getAll()
    {
        return port.getAll();
    }

    void print( Prediction p )
    {
        System.out.format( "%2d : %s : %s%n", p.getId(), p.getWho(), p.getWhat() );
    }
}
