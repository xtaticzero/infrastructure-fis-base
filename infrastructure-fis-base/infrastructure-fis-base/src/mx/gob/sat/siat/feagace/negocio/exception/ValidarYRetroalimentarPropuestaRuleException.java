/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.sat.siat.feagace.negocio.exception;

import mx.gob.sat.siat.base.excepcion.AgacePropuestasRulesException;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez
 */
public class ValidarYRetroalimentarPropuestaRuleException extends AgacePropuestasRulesException {

    private static final long serialVersionUID = 1233677460760794142L;

    public ValidarYRetroalimentarPropuestaRuleException(String situation) {
        super(situation);
    }

    public ValidarYRetroalimentarPropuestaRuleException(String situation, Object... args) {
        super(situation, args);
    }

    public ValidarYRetroalimentarPropuestaRuleException(String situation, Throwable cause) {
        super(situation, cause);
    }

    public ValidarYRetroalimentarPropuestaRuleException(String situation, Throwable cause,
            Object... args) {
        super(situation, cause, args);
    }
}
