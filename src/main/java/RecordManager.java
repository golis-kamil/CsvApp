import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by trot on 28.12.16.
 */
public class RecordManager {

    private static final String AVERAGE_VAL = "SAT_AVG_ALL";
    private static final String STATE_VAL = "STABBR";
    private static final String CSV_FILE = "MERGED2012_PP.csv";

    private Map<String, RecordStructure> records = new HashMap<>();

    private PrintStrategy printStrategy;

    private Reader reader;

    public RecordManager(PrintStrategy printStrategy) {
        ClassLoader cl = RecordManager.class.getClassLoader();
        try {
            reader = new FileReader(cl.getResource(CSV_FILE).getFile());
            getAndUpdate();
        } catch (IOException e) {
            System.err.println("Nie znaleziono pliku.");
        }
        this.printStrategy = printStrategy;
    }

    public RecordManager(PrintStrategy printStrategy, String filePath) {
        try {
            reader = new FileReader(filePath);
            getAndUpdate();
        } catch (IOException e) {
            System.err.println("Nie znaleziono pliku.");
        }
        this.printStrategy = printStrategy;
    }

    private void getAndUpdate() throws IOException {

        Iterable<CSVRecord> rec;
        rec = CSVFormat.RFC4180.withHeader().parse(reader);
        RecordStructure recordStructure;

        for (CSVRecord record : rec) {

            double dataAvg;
            try {
                dataAvg = Double.parseDouble(record.get(AVERAGE_VAL));
            } catch (NumberFormatException e) {
                continue;
            }

            String state = record.get(STATE_VAL);

            recordStructure = records.getOrDefault(state, new RecordStructure());

            int count = recordStructure.getCount();
            double averageValue = recordStructure.getAvgValue();
            Double maxValue = recordStructure.getMaxValue();
            Double minValue = recordStructure.getMinValue();

            count += 1;
            double average = averageValue + dataAvg;

            if (maxValue == null || dataAvg > maxValue) {
                recordStructure.setMaxValue(dataAvg);
            }
            if (minValue == null || dataAvg < minValue) {
                recordStructure.setMinValue(dataAvg);
            }

            recordStructure.setCount(count);
            recordStructure.setAvgValue(average);

            records.put(state, recordStructure);
        }
        countAverageValues();
    }

    private void countAverageValues() {
        for (RecordStructure r : records.values()) {
            int count = r.getCount();
            double avg = r.getAvgValue();
            r.setAvgValue((double) Math.round((avg / count) * 100) / 100);
        }
    }

    public void printValues(String fileName) {
        if (printStrategy instanceof PrintToFile) {
            ((PrintToFile) printStrategy).setFileName(fileName);
        }
        printStrategy.setSourceData(records);
        printStrategy.printData();
    }

    public void printValues() {
        printStrategy.setSourceData(records);
        printStrategy.printData();
    }


}
