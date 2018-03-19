package mx.gob.sat.siat.base.vista;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.nio.charset.Charset;
import java.text.MessageFormat;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import mx.gob.sat.siat.base.constante.CommonConstants;
import mx.gob.sat.siat.base.dto.UserProfileDTO;
import mx.gob.sat.siat.controlador.AccesoProceso;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class BaseManagedBean implements Serializable {

    /**
     * Serial
     */
    private static final long serialVersionUID = 4094548849617062158L;
    
    private static final String DEVELOP = "develop";
    
    /**
     * Propiedad para llevar a cabo un registro de eventos.
     */
    protected final Logger logger = LoggerFactory.getLogger(getClass());

    public static final int CONTRIBUYENTES = 1;
    public static final int EMPLEADOS = 2;
    private static final String ARCHIVO_WORD_DESPUES_2007 = ".docx";
    private static final String CONTENT_TYPE_WORD = "application/msword";
    public static final String ARCHIVO_PDF = ".pdf";
    public static final String CONTENT_TYPE_PDF = "application/pdf";

    protected BaseManagedBean() {
        super();
    }

    /**
     * Obtiene el RFC del usuario autenticado
     */
    public String getRFCSession() {
        return getUserProfile().getRfc();
    }

    /**
     * Obtiene el UserProfile del usuario autenticado
     */
    public UserProfileDTO getUserProfile() {
        return (UserProfileDTO) FacesContext.getCurrentInstance()
                .getExternalContext().getSessionMap()
                .get(CommonConstants.USER_PROFILE);
    }

    public HttpSession getSession() {
        return (HttpSession) FacesContext.getCurrentInstance()
                .getExternalContext().getSession(false);
    }

    public void setUserProfile(UserProfileDTO userProfile) {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
                .put(CommonConstants.USER_PROFILE, userProfile);
    }

    protected String buildRealPath(final String file) {
        ServletContext ctx = (ServletContext) FacesContext.getCurrentInstance()
                .getExternalContext().getContext();
        return null == file || file.trim().isEmpty() ? "" : ctx
                .getRealPath(File.separator) + file;
    }

    /**
     * Valida los procesos dentro de portal de contribuyentes.
     * 
     * @param identificador
     *            el nombre del proceso.
     */
    public void validaAccesoProcesoMenuCont(final String identificador) {
        if (!isDevelop()) {
            validaAccesoProcesoMenu(identificador, CONTRIBUYENTES);    
        }        
    }

    /**
     * Valida los procesos dentro de portal de empleados.
     * 
     * @param identificador
     *            el nombre del proceso.
     */
    public void validaAccesoProcesoMenuEmp(final String identificador) {
        if (!isDevelop()) {
            validaAccesoProcesoMenu(identificador, EMPLEADOS);    
        }        
    }

    protected void validaAccesoProcesoMenu(String identificador, int idMenu) {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
                .getExternalContext().getSession(true);
        HttpServletRequest request = (HttpServletRequest) FacesContext
                .getCurrentInstance().getExternalContext().getRequest();
        try {
            AccesoProceso.validaAccesoProcesoMenu(session, identificador,
                    idMenu);
        } catch (Exception e) {
            logger.error("Error al validar acceso al menu: [{}]", e);
            try {
                session.setAttribute("mensaje", e);
                FacesContext
                        .getCurrentInstance()
                        .getExternalContext()
                        .redirect(
                                request.getContextPath()
                                        + CommonConstants.REDIRECT_ACCESO_DENEGADO);
            } catch (IOException ioe) {
                logger.error("Error al validar acceso al menu: [{}]", ioe);
            }
        }
    }

    private void createMessage(String clientId, String summary, String detail,
            FacesMessage.Severity severity) {
        FacesMessage message = new FacesMessage(severity, summary, detail);
        FacesContext.getCurrentInstance().addMessage(clientId, message);
    }

    /**
     * Agrega un mensaje de informacion.
     * 
     * @param mensaje
     *            mensaje a mostrar.
     */
    public void addMessage(String mensaje) {
        createMessage(null, mensaje, "", FacesMessage.SEVERITY_INFO);
    }

    /**
     * Agrega un mensaje de informacion.
     * 
     * @param clientId
     *            identificador del componente
     * @param mensaje
     *            mensaje a mostrar.
     */
    public void addMessage(String clientId, String mensaje) {
        createMessage(clientId, mensaje, "", FacesMessage.SEVERITY_INFO);
    }

    /**
     * Agrega un mensaje de informacion.
     * 
     * @param clientId
     *            identificador del componente
     * @param mensaje
     *            mensaje a mostrar.
     * @param detalleMensaje
     *            detalle del mensaje.
     */
    public void addMessage(String clientId, String mensaje,
            String detalleMensaje) {
        createMessage(clientId, mensaje, detalleMensaje,
                FacesMessage.SEVERITY_INFO);
    }

    /**
     * Agrega un mensaje de error.
     * 
     * @param mensaje
     *            mensaje a mostrar.
     */
    public void addErrorMessage(String mensaje) {
        createMessage(null, mensaje, "", FacesMessage.SEVERITY_ERROR);
    }

    /**
     * Agrega un mensaje de error sin detalle.
     * @param clientId, identificador del componente.
     * @param msg mensaje a mostrar.
     */
    public static void addErrorMessage(String clientId, String msg) {
        FacesContext.getCurrentInstance()
            .addMessage(clientId, new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null));
    }
    
    /**
     * Agrega un mensaje de error.
     * 
     * @param clientId
     *            identificador del componente
     * @param mensaje
     *            mensaje a mostrar.
     * @param detalleMensaje
     *            detalle del mensaje.
     */
    public void addErrorMessage(String clientId, String mensaje,
            String detalleMensaje) {
        createMessage(clientId, mensaje, detalleMensaje,
                FacesMessage.SEVERITY_ERROR);
    }

    /**
     * Agrega un mensaje fatal.
     * 
     * @param mensaje
     *            mensaje a mostrar.
     */
    public void addFatalMessage(String mensaje) {
        createMessage(null, mensaje, "", FacesMessage.SEVERITY_FATAL);
    }

    /**
     * Agrega un mensaje fatal.
     * 
     * @param clientId
     *            identificador del componente
     * @param mensaje
     *            mensaje a mostrar.
     */
    public void addFatalMessage(String clientId, String mensaje) {
        createMessage(clientId, mensaje, "", FacesMessage.SEVERITY_FATAL);
    }

    /**
     * Agrega un mensaje fatal.
     * 
     * @param clientId
     *            identificador del componente
     * @param mensaje
     *            mensaje a mostrar.
     * @param detalleMensaje
     *            detalle del mensaje.
     */
    public void addFatalMessage(String clientId, String mensaje,
            String detalleMensaje) {
        createMessage(clientId, mensaje, detalleMensaje,
                FacesMessage.SEVERITY_FATAL);
    }

    /**
     * Agrega un mensaje warning.
     * 
     * @param mensaje
     *            mensaje a mostrar.
     */
    public void addWarningMessage(String mensaje) {
        createMessage(null, mensaje, "", FacesMessage.SEVERITY_WARN);
    }

    /**
     * Agrega un mensaje warning.
     * 
     * @param clientId
     *            identificador del componente
     * @param mensaje
     *            mensaje a mostrar.
     */
    public void addWarningMessage(String clientId, String mensaje) {
        createMessage(clientId, mensaje, "", FacesMessage.SEVERITY_WARN);
    }

    /**
     * Agrega un mensaje warning.
     * 
     * @param clientId
     *            identificador del componente
     * @param mensaje
     *            mensaje a mostrar.
     * @param detalleMensaje
     *            detalle del mensaje.
     */
    public void addWarningMessage(String clientId, String mensaje,
            String detalleMensaje) {
        createMessage(clientId, mensaje, detalleMensaje,
                FacesMessage.SEVERITY_WARN);
    }

    /**
     * Obtiene un mensaje del archivo de propiedades de una clave enviada.
     * 
     * @param key
     *            clave a buscar.
     * @param params
     *            uno varios objetos que determinan el formato del mensaje.
     * @return String con el mensaje obtenido.
     */
    public String getMessageResourceString(String key, Object... params) {
        String value = null;
        FacesContext context = FacesContext.getCurrentInstance();
        ResourceBundle bundle = context.getApplication().getResourceBundle(
                context, "msj");

        value = formatMessage(bundle, key, params);
        return value;
    }

    private String formatMessage(ResourceBundle bundle, String key,
            Object... params) {
        String text = null;

        try {
            text = bundle.getString(key);
        } catch (MissingResourceException e) {
            text = "?? key " + key + " not found ??";
            logger.error("Error en ", e);
        }
        if (params != null && params.length > 0) {
            text = MessageFormat.format(text, params);
        }
        return text;
    }

    /**
     * Metodo getDescargaArchivo
     *
     * @param path
     * @param nombreArchivo
     * @return archivo Permite la descarga de el archivos seleccionado
     */
    public StreamedContent getDescargaArchivo(final String path,
            final String nombreDescarga) {
        StreamedContent archivo = null;

        try {
            archivo = new DefaultStreamedContent(new FileInputStream(
                    limpiarPathArchivo(path)),
                    obtenContentTypeArchivo(nombreDescarga),
                    aplicarCodificacionTexto(nombreDescarga));
        } catch (FileNotFoundException e) {
            logger.error("No se pudo descargar el archivo. ", e);
            addErrorMessage("No se encontro el documento seleccionado");
        }

        return archivo;
    }

    private String aplicarCodificacionTexto(final String cadena) {
        String resultado = null;
        try {
            resultado = new String(cadena.getBytes(Charset.forName("UTF-8")));
        } catch (Exception e) {
            resultado = cadena;
            logger.error("Error al codificar el texto [{}]", e);
        }
        return resultado;
    }

    public String limpiarPathArchivo(String path) {
        String fileName;
        int idx = path.lastIndexOf('\\');
        if (idx >= 0) {
            fileName = path.substring(idx + 1);
        } else {
            fileName = path;
        }
        return fileName;
    }

    private String obtenContentTypeArchivo(final String nombreArchivo) {
        String contentType = null;

        if (nombreArchivo.endsWith(ARCHIVO_WORD_DESPUES_2007)) {
            contentType = CONTENT_TYPE_WORD;
        } else if (nombreArchivo.endsWith(ARCHIVO_PDF)) {
            contentType = CONTENT_TYPE_PDF;
        }
        return contentType;
    }

    public boolean isDevelop() {
        ServletContext servletContext = (ServletContext) FacesContext
              .getCurrentInstance().getExternalContext().getContext();

      return Boolean.parseBoolean(servletContext.getInitParameter(DEVELOP));
  }

}
