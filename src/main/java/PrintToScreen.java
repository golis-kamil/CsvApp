import java.util.HashMap;
import java.util.Map;

/**
 * Created by trot on 28.12.16.
 */
public class PrintToScreen implements PrintStrategy {

    private Map<String, RecordStructure> records = new HashMap<>();

    @Override
    public void printData() {
        System.out.println("Dane zostana wyswietlone na ekranie.\n");
        records.entrySet().stream().forEach(e -> System.out.println(e.getKey() + " "
                + e.getValue().getAvgValue() + " "
                + e.getValue().getMinValue() + " "
                + e.getValue().getMaxValue()));

    }

    public void setSourceData(Map<String, RecordStructure> records){
        this.records=records;
    }
}
