package predictions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;
import javax.servlet.ServletContext;

public class Predictions
{
    private final ConcurrentMap<Integer, Prediction> predictions;
    private ServletContext sctx;
    private final AtomicInteger mapKey;

    public Predictions()
    {
        predictions = new ConcurrentHashMap<>();
        mapKey = new AtomicInteger();
    }

    public void setServletContext( ServletContext sctx )
    {
        this.sctx = sctx;
        populate();
    }

    public ServletContext getServletContext()
    {
        return this.sctx;
    }

    public void setMap( ConcurrentMap<String, Prediction> predictions )
    {
    }

    public ConcurrentMap<Integer, Prediction> getMap()
    {
        return this.predictions;
    }

    public int addPrediction( Prediction p )
    {
        return _addPrediction( p );
    }

    private int _addPrediction( Prediction p )
    {
        int id = mapKey.incrementAndGet();
        p.setId( id );
        predictions.put( id, p );

        return id;
    }

    public Prediction getPrediction( int id )
    {
        return predictions.get( id );
    }

    public List<Prediction> getPredictions()
    {
        Object[] preds = predictions.values().toArray();
        Arrays.sort( preds );

        List<Prediction> list = new ArrayList<>();
        for ( Object obj : preds ) {
            list.add( (Prediction) obj );
        }
        return list;
    }

    String delete( int id )
    {
        String msg = "Prediction " + id + " not found.";
        Prediction p = predictions.get( id );
        if ( p != null ) {
            predictions.remove( id );
            msg = "Prediction " + id + " removed.";
        }

        return msg;
    }

    int addPrediction( String who, String what )
    {
        Prediction p = new Prediction();
        p.setWho( who );
        p.setWhat( what );

        return _addPrediction( p );
    }

    boolean update( int id, String who, String what )
    {
        Prediction p = predictions.get( id );
        if ( p == null ) {
            return false;
        }
        else {
            p.setWho( who );
            p.setWhat( what );

            return true;
        }
    }

    private void populate()
    {
        String filename = "/WEB-INF/data/predictions.db";
        InputStream in = sctx.getResourceAsStream( filename );

        // Read the data into the array of Predictions.
        if ( in != null ) {
            try {
                InputStreamReader isr = new InputStreamReader( in );
                BufferedReader reader = new BufferedReader( isr );

                String record;
                while (( record = reader.readLine() ) != null) {
                    String[] parts = record.split( "!" );
                    Prediction p = new Prediction();
                    p.setWho( parts[0] );
                    p.setWhat( parts[1] );
                    _addPrediction( p );
                }
            }
            catch (IOException e) {
            }
        }
    }
}
