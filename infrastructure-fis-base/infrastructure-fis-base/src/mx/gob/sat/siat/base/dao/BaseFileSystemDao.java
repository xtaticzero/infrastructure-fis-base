/*
 * Todos los Derechos Reservados   2013 SAT.
 * Servicio de Adminisstracion Tributaria (SAT).
 * 
 *  Este software contiene informacion propiedad exclusiva del SAT considerada
 *  Confidencial. Queda totalmente prohibido su uso o divulgacion en forma 
 *  parcial o total. 
 */

package mx.gob.sat.siat.base.dao;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;

import mx.gob.sat.siat.base.excepcion.FileSystemException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * Clase DAO principal para realizar funciones genericas para manipulacion de
 * archivos.
 * 
 * @author softtek
 * 
 */
public class BaseFileSystemDao implements Serializable {

	/**
     * 
     */
    private static final long serialVersionUID = 5062703350924996405L;
    protected final Logger logger = LoggerFactory.getLogger("file_system");

	/**
	 * Constructor principal
	 */
	protected BaseFileSystemDao() {
		super();
	}

	public void crearArchivo(InputStream inputStream, final String nombreArchivo)
			throws FileSystemException {

		File archivoDestino = null;
		FileOutputStream archivoDestinoOutput = null;
		BufferedOutputStream bufferDeSalida = null;

		try {

			if (inputStream != null && nombreArchivo != null
					&& !nombreArchivo.trim().equals("")) {
				logger.debug("Creando archivo archivo: [{}] ", nombreArchivo);
				archivoDestino = new File(nombreArchivo);
				// Asignamos permisos de lectura y escritura a todos los
				// usuarios.
				archivoDestino.setReadable(true, false);
				archivoDestino.setWritable(true, false);

				archivoDestinoOutput = new FileOutputStream(archivoDestino);

				int read = 0;
				byte[] bytes = new byte[1024];

				while ((read = inputStream.read(bytes)) != -1) {
					archivoDestinoOutput.write(bytes, 0, read);
				}

				bufferDeSalida = new BufferedOutputStream(archivoDestinoOutput);

				logger.debug(
						"Archivo generado: [{}], y su tamanio es de: [{}] ",
						nombreArchivo, archivoDestino.length());
			}

		} catch (IOException e) {
			logger.error("IOException crearArchivo: [{}]", e.getMessage());
			throw new FileSystemException("cannot.be.saved", e);
		} finally {
			try {
				if (archivoDestinoOutput != null) {
					archivoDestinoOutput.close();
				}

				if (bufferDeSalida != null) {
					bufferDeSalida.close();
				}
			} catch (IOException e) {
				logger.error("IOException crearArchivo: [{}]", e.getMessage());
				throw new FileSystemException("cannot.be.saved", e);
			}
		}
	}

	public void crearArchivo(byte[] arrayArchivo, final String nombreArchivo)
			throws FileSystemException {
		File archivoDestino = null;
		FileOutputStream archivoDestinoOutput = null;
		BufferedOutputStream bufferDeSalida = null;

		try {

			if (arrayArchivo != null && nombreArchivo != null
					&& !nombreArchivo.trim().equals("")) {
				logger.debug("Creando archivo archivo: [{}] ", nombreArchivo);
				archivoDestino = new File(nombreArchivo);
				// Asignamos permisos de lectura y escritura a todos los
				// usuarios.
				archivoDestino.setReadable(true, false);
				archivoDestino.setWritable(true, false);

				archivoDestinoOutput = new FileOutputStream(archivoDestino);

				archivoDestinoOutput.write(arrayArchivo);
				bufferDeSalida = new BufferedOutputStream(archivoDestinoOutput);
				logger.debug(
						"Archivo generado: [{}], y su tamanio es de: [{}] ",
						nombreArchivo, archivoDestino.length());
			}

		} catch (IOException e) {
			logger.error("IOException crearArchivo: [{}]", e.getMessage());
			throw new FileSystemException("cannot.be.saved", e);
		} finally {
			try {
				if (archivoDestinoOutput != null) {
					archivoDestinoOutput.close();
				}

				if (bufferDeSalida != null) {
					bufferDeSalida.close();
				}
			} catch (IOException e) {
				logger.error("IOException crearArchivo: [{}]", e.getMessage());
				throw new FileSystemException("cannot.be.saved", e);
			}
		}
	}

	public byte[] leerArchivo(String nombreDelArchivo)
			throws FileSystemException {
		byte[] bytesArchivo = null;

		try {
			if (nombreDelArchivo != null && !nombreDelArchivo.trim().equals("")) {
				logger.debug("Leyendo el archivo: [{}] ",
						nombreDelArchivo);
				FileDataSource fds = new FileDataSource(nombreDelArchivo);
				DataHandler dh = new DataHandler(fds);
				bytesArchivo = getArrayOfBytesFromInputStream(dh);
				logger.debug("Archivo leido: [{}], y su tamanio es de: [{}] ", nombreDelArchivo, bytesArchivo.length);
			}
		} catch (IOException e) {
			logger.error("IOException leerArchivo : [{}]",
							e.getMessage());
			throw new FileSystemException("cannot.be.reader", e);
		}

		return bytesArchivo;
	}

	private byte[] getArrayOfBytesFromInputStream(DataHandler dataHandler)
			throws IOException {
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		dataHandler.writeTo(output);
		output.close();
		return output.toByteArray();
	}

}
