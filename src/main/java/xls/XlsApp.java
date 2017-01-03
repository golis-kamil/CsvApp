package xls;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.NPOIFSFileSystem;
import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by trot on 03.01.17.
 */
public class XlsApp {

    public static final String XLS_FILE = "spreadsheet.xls";

    public static void main(String[] args) {

        ClassLoader classLoader = XlsApp.class.getClassLoader();
        File spreadsheet = new File(classLoader.getResource(XLS_FILE).getFile());

        // F1 - mnożnik, G1 - wynik
        try (NPOIFSFileSystem fs = new NPOIFSFileSystem(spreadsheet)) {
            Workbook workbook = new HSSFWorkbook(fs);
            Sheet sheet = workbook.getSheetAt(0);
            Row row = sheet.getRow(0);
            Cell cell = row.getCell(6);

            System.out.println("Formuła G1: " + cell.getCellFormula());
            System.out.println("Wartość bez uzupełnienia F1: " + cell.getNumericCellValue());

            double cellValue = getFormulaEvaluator(workbook).evaluate(cell).getNumberValue();

            System.out.println("Wartość po uzupełnieniu F1, z pamięci podręcznej: " + cellValue);

            row.createCell(5).setCellValue(50.0);
            cellValue = getFormulaEvaluator(workbook).evaluate(cell).getNumberValue();
            System.out.println("Wartość po uzupełnieniu F1: " + cellValue);

            saveWorkbook(workbook);

        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    private static void saveWorkbook(Workbook workbook) throws IOException {
        try (FileOutputStream stream = new FileOutputStream("/home/trot/git/CsvApp/src/main/resources/result.xls")) {
            workbook.write(stream);
        }
    }

    private static FormulaEvaluator getFormulaEvaluator(Workbook workbook) {
        return workbook.getCreationHelper().createFormulaEvaluator();
    }
}
