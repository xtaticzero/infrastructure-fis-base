package mx.gob.sat.siat.feagace.negocio.exception;

public class NoExisteEmpleadoException extends AgaceCommonException {

    /**
     * Serial
     */
    private static final long serialVersionUID = 1L;

    public NoExisteEmpleadoException(String situation) {
        super(situation);
    }

    public NoExisteEmpleadoException(String situation, Object... args) {
        super(situation, args);
    }

    public NoExisteEmpleadoException(String situation, Throwable cause) {
        super(situation, cause);
    }

    public NoExisteEmpleadoException(String situation, Throwable cause,
            Object... args) {
        super(situation, cause, args);
    }

}
