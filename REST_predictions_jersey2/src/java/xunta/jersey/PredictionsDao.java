package xunta.jersey;

import java.util.List;

public interface PredictionsDao
{
    Prediction add( String who, String what );

    Prediction find( final int id ) throws PredictionNotFoundException;

    List<Prediction> getPredictions();

    void remove( int id ) throws PredictionNotFoundException;
}
