package mx.gob.sat.siat.feagace.negocio.exception;

import mx.gob.sat.siat.feagace.modelo.util.exceptions.BusinessException;

public class AsignarSuplenciaAFirmanteCargaInfoExcption extends BusinessException {


    private static final String SUBCATEGORY = " AsignarSuplenciaCargarInformacion: ";
    @SuppressWarnings("compatibility:-1529869945324534057")
    private static final long serialVersionUID = 1L;


    public AsignarSuplenciaAFirmanteCargaInfoExcption(String situation) {
        super(SUBCATEGORY, situation);
    }

    public AsignarSuplenciaAFirmanteCargaInfoExcption(String situation, Object... args) {
        super(SUBCATEGORY, situation, args);
    }
}
