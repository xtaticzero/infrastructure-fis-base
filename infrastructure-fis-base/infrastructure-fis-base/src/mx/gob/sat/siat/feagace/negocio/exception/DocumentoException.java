package mx.gob.sat.siat.feagace.negocio.exception;

import mx.gob.sat.siat.feagace.modelo.util.exceptions.BusinessException;

public class DocumentoException extends BusinessException {

    private static final String SUBCATEGORY = " DOCUMENTO: ";
    private static final long serialVersionUID = 6872017145719231705L;

    public DocumentoException(String situation) {
        super(SUBCATEGORY, situation);
    }

    public DocumentoException(String situation, Object... args) {
        super(SUBCATEGORY, situation, args);
    }

}
