package mx.gob.sat.siat.common.plantillador.helper;

import java.io.InputStream;
import java.io.OutputStream;

import mx.gob.sat.siat.base.helper.BaseHelper;
import mx.gob.sat.siat.common.plantillador.runner.PlantilladorDocxRunner;

import org.springframework.stereotype.Component;

@Component
public class PlantilladorDocxHelper extends BaseHelper {

	private static final long serialVersionUID = -8233152073237801283L;

	public void generaPDF(InputStream entrada, OutputStream salida, String folio, String cadenaOriginal){
		Thread thread = new Thread(new PlantilladorDocxRunner(folio, cadenaOriginal, entrada, salida));
		thread.start();
	} 
	
	public void generaPDFEnlinea(InputStream entrada, OutputStream salida){
        new PlantilladorDocxRunner(null, null, entrada, salida).run();
    }
}
