/*
* Todos los Derechos Reservados 2013 SAT.
* Servicio de Administracion Tributaria (SAT).
*
* Este software contiene informacion propiedad exclusiva del SAT considerada
* Confidencial. Queda totalmente prohibido su uso o divulgacion en forma
* parcial o total.
*/
package mx.gob.sat.siat.base.helper;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Interfaz que servira para las clases Helper
 * @author softtek
 *
 */
public class BaseHelper implements Serializable {

    /**
     * Numero de version
     */
    private static final long serialVersionUID = 3867167003889199813L;
    /**
     * Propiedad para llevar a cabo un registro de eventos.
     */
    protected final transient Logger logger = LoggerFactory.getLogger(getClass());

}
