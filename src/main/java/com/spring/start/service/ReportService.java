package com.spring.start.service;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfPTable;
import com.spring.start.entity.PeriodicService;
import com.spring.start.entity.Rent;
import com.spring.start.entity.Service;
import com.spring.start.generator.PDFGenerator;
import com.spring.start.repository.RentRepository;
import com.sun.org.apache.regexp.internal.RE;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;


import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * Created by Vertig0 on 05.06.2017.
 */
@org.springframework.stereotype.Service
@Log4j
public class ReportService {

    @Autowired
    @Getter @Setter
    private RentRepository rentRepository;

    @Autowired
    @Getter @Setter
    private ServiceService serviceService;

    public void createReport(Date from, Date to, String type) throws IOException, DocumentException {
        List<Rent> rents = rentRepository.getRentsBetweenDates(from, to);
        PDFGenerator<Rent> pdf = new PDFGenerator<Rent>("Wyporzyczenia");
        pdf.createPdf(rentReport(rents));
    }


    private PdfPTable rentReport(List<Rent> data) {
        // a table with three columns
        PdfPTable table = new PdfPTable(3);
        // the cell object
        table.addCell("Lp.");
        table.addCell("Klient");
        table.addCell("Samochód");
        table.addCell("Kwota");
        table.addCell("Data rozpoczęcia");
        table.addCell("Data zakończenia");
        // we add the four remaining cells with addCell()
        int iterator = 1;
        for (Rent rent:data) {
            table.addCell(Integer.toString(iterator));
            table.addCell(rent.getCustomer().getFullName());
            table.addCell(rent.getCar().getFullName());
            table.addCell(rent.getIncome().toString());
            table.addCell(rent.getIncome().toString());
            table.addCell(rent.getStartDate().toString());
            table.addCell(rent.getEndDate().toString());
        }
        return table;
    }

//    private PdfPTable serviceReport(List<Service> services, List<PeriodicService> periodicServices) {
//        // a table with three columns
//        PdfPTable table = new PdfPTable(3);
//        // the cell object
//        table.addCell("Lp.");
//        table.addCell("Samochód");
//        table.addCell("Kwota");
//        table.addCell("Data rozpoczęcia");
//        table.addCell("Data zakończenia");
//        // we add the four remaining cells with addCell()
//        int iterator = 1;
//        for (Rent rent:data) {
//            table.addCell(Integer.toString(iterator));
//            table.addCell(rent.getCustomer().getFullName());
//            table.addCell(rent.getCar().getFullName());
//            table.addCell(rent.getIncome().toString());
//            table.addCell(rent.getIncome().toString());
//            table.addCell(rent.getStartDate().toString());
//            table.addCell(rent.getEndDate().toString());
//        }
//        return table;
//    }


}
