import core.WebDriverManager;
import org.openqa.selenium.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import pages.ItemMasterDataWin;
import pages.MainPage;
import utils.*;

import java.util.ArrayList;

public class BaseTest {
    ReadFileXLSX readFileXLSX = new ReadFileXLSX("Data.xlsx");
    protected WebDriver driver;
    public Integer currentRow;

    @BeforeClass
    public void BeforeClassMethod() {
        Logger.logSaveOn();
        Logger.logInfo("Start BeforeClass");
        Logger.logInfo("Screenshots is Dir Empty:" + FileUtils.cleanScreenshots());
        driver = WebDriverManager.getDriver();
        driver.get(Const.SITE.getConst());
        readFileXLSX.readToList();
        Pages.initPage(MainPage.class)
                .goToLoginPage()
                .userLogin("Sap B1 Robot.txt");
    }

    @BeforeMethod
    public void setUp() {
        Logger.logInfo("Start BeforeMethod");
    }


    @AfterMethod
    public void siteClose() {
        Logger.logInfo("Start AfterMethod");
    }

    @AfterClass
    public void tearDown() {
        Logger.logInfo("Start AfterClass");
        SaveToFileXLSX save = new SaveToFileXLSX();
        SaveToFileCSV save1 = new SaveToFileCSV();
        save1.saveList2ToFile(readFileXLSX.getList(),"Data");
        Logger.saveLog();
        if (save.saveListToXLSX(readFileXLSX.getList(),"Data")) {
            Logger.logInfo("Data save OK");
        } else {
            Logger.logErr("ERROR. Data not save");
        }
        driver.quit();
    }
}
