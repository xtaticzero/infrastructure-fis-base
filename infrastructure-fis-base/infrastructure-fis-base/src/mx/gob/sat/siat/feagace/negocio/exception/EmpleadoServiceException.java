/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.sat.siat.feagace.negocio.exception;

import java.text.MessageFormat;
import mx.gob.sat.siat.base.excepcion.BusinessException;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez
 */
public class EmpleadoServiceException extends BusinessException {
    private static final long serialVersionUID = 2585486759425763663L;

    private static final String CATEGORY = "agace.common.empleado.service.error";

    public EmpleadoServiceException(String situation) {
        super(CATEGORY, situation);
    }

    public EmpleadoServiceException(String situation, Object... args) {
        super(CATEGORY, situation, args);
    }

    public EmpleadoServiceException(String situation, Throwable cause) {
        super(CATEGORY, situation, cause);
    }

    public EmpleadoServiceException(String situation, Throwable cause, Object... args) {
        super(CATEGORY, situation, cause, args);
    }

    @Override
    public String getMessage() {
        if (getArgs() != null) {
            return MessageFormat.format(super.getMessage(), getArgs());
        } else {
            return super.getMessage();
        }
    }
}
