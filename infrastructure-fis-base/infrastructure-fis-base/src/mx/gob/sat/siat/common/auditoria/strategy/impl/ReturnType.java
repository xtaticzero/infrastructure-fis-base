package mx.gob.sat.siat.common.auditoria.strategy.impl;

import mx.gob.sat.siat.common.auditoria.strategy.ReturnTypeStrategy;

public class ReturnType {
	
	public static final ReturnTypeStrategy REGISTRO_ISUMODTO = 
			new ReturnTypeRegistroInsumosDto();
	
	public static final ReturnTypeStrategy FECETDOCEXPINSUMO = 
			new ReturnTypeFecetDocExpInsumo();
	
	public static final ReturnTypeStrategy REASIGNACIONINSUMOPK = 
			new ReturnTypeFecetReasignacionInsumoPk();
	
	public static final ReturnTypeStrategy BIGDECIMAL = 
			new ReturnTypeBigDecimal();
	
	public static final ReturnTypeStrategy STRING = 
			new ReturnTypeString();
	
	public static final ReturnTypeStrategy FECETRECHAZOPROPUESTA = 
			new ReturnTypeFecetRechazoPropuesta();
	
	public static final ReturnTypeStrategy CARGADOCUMENTOELECTRONICODTO = 
			new ReturnTypeCargaDocumentoElectronicoDTO();
	
	
			
	private ReturnTypeStrategy strategy;
	
	private Object objeto;
	
	public Object getObjeto() {
		return objeto;
	}

	public void setObjeto(Object objeto) {
		this.objeto = objeto;
	}

	public ReturnType(ReturnTypeStrategy strategy, Object objeto) {
		this.strategy = strategy;
	}
	
	public String obtenerIdRegistro() {
		return strategy.obtenerIdRegistro(objeto);
	}

}
