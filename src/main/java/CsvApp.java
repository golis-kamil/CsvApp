import java.io.IOException;

/**
 * Created by trot on 27.12.16.
 */
public class CsvApp {

    public static void main(String[] args) {

        RecordManager recordManager = new RecordManager(new PrintToFile());
        recordManager.printValues("/home/trot/git/CsvApp/src/main/resources/result.csv");

        recordManager = new RecordManager(new PrintToScreen());
        recordManager.printValues();

    }

}
