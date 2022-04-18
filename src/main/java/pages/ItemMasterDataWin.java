package pages;

import core.Main;
import utils.*;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.blocks.Tabs;

import java.util.List;

public class ItemMasterDataWin extends BasePage {

    SaveToFileXLSX saveFileXLSX = new SaveToFileXLSX();
    SaveToFileCSV saveDateToFile = new SaveToFileCSV();
    Pagination pagination = new Pagination();
    private static final Integer COLLUM_1 = 0;   //Код
    private static final Integer COLLUM_2 = 1;   //Группа ЕИ
    private static final Integer COLLUM_3 = 2;   //Код ЕИ закупок
    private static final Integer COLLUM_4 = 3;   //Код ЕИ продажи
    private static final Integer COLLUM_5 = 4;   //Status
    private Boolean check = false;
    private Boolean error = false;


    //   //label[text()='Код ЕИ закупок']/following-sibling::select[1] - универсальный xPath для заголовка
    private static final String BUTTON_OK = "//button[@class='c1']";

    public ItemMasterDataWin() {
        super();
//        WebUtils.waitUntilElementVisible(homeButton);
    }

    @FindBy(xpath = "//a[contains(@class, 'tab')]")
    List<WebElement> tabs;
    private final String TAB_BY_LABEL = "//div[contains(@class, 'tabheader') and @title='%s']";

    public Tabs openTab(String name) {
        clickElement(WebUtils.getElement(String.format(TAB_BY_LABEL, name)));
        waitUntilPageIsLoaded();
        return Pages.initPage(Tabs.class);
    }

    public MainPage gotoMainPage() {
//        driver.switchTo().window(MainWindow);

        return Pages.initPage(MainPage.class);
    }

    public Integer readStatusFile() {
        return readFile.ReadFileData();
    }

    private Boolean setCode(String label, String value) {
        Logger.logInfo("Code: " + value);
//        WebUtils.pause(Timeouts.ITEM_MASTER_DATE_WINDOW_UPLOAD);
        return TextInputs.byLabel(label).setValue(value).sendEnter().textEquals(value);
    }

    private Boolean setUomGroup(String label, String value) {
        Logger.logInfo("set " + value);
        if (value.equals(DropDowns.byLabel(label).getCurrentValue())) return true;
        return value.equals(DropDowns.byLabel(label).set(value).getCurrentValue());
    }

    private Boolean setTabUomGroup(String labelTab, String label, String value) {
        Logger.logInfo("Set Tab " + labelTab + " value " + value);
        openTab(labelTab);
        if (TextInputs.byLabel(label, 1).textEquals(value)) return true;
        return TextInputs.byLabel(label, 1).putValue(value).sendEnter().textEquals(value);
    }

    private Boolean operation(ReadFileXLSX readFileXLSX, Integer row) {
        Boolean dataSetOk = true;
        Pages.initPage(MainPage.class).clickItemMasterData();
        WebUtils.waitUntilPageIsLoaded();
        WebUtils.pause(Timeouts.ITEM_MASTER_DATE_WINDOW_UPLOAD);
        dataSetOk = dataSetOk && setCode("Код", readFileXLSX.getData(row, COLLUM_1));
        DropDowns.byLabel("Группа ЕИ").waitValue();
        WebUtils.pause(Timeouts.ITEM_MASTER_DATE_WINDOW_UPLOAD);
        dataSetOk = dataSetOk && setUomGroup("Группа ЕИ", readFileXLSX.getData(row, COLLUM_2));
//        WebUtils.pause(Timeouts.ITEM_MASTER_DATE_WINDOW_UPLOAD);
//        dataSetOk = dataSetOk && setTabUomGroup("Продажи", "Код ЕИ продажи", readFileXLSX.getData(row, COLLUM_4));
//        WebUtils.pause(Timeouts.ITEM_MASTER_DATE_WINDOW_UPLOAD);
//        dataSetOk = dataSetOk && setTabUomGroup("Закупки", "Код ЕИ закупок", readFileXLSX.getData(row, COLLUM_3));
//        WebUtils.pause(Timeouts.ITEM_MASTER_DATE_WINDOW_UPLOAD);
        Logger.logInfo("setting data for card " + readFileXLSX.getData(row, COLLUM_1) + " is OK = " + dataSetOk);
        return dataSetOk;
    }

    private Integer rowIterator(ReadFileXLSX readFileXLSX, Integer row) {
        row++;
        while (readFileXLSX.getData(row, COLLUM_5).equals("true")) {
            row++;
            if (row == readFileXLSX.sizeRows() - 1) return row;
        }
        return row;
    }

    private Integer check(ReadFileXLSX readFileXLSX, Integer row) {
        if (!check && (row == readFileXLSX.sizeRows() - 1)) {
            check = true;
            row = 0;
            Logger.logInfo("Start check");
        }
        error = false;
        return row;
    }

    public Integer dataSet(ReadFileXLSX readFileXLSX, Integer row, String path) {
        while (row < readFileXLSX.sizeRows() - 1) {
            row = rowIterator(readFileXLSX, row);
            Logger.logInfo("Row No=" + (row + 1));
            try {
                readFileXLSX.setStatus(operation(readFileXLSX, row), row, COLLUM_5);
                Buttons.close();
//                Modals.clickButtons("OK");
                row = check(readFileXLSX, row);
            } catch (Exception e) {
                Logger.log("Error data set", true, true);
                e.printStackTrace();
                readFileXLSX.setStatus(false, row, COLLUM_5);
                FileUtils.dataSave(readFileXLSX, path);
                WebUtils.pause(Timeouts.ITEM_MASTER_DATE_WINDOW_UPLOAD);
                Modals.clickButtons("Да");
                Buttons.close();
                if (error) {
                    Pages.initPage(MainPage.class).siteQuite();
                    return row;
                } else {
                    error = true;
                }
            }
        }
        Logger.logInfo("Data set completed");
        return row;
    }


}
