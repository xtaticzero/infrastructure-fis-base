/*
* Todos los Derechos Reservados 2013 SAT.
* Servicio de Administracion Tributaria (SAT).
*
* Este software contiene informacion propiedad exclusiva del SAT considerada
* Confidencial. Queda totalmente prohibido su uso o divulgacion en forma
* parcial o total.
*/
package mx.gob.sat.siat.base.dao.domain;

import java.io.Serializable;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * Clase principal para las clases model
 * @author softtek
 *
 */
public class BaseModel implements Serializable {

    /**
     * Numero de serie
     */
    private static final long serialVersionUID = -1252375230090280338L;

  public String toString()
  {
    
      return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE); 
    
  }
}
