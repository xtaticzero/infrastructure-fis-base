package mx.gob.sat.siat.feagace.negocio.exception;

public class ExpedienteNoEncontradoException extends Exception{
    @SuppressWarnings("compatibility:-3072582994535453756")
    private static final long serialVersionUID = 1L;
    private final Throwable throwable;
    
    public ExpedienteNoEncontradoException(String message) {
        super(message);
        throwable = null;
    }
    
    public ExpedienteNoEncontradoException(String message, Throwable throwable) {
        super(message);
        this.throwable = throwable;
    }    
    
    public Throwable getCause() {
            return throwable;
    }    
}
