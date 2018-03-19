package mx.gob.sat.siat.feagace.negocio.exception.consulta;

public class ConsultaEjecutivaOrdenesServiceException extends ConsultaAbstractServiceException {

    private static final long serialVersionUID = -8625671524042887317L;
    
    private static final String CATEGORY = "agace.negocio.consulta.ordenes.error";

    public ConsultaEjecutivaOrdenesServiceException(String situation) {
        super(CATEGORY, situation);
    }

    public ConsultaEjecutivaOrdenesServiceException(String situation, Object... args) {
        super(CATEGORY, situation, args);
    }

    public ConsultaEjecutivaOrdenesServiceException(String situation, Throwable cause) {
        super(CATEGORY, situation, cause);
    }

    public ConsultaEjecutivaOrdenesServiceException(String situation, Throwable cause, Object... args) {
        super(CATEGORY, situation, cause, args);
    }

}
