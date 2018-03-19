/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.sat.siat.feagace.negocio.exception.consulta;

import java.text.MessageFormat;
import mx.gob.sat.siat.base.excepcion.BusinessException;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez
 */
public abstract class ConsultaAbstractServiceException extends BusinessException {

    private static final long serialVersionUID = -8202675773759425166L;

    public ConsultaAbstractServiceException(String category, String situation) {
        super(category, situation);
    }

    public ConsultaAbstractServiceException(String category, String situation, Object... args) {
        super(category, situation, args);
    }

    public ConsultaAbstractServiceException(String category, String situation, Throwable cause) {
        super(category, situation, cause);
    }

    public ConsultaAbstractServiceException(String category, String situation, Throwable cause, Object... args) {
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
