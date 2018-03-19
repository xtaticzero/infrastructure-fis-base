package mx.gob.sat.siat.common.archivosTemp.helper;


import org.apache.axis.encoding.Base64;


public class ArchivoTempHelper {
    
    public String encodeArchivo(byte[] archivo){ 
        return Base64.encode(archivo);
    }
    
    public byte[] decodeArchivo(String archivo){        
        return Base64.decode(archivo);
    }
}
