package com.mmatuszewski.financial;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Component
public class LoadData {

    private static final String DATA_FILE = "/Users/michal/data/EURUSD_201711.csv";
    private static final int COLUMN = 4;
    private static final char DELIMITER = ';';

    List<Double> dataToAnalysis;

    public LoadData()
    {
        dataToAnalysis = new ArrayList<>();
        retrieveDataFromCsv();
    }


    private void retrieveDataFromCsv()
    {
        try
        {
            BufferedReader reader = Files.newBufferedReader(Paths.get(DATA_FILE));
            CSVParser csvParser = new CSVParser(reader, CSVFormat.EXCEL.withDelimiter(DELIMITER));

            csvParser.forEach(record ->
                dataToAnalysis.add(Double.valueOf(record.get(COLUMN)))
            );
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
