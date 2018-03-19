package mx.gob.sat.siat.common.auditoria.aspect;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import mx.gob.sat.siat.base.constante.CommonConstants;
import mx.gob.sat.siat.base.dto.UserProfileDTO;
import mx.gob.sat.siat.base.excepcion.AgacePropuestasRulesException;
import mx.gob.sat.siat.base.excepcion.BusinessException;
import mx.gob.sat.siat.base.filter.SpringBeanHttpSession;
import mx.gob.sat.siat.common.auditoria.annotation.PistaAuditoria;
import mx.gob.sat.siat.common.auditoria.service.PistasAuditoriaBaseService;
import mx.gob.sat.siat.common.auditoria.strategy.impl.ReturnType;

@Component
@Aspect
public class PistaAuditoriaAspect {

	@Autowired
	private PistasAuditoriaBaseService pistasAuditoriaService;
	
	@Autowired
	private SpringBeanHttpSession sessionBean;
	
	private Logger logger = LoggerFactory.getLogger(PistaAuditoriaAspect.class);	

	private static final String CLAVE_SISTEMA = "001";

	@Around("execution(* mx.gob.sat.siat.feagace..*.*(..)) && @annotation(mx.gob.sat.siat.common.auditoria.annotation.PistaAuditoria)")
	public Object guardandoPistaAuditoria(ProceedingJoinPoint pjp) throws BusinessException {
		logger.debug("pjp de GUARDAR PISTA........  " + pjp.getTarget());
		Object retorno = null;
		UserProfileDTO userProfileDTO = (UserProfileDTO) sessionBean.getHttpSession()
                .getAttribute(CommonConstants.USER_PROFILE);
		try {
			retorno = pjp.proceed();

			Method metodo = null;
			MethodSignature ms = (MethodSignature) pjp.getSignature();
			metodo = ms.getMethod();
			PistaAuditoria anotacion = metodo.getAnnotation(PistaAuditoria.class);
			
			String valorRetorno = obtenerIdRegistro(retorno);
			logger.debug("valor de RETORNO: " + valorRetorno);

			pistasAuditoriaService.insertaPistaAuditoria(userProfileDTO, 
					anotacion.idOperacion(), valorRetorno, CLAVE_SISTEMA);

		} catch (Throwable e) {
			logger.error("error.... en ProceedingJoinPoint  " + e.getMessage(), e);
			if (e instanceof AgacePropuestasRulesException) {
				throw new AgacePropuestasRulesException(e.getMessage());
			}			
		}
		return retorno;
	}

	public String obtenerIdRegistro(Object obj) {
		ReturnType valor = null;
		try {
			valor = this.validaObjeto(obj);
			if (valor != null) {		
				valor.setObjeto(obj);
				return valor.obtenerIdRegistro();
			} 			
		} catch (Exception e) {
			logger.error("obtenerIdRegistro: .... " + e.getMessage(), e);
		}
		return "0";
	}

	public String getMethodGetter(String fieldName) {
		StringBuilder sb = new StringBuilder("get");
		sb.append(fieldName.substring(0, 1).toUpperCase());
		sb.append(fieldName.substring(1));
		return sb.toString();
	}

	public ReturnType validaObjeto(Object objeto) {
		ReturnType tipoRetorno = null;
		if (objeto.getClass().getName().equals("mx.gob.sat.siat.feagace.modelo.dto.insumos.RegistroInsumosDto")) {
			tipoRetorno = new ReturnType(ReturnType.REGISTRO_ISUMODTO, objeto);
		} 
		else if (objeto.getClass().getName().equals("java.util.ArrayList")) {
			try {
				Method metodo = objeto.getClass().getMethod("isEmpty");
				Boolean isEmpty = (Boolean)metodo.invoke(objeto);
				if (!isEmpty) {
					if (getArrayListType(objeto).equals("mx.gob.sat.siat.feagace.modelo.dto.insumos.FecetDocExpInsumo") ||
							getArrayListType(objeto).equals("mx.gob.sat.siat.feagace.modelo.dto.insumos.FecetRetroalimentacionInsumo")) {
						tipoRetorno = new ReturnType(ReturnType.FECETDOCEXPINSUMO, objeto);
					}
					else if (getArrayListType(objeto).equals("mx.gob.sat.siat.feagace.modelo.dto.propuestas.FecetRechazoPropuesta")) {
						tipoRetorno = new ReturnType(ReturnType.FECETRECHAZOPROPUESTA, objeto);
					}
				}
			} catch (SecurityException e) {
				logger.error(e.getMessage(), e);
			} catch (NoSuchMethodException e) {
				logger.error(e.getMessage(), e);
			} catch (IllegalArgumentException e) {
				logger.error(e.getMessage(), e);
			} catch (IllegalAccessException e) {
				logger.error(e.getMessage(), e);
			} catch (InvocationTargetException e) {
				logger.error(e.getMessage(), e);
			}			
		} 
		else if (objeto.getClass().getName().equals("mx.gob.sat.siat.feagace.modelo.dto.insumos.FecetReasignacionInsumoPk")) {
			tipoRetorno = new ReturnType(ReturnType.REASIGNACIONINSUMOPK, objeto);
		}
		else if (objeto.getClass().getName().equals("java.math.BigDecimal")) {
			tipoRetorno = new ReturnType(ReturnType.BIGDECIMAL, objeto);
		}
		else if (objeto.getClass().getName().equals("java.lang.String")) {
			tipoRetorno = new ReturnType(ReturnType.STRING, objeto);
		}
		else if (objeto.getClass().getName().equals("mx.gob.sat.siat.feagace.modelo.dto.propuestas.CargaDocumentoElectronicoDTO")) {
			tipoRetorno = new ReturnType(ReturnType.CARGADOCUMENTOELECTRONICODTO, objeto);
		}
		return tipoRetorno;
	}
	
	public String getArrayListType(Object lista) {
		Object[] params = new Object[]{0};
		Method metodo = null;
		String clase = "";
		try {
			metodo = lista.getClass().getMethod("get", int.class);			
			Object objeto = metodo.invoke(lista, params);
			if (objeto != null) {
				clase = objeto.getClass().getName();
			}
		} catch (SecurityException e) {
			logger.error(e.getMessage(), e);
		} catch (NoSuchMethodException e) {
			logger.error(e.getMessage(), e);
		} catch (IllegalArgumentException e) {
			logger.error(e.getMessage(), e);
		} catch (IllegalAccessException e) {
			logger.error(e.getMessage(), e);
		} catch (InvocationTargetException e) {
			logger.error(e.getMessage(), e);
		}		
		return clase;
	}

}
