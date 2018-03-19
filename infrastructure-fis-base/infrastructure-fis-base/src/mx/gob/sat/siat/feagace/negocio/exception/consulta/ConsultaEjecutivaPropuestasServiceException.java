/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.sat.siat.feagace.negocio.exception.consulta;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez
 */
public class ConsultaEjecutivaPropuestasServiceException extends ConsultaAbstractServiceException {

    private static final long serialVersionUID = -2332703747729998547L;

    private static final String CATEGORY = "agace.negocio.consulta.propuestas.error";

    public ConsultaEjecutivaPropuestasServiceException(String situation) {
        super(CATEGORY, situation);
    }

    public ConsultaEjecutivaPropuestasServiceException(String situation, Object... args) {
        super(CATEGORY, situation, args);
    }

    public ConsultaEjecutivaPropuestasServiceException(String situation, Throwable cause) {
        super(CATEGORY, situation, cause);
    }

    public ConsultaEjecutivaPropuestasServiceException(String situation, Throwable cause, Object... args) {
        super(CATEGORY, situation, cause, args);
    }
}
