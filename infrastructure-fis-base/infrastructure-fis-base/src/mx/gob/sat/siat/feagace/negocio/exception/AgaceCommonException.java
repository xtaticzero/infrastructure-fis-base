package mx.gob.sat.siat.feagace.negocio.exception;

import mx.gob.sat.siat.base.excepcion.BusinessException;

public class AgaceCommonException extends BusinessException {

    /**
     * Serial
     */
    private static final long serialVersionUID = 1L;

    private static final String CATEGORY = "agace.common";

    public AgaceCommonException(String situation) {
        super(CATEGORY, situation);
    }

    public AgaceCommonException(String situation, Object... args) {
        super(CATEGORY, situation, args);
    }

    public AgaceCommonException(String situation, Throwable cause) {
        super(CATEGORY, situation, cause);
    }

    public AgaceCommonException(String situation, Throwable cause, Object... args) {
        super(CATEGORY, situation, cause, args);
    }

}
