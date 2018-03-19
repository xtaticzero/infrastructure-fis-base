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
public class CatalogosServiceException extends BusinessException {

    private static final long serialVersionUID = 6978712413283454071L;

    private static final String CATEGORY = "agace.common.catalogos";
    
    public CatalogosServiceException(String situation) {
        super(CATEGORY, situation);
    }

    public CatalogosServiceException(String situation, Object... args) {
        super(CATEGORY, situation, args);
    }

    public CatalogosServiceException(String situation, Throwable cause) {
        super(CATEGORY, situation, cause);
    }

    public CatalogosServiceException(String situation, Throwable cause, Object... args) {
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
