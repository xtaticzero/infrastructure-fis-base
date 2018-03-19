package mx.gob.sat.siat.feagace.negocio.util;

import java.math.BigDecimal;

public interface ConstantesReportes {
    /**
     * Numeros
     * */
    int N_0=0;
    int N_1=1;
    int N_2=2;
    int N_3=3;
    int N_4=4;
    int N_5=5;
    int N_6=6;
    int N_7=7;
    int N_8=8;
    int N_9=9;
    int N_10=10;
    int N_11=11;
    int N_12=12;
    
    /**
     * Dias que se van a restar a una fecha
     * */
    int NUMDIAS=729;
    int MINIMO_ANIOS=1990;
    
    String PATH_REPORTE_IMAGENES="/siat/fece/configuracion/reportes/imagenes/";
    String PATH_CONTEXTO="/resources/images/graficas/";
    String PATH_REPORTE="/siat/fece/configuracion/reportes/";
    String REPORTE_EJECTIVO="ReporteGraficas.jasper";
    String REPORTE_GERENCIAL="ReporteExcel.jasper";
    String REPORTE_INSUMOS="insumos/";
    String REPORTE_PROPUESTAS="propuestas/";
    String REPORTE_ORDENES="ordenes/";
    String ARCHIVO_EXCEL="ReporteExcel";
    String ARCHIVO_PDF="ReporteGraficas";
    String ARCHIVO_IMAGEN_GRAFICA="graficasGenerado.jpg";
    
    String REPORTE_PIS_AUDIT_INT_PDF = "reportPistasAuditInt.jasper";
    String REPORTE_PIS_AUDIT_INT_EXCEL = "reportPistasAuditInt.jasper";
    String REPORTE_PIS_AUDIT_EXT_PDF = "reportPistasAuditExt.jasper";
    String REPORTE_PIS_AUDIT_EXT_EXCEL = "reportPistasAuditExt.jasper";
    String REPORTE_CONSULTA_ADMIN_EXCEL = "ConsultaAdministrador.jasper";
    String REPORTE_CONSULTA_ESTATUS_EXCEL = "ConsultaEstatus.jasper";
    String REPORTE_CARGA_MASIVA_EXCEL = "CargaMasiva.jasper";
    String REPORTE_PA = "pAudioria/";
    String ARCHIVO_PISTAS_AUDITORIA_PDF = "PistasAuditoriaPDF";
    String ARCHIVO_PISTAS_AUDITORIA_XLS = "PistasAuditoriaXLS";
    
    /**
     * Constantes que identifican el tipo de reporte a generar
     * */
    BigDecimal USUARIO_REPORTE_EJECUTIVO = BigDecimal.valueOf(13L);
    BigDecimal USUARIO_REPORTE_GERENCIAL = BigDecimal.valueOf(14L);
    
    BigDecimal ARACE_INSUMOS = BigDecimal.valueOf(17L);
    
    /**
     * Constantes que representan el tipo de grafica a generar
     **/
    String TIPO_GRAFICA_PIE = "Circular";
    String TIPO_GRAFICA_BAR = "Barras";
    String ERROR_GRAFICA_BAR = "Se deben seleccionar dos filtros para la generaci\u00f3n de la gr\u00e1fica de barras."; 
    String ERROR_GRAFICA_PIE="\"Se debe seleccionar un filtro para la generaci\u00f3n de la gr\u00e1fica circular";
    /**
     * Constantes que representan el nivel de busqueda
     */
    int NIVEL_0 = 0; 
    int NIVEL_1 = 1; 
    int NIVEL_2 = 2; 
    int NIVEL_3 = 3; 
    int NIVEL_4 = 4; 
    int NIVEL_5 = 5; 
    
    
    /**
     * Constantes para las etiquetas
      */
    String TIPO_FECHA_FECHAS="lbl.reporte.columna.periodo.fechas";
    String TIPO_FECHA_MESES="lbl.reporte.columna.periodo.meses";
    
    /**
     * Constantes para mostrar mensajes en los reportes
     */
    String MSG_REPORTES="formReportes:msgReporte";
    
    String MSG_PRESUNTIVA="formReportes:msgpresuntivaFinalId";
    
    /**
     * Constantes de Modulos
     * */
    int ID_MODULO_INSUMOS=1;
    int ID_MODULO_PROPUESTAS=2;
    int ID_MODULO_ORDENES=3;
    
    /**
     * Constante que identifica si un usuario es central
     * */
    BigDecimal CENTRAL = BigDecimal.ONE;    
    
}
