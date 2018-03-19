package mx.gob.sat.siat.common.archivosTemp.dto;

import java.math.BigDecimal;

import java.util.Date;

import mx.gob.sat.siat.base.dao.domain.BaseModel;

public class FecetArchivoTemp extends BaseModel {

    @SuppressWarnings("compatibility:-3631667593848587400")
    private static final long serialVersionUID = 1L;
    
    private BigDecimal idArchivoTemp;
    private String sessionUUID;
    private String archivo;
    private Date fecha;
    private byte[] archivoByte;

    public void setIdArchivoTemp(BigDecimal idArchivoTemp) {
        this.idArchivoTemp = idArchivoTemp;
    }

    public BigDecimal getIdArchivoTemp() {
        return idArchivoTemp;
    }

    public void setSessionUUID(String sessionUUID) {
        this.sessionUUID = sessionUUID;
    }

    public String getSessionUUID() {
        return sessionUUID;
    }

    public void setArchivo(String archivo) {
        this.archivo = archivo;
    }

    public String getArchivo() {
        return archivo;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha != null ? new Date(fecha.getTime()) : null;
    }

    public Date getFecha() {
        return (fecha != null) ? (Date)fecha.clone() : null;
    }

    public void setArchivoByte(byte[] archivoByte) {
        this.archivoByte = archivoByte;
    }

    public byte[] getArchivoByte() {
        return archivoByte;
    }
}
