/*
 * Copyright (c) 2013 Servicio de Administracion Tributaria (SAT)
 * Todos los derechos reservados.
 * Este software contiene información confidencial propiedad de
 * la Servicio de Administracion Tributaria (SAT)
 * Por lo cual no puede ser reproducido, distribuido o
 * alterado sin el consentimiento previo del Servicio de Administracion Tributaria (SAT)
 */
package mx.gob.sat.siat.feagace.negocio.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.Serializable;

import java.util.Properties;

import mx.gob.sat.siat.feagace.negocio.exception.DocumentoException;

import org.apache.log4j.Logger;

/**
 * Clase encargada de administrar el archivo de propiedades de la aplicacion.
 *
 * @author Ignacio Sandoval (i.sandovalhiguera@tcs.com)
 * @version 1.0
 *
 */
public abstract class Propiedades implements Serializable {

    /**
     * Serial version UID.
     */
    private static final long serialVersionUID = 8950421846338542932L;

    /**
     * Logger de la clase.
     */
    private static final Logger LOG = Logger.getLogger(Propiedades.class);

    private static final String AGACE_PROPERTIES = "AGACE_APP";

    /**
     * Obtiene una propiedad de la aplicacion.
     *
     * @param code Codigo de la propiedad de la aplicacion.
     * @return Propiedad de la aplicacion.
     */
    public static String get(String code) {

        String valor = "Propiedad no encontrada";
        String aplicacion = System.getProperty(AGACE_PROPERTIES);
        aplicacion = "/siat/fece/configuracion/AGACE_PROPIEDADES.properties";
        Properties properties = null;

        try {
            properties = Propiedades.loadProperties(aplicacion);
        } catch (DocumentoException ex) {
            LOG.error(ex.getMessage(), ex);
        }

        if (properties != null) {
            valor = properties.getProperty(code);
        }

        return valor;
    }

    /**
     * Metodo encargado de cargar el archivo de propiedades dada la ruta del
     * archivo.
     *
     * @param propertyFile Ruta y nombre del archivo de propiedades.
     * @return Objeto Properties.
     * @throws ArchivoNoEncontradoException ArchivoNoEncontradoException.
     */
    public static Properties loadProperties(String propertyFile) throws DocumentoException {

        LOG.info("Archivo de propiedades: " + propertyFile);

        Properties properties = new Properties();
        FileInputStream in = null;

        try {
            LOG.info("Cargando archivo de propiedades...");
            in = new FileInputStream(propertyFile);
            properties.load(in);
            LOG.info("Archivo de propiedades cargado...");
        } catch (IOException ex) {
            throw new DocumentoException(propertyFile, ex.getCause(), ex);
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException e) {
                LOG.error(e.getMessage(), e);
            }
        }

        return properties;
    }

}
