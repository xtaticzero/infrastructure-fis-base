package mx.gob.sat.siat.feagace.modelo.util.exceptions;

public class BusinessException extends AGACEException {

    private static final String CATEGORY = "AGACE: ";
    @SuppressWarnings("compatibility:-1393030571045540879")
    private static final long serialVersionUID = 1L;


    public BusinessException(String subcategory, String situation) {
        super(CATEGORY, subcategory, situation);
    }


    public BusinessException(String subcategory, String situation, Object... args) {
        super(CATEGORY, subcategory, situation, args);
    }

    /**
     * Constructor de clase con parametros de situacion y de causa
     * @param situation
     * @param cause
     */
    public BusinessException(String subcategory, String situation, Throwable cause) {
        super(CATEGORY,subcategory, situation, cause);
    }

    /**
     * Constructor de clase con parametros de situacion, causa y ninguno o varios argumentos
     * @param situation
     * @param cause
     * @param args
     */
    public BusinessException(String subcategory,String situation, Throwable cause, Object... args) {
        super(CATEGORY,subcategory, situation, cause, args);
    }
}
