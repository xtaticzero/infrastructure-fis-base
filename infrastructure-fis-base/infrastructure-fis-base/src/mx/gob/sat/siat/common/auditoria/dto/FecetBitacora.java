package mx.gob.sat.siat.common.auditoria.dto;

import java.math.BigDecimal;
import java.util.Date;

import mx.gob.sat.siat.base.dao.domain.BaseModel;

public class FecetBitacora extends BaseModel{

    /**
	 * 
	 */
	private static final long serialVersionUID = -5903976135672532279L;
	
	private BigDecimal idBitacora;
    private BigDecimal idOperacion;
    private BigDecimal idEmpleado;
    private String idRegistro;
    private String numeroOrden;
    private String ipmaquina;
    private String nombremaquina;
    private String idUsuario;
    private Date fecha;
    private String descripcion;
    private String numCertiDigFiel;
    private String nomProptroCertiDigital;
    private BigDecimal folioExterno;
    
    public FecetBitacora(){
    }


public void setIdBitacora(BigDecimal idBitacora) {
    this.idBitacora = idBitacora;
}

public BigDecimal getIdBitacora() {
    return idBitacora;
}

public void setIdOperacion(BigDecimal idOperacion) {
    this.idOperacion = idOperacion;
}

public BigDecimal getIdOperacion() {
    return idOperacion;
}

public void setIdRegistro(String idRegistro) {
    this.idRegistro = idRegistro;
}

public String getIdRegistro() {
    return idRegistro;
}

public void setNumeroOrden(String numeroOrden) {
    this.numeroOrden = numeroOrden;
}

public String getNumeroOrden() {
    return numeroOrden;
}

public void setIpmaquina(String ipmaquina) {
    this.ipmaquina = ipmaquina;
}

public String getIpmaquina() {
    return ipmaquina;
}

public void setNombremaquina(String nombremaquina) {
    this.nombremaquina = nombremaquina;
}

public String getNombremaquina() {
    return nombremaquina;
}

public void setIdUsuario(String idUsuario) {
    this.idUsuario = idUsuario;
}

public String getIdUsuario() {
    return idUsuario;
}

public void setFecha(Date fecha) {
    this.fecha = fecha != null ? new Date(fecha.getTime()) : null;
}

public Date getFecha() {
    return (fecha != null) ? (Date) fecha.clone() : null;
}

public void setDescripcion(String descripcion) {
    this.descripcion = descripcion;
}

public String getDescripcion() {
    return descripcion;
}

public void setNumCertiDigFiel(String numCertiDigFiel) {
    this.numCertiDigFiel = numCertiDigFiel;
}

public String getNumCertiDigFiel() {
    return numCertiDigFiel;
}

public void setNomProptroCertiDigital(String nomProptroCertiDigital) {
    this.nomProptroCertiDigital = nomProptroCertiDigital;
}

public String getNomProptroCertiDigital() {
    return nomProptroCertiDigital;
}

public void setIdEmpleado(BigDecimal idEmpleado) {
    this.idEmpleado = idEmpleado;
}

public BigDecimal getIdEmpleado() {
    return idEmpleado;
}

public void setFolioExterno(BigDecimal folioExterno) {
    this.folioExterno = folioExterno;
}

public BigDecimal getFolioExterno() {
    return folioExterno;
}

}
