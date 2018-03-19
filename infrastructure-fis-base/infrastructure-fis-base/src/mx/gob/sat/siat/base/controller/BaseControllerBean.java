/*
 *  Todos los Derechos Reservados © 2013 SAT
 *  Servicio de Administracion Tributaria (SAT).
 *  
 *  Este software contiene informacion propiedad exclusiva del SAT considerada
 *  Confidencial. Queda totalmente prohibido su uso o divulgacion en forma
 *  parcial o total.
 */

package mx.gob.sat.siat.base.controller;

import java.io.Serializable;
import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.servlet.ServletContext;
import mx.gob.sat.siat.controlador.AccesoProceso;
import mx.gob.sat.siat.base.dto.UserProfileDTO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Clase Base para los ControllerBean
 * 
 * Bean que implementan la logica de negocio, consumiendo objetos del
 * modelo por medio de ModelBeans
 * 
 * @author Softtek
 */
public abstract class BaseControllerBean implements Serializable {
    /**
     * Propiedad para llevar a cabo un registro de eventos.
     */
    protected final transient Logger logger = LoggerFactory.getLogger(getClass());
    /**
     * Numero de serie
     */
    private static final long serialVersionUID = -7509780772730323110L;
    
    
    /** Constructor vacio */
    protected BaseControllerBean() {
        super();
    }

    /**
     * 
     * @return UserProfileDTO
     */
    public UserProfileDTO getUserProfile() {
        return (UserProfileDTO) FacesContext.getCurrentInstance().getExternalContext().getSessionMap()
                .get("userProfile");
    }
	
	public HttpSession getSession() {
        return (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
    }

    /**
     * 
     * @param userProfile
     *            el userProfile a fijar
     */
    public void setUserProfile(UserProfileDTO userProfile) {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("userProfile", userProfile);
    }

    /**
     * 
     * @return Flash
     */
    public Flash getFlash() {
        return FacesContext.getCurrentInstance().getExternalContext().getFlash();
    }

    protected String buildRealPath(final String file) {
        ServletContext ctx = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
        return null == file || file.trim().isEmpty() ? "" : ctx.getRealPath("/") + file;
    }


	protected void validaAccesoProcesoMenu( String identificador, int idMenu) {
		HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		try {
			
			AccesoProceso.validaAccesoProcesoMenu(session,identificador,idMenu); 
		} catch (Exception e) {
			try {
				
					session.setAttribute("mensaje", e); 
       
				FacesContext.getCurrentInstance().getExternalContext().redirect(request.getContextPath() +  "/resources/pages/error/accesoDenegado.jsf");
			} catch (IOException ioe) {
				logger.error("Error al validar acceso al menu: ", ioe);
			}
		}			
	}
	
	protected void validaAccesoContribuytente() {
		
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		UserProfileDTO userProfile = (UserProfileDTO)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userProfile");
		
		if(!userProfile.getEsContribuyente()){
			try {
			    FacesContext.getCurrentInstance().getExternalContext().redirect(request.getContextPath() +  "/resources/pages/error/accesoDenegado.jsf");
			}catch(IOException e){
				logger.error("Error al validar acceso del contribuyente: ", e);
			}
		}
	}
	
	protected void validaAccesoRolEmpleado(String rol) {
		
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		UserProfileDTO userProfile = (UserProfileDTO)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userProfile");
		boolean accesoValido = false;
		
		logger.debug("rol: {}", rol);
		logger.debug("userProfile.getRolesNovell(): {}", userProfile.getRolesNovell());
		if(null != userProfile.getRolesNovell()){
		    if(userProfile.getRolesNovell().contains(rol)){
                accesoValido = true;
		    }
		}
		if(!accesoValido) {
		    try {
		        FacesContext.getCurrentInstance().getExternalContext().redirect(request.getContextPath() +  "/resources/pages/error/accesoDenegado.jsf");
		    }catch(IOException e){
		    	logger.error("Error al validar acceso del rol: ", e);
		    }
	    }
	}

    protected void validaAccesoAnonimo() {
		
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		UserProfileDTO userProfile = (UserProfileDTO)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userProfile");
		
		if(!userProfile.isAnonimo()){
			try {
			    FacesContext.getCurrentInstance().getExternalContext().redirect(request.getContextPath() +  "/resources/pages/error/accesoDenegado.jsf");
			}catch(IOException e){
				logger.error("Error al validar acceso de anonimo: ", e);
			}
		}
	}
	
	protected void validaAccesoMultiplesRoles(List<String> roles) {
		
		HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		UserProfileDTO userProfile = (UserProfileDTO)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userProfile");
		
		logger.debug("Roles: {}", roles);
		logger.debug("userProfile.getRolesNovell(): {}", userProfile.getRolesNovell());
		boolean accesoValido = false;
		if(null != userProfile.getRolesNovell()){
		    for(String rol : roles){
		        if(userProfile.getRolesNovell().contains(rol)){
                    accesoValido = true;
		    		break;
		        }
	        }
		}
		if(!accesoValido) {
		    try {
		        FacesContext.getCurrentInstance().getExternalContext().redirect(request.getContextPath() +  "/resources/pages/error/accesoDenegado.jsf");
		    }catch(IOException e){
		    	logger.error("Error al validar acceso de los roles: ", e);
		    }
	    }
	}
}
