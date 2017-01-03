import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * Created by trot on 28.12.16.
 */

//TODO Dokonczyc klase do pobierania rekordow streamem
public class RecordStream {

    private static final String AVERAGE_VAL = "SAT_AVG_ALL";
    private static final String STATE_VAL = "STABBR";
    private static final String CSV_FILE = "MERGED2012_PP.csv";

    private Reader reader;

    public RecordStream() {
        ClassLoader cl = RecordManager.class.getClassLoader();
        try {
            reader = new FileReader(cl.getResource(CSV_FILE).getFile());
            getAndUpdate();
        } catch (IOException e) {
            System.err.println("Nie znaleziono pliku.");
        }
    }

    private void getAndUpdate() throws IOException {

//        Iterable<CSVRecord> rec;
//        rec = CSVFormat.RFC4180.withHeader().parse(reader);
//
//        Stream<CSVRecord> data = StreamSupport.stream(rec.spliterator(), false);
//        data.filter(c -> !c.get(AVERAGE_VAL).equalsIgnoreCase("null"))
//                .collect(Collectors.toMap(c -> c.get(STATE_VAL), Streaming::toSummaryRecord, SummaryRecord::plus))
//                .forEach(Streaming.print(printer));

    }
}
