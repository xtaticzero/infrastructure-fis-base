package mx.gob.sat.siat.feagace.negocio.exception;

import mx.gob.sat.siat.feagace.modelo.util.exceptions.BusinessException;

public class AsignarSuplenciaAFirmanteUpdateException extends BusinessException {


    private static final String SUBCATEGORY = " AsignarSuplenciaAFirmanteUpdate: ";
    @SuppressWarnings("compatibility:5563786476476370529")
    private static final long serialVersionUID = 1L;

    public AsignarSuplenciaAFirmanteUpdateException(String situation) {
        super(SUBCATEGORY, situation);
    }

    public AsignarSuplenciaAFirmanteUpdateException(String situation, Object... args) {
        super(SUBCATEGORY, situation, args);
    }
}
