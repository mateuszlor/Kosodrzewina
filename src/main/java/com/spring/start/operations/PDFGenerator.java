package com.spring.start.operations;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import lombok.Getter;
import lombok.Setter;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Vertig0 on 05.06.2017.
 */
public class PDFGenerator {

    @Getter @Setter
    private String filename;

    public PDFGenerator(String filaname) {
        this.filename = filaname;
    }

    public void createPdf(PdfPTable... pdfPTables)
            throws IOException, DocumentException {
        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream(filename));
        document.open();
        for (PdfPTable table:pdfPTables) {
            document.add(table);
        }
        document.close();
    }
}
