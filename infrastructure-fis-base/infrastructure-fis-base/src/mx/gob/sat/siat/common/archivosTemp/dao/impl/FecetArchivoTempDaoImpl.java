package mx.gob.sat.siat.common.archivosTemp.dao.impl;

import java.math.BigDecimal;

import java.sql.SQLException;

import java.util.List;

import mx.gob.sat.siat.base.dao.BaseJDBCDao;
import mx.gob.sat.siat.common.archivosTemp.dao.FecetArchivoTempDao;
import mx.gob.sat.siat.common.archivosTemp.dto.FecetArchivoTemp;

import org.springframework.stereotype.Repository;

@Repository("fecetArchivoTempDao")
public class FecetArchivoTempDaoImpl extends BaseJDBCDao<FecetArchivoTemp> implements FecetArchivoTempDao {

    private static final String SQL_INSERT =
        " INSERT INTO FECET_ARCHIVO_TEMP( ID_ARCHIVO_TEMP, SESSION_UUID, ARCHIVO, FECHA) VALUES(?,?,?,SYSDATE)";
    
    private static final String SQL_SELECT =
        " SELECT ARCHIVO FROM FECET_ARCHIVO_TEMP WHERE ID_ARCHIVO_TEMP = ?  AND SESSION_UUID = ? ";

    @SuppressWarnings("compatibility:-1096476538961656681")
    private static final long serialVersionUID = 1L;

    private BigDecimal obtenIdConsecutivo() {
        return getJdbcTemplateBase().queryForObject("SELECT FECEQ_ARCHIVO_TEMP.NEXTVAL FROM DUAL", BigDecimal.class);
    }

    public BigDecimal insertaArchivoTemp(FecetArchivoTemp fecetArchivoTemp) {
        if (fecetArchivoTemp.getIdArchivoTemp() == null) {
            fecetArchivoTemp.setIdArchivoTemp(obtenIdConsecutivo());
        }
        
        getJdbcTemplateBase().update(SQL_INSERT, fecetArchivoTemp.getIdArchivoTemp(),
                                     fecetArchivoTemp.getSessionUUID(), fecetArchivoTemp.getArchivo());
        return fecetArchivoTemp.getIdArchivoTemp();

    }
    
    public List<String> consultaArchivoTemp(BigDecimal idArchivoTemp, String rfc){
        return getJdbcTemplateBase().queryForList(SQL_SELECT, String.class, idArchivoTemp, rfc);
    }
}
