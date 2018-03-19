package mx.gob.sat.siat.feagace.negocio.exception;

import mx.gob.sat.siat.feagace.modelo.util.exceptions.BusinessException;

public class PistasAuditoriaException extends BusinessException {

    /**
     * 
     */
    private static final long serialVersionUID = -2730673104510853876L;

    private static final String SUBCATEGORY = " PISTAS DE AUDITORÍA: ";

    public PistasAuditoriaException(String situation) {
        super(SUBCATEGORY, situation);
    }

    public PistasAuditoriaException(String situation, Object... args) {
        super(SUBCATEGORY, situation, args);
    }
}
