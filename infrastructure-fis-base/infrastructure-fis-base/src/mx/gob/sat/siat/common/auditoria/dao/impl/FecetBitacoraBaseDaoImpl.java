package mx.gob.sat.siat.common.auditoria.dao.impl;

import java.math.BigDecimal;

import org.springframework.stereotype.Repository;

import mx.gob.sat.siat.base.dao.BaseJDBCDao;
import mx.gob.sat.siat.common.auditoria.dao.FecetBitacoraBaseDao;
import mx.gob.sat.siat.common.auditoria.dto.FecetBitacora;

@Repository("fecetBitacoraBaseDao")
public class FecetBitacoraBaseDaoImpl extends BaseJDBCDao<FecetBitacora> implements FecetBitacoraBaseDao {

	private static final long serialVersionUID = -6450886150208903255L;

	private static final String SQL_INSERT = " INSERT INTO FECET_BITACORA( ID_BITACORA, ID_OPERACION, ID_EMPLEADO, ID_REGISTRO, IPMAQUINA , NOMBREMAQUINA, ID_USUARIO, FECHA, DESCRIPCION,"
			+ " NUMEROCERTIFICADODIGITALFIEL, NOMPROPTROCERTIFICADODIGITAL, FOLIOEXTERNO)VALUES (?,?,?,?,?,?,?,?, "
			+ " (SELECT FA.NOMBRE FROM FECEC_ACCIONES FA, FECEA_OPERACIONES FO WHERE FO.ID_OPERACION = ? AND FA.ID_ACCION = FO.ID_ACCION), ?,?, "
			+ " (SELECT FA.ID_MOVIMIENTO FROM FECEC_ACCIONES FA, FECEA_OPERACIONES FO WHERE FO.ID_OPERACION = ? AND FA.ID_ACCION = FO.ID_ACCION))";

	public BigDecimal obtenIdConsecutivo() {
		return getJdbcTemplateBase().queryForObject("SELECT FECEQ_BITACORA.NEXTVAL FROM DUAL", BigDecimal.class);
	}

	@Override
	public void insertaPistaAuditoria(FecetBitacora fb) {
		if (fb.getIdBitacora() == null) {
			fb.setIdBitacora(obtenIdConsecutivo());			
		}
		getJdbcTemplateBase().update(SQL_INSERT, fb.getIdBitacora(), fb.getIdOperacion(), fb.getIdEmpleado(),
				fb.getIdRegistro(), fb.getIpmaquina(), fb.getNombremaquina(), fb.getIdUsuario(), fb.getFecha(),
				fb.getIdOperacion(), fb.getNumCertiDigFiel(), fb.getNomProptroCertiDigital(), fb.getIdOperacion());
	}

}
