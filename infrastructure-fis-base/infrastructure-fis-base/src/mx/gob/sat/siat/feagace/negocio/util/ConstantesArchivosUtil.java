/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.gob.sat.siat.feagace.negocio.util;

import java.nio.charset.Charset;

/**
 *
 * @author Ing. Emmanuel Estrada Gonzalez
 */
public interface ConstantesArchivosUtil {

    String CONTENT_TYPE_WORD = "application/msword";
    String CONTENT_TYPE_WORD_X = "application/vnd.openxmlformats-officedocument.wordprocessingml.document";
    String CONTENT_TYPE_PDF = "application/pdf";
    String CONTENT_TYPE_EXCEL = "application/vnd.ms-excel";
    String CONTENT_TYPE_EXCEL_X = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
    String EXTENSION_ARCHIVO_ORDEN = ".docx";
    String EXTENSION_ARCHIVO_ORDEN_PDF = ".pdf";
    String NOMBRE_ARCHIVO_PROMOCION_JPG = "Promocion.jpg";
    String NOMBRE_ARCHIVO_PROMOCION_PDF = "Promocion.pdf";
    String CARPETA_PROMOCION = "Promo_";
    String CARPETA_PRUEBAS_ALEGATOS = "PruebasAlegatos";
    String CARPETA_PRORROGA = "Prorrogas";
    String CARPETA_PRORRO_ID = "Prorro_";
    String CARPETA_PROPUESTA_RECHAZO_AUDITOR_ID = "RechazoAuditor_";
    String CARPETA_PROPUESTA_TRANSFERENCIA_AUDITOR_ID = "TransferenciaAuditor_";
    String CARPETA_PROPUESTA_RETROALIMENTAR_AUDITOR_ID = "RetroalimentarAuditor_";
    Charset UTF_8 = Charset.forName("UTF-8");
    String CARPETA_PROMOCION_OFICIO = "PromoOficio_";
    String CARPETA_PRUEBAS_ALEGATOS_OFICIO = "PruebasAlegatosOficio";
    String CARPETA_SAT = "SAT";
    int CUATRO_KILOS = 4096;
    String SALTO_DE_LINEA = "\n";
    String CARPETA_PRUEBAS_PERICIALES = "PruebasPericiales";
    String CARPETA_PRUEBA_PERI_ID = "PruebaPeri_";

}
