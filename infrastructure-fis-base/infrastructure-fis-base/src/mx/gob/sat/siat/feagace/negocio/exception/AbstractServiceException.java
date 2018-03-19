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
public abstract class AbstractServiceException extends BusinessException{
    private static final long serialVersionUID = 4264160975781408145L;
    
    public AbstractServiceException(String category, String situation) {
        super(category, situation);
    }

    public AbstractServiceException(String category, String situation, Object... args) {
        super(category, situation, args);
    }

    public AbstractServiceException(String category, String situation, Throwable cause) {
        super(category, situation, cause);
    }

    public AbstractServiceException(String category, String situation, Throwable cause, Object... args) {
        super(category, situation, cause, args);
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
