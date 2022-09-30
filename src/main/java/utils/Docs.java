package utils;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Docs {

    public static String[][] readExcelSheet(String excelFile) {
        String[][] dataTable = null;
        try {
            FileInputStream file = new FileInputStream(excelFile);
            XSSFWorkbook workbook = new XSSFWorkbook(file);

            XSSFSheet sheet = workbook.getSheetAt(0);

            int numRows = sheet.getLastRowNum() + 1;
            int numCols = sheet.getRow(0).getLastCellNum();

            dataTable = new String[numRows][numCols];

            for (int i = 0; i < numRows; i++) {
                XSSFRow row = sheet.getRow(i);
                for (int j = 0; j < numCols; j++) {
                    XSSFCell cell = row.getCell(j);
                    dataTable[i][j] = cell.toString();
                }
            }
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getLocalizedMessage());
        }
        return dataTable;
    }

    public static List<String[]> readCSV(String csvFile) {
        List<String[]> returnValue = new ArrayList<>();

        try {
            FileReader filereader = new FileReader(csvFile);

            CSVReader csvReader = new CSVReaderBuilder(filereader)
                    .withSkipLines(1)
                    .build();
            returnValue = csvReader.readAll();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return returnValue;
    }


}
