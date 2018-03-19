package mx.gob.sat.siat.common.archivosTemp.service.impl;

import java.math.BigDecimal;
import java.util.List;

import mx.gob.sat.siat.base.service.BaseBusinessServices;
import mx.gob.sat.siat.common.archivosTemp.dao.FecetArchivoTempDao;
import mx.gob.sat.siat.common.archivosTemp.dto.FecetArchivoTemp;
import mx.gob.sat.siat.common.archivosTemp.helper.ArchivoTempHelper;
import mx.gob.sat.siat.common.archivosTemp.service.ArchivoTempService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("archivoTempService")
public class ArchivoTempServiceImpl extends BaseBusinessServices implements ArchivoTempService {

    private static final long serialVersionUID = 4356425654675678881L;

    @Autowired
    private transient FecetArchivoTempDao fecetArchivoTempDao;
    
    private transient ArchivoTempHelper helper = new ArchivoTempHelper();

    
    public BigDecimal insertaArchivoTemp(FecetArchivoTemp fecetArchivoTemp) {        
        fecetArchivoTemp.setArchivo(helper.encodeArchivo(fecetArchivoTemp.getArchivoByte()));
        return fecetArchivoTempDao.insertaArchivoTemp(fecetArchivoTemp);
    }

    public byte[] consultaArchivoTemp(BigDecimal idArchivoTemp, String rfc) {
        List<String> lista = fecetArchivoTempDao.consultaArchivoTemp(idArchivoTemp, rfc);
        byte[] archivo = null;
        if (lista.size() > 0 && lista != null) {            
            archivo = helper.decodeArchivo(lista.get(0));
        }
        return archivo;
    }
}
