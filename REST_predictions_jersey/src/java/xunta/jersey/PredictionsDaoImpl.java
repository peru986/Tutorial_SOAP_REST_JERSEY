package xunta.jersey;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement( name = "predictionsList" )
public class PredictionsDaoImpl implements PredictionsDao
{
    private List<Prediction> predictions;
    private final AtomicInteger predId;

    public PredictionsDaoImpl()
    {
        predictions = new CopyOnWriteArrayList<>();
        predId = new AtomicInteger();
    }

    @XmlElement
    @XmlElementWrapper( name = "predictions" )
    @Override
    public List<Prediction> getPredictions()
    {
        return predictions;
    }

    public void setPredictions( List<Prediction> preds )
    {
        this.predictions = preds;
    }

    @Override
    public Prediction find( final int id ) throws PredictionNotFoundException
    {
        Optional<Prediction> optional
            = predictions.parallelStream().filter( p -> p.getId() == id ).findAny();

        return optional.orElseThrow( () -> new PredictionNotFoundException( id ) );
    }

    @Override
    public void remove( int id ) throws PredictionNotFoundException
    {
        Prediction pred = find( id );
        predictions.remove( pred );
    }

    @Override
    public Prediction add( String who, String what )
    {
        int id = predId.incrementAndGet();
        Prediction p = new Prediction( id, who, what );
        predictions.add( p );

        return p;
    }

    @Override
    public String toString()
    {
        String s = predictions
            .stream()
            .map( p -> p.toString() )
            .collect( Collectors.joining( "\n" ) );

        return "Lista de predicciones:\n" + s;
    }
}
