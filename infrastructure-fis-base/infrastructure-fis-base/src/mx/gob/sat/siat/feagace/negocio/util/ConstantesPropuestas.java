package mx.gob.sat.siat.feagace.negocio.util;

import java.math.BigDecimal;

public interface ConstantesPropuestas {
    
    /**
      * Este atributo corresponde al expediente activo
      */
    Integer EXP_ACTIVO=1;
    
    /**
      * Este atributo corresponde al numero de inicializacion e incremento del folio de propuesta por anio
      */
    BigDecimal INICIAR_FOLIO_PROPUESTA=new BigDecimal(1L);
    
    /**
      * Este atributo corresponde estatus de propuesta Asignada a central
      */
    BigDecimal PROPUESTA_ASIGNACION_CENTRAL=new BigDecimal(46L);
    
    /**
      * Este atributo corresponde estatus de propuesta Confirmado por Regional
      */
    BigDecimal PROPUESTA_CONFIRMADO_REGIONAL=new BigDecimal(49L);
    
    /**
      * Este atributo corresponde estatus de propuesta Pendiente
      */
    BigDecimal PROPUESTA_PENDIENTE=new BigDecimal(56L);
    
    /**
      * Este atributo corresponde estatus de No aprobada la transferencia
      */
    int PROPUESTA_NO_APROBADA_TRANSFERENCIA_INT = 53;
    
    /**
      * Este atributo corresponde estatus de propuesta Propuesta Rechazada para adecuar por Auditor
      */
    int PROPUESTA_RECHAZADA_ADECUAR_AUDITOR = 61;
    
    /**
      * Este atributo corresponde estatus de propuesta Rechazada por programador (al validar)
      */
    BigDecimal PROPUESTA_RECHAZADA_PROGRAMADOR_VALIDAR=new BigDecimal(65L);
    
    /**
      * Este atributo corresponde estatus de propuesta Registrada
      */
    BigDecimal PROPUESTA_REGISTRADA=new BigDecimal(68L);
    
    /**
      * Este atributo corresponde estatus de propuesta Transferencia Aprobada
      */
    BigDecimal PROPUESTA_TRANSFERENCIA_APROBADA=new BigDecimal(75L);
    
    /**
      * Este atributo corresponde estatus de propuesta Transferencia Aprobada
      */
    int PROPUESTA_TRANSFERENCIA_APROBADA_INT = 75;
    
    /**
      * Este atributo corresponde estatus de propuesta Transferida
      */
    BigDecimal PROPUESTA_TRANSFERIDA=new BigDecimal(76L);
    
    
    
    
    
    /**
      * Este atributo corresponde al origen de  al propuesta Individual
      */
    BigDecimal PROPUESTA_INDIVIDUAL=new BigDecimal(1L);
    
    
    /**
      * Este atributo corresponde al origen de  al propuesta Masiva
      */
    BigDecimal PROPUESTA_MASIVA=new BigDecimal(2L);
    
    /**
      * Este atributo corresponde al origen de  al propuesta MCAS
      */
    BigDecimal PROPUESTA_MCAS=new BigDecimal(3L);
    
    /**
      * Este atributo corresponde a la consulta de antecedentes de la propuestas SICSEP
      */
    String SICSEP="SICSEP";
    
     /**
       * Este atributo corresponde a la consulta de antecedentes de la propuestas SIUIEFI
       */
    String SIUIEFI="SUIEFI";
     
    /**
      * Este atributo corresponde a la consulta de antecedentes de la propuestas AGAFF
      */
    String AGAFF="AGAFF";
    String AGAFF_DESCRIPCION="AGAFF Fiscalizaci\u00f3n Electr\u00f3nica";
    
    /**
      * Este atributo corresponde a la consulta de antecedentes de la propuestas AGACE
      */
    String AGACE="AGACE";
    String AGACE_DESCRIPCION="AGACE Fiscalizaci\u00f3n Electr\u00f3nica";
    String SIN_MEDIOS_CONTACTO="El Contribuyente no cuenta con medios de contacto por lo que no se podr\u00E1 registrar en el sistema.";
    String SIN_MEDIOS_CONTACTO_ERROR_CONSULTA="No se pudo validar los Medios de Contacto, Amparado o PPEE; contacte a su administrador o int\u00e9ntelo nuevamente.";
    
    /**
     * Este atributo corresponde a la consulta de antecedentes de la propuesta en INSUMOS
     */
     String INSUMOS_DESCRIPCION="INSUMOS";
    
    
    /**
      * Este atributo corresponde a la consulta de medios de contacto del contribuyente
      */
    String CORREO="CORREO";
    
    /**
      * Este atributo corresponde al error de la consulta de medios de contacto del contribuyente
      */
    String ERROR_CONSULTAMEDIOS="lbl.propuestas.contribuyente.error.consultamedios";
    
    
    /**
      * Este atributo corresponde al contribuyente sin medios de contacto
      */
    String SIN_MEDIOS="lbl.propuestas.contribuyente.sinmedios";
    
    /**
      * Este atributo corresponde al contribuyente sin medios de contacto
      */
    String NO_MEDIOS="lbl.propuestas.contribuyente.nomedioscontacto";
    
    /**
      * Este atributo corresponde al contribuyente con medios de contacto
      */
    String CON_MEDIOS="lbl.propuestas.contribuyente.medioscontacto";
    
    
    /**
      * Este atributo corresponde al contribuyente con PPE
      */
    String CON_PPE="lbl.propuestas.contribuyente.ppee";
    
    /**
      * Este atributo corresponde al contribuyente Amparado
      */
    String AMPARADO="lbl.propuestas.contribuyente.amparado";
    
    
    /**
      * Este atributo corresponde al tamanio del archivo que se va adjuntar en propuestas
      */
    long N_4196000L = 4194304L;
    
    /**
      * Este atributo corresponde al tipo de impuesto No Aplica (N/A)
      */
    BigDecimal TIPO_IMPUESTO_NO_APLICA=new BigDecimal(18L);
    
    /**
     * Este atributo corresponde a la seccion de impuestos de la propuesta
     * */
    String MSG_IMPUESTO="formInsumo:msgImpuestos";
    String MSG_NOREGISTROS="lbl.propuestas.consultaAntecedentesNoRegistros";
    String MSG_SIREGISTROS="lbl.propuestas.consultaAntecedentesSiRegistros";
    String MSG_ARCHIVO="formInsumo:msgExitoDescartarOficioAnexo";
    
}
