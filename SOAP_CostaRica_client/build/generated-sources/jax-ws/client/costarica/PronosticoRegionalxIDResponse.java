
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
 *         &lt;element name="PronosticoRegionalxIDResult" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "pronosticoRegionalxIDResult"
})
@XmlRootElement(name = "PronosticoRegionalxIDResponse")
public class PronosticoRegionalxIDResponse {

    @XmlElement(name = "PronosticoRegionalxIDResult")
    protected String pronosticoRegionalxIDResult;

    /**
     * Obtiene el valor de la propiedad pronosticoRegionalxIDResult.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPronosticoRegionalxIDResult() {
        return pronosticoRegionalxIDResult;
    }

    /**
     * Define el valor de la propiedad pronosticoRegionalxIDResult.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPronosticoRegionalxIDResult(String value) {
        this.pronosticoRegionalxIDResult = value;
    }

}
