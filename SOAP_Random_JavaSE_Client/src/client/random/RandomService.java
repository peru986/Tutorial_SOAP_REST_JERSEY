
package client.random;

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
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebService(name = "RandomService", targetNamespace = "http://random/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface RandomService {


    /**
     * 
     * @return
     *     returns int
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "next", targetNamespace = "http://random/", className = "client.random.Next")
    @ResponseWrapper(localName = "nextResponse", targetNamespace = "http://random/", className = "client.random.NextResponse")
    @Action(input = "http://random/RandomService/nextRequest", output = "http://random/RandomService/nextResponse")
    public int next();

    /**
     * 
     * @param arg0
     * @return
     *     returns java.util.List<java.lang.Integer>
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "nextN", targetNamespace = "http://random/", className = "client.random.NextN")
    @ResponseWrapper(localName = "nextNResponse", targetNamespace = "http://random/", className = "client.random.NextNResponse")
    @Action(input = "http://random/RandomService/nextNRequest", output = "http://random/RandomService/nextNResponse")
    public List<Integer> nextN(
        @WebParam(name = "arg0", targetNamespace = "")
        int arg0);

}
