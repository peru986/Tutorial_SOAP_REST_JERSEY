package client.predictions.main;

import client.predictions.Prediction;
import client.predictions.PredictionsSoap;
import client.predictions.PredictionsSoapService;
import java.util.List;

public class Client
{
    final PredictionsSoap port;

    public Client( String name, String key )
    {
        PredictionsSoapService service = new PredictionsSoapService();
        port = service.getPredictionsSoapPort();
    }

    Prediction getOne( int id )
    {
        return port.getOne( id );
    }

    String create( String who, String what )
    {
        return port.create( who, what );
    }

    String edit( int id, String who, String what )
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
