package mx.gob.sat.siat.common.plantillador.service;

import java.io.InputStream;
import java.io.OutputStream;

public interface PlantilladorService {
	
	/**
	 * Genera un PDF a partir de un Documento WORD Agregandole el folio como encabezado y la cadena orignial al final del documento
	 * @param entrada Archivo de entrada en Formato WORD. 
	 * @param salida  Salida donde se colocara el archivo en formato PDF
	 * @param folio   Folio que se colocara como encabezado
	 * @param cadenaOriginal Cadena Orignial que se colocara al final del documento
	 */
	void generaPDF(InputStream entrada, OutputStream salida, String folio, String cadenaOriginal);
	
	/**
	 * Genera un PDF a partir de un Documento en el formato indicado.
	 *  Agregandole el folio como encabezado y la cadena orignial al final del documento.
	 * @param entrada Archivo de entrada en Formato indicado. 
	 * @param salida  Salida donde se colocara el archivo en formato PDF
	 * @param folio   Folio que se colocara como encabezado
	 * @param cadenaOriginal Cadena Orignial que se colocara al final del documento
	 * @param formatoEntrada Formato del archivo de entrada WORD = 1, PDF = 2. Usar constantes de PlantilladorConstants
	 */
	void generaPDF(InputStream entrada, OutputStream salida, String folio, String cadenaOriginal,int formatoEntrada);

}
