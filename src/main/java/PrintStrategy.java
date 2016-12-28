import java.util.Map;

/**
 * Created by trot on 28.12.16.
 */
public interface PrintStrategy {

    public void printData();
    public void setSourceData(Map<String, RecordStructure> records);
}
