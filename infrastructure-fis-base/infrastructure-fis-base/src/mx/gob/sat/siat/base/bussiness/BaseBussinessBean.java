/*
 *  Todos los Derechos Reservados © 2013 SAT
 *  Servicio de Administracion Tributaria (SAT).
 *  
 *  Este software contiene informacion propiedad exclusiva del SAT considerada
 *  Confidencial. Queda totalmente prohibido su uso o divulgacion en forma
 *  parcial o total.
 */

package mx.gob.sat.siat.base.bussiness;

import java.io.Serializable;

import javax.faces.context.FacesContext;

import mx.gob.sat.siat.base.dto.UserProfileDTO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Clase Base para los BussinessBean
 * 
 * Bean que implementan la logica de negocio y brindan soporte a los
 * BackingBeans
 * 
 * @author Softtek
 */
public class BaseBussinessBean implements Serializable {

    protected final transient Logger logger = LoggerFactory.getLogger(getClass());

    private static final long serialVersionUID = 1L;

    /** Constructor vacio */
    protected BaseBussinessBean() {
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

    /**
     * 
     * @param userProfile
     *            el userProfile a fijar.
     */
    public void setUserProfile(UserProfileDTO userProfile) {
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("userProfile", userProfile);
    }

}
