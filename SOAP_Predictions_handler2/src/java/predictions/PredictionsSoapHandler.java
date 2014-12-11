package predictions;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.jws.HandlerChain;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.servlet.ServletContext;
import javax.xml.ws.WebServiceContext;
import javax.xml.ws.handler.MessageContext;

@WebService
@HandlerChain( file = "serviceHandler.xml" )
public class PredictionsSoapHandler
{
    @Resource
    private WebServiceContext wsCtx;
    private ServletContext sCtx;
    private static final Predictions predictions = new Predictions();
    private static final int MAX_LENGTH = 3;

    @PostConstruct
    public void postConstruct()
    {
        //init();
    }

    @WebMethod
    public List<Prediction> getAll()
    {
        init();
        return predictions.getPredictions();
    }

    @WebMethod
    public Prediction getOne( int id )
    {
        init();
        return predictions.getPrediction( id );
    }

    @WebMethod
    public String create( String who, String what ) throws VerbosityException
    {
        init();
        checkLength( what );

        int id = predictions.addPrediction( who, what );
        String msg = "Prediction " + id + " creada.";
        return msg;
    }

    private void checkLength( String what ) throws VerbosityException
    {
        int count = wordCount( what );
        if ( count > MAX_LENGTH ) {
            throw new VerbosityException( count + " is too verbose!",
                "Max words: " + MAX_LENGTH );
        }
    }

    @WebMethod
    public String edit( int id, String who, String what ) throws VerbosityException
    {
        checkLength( what );

        init();

        boolean updated = predictions.update( id, who, what );
        return updated ? "Prediction " + id + " actualizada." : "Prediction " + id + " no encontrada.";
    }

    @WebMethod
    public String delete( int id )
    {
        init();
        return predictions.delete( id );
    }

    private void init()
    {
        if ( wsCtx == null ) {
            throw new RuntimeException( "DI failed on wsCtx!" );
        }
        if ( sCtx == null ) { // ServletContext not yet set?
            MessageContext mCtx = wsCtx.getMessageContext();
            sCtx = (ServletContext) mCtx.get( MessageContext.SERVLET_CONTEXT );
            predictions.setServletContext( sCtx );
        }
    }

    private int wordCount( String words )
    {
        if ( words == null ) {
            return -1;
        }
        return words.trim().split( "\\s+" ).length;
    }
}
