package mx.gob.sat.siat.common.auditoria.strategy.impl;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import mx.gob.sat.siat.common.auditoria.strategy.ReturnTypeStrategy;

public class ReturnTypeFecetRechazoPropuesta implements ReturnTypeStrategy {

	@Override
	public String obtenerIdRegistro(Object returnType) {
		Object idPropuesta = null;
		try {			
			Object[] params = new Object[]{0};
			Method metodo = returnType.getClass().getMethod("get", int.class);
			Object insumo = metodo.invoke(returnType, params);			
			Method metodo1 = insumo.getClass().getMethod("getIdPropuesta");
			idPropuesta = metodo1.invoke(insumo);
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
		return idPropuesta.toString();
	}

}
