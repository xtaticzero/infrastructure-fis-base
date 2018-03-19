package mx.gob.sat.siat.feagace.negocio.exception;

import mx.gob.sat.siat.feagace.modelo.util.exceptions.BusinessException;

public class DiasFestivosException extends BusinessException {

    /**
     * 
     */
    private static final long serialVersionUID = -2730673104510853876L;
    /**
     * 
     */

    private static final String SUBCATEGORY = " DÍAS FESTIVOS: ";

    public DiasFestivosException(String situation) {
        super(SUBCATEGORY, situation);
    }

    public DiasFestivosException(String situation, Object... args) {
        super(SUBCATEGORY, situation, args);
    }
}
