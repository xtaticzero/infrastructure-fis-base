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
public class AgacePropuestaException extends BusinessException {

    private static final long serialVersionUID = -5695979221494687098L;
    private static final String CATEGORY = "agace.propuestas";
    
    public AgacePropuestaException(String situation) {
        super(CATEGORY, situation);
    }

    public AgacePropuestaException(String situation, Object... args) {
        super(CATEGORY, situation, args);
    }

    public AgacePropuestaException(String situation, Throwable cause) {
        super(CATEGORY, situation, cause);
    }

    public AgacePropuestaException(String situation, Throwable cause, Object... args) {
        super(CATEGORY, situation, cause, args);
    }

}
