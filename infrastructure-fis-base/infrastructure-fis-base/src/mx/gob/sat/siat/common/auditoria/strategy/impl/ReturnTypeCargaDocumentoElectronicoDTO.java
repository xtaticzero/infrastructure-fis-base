package mx.gob.sat.siat.common.auditoria.strategy.impl;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import mx.gob.sat.siat.common.auditoria.strategy.ReturnTypeStrategy;

public class ReturnTypeCargaDocumentoElectronicoDTO implements ReturnTypeStrategy {

	@Override
	public String obtenerIdRegistro(Object returnType) {
		Object idPropuesta = null;
		try {					
			Method metodo1 = returnType.getClass().getMethod("getFecetPropuesta");
			Object propuesta = metodo1.invoke(returnType);
			Method metodo2 = propuesta.getClass().getMethod("getIdPropuesta");
			idPropuesta = metodo2.invoke(propuesta);
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
