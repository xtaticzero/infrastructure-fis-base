package mx.gob.sat.siat.feagace.negocio.exception;

import mx.gob.sat.siat.feagace.modelo.util.exceptions.BusinessException;

public class CorreoException extends BusinessException {
    private static final String SUBCATEGORY = " CORREO: ";
    private static final long serialVersionUID = 6872017145719231705L;

    public CorreoException(String situation) {
        super(SUBCATEGORY, situation);
    }

    public CorreoException(String situation, Object... args) {
        super(SUBCATEGORY, situation, args);
    }

}
