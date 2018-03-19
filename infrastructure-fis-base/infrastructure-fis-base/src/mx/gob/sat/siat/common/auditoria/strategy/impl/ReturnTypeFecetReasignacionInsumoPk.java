package mx.gob.sat.siat.common.auditoria.strategy.impl;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import mx.gob.sat.siat.common.auditoria.strategy.ReturnTypeStrategy;

public class ReturnTypeFecetReasignacionInsumoPk implements ReturnTypeStrategy {

	@Override
	public String obtenerIdRegistro(Object returnType) {
		Object idInsumo = null;
		try {			
			Method metodo = returnType.getClass().getMethod("getIdReasignacion");
			idInsumo = metodo.invoke(returnType);			
		} catch (SecurityException e) {
			LOGGER.error(e.getMessage(), e);
		} catch (NoSuchMethodException e) {
			LOGGER.error(e.getMessage(), e);
		} catch (IllegalArgumentException e) {
			LOGGER.error(e.getMessage(), e);
		} catch (IllegalAccessException e) {
			LOGGER.error(e.getMessage(), e);
		} catch (InvocationTargetException e) {
			LOGGER.error(e.getMessage(), e);
		}
		return idInsumo.toString();
	}

}
