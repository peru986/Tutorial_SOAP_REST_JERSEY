package xunta.jersey;

import java.util.List;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class PredictionsTest
{
    private static final String REST_SERVICE_URL
        = "http://localhost:8080/REST_predictions_jersey2/webresources/predictions";

    private static final String PREDICTION_1_WHO = "Cornelius Tillman";
    private static final String PREDICTION_1_WHAT = "Managed holistic contingency will grow killer action-items.";

    private static final int PREDICTIONS_SIZE = 32;

    private static Client client;
    private static WebTarget target;

    @BeforeClass
    public static void setUpClass()
    {
        client = ClientBuilder.newClient();
        target = client.target( REST_SERVICE_URL );
    }

    @AfterClass
    public static void tearDownClass()
    {
        client.close();
    }

    /*
     * Crea la base de datos para que los
     * tests empiecen en una posición conocida.
     */
    @Before
    public void setUp()
    {
        String result = target
            .path( "/reset" )
            .request()
            .post( Entity.form( (Form) null ), String.class );

        assertEquals( "OK.  Database creada", result );
    }

    @After
    public void tearDown()
    {
    }

    @Test
    public void doGetXml()
    {
        String path = "/xml/1";
        Prediction p = target
            .path( path )
            .request()
            .get( Prediction.class );

        assertEquals( 1, p.getId() );
        assertEquals( PREDICTION_1_WHO, p.getWho() );
        assertEquals( PREDICTION_1_WHAT, p.getWhat() );
    }

    @Test
    public void doGetXmlTemplate()
    {
        String template = "/xml/{id}";
        Prediction p = target
            .path( template )
            .resolveTemplate( "id", 1 )
            .request()
            .get( Prediction.class );

        assertEquals( 1, p.getId() );
        assertEquals( PREDICTION_1_WHO, p.getWho() );
        assertEquals( PREDICTION_1_WHAT, p.getWhat() );
    }

    @Test
    public void doGetJson()
    {
        int id = 1;
        String path = "/json/" + id;
        Prediction p = target
            .path( path )
            .request()
            .get( Prediction.class );

        assertEquals( id, p.getId() );
        assertEquals( PREDICTION_1_WHO, p.getWho() );
        assertEquals( PREDICTION_1_WHAT, p.getWhat() );
    }

    @Test
    public void doGetJsonTemplate()
    {
        String template = "/json/{id}";
        Prediction p = target
            .path( template )
            .resolveTemplate( "id", 1 )
            .request()
            .get( Prediction.class );

        assertEquals( 1, p.getId() );
        assertEquals( PREDICTION_1_WHO, p.getWho() );
        assertEquals( PREDICTION_1_WHAT, p.getWhat() );
    }

    @Test
    public void doPostCreate()
    {
        List<Prediction> list = readAllPredictions();
        assertEquals( PREDICTIONS_SIZE, list.size() );

        MultivaluedMap<String, String> params = new MultivaluedHashMap<>();
        String who = "Antonio Gamoneda";
        String what = "Tengo frío junto a los manantiales. He subido hasta cansar mi corazón.";
        params.add( "who", who );
        params.add( "what", what );

        String result = target
            .path( "/create" )
            .request()
            .post( Entity.form( params ), String.class );

        assertTrue( result.contains( who ) );
        assertTrue( result.contains( what ) );

        /*
         * Comprobar ahora el número de Predictions
         */
        list = readAllPredictions();
        assertEquals( PREDICTIONS_SIZE + 1, list.size() );

        Prediction p = list.get( list.size() - 1 );
        assertEquals( PREDICTIONS_SIZE + 1, p.getId() );
        assertEquals( who, p.getWho() );
        assertEquals( what, p.getWhat() );
    }

    @Test
    public void doPutUpdate()
    {
        /*
         * Actualiza prediction.
         */

        final int id = 2;

        MultivaluedMap<String, String> params = new MultivaluedHashMap<>();

        String who = "César Vallejo";
        String what = "La noche es una copa de mal.";
        params.add( "who", who );
        params.add( "what", what );

        String result = target
            .path( "/update/" + id )
            .request()
            .put( Entity.form( params ), String.class );

        System.out.format( "Resultado put: %s%n", result );
        assertTrue( result.contains( who ) );
        assertTrue( result.contains( what ) );

        /*
         * Lee Prediction y comprueba cambios
         */
        String path = "/xml/" + id;
        Prediction p = target
            .path( path )
            .request()
            .get( Prediction.class );

        assertEquals( id, p.getId() );
        assertEquals( who, p.getWho() );
        assertEquals( what, p.getWhat() );
    }

    @Test
    public void doDelete()
    {
        int id = 10;
        String path = "/delete/" + id;

        String result = target
            .path( path )
            .request()
            .delete( String.class );

        String expected = String.format( "La Predicion %d se ha borrado", id );
        assertEquals( expected, result );

        List<Prediction> list = readAllPredictions();
        assertEquals( PREDICTIONS_SIZE - 1, list.size() );
    }

    private List<Prediction> readAllPredictions()
    {
        GenericType<List<Prediction>> predictionType = new GenericType<List<Prediction>>()
        {
        };

        return target
            .path( "/xml" )
            .request()
            .get( predictionType );
    }
}
