package mx.gob.sat.siat.feagace.negocio.exception;

public class NoExisteContribuyenteException extends AgaceCommonException {
    
    /**
     * Serial
     */
    private static final long serialVersionUID = 1L;

    public NoExisteContribuyenteException(String situation) {
        super(situation);
    }

    public NoExisteContribuyenteException(String situation, Object... args) {
        super(situation, args);
    }

    public NoExisteContribuyenteException(String situation, Throwable cause) {
        super(situation, cause);
    }

    public NoExisteContribuyenteException(String situation, Throwable cause,
            Object... args) {
        super(situation, cause, args);
    }

}
