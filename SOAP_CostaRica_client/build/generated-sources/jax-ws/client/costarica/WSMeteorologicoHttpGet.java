
package client.costarica;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.6-1b01 
 * Generated source version: 2.2
 * 
 */
@WebService(name = "WSMeteorologicoHttpGet", targetNamespace = "http://tempuri.org/")
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
@XmlSeeAlso({
    ObjectFactory.class
})
public interface WSMeteorologicoHttpGet {


    /**
     * 
     * @return
     *     returns java.lang.String
     */
    @WebMethod(operationName = "PronosticoRegional")
    @WebResult(name = "string", targetNamespace = "http://tempuri.org/", partName = "Body")
    public String pronosticoRegional();

    /**
     * 
     * @param regionID
     * @return
     *     returns java.lang.String
     */
    @WebMethod(operationName = "PronosticoRegionalxID")
    @WebResult(name = "string", targetNamespace = "http://tempuri.org/", partName = "Body")
    public String pronosticoRegionalxID(
        @WebParam(name = "string", targetNamespace = "http://www.w3.org/2001/XMLSchema", partName = "regionID")
        String regionID);

    /**
     * 
     * @return
     *     returns java.lang.String
     */
    @WebMethod(operationName = "PronosticoPorCiudad")
    @WebResult(name = "string", targetNamespace = "http://tempuri.org/", partName = "Body")
    public String pronosticoPorCiudad();

    /**
     * 
     * @param ciudadID
     * @return
     *     returns java.lang.String
     */
    @WebMethod(operationName = "PronosticoPorCiudadxID")
    @WebResult(name = "string", targetNamespace = "http://tempuri.org/", partName = "Body")
    public String pronosticoPorCiudadxID(
        @WebParam(name = "string", targetNamespace = "http://www.w3.org/2001/XMLSchema", partName = "ciudadID")
        String ciudadID);

    /**
     * 
     * @return
     *     returns java.lang.String
     */
    @WebMethod(operationName = "Efemerides")
    @WebResult(name = "string", targetNamespace = "http://tempuri.org/", partName = "Body")
    public String efemerides();

    /**
     * 
     * @return
     *     returns java.lang.String
     */
    @WebMethod(operationName = "Fecha")
    @WebResult(name = "string", targetNamespace = "http://tempuri.org/", partName = "Body")
    public String fecha();

    /**
     * 
     * @return
     *     returns java.lang.String
     */
    @WebMethod(operationName = "Hora")
    @WebResult(name = "string", targetNamespace = "http://tempuri.org/", partName = "Body")
    public String hora();

    /**
     * 
     * @return
     *     returns java.lang.String
     */
    @WebMethod(operationName = "EstadoActualRegional")
    @WebResult(name = "string", targetNamespace = "http://tempuri.org/", partName = "Body")
    public String estadoActualRegional();

    /**
     * 
     * @return
     *     returns java.lang.String
     */
    @WebMethod(operationName = "EstadoActualPorCiudad")
    @WebResult(name = "string", targetNamespace = "http://tempuri.org/", partName = "Body")
    public String estadoActualPorCiudad();

    /**
     * 
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(name = "string", targetNamespace = "http://tempuri.org/", partName = "Body")
    public String pronosticoExtendidoRegion();

    /**
     * 
     * @param regionID
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(name = "string", targetNamespace = "http://tempuri.org/", partName = "Body")
    public String pronosticoExtendidoRegionxID(
        @WebParam(name = "string", targetNamespace = "http://www.w3.org/2001/XMLSchema", partName = "regionID")
        String regionID);

    /**
     * 
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(name = "string", targetNamespace = "http://tempuri.org/", partName = "Body")
    public String pronosticoExtendidoCiudad();

    /**
     * 
     * @param ciudadID
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(name = "string", targetNamespace = "http://tempuri.org/", partName = "Body")
    public String pronosticoExtendidoCiudadxID(
        @WebParam(name = "string", targetNamespace = "http://www.w3.org/2001/XMLSchema", partName = "ciudadID")
        String ciudadID);

}
