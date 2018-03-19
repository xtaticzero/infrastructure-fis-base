package mx.gob.sat.siat.base.dto;

import java.math.BigDecimal;
import java.util.Collection;

import mx.gob.sat.siat.base.dao.domain.BaseModel;

public class UserProfileDTO extends BaseModel {

    private static final long serialVersionUID = 7276244993138610152L;

    private String rfc;
    private String nombreCompleto;
    private String usuario;
    private String rol;
    private Collection<String> roles;
	private Collection<String> rolesNovell;
    private String nombres;
    private String primerApellido;
    private String segundoApellido;
    private String ip;
	private boolean esContribuyente;
	private String sessionIdNovell;
	private String idTipoPersona;
	private boolean anonimo;
	private String rfcCorto;
	private String nombreMaquina;
	private BigDecimal idEmpleado;

	public String getRfcCorto() {
        return rfcCorto;
    }

    public void setRfcCorto(String rfcCorto) {
        this.rfcCorto = rfcCorto;
    }
	
    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public Collection<String> getRoles() {
        return roles;
    }

    public void setRoles(Collection<String> roles) {
        this.roles = roles;
    }
	
	public Collection<String> getRolesNovell() {
        return rolesNovell;
    }

    public void setRolesNovell(Collection<String> rolesNovell) {
        this.rolesNovell = rolesNovell;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getPrimerApellido() {
        return primerApellido;
    }

    public void setPrimerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
    }

    public String getSegundoApellido() {
        return segundoApellido;
    }

    public void setSegundoApellido(String segundoApellido) {
        this.segundoApellido = segundoApellido;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
	
	public boolean getEsContribuyente() {
        return esContribuyente;
    }

    public void setEsContribuyente(boolean esContribuyente) {
        this.esContribuyente = esContribuyente;
    }
	
	public String getSessionIdNovell() {
        return sessionIdNovell;
    }

    public void setSessionIdNovell(String sessionIdNovell) {
        this.sessionIdNovell = sessionIdNovell;
    }

	public String getIdTipoPersona() {
        return idTipoPersona;
    }

    public void setIdTipoPersona(String idTipoPersona) {
        this.idTipoPersona = idTipoPersona;
    }

	public boolean isAnonimo() {
		return anonimo;
	}

	public void setAnonimo(boolean anonimo) {
		this.anonimo = anonimo;
	}	
	
	public String getNombreMaquina() {
		return nombreMaquina;
	}

	public void setNombreMaquina(String nombreMaquina) {
		this.nombreMaquina = nombreMaquina;
	}

	public BigDecimal getIdEmpleado() {
		return idEmpleado;
	}

	public void setIdEmpleado(BigDecimal idEmpleado) {
		this.idEmpleado = idEmpleado;
	}
} 
