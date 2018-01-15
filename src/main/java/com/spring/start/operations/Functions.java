package com.spring.start.operations;

import com.mysql.jdbc.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Vertig0 on 05.06.2017.
 */
public class Functions {

    //TODO: wstawić gdzieś do properties używane formaty dat
    public static String DATE_FORMAT = "dd-MM-yyyy";

    public static String DATETIME_FORMAT = "dd-MM-yyyy HH:mm:ss";

    private static final String DEFAULT_FILENAME = "Raport";
    private static final String DEFAULT_ENLARGEMENT = ".pdf";

    public static String generateFilename(String filename, String from, String to, String type) {
        String newFileName = !StringUtils.isNullOrEmpty(filename) ? filename : null;
        if (StringUtils.isNullOrEmpty(newFileName)) {
            if (!StringUtils.isNullOrEmpty(from) && !StringUtils.isNullOrEmpty(to) && !StringUtils.isNullOrEmpty(type)){
                newFileName = DEFAULT_FILENAME + " " + type + " " + from + "-" + to  + DEFAULT_ENLARGEMENT;
            }
            else {
                newFileName = DEFAULT_FILENAME + DEFAULT_ENLARGEMENT;
            }
        } else if (!filename.contains(DEFAULT_ENLARGEMENT)){
            newFileName += DEFAULT_ENLARGEMENT;
        }
        return newFileName;
    }

    public static Date convertStringToDate(String stringDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT, Locale.ENGLISH);
        LocalDate date = LocalDate.parse(stringDate, formatter);
        return java.sql.Date.valueOf(date);
    }

    public static Date convertStringToDateTime(String stringDate) {
        Date date = new Date();
        try {
            date = new SimpleDateFormat(DATETIME_FORMAT).parse(stringDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

}
