import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by trot on 27.12.16.
 */
public class CsvApp {


    public static Map<String, RecordStruct> data = new HashMap<>();

    public static void main(String[] args) throws IOException {

        ClassLoader cl = CsvApp.class.getClassLoader();
        File file = new File(cl.getResource("MERGED2012_PP.csv").getFile());

        RecordStruct recordStruct;

        Reader in = new FileReader(cl.getResource("MERGED2012_PP.csv").getFile());
        Iterable<CSVRecord> records = CSVFormat.RFC4180.withHeader().parse(in);

        for (CSVRecord record : records) {

            String recordAvgAll = record.get("SAT_AVG_ALL");
            String recordStaBbr = record.get("STABBR");
            if (recordAvgAll.equals("NULL")) {
                continue;
            }

            recordStruct = data.getOrDefault(recordStaBbr, new RecordStruct());

            int count = recordStruct.getCount();
            count += 1;

            double dataAvg = Double.parseDouble(recordAvgAll);
            double structAvg = recordStruct.getAvgValue();
            double avg = structAvg + dataAvg;

            if (dataAvg > structAvg) {
                recordStruct.setMaxValue(dataAvg);
            }
            //TODO cos tu nie dziala jak powinno.
            if (dataAvg < structAvg) {
                recordStruct.setMinValue(dataAvg);
            }

            recordStruct.setCount(count);
            recordStruct.setAvgValue(avg);

            data.put(recordStaBbr, recordStruct);

        }

        countAverage();
        data.entrySet().stream().forEach(e -> System.out.println(e.getKey() + " "
                + e.getValue().getAvgValue() + " "
                + e.getValue().getMinValue() + " "
                + e.getValue().getMaxValue()));
        saveNewCsv("/home/trot/test.csv");
    }

    public static void countAverage() {
        for (RecordStruct r : data.values()) {
            int count = r.getCount();
            double avg = r.getAvgValue();

            r.setAvgValue((double) Math.round((avg / count) * 100) / 100);
        }
    }

    public static void saveNewCsv(String output) throws IOException {

        Writer writer = new FileWriter(output);
        CSVPrinter saveFile = CSVFormat.RFC4180.withHeader("Group", "Size", "Avg. Value", "Min. Value", "Max. Value").print(writer);
        try {
            for (Map.Entry<String, RecordStruct> m : data.entrySet()) {
                saveFile.printRecord(m.getKey(),
                        m.getValue().getCount(),
                        m.getValue().getAvgValue(),
                        m.getValue().getMinValue(),
                        m.getValue().getMaxValue());
            }

        } finally {
            saveFile.close();
        }
    }

}

