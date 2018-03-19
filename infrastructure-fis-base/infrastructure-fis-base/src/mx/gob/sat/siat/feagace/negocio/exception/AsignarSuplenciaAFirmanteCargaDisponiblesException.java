package mx.gob.sat.siat.feagace.negocio.exception;

import mx.gob.sat.siat.feagace.modelo.util.exceptions.BusinessException;

public class AsignarSuplenciaAFirmanteCargaDisponiblesException extends BusinessException {


    private static final String SUBCATEGORY = " AsignarSuplenciaAFirmanteCargaDisponiblesException: ";
   
    private static final long serialVersionUID = 1L;


    public AsignarSuplenciaAFirmanteCargaDisponiblesException(String situation) {
        super(SUBCATEGORY, situation);
    }

    public AsignarSuplenciaAFirmanteCargaDisponiblesException(String situation, Object... args) {
        super(SUBCATEGORY, situation, args);
    }
}
