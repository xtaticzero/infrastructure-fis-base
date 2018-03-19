package mx.gob.sat.siat.common.auditoria.strategy.impl;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import mx.gob.sat.siat.common.auditoria.strategy.ReturnTypeStrategy;

public class ReturnTypeRegistroInsumosDto implements ReturnTypeStrategy {

	@Override
	public String obtenerIdRegistro(Object returnType) {		
		Object idInsumo = null;
		try {
			Object[] params = new Object[]{0};
			Method metodo = returnType.getClass().getMethod("getInsumosRegistrados");
			Object insumos = metodo.invoke(returnType);
			Method metodo1 = insumos.getClass().getMethod("get", int.class);
			Object insumo = metodo1.invoke(insumos, params);
			Method metodo2 = insumo.getClass().getMethod("getIdInsumo");
			idInsumo = metodo2.invoke(insumo);
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
