package mx.gob.sat.siat.feagace.negocio.exception;

/**
 * Clase que representa una Exception a nivel de negocio del sistema de marbetes
 */
public class NegocioException extends Exception {
    /**
     *
     */
    private static final long serialVersionUID = 1494632012689711163L;

    /**
     * Error que se envuelve.
     */
    private final Throwable throwable;

    /**
     * Constructor 'NegocioException'
     *
     * @param message
     *            Mensaje de error
     */
    public NegocioException(String message) {
        super(message);
        throwable = null;
    }

    /**
     * Constructor 'NegocioException'
     *
     * @param message
     *            Mensaje de error
     * @param throwable
     *            Error de origen.
     */
    public NegocioException(String message, Throwable throwable) {
        super(message);
        this.throwable = throwable;
    }

    /**
     * Method 'getCause'
     *
     * @return Throwable
     */
    public Throwable getCause() {
        return throwable;
    }
}
