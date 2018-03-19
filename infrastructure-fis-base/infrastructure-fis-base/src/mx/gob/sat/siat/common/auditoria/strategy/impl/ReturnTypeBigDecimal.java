package mx.gob.sat.siat.common.auditoria.strategy.impl;

import mx.gob.sat.siat.common.auditoria.strategy.ReturnTypeStrategy;

public class ReturnTypeBigDecimal implements ReturnTypeStrategy {

	@Override
	public String obtenerIdRegistro(Object returnType) {
		return returnType.toString();
	}

}
