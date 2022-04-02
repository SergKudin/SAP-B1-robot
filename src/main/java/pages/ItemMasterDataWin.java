package pages;

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

    public ReadFileXLSX readDataFile() {
        readFileXLSX.readToList();
        utils.Logger.logInfo("Data.xlsx - rows:collum = "
                + readFileXLSX.sizeRows() + ":" + readFileXLSX.sizeCollum());
        return readFileXLSX;
    }

    public Integer readStatusFile () {
       return readFile.ReadFileData();
    }

    private Boolean setCode(String label, String value) {
        Logger.logInfo(value);
        WebUtils.pause(Timeouts.ITEM_MASTER_DATE_WINDOW_UPLOAD);
        return TextInputs.byLabel(label).setValue(value).sendEnter().textEquals(value);
    }

    private Boolean setUomGroup(String label, String value) {
        Logger.logInfo(value);
        return value.equals(DropDowns.byLabel(label).open().set(value).getCurrentValue());
    }

    private Boolean setTabUomGroup(String labelTab, String label, String value) {
        Logger.logInfo(value);
        openTab(labelTab);
        return TextInputs.byLabel(label, 1).putValue(value).sendEnter().textEquals(value);
    }

    public ItemMasterDataWin dataSet(ReadFileXLSX readFileXLSX, Integer nRow) {
        readDataFile();

        for (int row = nRow; row < readFileXLSX.sizeRows(); row++) {
            Logger.logInfo("Row No=" + row);
            Boolean dataSetOk = true;
            try {
                dataSetOk = dataSetOk && setCode("Код", readFileXLSX.getData(row, COLLUM_1));
                WebUtils.pause(Timeouts.ITEM_MASTER_DATE_UPLOAD);
                dataSetOk = dataSetOk && setUomGroup("Группа ЕИ", readFileXLSX.getData(row, COLLUM_2));
                WebUtils.pause(Timeouts.ITEM_MASTER_DATE_WINDOW_UPLOAD);
                dataSetOk = dataSetOk && setTabUomGroup("Продажи", "Код ЕИ продажи", readFileXLSX.getData(row, COLLUM_4));
                WebUtils.pause(Timeouts.ITEM_MASTER_DATE_WINDOW_UPLOAD);
                dataSetOk = dataSetOk && setTabUomGroup("Закупки", "Код ЕИ закупок", readFileXLSX.getData(row, COLLUM_3));
                WebUtils.pause(Timeouts.ITEM_MASTER_DATE_WINDOW_UPLOAD);
                Logger.logInfo("setting data for card " + readFileXLSX.getData(row, COLLUM_1) + " is OK = " + dataSetOk);
//            readFileXLSX.setStatus(dataSetOk, row, COLLUM_5);
                saveFileXLSX.setCellToFileXLSX(dataSetOk, row, COLLUM_1);
                saveDateToFile.saveDateToFile(row);
                Buttons.close();
                Pages.initPage(MainPage.class).clickItemMasterData();
//            Buttons.byLabel("Найти").clickIt();
                WebUtils.waitUntilPageIsLoaded();
                WebUtils.pause(Timeouts.ITEM_MASTER_DATE_WINDOW_UPLOAD);
            } catch (Exception e) {
                Logger.log("Error", true, true);
                e.printStackTrace();
//                row--;
                saveDateToFile.saveDateToFile(row);
                WebUtils.pause(Timeouts.ITEM_MASTER_DATE_WINDOW_UPLOAD);
                Buttons.close();
                Pages.initPage(MainPage.class).clickItemMasterData();
            }
        }
        return this;
    }

}
