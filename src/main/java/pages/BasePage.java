package pages;

import core.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.*;

import java.util.ArrayList;

public class BasePage {
    protected WebDriver driver = WebDriverManager.getDriver();
    protected final Logger logger;
    String MainWindow;
    String ItemMasterDataWindow;
    ReadFileXLSX readFileXLSX = new ReadFileXLSX("Data.xlsx");
    ReadFile readFile = new ReadFile();

    public BasePage() {
        PageFactory.initElements(driver, this);
        logger = LoggerFactory.getLogger(this.getClass());
        waitUntilPageIsLoaded();

    }

    public MainPage logOut() {
        String FILE = "//*[text()='Файл']/..";
        String EXIT = "//a[@id='mb_526']";
        WebElement hiderFile = driver.findElement(By.xpath(FILE));
        WebElement exit = driver.findElement(By.xpath(EXIT));
        Actions hider = new Actions(driver);
        hider
                .moveToElement(hiderFile)
                .pause(Timeouts.CLICK_TIMEOUT).build();
        hider.moveToElement(exit)
                .pause(Timeouts.CLICK_TIMEOUT).click().build();
        hider.perform();
        return Pages.initPage(MainPage.class);
    }

    public void waitUntilPageIsLoaded() {
        WebUtils.waitUntilPageIsLoaded();
    }

    public void clickElement(WebElement e) {
        WebUtils.clickElement(e);
    }

}
