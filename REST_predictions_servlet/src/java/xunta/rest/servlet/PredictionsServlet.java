package xunta.rest.servlet;

import java.beans.XMLEncoder;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Collection;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.http.HTTPException;
import org.json.JSONObject;

public class PredictionsServlet extends HttpServlet
{
    private Predictions predictions;

    /*
     * Se ejecuta cuando el servlet se carga en el contenedor.
     * Crea predictionsImpl y le pasa el ServletContext para que
     *      pueda realizar su trabajo.
     */
    @Override
    public void init()
    {
        String pathToData = getPathToData();
        predictions = new PredictionsImpl( pathToData );
        ( (PredictionsImpl) predictions ).setServletContext( this.getServletContext() );
    }

    /*
     * Si la cabecera Accept está puesta a application/json se devuelve JSON, si
     * no se devuelve XML.
     *
     * Es el único método que puede devolver json
     */
    @Override
    public void doGet( HttpServletRequest request, HttpServletResponse response )
    {
        int id = getId( request );
        ContentType contentType = getContentType( request );

        /*
         * Si no hay query, devuelve toda la lista.
         */
        if ( id == -1 ) {
            Collection<Prediction> values = predictions.getAllPredictions();

            String xml = toXML( values );
            sendResponse( response, xml, contentType );
        }
        /*
         * En otro caso devolvemos la Prediction solicitada.
         */
        else {
            try {
                Prediction pred = predictions.getPrediction( id );
                sendResponse( response, toXML( pred ), contentType );
            }
            catch (PredictionNotFoundException e) {
                String msg = String.format( "No hay Prediction con id %d", id );
                sendResponse( response, toXML( msg ), ContentType.XML );
            }
        }
    }

    private ContentType getContentType( HttpServletRequest request )
    {
        String accept = request.getHeader( "Accept" );
        if ( accept != null && accept.contains( "json" ) ) {
            return ContentType.JSON;
        }
        else {
            return ContentType.XML;
        }
    }

    /*
     * POST
     * El body deber tener dos claves: who y what
     */
    @Override
    public void doPost( HttpServletRequest request, HttpServletResponse response )
    {
        /*
         * Comprobar datos
         */

        /*
         * Crear la Prediction
         */
        /*
         * Generar respuesta OK
         */
    }

    /*
     * El body debe contener who, what o las dos.
     *
     */
    @Override
    public void doPut( HttpServletRequest request, HttpServletResponse response )
    {
        int id = getId( request );
        if ( id == -1 ) {
            sendStatusCode( response, HttpServletResponse.SC_BAD_REQUEST );
            return;
        }

        PutData data;
        try {
            data = getPutData( request );
            checkPutData( data );
        }
        catch (IOException | BadDataException e) {
            sendStatusCode( response, HttpServletResponse.SC_BAD_REQUEST );
            return;
        }

        try {
            /*
             * Se obtienen los parámetros explícitamente ya que hay un bug
             * que no permite obtenerlos cuando la petición es PUT.
             */
            predictions.updatePrediction( id, data.who, data.what );
            String msg = String.format( "Prediction %s actualizada", id );
            sendResponse( response, toXML( msg ), ContentType.XML );
        }
        catch (PredictionNotFoundException e) {
            String msg = String.format( "No hay Prediction con id %d", id );
            sendResponse( response, toXML( msg ), ContentType.XML );
        }
        catch (NumberFormatException e) {
            sendStatusCode( response, HttpServletResponse.SC_BAD_REQUEST );
        }
    }

    private static class PutData
    {
        String who;
        String what;
    }

