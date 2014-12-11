
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
 *         &lt;element name="ciudadID" type="{http://www.w3.org/2001/XMLSchema}int"/>
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
    "ciudadID"
})
@XmlRootElement(name = "pronosticoExtendidoCiudadxID")
public class PronosticoExtendidoCiudadxID {

    protected int ciudadID;

    /**
     * Obtiene el valor de la propiedad ciudadID.
     * 
     */
    public int getCiudadID() {
        return ciudadID;
    }

    /**
     * Define el valor de la propiedad ciudadID.
     * 
     */
    public void setCiudadID(int value) {
        this.ciudadID = value;
    }

}
