/*
* Todos los Derechos Reservados 2013 SAT.
* Servicio de Administracion Tributaria (SAT).
*
* Este software contiene informacion propiedad exclusiva del SAT considerada
* Confidencial. Queda totalmente prohibido su uso o divulgacion en forma
* parcial o total.
*/
package mx.gob.sat.siat.common.correo.services;

import java.io.Serializable;

import mx.gob.sat.siat.common.correo.exception.MailException;
import mx.gob.sat.siat.common.correo.model.MailMessage;

/**
 * Interfaz para los servicios de correo
 * @author softtek
 *
 */
public interface MailServices extends Serializable {
    /**
     * Metodo para el envio de un correo HTML
     * @param message
     * @throws MailException
     */
    void sendHTMLMessage(MailMessage message) throws MailException;

    /**
     * Metodo para el envio del texto de un correo
     * @param message
     * @throws MailException
     */
    void sendTextMessage(MailMessage message) throws MailException;
    
    void getMessagesFromInbox();
}
