package xunta.jersey;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Singleton;
import javax.servlet.ServletContext;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Singleton
@Path( "/predictions" )
public class PredictionsResource
{
    @Context
    private ServletContext context;       // inyección de la dependencia
    private volatile static PredictionsDaoImpl database;

    @PostConstruct
    public void postConstruct()
    {
        System.out.format(
            "********************* Creando la base de datos *********************%n" );
        createDatabase();
    }

    @GET
    @Path( "/xml" )
    @Produces( { MediaType.APPLICATION_XML } )
    public List<Prediction> getXml()
    {
        return database.getPredictions();
    }

    /*
     * Si no se encuentra la Prediction devuelve
     * un status 404 en una página html estándar.
     */
    @GET
    @Path( "/xml/{id: \\d+}" )
    @Produces( { MediaType.APPLICATION_XML } )
    public Prediction getXml( @PathParam( "id" ) int id )
    {
        try {
            return database.find( id );
        }
        catch (PredictionNotFoundException e) {
            throw new NotFoundException( "Predicción no encontrada", e );
        }
    }

    @GET
    @Produces( { MediaType.APPLICATION_JSON } )
    @Path( "/json" )
    public List<Prediction> getJson()
    {
        return database.getPredictions();
    }

    /*
     * Usa un ExceptionMapper para devolver una respuesta
     * personalizada cuando no se encuentra la Prediction.
     *
     * Ver la clase PredictionNotFoundExceptionMapper
     */
    @GET
    @Produces( { MediaType.APPLICATION_JSON } )
    @Path( "/json/{id: \\d+}" )
    public Prediction getJson( @PathParam( "id" ) int id ) throws PredictionNotFoundException
    {
        return database.find( id );
    }

    @GET
    @Path( "/plain" )
    @Produces( { MediaType.TEXT_PLAIN } )
    public String getPlain()
    {
        return database.getPredictions().toString();
    }

    @POST
    @Produces( { MediaType.TEXT_PLAIN } )
    @Path( "/create" )
    public Response create(
        @FormParam( "who" ) String who,
        @FormParam( "what" ) String what )
    {
        // Son necesarios who y what.
        if ( who == null || what == null ) {
            String msg = "who y what tienen que tener un valor";
            return generateResponse( msg, Response.Status.BAD_REQUEST );
        }

        // Crear Prediction y añadirla a la bbdd
        Prediction p = database.add( who, what );
        String msg = String.format(
            "Prediction %d creada: (who = %s, what = %s)",
            p.getId(), p.getWho(), p.getWhat() );

        return generateResponse( msg, Response.Status.OK );
    }

    /*
     * Para JUnit.
     * Crea la bbdd de nuevo para partir en
     * los tests de una situación conocida.
     */
    @POST
    @Produces( { MediaType.TEXT_PLAIN } )
    @Path( "/reset" )
    public String reset()
    {
        createDatabase();

        return "OK.  Database creada";
    }

    @PUT
    @Produces( { MediaType.TEXT_PLAIN } )
    @Path( "/update/{id: \\d+}" )
    public Response update(
        @PathParam( "id" ) int id,
        @FormParam( "who" ) String who,
        @FormParam( "what" ) String what )
    {
        // Comprobar los parámetros.
        if ( who == null && what == null ) {
            String msg = "who y what son ambos nulos";
            return generateResponse( msg, Response.Status.BAD_REQUEST );
        }

        try {
            Prediction p = database.find( id );
            if ( who != null ) {
                p.setWho( who );
            }
            if ( what != null ) {
                p.setWhat( what );
            }

            String msg = String.format( "Prediction %d actualizada.  who=%s, what=%s", id, who, what );
            return generateResponse( msg, Response.Status.OK );
        }
        catch (PredictionNotFoundException e) {
            String msg = String.format( "No hay Prediction con id = %d%n", id );
            return generateResponse( msg, Response.Status.NOT_FOUND );
        }
    }

    @DELETE
    @Produces( { MediaType.TEXT_PLAIN } )
    @Path( "/delete/{id: \\d+}" )
    public Response delete( @PathParam( "id" ) int id )
    {
        try {
            database.remove( id );
            String msg = String.format( "La Predicion %d se ha borrado", id );
            return generateResponse( msg, Response.Status.OK );
        }
        catch (PredictionNotFoundException e) {
            String msg = String.format(
                "No existe la Prediction %d.  No se puede borrar.", id );
            return generateResponse( msg, Response.Status.NOT_FOUND );
        }
    }

    private void createDatabase()
    {
        synchronized (this) {
            database = createAndPopulateDatabase();
        }
    }

    private PredictionsDaoImpl createAndPopulateDatabase()
    {
        PredictionsDaoImpl _database = new PredictionsDaoImpl();

        String filename = "/WEB-INF/data/predictions.db";
        InputStream in = context.getResourceAsStream( filename );

        if ( in != null ) {
            try {
                BufferedReader reader = new BufferedReader( new InputStreamReader( in ) );
                int i = 0;
                String record;
                while (( record = reader.readLine() ) != null) {
                    String[] parts = record.split( "!" );
                    _database.add( parts[0], parts[1] );
                }
            }
            catch (Exception e) {
                throw new RuntimeException( "I/O failed!" );
            }
        }

        return _database;
    }

    private Response generateResponse( String msg, Response.Status status )
    {
        return Response.status( status )
            .entity( msg )
            .type( MediaType.TEXT_PLAIN )
            .build();
    }
}
