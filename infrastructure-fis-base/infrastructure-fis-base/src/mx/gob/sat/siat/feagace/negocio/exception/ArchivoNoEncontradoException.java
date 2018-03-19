/*
 * Copyright (c) 2013 Servicio de Administracion Tributaria (SAT)
 * Todos los derechos reservados.
 * Este software contiene información confidencial propiedad de
 * la Servicio de Administracion Tributaria (SAT)
 * Por lo cual no puede ser reproducido, distribuido o
 * alterado sin el consentimiento previo del Servicio de Administracion Tributaria (SAT)
 */

package mx.gob.sat.siat.feagace.negocio.exception;

/**
 * Clase encargada de manejar la exception cuando no se encuentra un archivo.
 * 
 * @author Ignacio Sandoval (i.sandovalhiguera@tcs.com)
 * @version 1.0
 * 
 */
public class ArchivoNoEncontradoException extends RuntimeException {

    /**
     * Serial version UID.
     */
    private static final long serialVersionUID = 6157829778222096267L;

    /**
     * Constructor por defecto.
     */
    public ArchivoNoEncontradoException() {
        super("No se encuentra el archivo.");
    }

    /**
     * Constructor.
     * 
     * @param message
     *            Mensaje de la exception.
     * @param cause
     *            Causa de la exception.
     */
    public ArchivoNoEncontradoException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructor por mensaje.
     * 
     * @param message
     *            Mensaje de la exception.
     */
    public ArchivoNoEncontradoException(String message) {
        super("No se encuentra el archivo: " + message);
    }

    /**
     * Constructor por causa.
     * 
     * @param cause
     *            Causa de la exception.
     */
    public ArchivoNoEncontradoException(Throwable cause) {
        super(cause);
    }

}
