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
public class AgacePropuestasRulesException extends BusinessException {

    private static final String CATEGORY = "agace.propuestas.reglas";
    private static final long serialVersionUID = -6829553940043108145L;

    public AgacePropuestasRulesException(String situation) {
        super(CATEGORY, situation);
    }

    public AgacePropuestasRulesException(String situation, Object... args) {
        super(CATEGORY, situation, args);
    }

    public AgacePropuestasRulesException(String situation, Throwable cause) {
        super(CATEGORY, situation, cause);
    }

    public AgacePropuestasRulesException(String situation, Throwable cause, Object... args) {
        super(CATEGORY, situation, cause, args);
    }
}
