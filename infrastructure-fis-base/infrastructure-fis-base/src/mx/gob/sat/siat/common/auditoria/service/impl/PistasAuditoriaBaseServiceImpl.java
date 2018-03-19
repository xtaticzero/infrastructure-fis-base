package mx.gob.sat.siat.common.auditoria.service.impl;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import mx.gob.sat.siat.base.dto.UserProfileDTO;
import mx.gob.sat.siat.base.service.BaseBusinessServices;
import mx.gob.sat.siat.common.auditoria.dao.FecetBitacoraBaseDao;
import mx.gob.sat.siat.common.auditoria.dto.FecetBitacora;
import mx.gob.sat.siat.common.auditoria.service.PistasAuditoriaBaseService;

@Service("pistasAuditoriaBaseService")
public class PistasAuditoriaBaseServiceImpl extends BaseBusinessServices  
	implements PistasAuditoriaBaseService {
    
    private static final long serialVersionUID = -5871454158884523603L;        
    
    @Autowired
    private transient FecetBitacoraBaseDao fecetBitacoraBaseDao;               
    
    @Override
    public void insertaPistaAuditoria(UserProfileDTO userProfileDTO, 
    		int idOperacion, String idRegistro, String claveSistema) {  
        String idUsuario = "";
        try {
        	if (idRegistro.length() > 13) {
        		idRegistro = idRegistro.substring(0, 13);
        	}
        	if (userProfileDTO.getIp() != null && userProfileDTO.getIp().length() > 255) {
        		userProfileDTO.setIp(userProfileDTO.getIp().substring(0, 254));
        	}
        	if (userProfileDTO.getNombreMaquina() != null && userProfileDTO.getNombreMaquina().length() > 255) {
                userProfileDTO.setNombreMaquina(userProfileDTO.getNombreMaquina().substring(0, 254));
            }
        	if (userProfileDTO.getUsuario() != null && !userProfileDTO.getUsuario().equals("")) {
        	    idUsuario = userProfileDTO.getUsuario();
            }
        	else {
        	    idUsuario = userProfileDTO.getRfc();
        	}
        	logger.debug(":::::::::  Insertando Pista AUDITORIA :::::::::::::::::");            
            FecetBitacora bitacora = new FecetBitacora();
            bitacora.setIdOperacion(new BigDecimal(idOperacion));
            bitacora.setIdEmpleado(userProfileDTO.getIdEmpleado());
            bitacora.setIpmaquina(userProfileDTO.getIp());
            bitacora.setNombremaquina(userProfileDTO.getNombreMaquina());
            bitacora.setIdUsuario(idUsuario);
            bitacora.setIdRegistro(idRegistro);            
            bitacora.setFecha(new Date());
            bitacora.setNumCertiDigFiel(null);
            bitacora.setNomProptroCertiDigital(null);                      
            fecetBitacoraBaseDao.insertaPistaAuditoria(bitacora);                        
        } catch (Exception e) {
            logger.error("Error al cargar la direccion IP del equipo" + e);
        }
    }

	public FecetBitacoraBaseDao getFecetBitacoraBaseDao() {
		return fecetBitacoraBaseDao;
	}

	public void setFecetBitacoraBaseDao(FecetBitacoraBaseDao fecetBitacoraBaseDao) {
		this.fecetBitacoraBaseDao = fecetBitacoraBaseDao;
	}

}

