package pages;

import core.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.ReadFileXLSX;
import utils.WebUtils;

import java.util.ArrayList;

public class BasePage {
    protected WebDriver driver = WebDriverManager.getDriver();
    protected final Logger logger;
    String MainWindow;
    String ItemMasterDataWindow;
    ReadFileXLSX readFileXLSX = new ReadFileXLSX("Data.xlsx");

    public BasePage() {
        PageFactory.initElements(driver, this);
        logger = LoggerFactory.getLogger(this.getClass());
        waitUntilPageIsLoaded();

    }

    public void waitUntilPageIsLoaded() {
        WebUtils.waitUntilPageIsLoaded();
    }

    public void clickElement(WebElement e) {
        WebUtils.clickElement(e);
    }

    public void readDataFile() {
            readFileXLSX.readToList();
            utils.Logger.logInfo("Data.xlsx - rows:collum = "
                    + readFileXLSX.sizeRows().toString() + ":" + readFileXLSX.sizeCollum());
    }
}
