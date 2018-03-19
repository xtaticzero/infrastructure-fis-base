/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.sat.siat.base.excepcion;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez
 */
public class ValidarRetroalimentarPropuestaException extends AgacePropuestaException {

    private static final long serialVersionUID = -7624359996347691886L;

    public ValidarRetroalimentarPropuestaException(String situation) {
        super(situation);
    }

    public ValidarRetroalimentarPropuestaException(String situation, Object... args) {
        super(situation, args);
    }

    public ValidarRetroalimentarPropuestaException(String situation, Throwable cause) {
        super(situation, cause);
    }

    public ValidarRetroalimentarPropuestaException(String situation, Throwable cause,
            Object... args) {
        super(situation, cause, args);
    }
}
