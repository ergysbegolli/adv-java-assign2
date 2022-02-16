/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.mycompany.covidApp;

import com.mycompany.covidApp.core.controller.AppController;
import com.mycompany.covidApp.models.Column;
import com.mycompany.covidApp.persistence.entities.Country;
import com.mycompany.covidApp.persistence.entities.CovidDataRecord;
import static com.mycompany.covidApp.utils.Utilities.checkPKsAreNotEmpty;
import static com.mycompany.covidApp.utils.Utilities.dateFromString;
import static com.mycompany.covidApp.utils.Utilities.getRowsFromCsv;
import static com.mycompany.covidApp.utils.Utilities.isValidColumnName;
import static com.mycompany.covidApp.utils.Utilities.safeParseToDouble;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 *
 * @author Nasty
 */
public class PopulateDbFromCsvFile {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        populateDb(getRowsFromCsv(CSV_FILE_PATH));

    }

    //change this 
    static final String CSV_FILE_PATH = "src/main/sql/data.csv";

    private static AppController getAppController() {
        return AppController.getInstance();
    }

    private static void populateDb(List<String[]> rows) {
        String[] headers = rows.remove(0);
        Set<Integer> validIndexes = IntStream
                .range(0, headers.length)
                .filter(i -> isValidColumnName(headers[i]))
                .boxed()
                .collect(Collectors.toSet());

        Function<String[], Map<Column, String>> rowToLabeledRowCells = (row) -> validIndexes
                .stream()
                .collect(HashMap<Column, String>::new,
                        (m, i) -> m.put(Column.fromColumn(headers[i]), row[i]),
                        Map::putAll);

        rows
                .stream()
                .map(rowToLabeledRowCells)
                .filter(checkPKsAreNotEmpty)
                .forEach((x) -> {
                    CovidDataRecord record = new CovidDataRecord.Builder(dateFromString(x.get(Column.DT)), x.get(Column.COD))
                            .newCases(safeParseToDouble(x.get(Column.NC)))
                            .totalCases(safeParseToDouble(x.get(Column.TC)))
                            .newCasesSmoothed(safeParseToDouble(x.get(Column.NCS)))
                            .totalDeaths(safeParseToDouble(x.get(Column.TD)))
                            .newDeaths(safeParseToDouble(x.get(Column.ND)))
                            .newDeathsSmoothed(safeParseToDouble(x.get(Column.NDS)))
                            .totalTests(safeParseToDouble(x.get(Column.TT)))
                            .newTests(safeParseToDouble(x.get(Column.NT)))
                            .build();

                    getAppController().addCovidDataRecord(record);

                    if (!getAppController().checkIfCountryExist(x.get(Column.COD))) {
                        Country country = new Country.Builder(x.get(Column.COD))
                                .location(x.get(Column.LOC))
                                .continent(x.get(Column.CNT))
                                .population(safeParseToDouble(x.get(Column.POP)))
                                .medianAge(safeParseToDouble(x.get(Column.MA)))
                                .stringencyIndex(safeParseToDouble(x.get(Column.SI)))
                                .reproductionRate(safeParseToDouble(x.get(Column.RR)))
                                .build();

                        getAppController().addNewCountry(country);
                    }
                });
    }

}