    private PutData getPutData( HttpServletRequest request ) throws IOException, BadDataException
    {
        PutData putData = new PutData();
        String data;
        /*
         * Leer el body de la petición
         */
        try (InputStreamReader isr = new InputStreamReader( request.getInputStream() );
            BufferedReader br = new BufferedReader( isr )) {

            data = br.readLine();
            if ( data == null ) {
                throw new BadDataException( "No hay datos en petición PUT" );
            }
        }

        String[] args = data.split( "&" );

        for ( String parameter : args ) {
            String[] s = parameter.split( "=" );
            switch (s[0]) {
                case "who":
                    putData.who = s[1];
                    break;
                case "what":
                    putData.what = s[1];
                    break;
            }
        }

        return putData;
    }

    private void checkPutData( PutData data ) throws BadDataException
    {
        if ( data.what == null && data.who == null ) {
            throw new BadDataException( "Datos de PUT incorrectos" );
        }
    }

    private static class BadDataException extends Exception
    {
        public BadDataException( String message )
        {
            super( message );
        }
    }

    /*
     * DELETE
     */
    @Override
    public void doDelete( HttpServletRequest request, HttpServletResponse response )
    {
    }

    /*
     * request.getRequestURI() tiene que ser:
     *      /context-root/predictions/2 o /context-root/predictions/
     */
    private int getId( HttpServletRequest request ) throws NumberFormatException
    {
        String[] path = request.getRequestURI().split( "/" );
        switch (path.length) {
            // /context-root/predictions/
            case 3:
                return -1;
            // /context-root/predictions/45
            case 4:
                try {
                    int result = Integer.parseInt( path[3] );
                    if ( result >= 0 ) {
                        return result;
                    }
                    else {
                        throw new HTTPException( HttpServletResponse.SC_BAD_REQUEST );
                    }
                }
                catch (NumberFormatException e) {
                    throw new HTTPException( HttpServletResponse.SC_BAD_REQUEST );
                }
            default:
                throw new HTTPException( HttpServletResponse.SC_BAD_REQUEST );
        }
    }

    private void sendStatusCode( HttpServletResponse response, int statusCode )
    {
        try {
            response.sendError( statusCode );
        }
        catch (IOException ex) {
            getServletContext().log( "sendStatusCode", ex );
        }
    }

    /*
     * Método no permitido
     */
    @Override
    public void doTrace( HttpServletRequest request, HttpServletResponse response )
    {
        throw new HTTPException( HttpServletResponse.SC_METHOD_NOT_ALLOWED );
    }

    /*
     * Método no permitido
     */
    @Override
    public void doHead( HttpServletRequest request, HttpServletResponse response )
    {
        throw new HTTPException( HttpServletResponse.SC_METHOD_NOT_ALLOWED );
    }

    /*
     * Método no permitido
     */
    @Override
    public void doOptions( HttpServletRequest request, HttpServletResponse response )
    {
        throw new HTTPException( HttpServletResponse.SC_METHOD_NOT_ALLOWED );
    }

    /*
     * Enviar la respuesta al cliente
     */
    private void sendResponse(
        final HttpServletResponse response, String payload, final ContentType contentType )
    {
        response.setContentType( contentType.getContentType() );
        try {
            switch (contentType) {
                case JSON:
                    JSONObject json = org.json.XML.toJSONObject( payload );
                    payload = json.toString( 3 ); // nivel de indentación 3
                    break;
                default:
                    break;
            }

            OutputStream out = response.getOutputStream();
            out.write( payload.getBytes() );
            out.flush();
        }
        catch (Exception e) {
            throw new HTTPException( HttpServletResponse.SC_INTERNAL_SERVER_ERROR );
        }
    }

    private String getPathToData()
    {
        String path = this.getInitParameter( "pathToData" );
        if ( path == null ) {
            path = "/WEB-INF/data/predictions.db";
        }
        return path;
    }

    private String toXML( Object obj )
    {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try (XMLEncoder encoder = new XMLEncoder( out )) {
            encoder.writeObject( obj ); // serializar a XML
        }
        catch (Exception e) {
            throw new HTTPException( HttpServletResponse.SC_INTERNAL_SERVER_ERROR );
        }

        return out.toString();
    }
}
