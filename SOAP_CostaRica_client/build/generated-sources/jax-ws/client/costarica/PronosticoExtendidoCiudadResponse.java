
package client.costarica;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
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
 *         &lt;element name="pronosticoExtendidoCiudadResult" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "pronosticoExtendidoCiudadResult"
})
@XmlRootElement(name = "pronosticoExtendidoCiudadResponse")
public class PronosticoExtendidoCiudadResponse {

    protected String pronosticoExtendidoCiudadResult;

    /**
     * Obtiene el valor de la propiedad pronosticoExtendidoCiudadResult.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPronosticoExtendidoCiudadResult() {
        return pronosticoExtendidoCiudadResult;
    }

    /**
     * Define el valor de la propiedad pronosticoExtendidoCiudadResult.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPronosticoExtendidoCiudadResult(String value) {
        this.pronosticoExtendidoCiudadResult = value;
    }

}
