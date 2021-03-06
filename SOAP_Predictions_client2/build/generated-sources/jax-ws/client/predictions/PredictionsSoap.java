
package client.predictions;

import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Action;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.6-1b01 
 * Generated source version: 2.2
 * 
 */
@WebService(name = "PredictionsSoap", targetNamespace = "http://predictions/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface PredictionsSoap {


    /**
     * 
     * @param arg0
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "delete", targetNamespace = "http://predictions/", className = "client.predictions.Delete")
    @ResponseWrapper(localName = "deleteResponse", targetNamespace = "http://predictions/", className = "client.predictions.DeleteResponse")
    @Action(input = "http://predictions/PredictionsSoap/deleteRequest", output = "http://predictions/PredictionsSoap/deleteResponse")
    public String delete(
        @WebParam(name = "arg0", targetNamespace = "")
        int arg0);

    /**
     * 
     * @param arg1
     * @param arg0
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "create", targetNamespace = "http://predictions/", className = "client.predictions.Create")
    @ResponseWrapper(localName = "createResponse", targetNamespace = "http://predictions/", className = "client.predictions.CreateResponse")
    @Action(input = "http://predictions/PredictionsSoap/createRequest", output = "http://predictions/PredictionsSoap/createResponse")
    public String create(
        @WebParam(name = "arg0", targetNamespace = "")
        String arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        String arg1);

    /**
     * 
     */
    @WebMethod
    @RequestWrapper(localName = "postConstruct", targetNamespace = "http://predictions/", className = "client.predictions.PostConstruct")
    @ResponseWrapper(localName = "postConstructResponse", targetNamespace = "http://predictions/", className = "client.predictions.PostConstructResponse")
    @Action(input = "http://predictions/PredictionsSoap/postConstructRequest", output = "http://predictions/PredictionsSoap/postConstructResponse")
    public void postConstruct();

    /**
     * 
     * @param arg2
     * @param arg1
     * @param arg0
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "edit", targetNamespace = "http://predictions/", className = "client.predictions.Edit")
    @ResponseWrapper(localName = "editResponse", targetNamespace = "http://predictions/", className = "client.predictions.EditResponse")
    @Action(input = "http://predictions/PredictionsSoap/editRequest", output = "http://predictions/PredictionsSoap/editResponse")
    public String edit(
        @WebParam(name = "arg0", targetNamespace = "")
        int arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        String arg1,
        @WebParam(name = "arg2", targetNamespace = "")
        String arg2);

    /**
     * 
     * @param arg0
     * @return
     *     returns client.predictions.Prediction
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "getOne", targetNamespace = "http://predictions/", className = "client.predictions.GetOne")
    @ResponseWrapper(localName = "getOneResponse", targetNamespace = "http://predictions/", className = "client.predictions.GetOneResponse")
    @Action(input = "http://predictions/PredictionsSoap/getOneRequest", output = "http://predictions/PredictionsSoap/getOneResponse")
    public Prediction getOne(
        @WebParam(name = "arg0", targetNamespace = "")
        int arg0);

    /**
     * 
     * @return
     *     returns java.util.List<client.predictions.Prediction>
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "getAll", targetNamespace = "http://predictions/", className = "client.predictions.GetAll")
    @ResponseWrapper(localName = "getAllResponse", targetNamespace = "http://predictions/", className = "client.predictions.GetAllResponse")
    @Action(input = "http://predictions/PredictionsSoap/getAllRequest", output = "http://predictions/PredictionsSoap/getAllResponse")
    public List<Prediction> getAll();

}
