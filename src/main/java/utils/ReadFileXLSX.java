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

    ArrayList<ArrayList<String>> listDataFile = new ArrayList<>();

    public ArrayList<ArrayList<String>> readToList() {
        try {
            FileInputStream inputStream = new FileInputStream(fileName);
            XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
            XSSFRow row;
            for (Row cells : workbook.getSheetAt(0)) {
                row = (XSSFRow) cells;
                Iterator<Cell> cellIterator = row.cellIterator();
                listDataFile.add(readRows(cellIterator));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        utils.Logger.logInfo("Data.xlsx - rows:collum = " + sizeRows() + ":" + sizeCollum());
        return listDataFile;
    }

    private ArrayList<String> readRows(Iterator<Cell> cellIterator) {
        ArrayList<String> newItemMasterDate = new ArrayList<>(5);
        while (cellIterator.hasNext()) {
            Cell cell = cellIterator.next();
            newItemMasterDate.add(getCellValue(cell));
        }
        try {
            boolean b = newItemMasterDate.get(4).isEmpty();
        } catch (Exception e) {
            newItemMasterDate.add(4, false + "");
        }
        return newItemMasterDate;
    }

    private String getCellValue(Cell cell) {
        String s = null;
        switch (cell.getCellType()) {
            case NUMERIC -> {
                s = (long) cell.getNumericCellValue() + "";
                s = s.replace(".0", "");
            }
            case STRING -> s = cell.getStringCellValue();
        }
        return s;
    }

    public void setStatus(Boolean status, Integer nRow, Integer nCollum) {
        ArrayList<String> ItemMasterDate = new ArrayList<>(5);
        ItemMasterDate.addAll(listDataFile.get(nRow));
        ItemMasterDate.set(nCollum, status.toString());
        listDataFile.set(nRow, ItemMasterDate);
    }

    public String getData(Integer nRow, Integer nCollum) {
        return listDataFile.get(nRow).get(nCollum);
    }

    public ArrayList<ArrayList<String>> getList() {
        return listDataFile;
    }

    public ArrayList<String> getRows(Integer nRow) {
        return listDataFile.get(nRow);
    }

    public Integer sizeRows() {
        return listDataFile.size();
    }

    public Integer sizeCollum() {
        return listDataFile.get(0).size();
    }
}
