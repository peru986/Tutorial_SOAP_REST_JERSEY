package xunta.rest.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import javax.servlet.ServletContext;

public class PredictionsImpl implements Predictions
{
    private final Map<Integer, Prediction> predictions;
    private ServletContext sctx;
    private final AtomicInteger mapKey;
    private final String fileName;

    public PredictionsImpl( String fileName )
    {
        this.predictions = new ConcurrentHashMap<>();
        this.mapKey = new AtomicInteger();
        this.fileName = fileName;
    }

    /*
     * El ServletContext es necesario para leer los datos de
     * un fichero de texto empaquetado en el fichero war.
     */
    public void setServletContext( ServletContext sctx )
    {
        this.sctx = sctx;
        populate( fileName );
    }

    public ServletContext getServletContext()
    {
        return this.sctx;
    }

    /*
     * Los devuelve ordenados
     */
    @Override
    public Collection<Prediction> getAllPredictions()
    {
        if ( getServletContext() == null || predictions.isEmpty() ) {
            return Collections.EMPTY_SET;
        }

        return new TreeSet<>( predictions.values() );
    }

    @Override
    public final Prediction addPrediction( String who, String what )
    {
        int id = mapKey.incrementAndGet();
        Prediction p = new Prediction( id, who, what );
        predictions.put( id, p );

        return p;
    }

    @Override
    public void remove( Integer key )
    {
        checkPredictionExists( key );  // comprueba que existe
        predictions.remove( key );
    }

    public void checkPredictionExists( Integer key )
    {
        // getPrediction(key) devuelve PredictionNotFoundException
        // si no hay una prediction con clave key
        getPrediction( key );
    }

    @Override
    public final Prediction getPrediction( Integer key )
    {
        checkKeyIsNotNull( key );

        Prediction p = predictions.get( key );
        if ( p == null ) {
            throw new PredictionNotFoundException( key );
        }

        return p;
    }

    private void populate( String filename )
    {
        InputStream in = sctx.getResourceAsStream( filename );

        // Lee los datos
        if ( in != null ) {
            try {
                InputStreamReader isr = new InputStreamReader( in );
                BufferedReader reader = new BufferedReader( isr );

                int i = 0;
                String record;
                while (( record = reader.readLine() ) != null) {
                    String[] parts = record.split( "!" );
                    String who = parts[0];
                    String what = parts[1];
                    addPrediction( who, what );
                }
            }
            catch (IOException e) {
            }
        }
    }

    private void checkKeyIsNotNull( Integer key )
    {
        if ( key == null ) {
            throw new IllegalArgumentException( "null key" );
        }
    }

    /*
     * who o what pueden ser null con lo que no se actualizará su valor.
     * Pero los dos no pueden ser null
     */
    @Override
    public Prediction updatePrediction( int id, String who, String what )
    {
        if ( who == null && what == null ) {
            throw new IllegalArgumentException( "updatePrediction" );
        }

        Prediction p = getPrediction( id );    // comprueba que existe
        if ( who != null ) {
            p.setWho( who );
        }
        if ( what != null ) {
            p.setWhat( what );
        }

        return p;
    }
}
