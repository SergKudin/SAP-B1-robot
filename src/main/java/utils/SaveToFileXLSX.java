package utils;

import org.apache.poi.common.usermodel.HyperlinkType;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class SaveToFileXLSX {

    private Object Exception;

    public SaveToFileXLSX() throws IOException {
    }

    private static XSSFCellStyle createStyleForTitle(XSSFWorkbook workbook) {
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        XSSFCellStyle style = workbook.createCellStyle();
        style.setFont(font);
        return style;
    }

    private static XSSFCellStyle createStyleForTextGrin(XSSFWorkbook workbook) {
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setColor(IndexedColors.GREEN.index);
        XSSFCellStyle style = workbook.createCellStyle();
        style.setFont(font);
        return style;
    }

    private static XSSFCellStyle createStyleForTextRed(XSSFWorkbook workbook) {
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setColor(IndexedColors.RED.index);
        XSSFCellStyle style = workbook.createCellStyle();
        style.setFont(font);
        return style;
    }

    public void saveDateToFileXLSX(ArrayList<ResultSearch> ListResultSearch, String nameFile) throws IOException {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("OLX");
        CreationHelper createHelper = workbook.getCreationHelper();
        XSSFCellStyle hLinkStyle = workbook.createCellStyle();
        XSSFFont hLinkFont = workbook.createFont();
        hLinkFont.setUnderline(XSSFFont.U_SINGLE);
        hLinkFont.setColor(IndexedColors.BLUE.index);
        hLinkStyle.setFont(hLinkFont);

        int rownum = 0;
        Cell cell;
        Row row;
        row = sheet.createRow(rownum);

        XSSFCellStyle style = createStyleForTitle(workbook);
        XSSFCellStyle styleGrin = createStyleForTextGrin(workbook);
        XSSFCellStyle styleRed = createStyleForTextRed(workbook);


        // ???????
        cell = row.createCell(0, CellType.STRING);
        cell.setCellValue("???????");
        cell.setCellStyle(style);
        // ????????????????
        cell = row.createCell(1, CellType.STRING);
        cell.setCellValue("????????????????");
        cell.setCellStyle(style);
        // ??????????????????
        cell = row.createCell(2, CellType.STRING);
        cell.setCellValue("??????????????????");
        cell.setCellStyle(style);
        // ????????????
        cell = row.createCell(3, CellType.STRING);
        cell.setCellValue("????????????");
        cell.setCellStyle(style);

        // Data
        for (ResultSearch news : ListResultSearch) {
            rownum++;
            row = sheet.createRow(rownum);

            // ??????? (A)
            cell = row.createCell(0, CellType.NUMERIC);
            cell.setCellValue(rownum);
            cell.setCellStyle(styleRed);
            // ???????????????? (B)
            cell = row.createCell(1, CellType.STRING);
            cell.setCellValue(news.getNameLot());
            XSSFHyperlink link = (XSSFHyperlink) createHelper.createHyperlink(HyperlinkType.URL);
            link.setAddress(news.getLink());
            cell.setHyperlink((XSSFHyperlink) link);
            cell.setCellStyle(hLinkStyle);

            // ?????????????????? (C)
            cell = row.createCell(2, CellType.NUMERIC);
            cell.setCellValue(news.getPrice());
            cell.setCellStyle(styleGrin);
            // ???????????? (D)
            cell = row.createCell(3, CellType.STRING);
            cell.setCellValue(news.getLink());
        }
        File file = new File(nameFile + ".xlsx");
//        file.getParentFile().mkdirs();

        FileOutputStream outFile = new FileOutputStream(file);
        workbook.write(outFile);
        Logger.logInfo("Created file: " + file.getAbsolutePath());
//        System.out.println("Created file: " + file.getAbsolutePath());

    }

}
