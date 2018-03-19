package mx.gob.sat.siat.feagace.negocio.exception;

import mx.gob.sat.siat.feagace.modelo.util.exceptions.BusinessException;

public class AuditorException extends BusinessException {

    /**
     *
     */
    private static final long serialVersionUID = 365297732426892247L;

    private static final String SUBCATEGORY = " AUDITOR: ";

    public AuditorException(String situation) {
        super(SUBCATEGORY, situation);
    }

    public AuditorException(String situation, Object... args) {
        super(SUBCATEGORY, situation, args);
    }

}
