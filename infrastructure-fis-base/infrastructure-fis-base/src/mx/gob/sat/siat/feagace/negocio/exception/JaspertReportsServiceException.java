/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.sat.siat.feagace.negocio.exception;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez
 */
public class JaspertReportsServiceException extends AbstractServiceException {

    private static final long serialVersionUID = -3956921694850224993L;

    private static final String CATEGORY = "agace.negocio.generar.reporte.error";

    public JaspertReportsServiceException(String situation) {
        super(CATEGORY, situation);
    }

    public JaspertReportsServiceException(String situation, Object... args) {
        super(CATEGORY, situation, args);
    }

    public JaspertReportsServiceException(String situation, Throwable cause) {
        super(CATEGORY, situation, cause);
    }

    public JaspertReportsServiceException(String situation, Throwable cause, Object... args) {
        super(CATEGORY, situation, cause, args);
    }
}
