package mx.gob.sat.siat.common.auditoria.strategy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public interface ReturnTypeStrategy {
	
	public static final Logger LOGGER = LoggerFactory.getLogger(ReturnTypeStrategy.class);
	
	public String obtenerIdRegistro(Object data); 

}
