
package client.predictions;

import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Action;
import javax.xml.ws.FaultAction;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.6-1b01 
 * Generated source version: 2.2
 * 
 */
@WebService(name = "PredictionsSoapHandler", targetNamespace = "http://predictions/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface PredictionsSoapHandler {


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
    @Action(input = "http://predictions/PredictionsSoapHandler/deleteRequest", output = "http://predictions/PredictionsSoapHandler/deleteResponse")
    public String delete(
        @WebParam(name = "arg0", targetNamespace = "")
        int arg0);

    /**
     * 
     * @param arg1
     * @param arg0
     * @return
     *     returns java.lang.String
     * @throws VerbosityException_Exception
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "create", targetNamespace = "http://predictions/", className = "client.predictions.Create")
    @ResponseWrapper(localName = "createResponse", targetNamespace = "http://predictions/", className = "client.predictions.CreateResponse")
    @Action(input = "http://predictions/PredictionsSoapHandler/createRequest", output = "http://predictions/PredictionsSoapHandler/createResponse", fault = {
        @FaultAction(className = VerbosityException_Exception.class, value = "http://predictions/PredictionsSoapHandler/create/Fault/VerbosityException")
    })
    public String create(
        @WebParam(name = "arg0", targetNamespace = "")
        String arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        String arg1)
        throws VerbosityException_Exception
    ;

    /**
     * 
     */
    @WebMethod
    @RequestWrapper(localName = "postConstruct", targetNamespace = "http://predictions/", className = "client.predictions.PostConstruct")
    @ResponseWrapper(localName = "postConstructResponse", targetNamespace = "http://predictions/", className = "client.predictions.PostConstructResponse")
    @Action(input = "http://predictions/PredictionsSoapHandler/postConstructRequest", output = "http://predictions/PredictionsSoapHandler/postConstructResponse")
    public void postConstruct();

    /**
     * 
     * @param arg2
     * @param arg1
     * @param arg0
     * @return
     *     returns java.lang.String
     * @throws VerbosityException_Exception
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "edit", targetNamespace = "http://predictions/", className = "client.predictions.Edit")
    @ResponseWrapper(localName = "editResponse", targetNamespace = "http://predictions/", className = "client.predictions.EditResponse")
    @Action(input = "http://predictions/PredictionsSoapHandler/editRequest", output = "http://predictions/PredictionsSoapHandler/editResponse", fault = {
        @FaultAction(className = VerbosityException_Exception.class, value = "http://predictions/PredictionsSoapHandler/edit/Fault/VerbosityException")
    })
    public String edit(
        @WebParam(name = "arg0", targetNamespace = "")
        int arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        String arg1,
        @WebParam(name = "arg2", targetNamespace = "")
        String arg2)
        throws VerbosityException_Exception
    ;

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
    @Action(input = "http://predictions/PredictionsSoapHandler/getOneRequest", output = "http://predictions/PredictionsSoapHandler/getOneResponse")
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
    @Action(input = "http://predictions/PredictionsSoapHandler/getAllRequest", output = "http://predictions/PredictionsSoapHandler/getAllResponse")
    public List<Prediction> getAll();

}
