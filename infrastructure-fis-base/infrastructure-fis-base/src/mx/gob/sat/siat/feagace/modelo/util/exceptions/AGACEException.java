package mx.gob.sat.siat.feagace.modelo.util.exceptions;

import java.util.IllegalFormatConversionException;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class AGACEException extends Exception {

    /**
     * Numero de version
     */
    private static final long serialVersionUID = 2619517140543845683L;

    /**
     * Categoria de la excepcion
     */
    private String category;

    /**
     * Categoria de la excepcion
     */
    private String subCategory;

    /**
     * Situacion de la excepcion
     */
    private String situation;

    /**
     * Argumentos de la excepcion
     */
    private Object[] args;

    /**
     * Causa de la excepcion
     */
    private Throwable cause;

    /**
     * Mensaje de la excepcion
     */
    private String message;

    /**
     * Constante para una linea separadora
     */
    private static final String LINE_SEPARATOR = System.getProperty("line.separator");

    /**
     * Variable que guarda el nombre del paquete de datos
     */
    private static String exceptionMessagesResourceBundle;


    /**
     * Constructor de la clase
     */
    protected AGACEException() {
        super();
    }

    /**
     * Sobrecarga del constructor
     * @param category
     * @param situation
     */
    public AGACEException(final String category, final String subCategory, final String situation) {
        this();

        this.category = category;
        this.subCategory = subCategory;
        this.situation = situation;
    }

    /**
     * Sobrecarga del constructor
     * @param category
     * @param situation
     * @param args
     */
    public AGACEException(final String category, final String subCategory, final String situation,
                          final Object... args) {
        this(category, subCategory, situation);

        this.args = (null == args ? null : args.clone());
    }

    /**
     * Sobrecarga del constructor
     * @param category
     * @param situation
     * @param cause
     */
    public AGACEException(final String category, final String subCategory, final String situation,
                          final Throwable cause) {
        this(category, subCategory, situation);

        this.cause = cause;
    }

    /**
     * Sobrecarga del constructor
     * @param category
     * @param situation
     * @param cause
     * @param args
     */
    public AGACEException(final String category, final String subCategory, final String situation,
                          final Throwable cause, final Object... args) {
        this(category, subCategory, situation, args);

        this.cause = cause;
    }

    /**
     * Se obtiene la categoria
     * @return category
     */
    public String getCategory() {
        return category;
    }

    public String getSubCategory() {
        return subCategory;
    }

    /**
     * se obtiene la situacion de la excepcion
     * @return situation
     */
    public String getSituation() {
        return situation;
    }

    /**
     * Obtiene los argumentos
     * @return args
     */
    public Object[] getArgs() {
        return (null == args ? null : args.clone());
    }

    /**
     *
     * @param args a fijar
     */
    protected void setArgs(final Object[] args) {
        this.args = (null == args ? null : args.clone());
    }

    /**
     * Se obtiene la causa de la excepcion
     * @return cause
     */
    public Throwable getCause() {
        return cause;
    }

    /**
     * Se obtiene el mensaje de escepcion
     * @return message
     */
    public String getMessage() {
        if (null == message) {
            message = getExceptionMessage(Locale.getDefault());
        }

        return message;
    }

    /**
     * Metodo para lozalizar el mensaje
     * @param locale
     * @return getExceptionMessage
     */
    public String getLocalizedMessage(final Locale locale) {
        return getExceptionMessage(locale);
    }

    /**
     * Metodo para dar formato al mensaje
     */
    public String toString() {
        final StringBuffer formattedMessage = new StringBuffer();

        formattedMessage.append("Class name: ").append(getClass().getName());

        if (null != getMessage() && getMessage().trim().length() > 0) {
            formattedMessage.append(LINE_SEPARATOR).append("   Message: ").append(getMessage());
        }

        if (null != getCause()) {
            formattedMessage.append(LINE_SEPARATOR).append("     Cause: ").append(getCause());
        }

        return formattedMessage.toString();
    }

    /**
     * Metodo para obtener el mensaje de error
     * @param locale
     * @return exceptionMessage
     */
    private String getExceptionMessage(final Locale locale) {
        String exceptionMessage = "<Invalid exception category and/or situation>";

        ResourceBundle resources = null;
        exceptionMessagesResourceBundle = "";

        try {
            resources = ResourceBundle.getBundle(exceptionMessagesResourceBundle, locale);
        } catch (MissingResourceException mre) {
            exceptionMessage = "<Resource bundle: [" + exceptionMessagesResourceBundle + "] does not exist>";
        } catch (Exception npe) {
            // Void
        }


        if (null != resources) {
            try {
                exceptionMessage = resources.getString(getExceptionMessageKey());
            } catch (MissingResourceException mre) {
                exceptionMessage = "<No entry found for: [" + getExceptionMessageKey() + "] key>";
            }

            catch (Exception npe) {
                // Void
            }

        }

        if (null != args) {
            try {
                exceptionMessage = String.format(exceptionMessage, args);
            } catch (IllegalFormatConversionException ifce) {
                // Void
            }
        }

        return exceptionMessage;
    }

    /**
     * Se obtiene la calve de categoria o situacion
     * @return key
     */
    private String getExceptionMessageKey() {
        final StringBuffer key = new StringBuffer();

        if (null != category) {
            key.append(category);
        }

        if (null != subCategory) {
            key.append(".").append(subCategory);
        }

        if (null != situation) {
            key.append(".").append(situation);
        }

        return key.toString();
    }
}
