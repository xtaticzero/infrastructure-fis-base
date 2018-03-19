/**
 * 
 */
package mx.gob.sat.siat.feagace.negocio.exception;

/**
 * @author irving.martinez
 *
 */
public class NoSeGeneroReporteException extends AgaceCommonException {
    
    /**
     * Serial
     */
    private static final long serialVersionUID = 1L;

    public NoSeGeneroReporteException(String situation) {
        super(situation);
    }

    public NoSeGeneroReporteException(String situation, Object... args) {
        super(situation, args);
    }

    public NoSeGeneroReporteException(String situation, Throwable cause) {
        super(situation, cause);
    }

    public NoSeGeneroReporteException(String situation, Throwable cause,
            Object... args) {
        super(situation, cause, args);
    }

}
