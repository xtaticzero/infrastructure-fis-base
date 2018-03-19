package mx.gob.sat.siat.common.auditoria.service;

import mx.gob.sat.siat.base.dto.UserProfileDTO;

public interface PistasAuditoriaBaseService {
	
    void insertaPistaAuditoria(UserProfileDTO userProfileDTO, 
    		int idOperacion, String idRegistro, String claveSistema);

}
