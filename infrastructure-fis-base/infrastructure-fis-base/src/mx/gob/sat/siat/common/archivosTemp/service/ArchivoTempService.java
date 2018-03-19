package mx.gob.sat.siat.common.archivosTemp.service;

import java.math.BigDecimal;

import mx.gob.sat.siat.common.archivosTemp.dto.FecetArchivoTemp;

import org.primefaces.model.StreamedContent;

public interface ArchivoTempService {
    
    BigDecimal insertaArchivoTemp(FecetArchivoTemp fecetArchivoTemp);
    
    byte[] consultaArchivoTemp(BigDecimal idArchivoTempo, String rfc);
    
    
}
