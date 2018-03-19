package mx.gob.sat.siat.base.excepcion;

/**
 * Clase encargada de manejar la exception en la generacion de instancias de los
 * row mappers.
 *
 */
public class RowMapperException extends RuntimeException {

    /**
     * Serial version UID.
     */
    private static final long serialVersionUID = -7704749909423268488L;

    /**
     * Constructor por defecto.
     */
    public RowMapperException() {
        super("RowMapper exception...");
    }

    /**
     * Constructor.
     *
     * @param message
     *            Mensaje de la exception.
     * @param cause
     *            Exception original.
     */
    public RowMapperException(final String message, final Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructor.
     *
     * @param message
     *            Mensaje de la exception.
     */
    public RowMapperException(final String message) {
        super(message);
    }

    /**
     * Constructor.
     *
     * @param cause
     *            Exception original.
     */
    public RowMapperException(final Throwable cause) {
        super(cause);
    }

}
