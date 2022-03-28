package utils;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;


public class ReadFileXLSX {

    private String fileName;

    public ReadFileXLSX(String fileName) throws IOException {
        this.fileName = fileName;
    }

    private ArrayList<String> newItemMasterDate = new ArrayList<String>(4);
    private ArrayList<ArrayList<String>> listDataFile = new ArrayList<ArrayList<String>>();


//    public ReadFileXLSX setFileName(String fileName) {
//        FileName = fileName;
//        return this;
//    }

    public void readToList() throws IOException {
        FileInputStream inputStream = new FileInputStream(new File(fileName));
        XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
        Iterator<Row> rowIterator = workbook.getSheetAt(0).iterator();
        while (rowIterator.hasNext()) {
            listDataFile.add(readRows(rowIterator.next().cellIterator()));
        }
    }

    private ArrayList<String> readRows(Iterator<Cell> cellIterator) {
        while (cellIterator.hasNext()) {
            Cell cell = cellIterator.next();
            newItemMasterDate.add(cell.getStringCellValue());
        }
        return newItemMasterDate;
    }

    public String getData(Integer nRow, Integer nCollum){return listDataFile.get(nRow).get(nCollum);}

    public Integer sizeList() {return listDataFile.size();}
}
