package mx.gob.sat.siat.common.plantillador.service.impl;

import java.io.InputStream;
import java.io.OutputStream;

import mx.gob.sat.siat.base.service.BaseSerializableBusinessServices;
import mx.gob.sat.siat.common.plantillador.constants.PlantilladorConstants;
import mx.gob.sat.siat.common.plantillador.helper.PlantilladorDocxHelper;
import mx.gob.sat.siat.common.plantillador.service.PlantilladorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("plantilladorService")
public class PlantilladorServiceImpl extends BaseSerializableBusinessServices implements PlantilladorService {

	private static final long serialVersionUID = -1353282636985070076L;
	
	@Autowired
	private PlantilladorDocxHelper plantilladorDocxHelper;
	
	public void generaPDF(InputStream entrada, OutputStream salida, String folio, String cadenaOriginal){
		generaPDF(entrada, salida, folio, cadenaOriginal, PlantilladorConstants.FORMATO_WORD);
	}
	
	public void generaPDF(InputStream entrada, OutputStream salida, String folio, String cadenaOriginal,int formatoEntrada){
		switch (formatoEntrada) {
		case PlantilladorConstants.FORMATO_WORD:
			plantilladorDocxHelper.generaPDF(entrada, salida, folio, cadenaOriginal);	
			break;
		case PlantilladorConstants.FORMATO_PDF:
			break;
		}
	}

}
