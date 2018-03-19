/*
* Todos los Derechos Reservados 2013 SAT.
* Servicio de Administracion Tributaria (SAT).
*
* Este software contiene informacion propiedad exclusiva del SAT considerada
* Confidencial. Queda totalmente prohibido su uso o divulgacion en forma
* parcial o total.
*/
package mx.gob.sat.siat.base.service;

import java.io.Serializable;

/**
 * Clase principal que sirve de base para los servicios de negocio
 * @author softtek
 *
 */
public class BaseBusinessServices extends BaseSupportServices implements Serializable {

	/**
	 * Numero de version
	 */
	private static final long serialVersionUID = 942797157416653571L;

	/**
     * Constructor de la clase
     */
    protected BaseBusinessServices() {
        super();
    }

}
