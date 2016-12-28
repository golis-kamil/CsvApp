import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by trot on 28.12.16.
 */
public class PrintToFile implements PrintStrategy {

    private Map<String, RecordStructure> records = new HashMap<>();
    private String fileName;

    @Override
    public void printData() {
        if(fileName==null || fileName.length()<1){
            throw new IllegalArgumentException("Nazwa pliku jest bledna.");
        }
        try (Writer writer = new FileWriter(fileName)){
            CSVPrinter saveFile = CSVFormat.RFC4180.withHeader("Group", "Size", "Avg. Value", "Min. Value", "Max. Value").print(writer);
            for (Map.Entry<String, RecordStructure> m : records.entrySet()) {
                saveFile.printRecord(m.getKey(),
                        m.getValue().getCount(),
                        m.getValue().getAvgValue(),
                        m.getValue().getMinValue(),
                        m.getValue().getMaxValue());
            }
        } catch (IOException e){
            System.err.println("Bledna nazwa pliku.");
        }

        System.out.println("Dane zostaly zapisane do pliku "+fileName);
    }

    @Override
    public void setSourceData(Map<String, RecordStructure> records){
        this.records=records;
    }

    public void setFileName(String fileName){
        this.fileName=fileName;
    }
}
