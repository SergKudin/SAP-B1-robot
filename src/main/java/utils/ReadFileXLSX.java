package utils;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;


public class ReadFileXLSX {

    private String fileName;

    public ReadFileXLSX(String fileName) {
        this.fileName = fileName;
    }

    ArrayList<ArrayList<String>> listDataFile = new ArrayList<ArrayList<String>>();

    public ArrayList<ArrayList<String>> readToList() {
        try {
            FileInputStream inputStream = new FileInputStream(new File(fileName));
            XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
            XSSFRow row;
            Iterator<Row> rowIterator = workbook.getSheetAt(0).iterator();
            while (rowIterator.hasNext()) {
                row = (XSSFRow) rowIterator.next();
                Iterator<Cell> cellIterator = row.cellIterator();
                listDataFile.add(readRows(cellIterator));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return listDataFile;
    }

    private ArrayList<String> readRows(Iterator<Cell> cellIterator) {
        ArrayList<String> newItemMasterDate = new ArrayList<String>(5);
        while (cellIterator.hasNext()) {
            Cell cell = cellIterator.next();
            newItemMasterDate.add(cell.getStringCellValue());
        }
        return newItemMasterDate;
    }

    public void setStatus (Boolean status, Integer nRow, Integer nCollum) {
        ArrayList<String> ItemMasterDate = new ArrayList<String>(5);
        ItemMasterDate.addAll(listDataFile.get(nRow));
        ItemMasterDate.add(status.toString());
        listDataFile.set(nRow, ItemMasterDate);
    }

    public String getData(Integer nRow, Integer nCollum) {return listDataFile.get(nRow).get(nCollum);}

    public Integer sizeRows() {return listDataFile.size();}

    public Integer sizeCollum() {return listDataFile.get(0).size();}
}
