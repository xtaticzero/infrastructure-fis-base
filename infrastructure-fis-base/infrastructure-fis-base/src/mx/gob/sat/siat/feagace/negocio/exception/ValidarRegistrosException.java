package mx.gob.sat.siat.feagace.negocio.exception;

import mx.gob.sat.siat.base.excepcion.BusinessException;

public class ValidarRegistrosException extends BusinessException {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private static final String CATEGORY = "agace.orden.consultarreimprimir";

    public ValidarRegistrosException(String situation) {
        super(CATEGORY, situation);
    }
}
