package mx.gob.sat.siat.common.plantillador.runner;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;

public class PlantilladorDocxRunner implements Runnable {
    private static final Logger logger = LoggerFactory.getLogger(PlantilladorDocxRunner.class);
    
    private static final int POSICION_X_FOLIO = 490;
    private static final int POSICION_Y_FOLIO = 775;
    private static final int POSICION_X_CADENA = 15;
    private static final int POSICION_Y_CADENA = 620;
    private static final int TAMANIO_Y_RENGLON = 12;
    private static final int TAMANIO_RENGLON = 85;
    private static final int TAMANIO_FUNTE_11 = 11;
    private static final int TAMANIO_MAX_FOLIO = 16;

    private String folio;
    private String cadenaOriginal;
    private InputStream entrada;
    private OutputStream salida;

    public PlantilladorDocxRunner(String folio, String cadenaOriginal,
            InputStream entrada, OutputStream salida) {
        this.folio = folio;
        this.cadenaOriginal = cadenaOriginal;
        this.entrada = entrada;
        this.salida = salida;
    }

    public void run() {
        logger.info("INICIANDO PLANTILLADOR ".concat(StringUtils.trimToEmpty(folio)));
        try {
            final ByteArrayOutputStream salidaNueva = new ByteArrayOutputStream();
            
            Converter converter = null;
            if (StringUtils.isNotBlank(folio) && StringUtils.isNotBlank(cadenaOriginal)) {
                converter = new DocxToPDFConverter(this.entrada, salidaNueva, false, true);
                converter.convert();
                agregarFolioCadena(salidaNueva);
            } else {
                converter = new DocxToPDFConverter(this.entrada, this.salida, false, true);
                converter.convert();
            }
        } catch (Exception e) {
            logger.error("Ocurrio un error al realizar la conversion del archivo", e);
            return;
        }
        logger.info("TERMINADNDO PLANTILLADOR".concat(StringUtils.trimToEmpty(folio)));
    }

    private void agregarFolioCadena(final ByteArrayOutputStream salidaNueva) throws IOException, DocumentException {
        final ByteArrayInputStream entradaArchivo = new ByteArrayInputStream(salidaNueva.toByteArray());
        PdfReader reader = null; 
        PdfStamper stamper = null;
        try {
            reader = new PdfReader(entradaArchivo);
            stamper = new PdfStamper(reader, salida);
            final BaseFont fuenteBase = BaseFont.createFont(BaseFont.HELVETICA_BOLD, BaseFont.CP1252, BaseFont.NOT_EMBEDDED); // set font
            PdfContentByte canvas = null;
            int index = 1;
            String [] folios = this.folio.split("/n/");
            int indexFolio = 0;
            while (index <= reader.getNumberOfPages()){
                indexFolio = 0;
                for (String folioActual : folios) {
                    if (folioActual == null || folioActual.trim().isEmpty()) {
                        continue;
                    }
                    canvas = stamper.getOverContent(index);
                    canvas.beginText();
                    canvas.setFontAndSize(fuenteBase, TAMANIO_FUNTE_11);
                    if (folioActual.trim().length() > TAMANIO_MAX_FOLIO) {
                        canvas.setTextMatrix(POSICION_X_FOLIO - folioActual.trim().length() * 2, (POSICION_Y_FOLIO - (indexFolio * TAMANIO_Y_RENGLON)));
                    } else {
                        canvas.setTextMatrix(POSICION_X_FOLIO - folioActual.trim().length(), (POSICION_Y_FOLIO - (indexFolio * TAMANIO_Y_RENGLON)));
                    }
                    canvas.showText(folioActual.trim());
                    canvas.endText();
                    indexFolio++;
                }
                index++;
            }
            stamper.insertPage(index, PageSize.LETTER);
            canvas = stamper.getOverContent(index);
            agregarCadenaOriginal(canvas, fuenteBase);
            
        } finally {
            if (stamper != null) {
                stamper.close();
            }
            
            if (reader != null) {
                reader.close();
            }
            
        }

    }

    private void agregarCadenaOriginal(PdfContentByte canvas, BaseFont fuenteBase) {
        if (this.cadenaOriginal != null && !this.cadenaOriginal.isEmpty()) {
            String[] cadenas = this.cadenaOriginal.split("/n/");
            int index = 0;
            for (String cadena : cadenas) {
                while (cadena.length() > TAMANIO_RENGLON) {
                    crearTexto(canvas, fuenteBase, index, cadena.substring(0, TAMANIO_RENGLON));
                    cadena = cadena.substring(TAMANIO_RENGLON);
                    index++;
                }
                crearTexto(canvas, fuenteBase, index, cadena);
                index+=2;
            }
        }
    }
    
    private void crearTexto(PdfContentByte canvas, BaseFont fuenteBase, int index, String cadena) {
        canvas.beginText();
        canvas.setFontAndSize(fuenteBase, TAMANIO_FUNTE_11);
        canvas.setTextMatrix(POSICION_X_CADENA, (POSICION_Y_CADENA - (TAMANIO_Y_RENGLON * index)));
        canvas.showText(cadena);
        canvas.endText();
    }
}
