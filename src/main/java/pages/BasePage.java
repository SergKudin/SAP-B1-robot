package pages;

import core.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.WebUtils;

public class BasePage {
    protected WebDriver driver = WebDriverManager.getDriver();
    protected final Logger logger;
    String MainWindow;
    String ItemMasterDataWindow;

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
}
