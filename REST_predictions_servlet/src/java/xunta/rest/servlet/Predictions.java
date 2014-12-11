package xunta.rest.servlet;

import java.util.Collection;

public interface Predictions
{
    Collection<Prediction> getAllPredictions();

    Prediction getPrediction( Integer key );

    void remove( Integer key );

    /*
     * who o what pueden ser null con lo que no se actualizará su valor.
     * Pero los dos no pueden ser null
     */
    Prediction updatePrediction( int id, String who, String what );

    Prediction addPrediction( String who, String what );
}
