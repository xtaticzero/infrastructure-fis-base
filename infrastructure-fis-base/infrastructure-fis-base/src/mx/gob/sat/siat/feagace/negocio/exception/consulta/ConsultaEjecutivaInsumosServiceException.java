/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.sat.siat.feagace.negocio.exception.consulta;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez
 */
public class ConsultaEjecutivaInsumosServiceException extends ConsultaAbstractServiceException {

    private static final long serialVersionUID = -1269057254668945621L;

    private static final String CATEGORY = "agace.negocio.consulta.insumos.error";

    public ConsultaEjecutivaInsumosServiceException(String situation) {
        super(CATEGORY, situation);
    }

    public ConsultaEjecutivaInsumosServiceException(String situation, Object... args) {
        super(CATEGORY, situation, args);
    }

    public ConsultaEjecutivaInsumosServiceException(String situation, Throwable cause) {
        super(CATEGORY, situation, cause);
    }

    public ConsultaEjecutivaInsumosServiceException(String situation, Throwable cause, Object... args) {
        super(CATEGORY, situation, cause, args);
    }
}
