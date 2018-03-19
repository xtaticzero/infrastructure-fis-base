package mx.gob.sat.siat.common.archivosTemp.dao;

import java.math.BigDecimal;

import java.util.List;

import mx.gob.sat.siat.common.archivosTemp.dto.FecetArchivoTemp;

public interface FecetArchivoTempDao {
    
    BigDecimal insertaArchivoTemp(FecetArchivoTemp fecetArchivoTemp);
    
    List<String> consultaArchivoTemp(BigDecimal idArchivoTemp, String rfc);
}
