package xunta.jersey;

public class PredictionNotFoundException extends Exception
{
    public PredictionNotFoundException( int id )
    {
        super( id + "" );
    }
}
