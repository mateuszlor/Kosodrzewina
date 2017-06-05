package com.spring.start.service;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfPTable;
import com.spring.start.entity.PeriodicService;
import com.spring.start.entity.Rent;
import com.spring.start.entity.ReportType;
import com.spring.start.entity.Service;
import com.spring.start.operations.PDFGenerator;
import com.spring.start.repository.PeriodicServiceRepository;
import com.spring.start.repository.RentRepository;
import com.spring.start.repository.ServiceRepository;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.var;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Vertig0 on 05.06.2017.
 */
@org.springframework.stereotype.Service
@Log4j
public class ReportService {

    @Autowired
    @Getter
    @Setter
    private RentRepository rentRepository;

    @Autowired
    @Getter
    @Setter
    private PeriodicServiceRepository periodicServiceRepository;

    @Autowired
    @Getter
    @Setter
    private ServiceRepository serviceRepository;


    public void createReport(Date from, Date to, ReportType type, String filename) throws IOException, DocumentException {

        switch (type) {
            case RENT:
                createRentReport(from, to, filename);
                break;
            case SERVICE:
                createServiceReport(from, to, filename);
                break;
        }
    }

    private void createRentReport(Date from, Date to, String filename) throws IOException, DocumentException {
        List<Rent> rents = rentRepository.getRentsBetweenDates(from, to);
        PDFGenerator pdf = new PDFGenerator(filename);
        pdf.createPdf(rentReportTable(rents));
    }

    private void createServiceReport(Date from, Date to, String filename) throws IOException, DocumentException {
        List<Service> services = serviceRepository.getServicesFromPeriodOfTime(from, to);
        List<PeriodicService> periodicServices = periodicServiceRepository.getServicesFromPeriodOfTime(from, to);
        PDFGenerator pdf = new PDFGenerator(filename);
        pdf.createPdf(serviceReport(services, periodicServices));
    }

    private PdfPTable rentReportTable(List<Rent> data) {
        Format formatter = new SimpleDateFormat("dd-MM-yyyy");
        // a table with three columns
        PdfPTable table = new PdfPTable(6);
        // the cell object
        table.addCell("Lp.");
        table.addCell("Klient");
        table.addCell("Samochód");
        table.addCell("Kwota");
        table.addCell("Data rozpoczęcia");
        table.addCell("Data zakończenia");
        // we add the four remaining cells with addCell()
        int iterator = 1;
        for (Rent rent : data) {
            table.addCell(Integer.toString(iterator));
            table.addCell(rent.getCustomer().getFullName());
            table.addCell(rent.getCar().getFullName());
            table.addCell(rent.getIncome().toString() + "zł");
            table.addCell(formatter.format(rent.getStartDate()));
            table.addCell(formatter.format(rent.getEndDate()));
            iterator++;
        }
        return table;
    }

    private PdfPTable serviceReport(List<Service> services, List<PeriodicService> periodicServices) {
        Format formatter = new SimpleDateFormat("dd-MM-yyyy");

        PdfPTable table = new PdfPTable(6);
        table.addCell("Lp.");
        table.addCell("Samochód");
        table.addCell("Nazwa");
        table.addCell("Kwota");
        table.addCell("Data (rozpoczęcia)");
        table.addCell("Data zakończenia");
        int iterator = 1;
        for (Service service : services) {
            table.addCell(Integer.toString(iterator));
            table.addCell(service.getCar().getFullName());
            table.addCell(service.getType().getName());
            var cost = service.getCost();
            table.addCell(String.format("%s zł", cost == null ? 0 : cost));
            table.addCell(formatter.format(service.getExecute()));
            table.addCell("");
            iterator++;
        }
        for (PeriodicService periodicService : periodicServices) {
            table.addCell(Integer.toString(iterator));
            table.addCell(periodicService.getCar().getFullName());
            table.addCell(periodicService.getType().getName());
            var cost = periodicService.getCost();
            table.addCell(String.format("%s zł", cost == null ? 0 : cost));
            table.addCell(formatter.format(periodicService.getDateFrom()));
            table.addCell(formatter.format(periodicService.getDateTo()));
            iterator++;
        }
        return table;
    }


}
