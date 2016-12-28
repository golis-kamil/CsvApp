import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by trot on 27.12.16.
 */
public class CsvApp {


    public static Map<String, RecordStructure> data = new HashMap<>();

    public static void main(String[] args) throws IOException {

        RecordManager recordManager = new RecordManager(new PrintToFile());
        data=recordManager.getRecords();
        recordManager.printValues("/home/trot/test.csv");

        recordManager = new RecordManager(new PrintToScreen());
        data=recordManager.getRecords();
        recordManager.printValues();

    }

}

