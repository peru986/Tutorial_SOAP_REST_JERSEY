package xunta.jersey;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class PredictionNotFoundExceptionMapper implements ExceptionMapper<PredictionNotFoundException>
{
    @Override
    public Response toResponse( PredictionNotFoundException e )
    {
        String msg = String.format( "Prediction %s no existe.", e.getMessage() );

        return Response.status( Response.Status.NOT_FOUND )
            .entity( msg )
            .type( MediaType.TEXT_PLAIN )
            .build();
    }
}
