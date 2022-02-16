package com.mycompany.covidApp.utils;

import com.mycompany.covidApp.models.Column;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;
/**
 *
 * @author Mysafir
 */
public class Utilities {

    static SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
    static DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public static Date dateFromString(String datestring) {
        Date date = new Date();
        try {
            date = formatter.parse(datestring);
        } 
        catch (ParseException e){}
        
        return date;
    }

    public static String StringFromDate(Date date){

        return   dateFormat.format(date);

    }

    public static double safeParseToDouble(String value) {
        if (value == null || value.isEmpty()) return 0;

        return Double.parseDouble(value);
    }

   public static Boolean isNotEmpty(String str) {
        if (str == null || str.isEmpty()) return false;
        return true;
    }

    public static Boolean areNotEmpty(String... strs) {
        return Arrays.stream(strs).filter(s -> !isNotEmpty(s)).collect(Collectors.toList()).isEmpty();
    }

    public static Boolean isValidColumnName(String columnName) {
        return Arrays.stream(Column.values()).map(Column::getColumn).anyMatch(columnName::equals);
    }
    
    public static List<String[]> getRowsFromCsv(String filepath) throws IOException {
        List<String[]> rowData = new ArrayList<>();
        Files.lines(Paths.get(filepath))
                .map(line -> Arrays.copyOf(line.split(","), 50))
                .forEach(rowData::add);

        return rowData;
    }
    
    public static Predicate<Map<Column, String>> checkPKsAreNotEmpty = (map) -> areNotEmpty(map.get(Column.DT), map.get(Column.COD));
}
