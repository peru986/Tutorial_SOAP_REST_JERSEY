
package client.costarica;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para anonymous complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="EstadoActualRegionalResult" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "estadoActualRegionalResult"
})
@XmlRootElement(name = "EstadoActualRegionalResponse")
public class EstadoActualRegionalResponse {

    @XmlElement(name = "EstadoActualRegionalResult")
    protected String estadoActualRegionalResult;

    /**
     * Obtiene el valor de la propiedad estadoActualRegionalResult.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEstadoActualRegionalResult() {
        return estadoActualRegionalResult;
    }

    /**
     * Define el valor de la propiedad estadoActualRegionalResult.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEstadoActualRegionalResult(String value) {
        this.estadoActualRegionalResult = value;
    }

}
