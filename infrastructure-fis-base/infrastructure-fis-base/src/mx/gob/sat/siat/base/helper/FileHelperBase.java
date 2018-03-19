/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.sat.siat.base.helper;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez
 */
public abstract class FileHelperBase implements Serializable {

    private static final long serialVersionUID = 953625944712088563L;
    protected final Logger logger;

    public FileHelperBase() {
        logger = Logger.getLogger(getClass());
    }

    private File archivo;
    private File folder;
    private Writer output;

    public boolean cearArchivo(InputStream contenidoArchivo, String rutaDirectorio, String nombreArchivo) {
        OutputStream outStream = null;
        boolean flgArchivoExiste;
        try {
            folder = new File(rutaDirectorio);
            flgArchivoExiste = folder.exists();
            logger.error("La estructura de directorios " + rutaDirectorio + " existe:" + folder.exists());
            if (!flgArchivoExiste) {
                boolean fuecrado = folder.mkdirs();
                if (fuecrado) {
                    logger.debug("La estructura de directorios " + rutaDirectorio + " se creo correctamente :");
                } else {
                    logger.error("La estructura de directorios " + rutaDirectorio + " no pudo ser creada correctamente :");
                }
                creaRutaArchivo(rutaDirectorio, nombreArchivo);
            } else {
                creaRutaArchivo(rutaDirectorio, nombreArchivo);
            }
            byte[] buffer = new byte[contenidoArchivo.available()];
            contenidoArchivo.read(buffer);
            outStream = new FileOutputStream(archivo);
            outStream.write(buffer);
            return true;
        } catch (FileNotFoundException ex) {
            logger.error("no se encuentra el archivo el la ruta especificada", ex);
            return false;
        } catch (IOException ex) {
            logger.error("Error al procesar el archivo", ex);
            return false;
        } finally {
            try {
                IOUtils.closeQuietly(outStream);
                IOUtils.closeQuietly(contenidoArchivo);

                contenidoArchivo.close();
            } catch (IOException ex) {
                logger.error("No se pudo cerrar el archivo correctamente ", ex);
            }
        }
    }

    public boolean cearArchivo(String newLine, String rutaDirectorio, String nombreArchivo) {
        boolean flgArchivoExiste;
        try {
            if (rutaDirectorio != null && !rutaDirectorio.isEmpty()) {
                validarRuta(rutaDirectorio);
            }

            folder = new File(rutaDirectorio);
            flgArchivoExiste = folder.exists();
            logger.error("La estructura de directorios " + rutaDirectorio + " existe:" + folder.exists());
            if (!flgArchivoExiste) {
                boolean fuecrado = folder.mkdirs();
                if (fuecrado) {
                    logger.debug("La estructura de directorios " + rutaDirectorio + " se creo correctamente :");
                } else {
                    logger.error("La estructura de directorios " + rutaDirectorio + " no pudo ser creada correctamente :");
                }
                creaRutaArchivo(rutaDirectorio, nombreArchivo);

                if (newLine != null && !newLine.isEmpty()) {
                    output = new BufferedWriter(new FileWriter(rutaDirectorio.concat(nombreArchivo)));  //clears file every time
                    output.append(newLine.concat("\n"));
                }

            } else {
                creaRutaArchivo(rutaDirectorio, nombreArchivo);
            }

        } catch (Exception e) {
            logger.error(e);
        } finally {
            if (output != null) {
                try {
                    output.close();
                } catch (IOException ex) {
                    logger.error(ex);
                }
            }
        }

        return false;
    }

    public void creaRutaArchivo(String rutaArchivo, String nombreArchivo) {
        String[] carpetas = rutaArchivo.split("/");
        boolean flgArchivoCreado;
        try {
            if (carpetas != null && carpetas.length > 0) {
                archivo = new File(rutaArchivo + "/" + nombreArchivo);
                flgArchivoCreado = archivo.createNewFile();
            } else {
                /*Windows*/
                archivo = new File(rutaArchivo + "\\" + nombreArchivo);
                flgArchivoCreado = archivo.createNewFile();
            }

            if (flgArchivoCreado) {
                logger.debug("El archivo " + nombreArchivo + " se creo cprrectamente");
            }

        } catch (IOException ex) {
            logger.error(ex);
        }
    }

    public List<String> leerArchivo(String rutaArchivo, String nombreArchivo) {
        List<String> lstLines = new ArrayList<String>();
        FileReader fileR = null;
        BufferedReader bufferedR = null;
        try {
            if (rutaArchivo != null && nombreArchivo != null) {
                validarRuta(rutaArchivo);
                fileR = new FileReader(new File(rutaArchivo.concat("/").concat(nombreArchivo)));
                bufferedR = new BufferedReader(fileR);

                while (bufferedR.readLine() != null) {
                    String line = bufferedR.readLine();
                    line = line != null && !line.isEmpty() ? line.trim().replace("\\n", "") : line;
                    if (line != null && !line.isEmpty()) {
                        lstLines.add(line);
                    }

                }

                return lstLines;

            }
        } catch (Exception e) {
            logger.error("No se encontro el archivo", e);
        } finally {
            try {
                if (fileR != null) {
                    fileR.close();
                }
                if (bufferedR != null) {
                    bufferedR.close();
                }
            } catch (IOException ex) {
                logger.error(ex);
            }
        }
        return lstLines;
    }

    public void eliminarArchivo(String rutaArchivo, String nombreArchivo) {

        if (rutaArchivo != null && nombreArchivo != null) {
            validarRuta(rutaArchivo);
            archivo = new File(rutaArchivo.concat("/").concat(nombreArchivo));
            if (archivo.delete()) {
                logger.info("El archivo fue borrado");
            } else {
                logger.error("El archivo no fue borrado");
            }
        }

    }

    private void validarRuta(String rutaDirectorio) {
        if (rutaDirectorio != null && !rutaDirectorio.isEmpty()) {
            char character = rutaDirectorio.charAt(rutaDirectorio.length() - 1);
            if (character == '/' || character == '\\') {
                rutaDirectorio = rutaDirectorio.substring(0, rutaDirectorio.length() - 1);
                logger.debug(rutaDirectorio);
            }
        }
    }
}
