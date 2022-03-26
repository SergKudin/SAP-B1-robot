package utils;

import core.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.nio.file.FileSystems;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;

public class WebUtils {
    private static WebDriver driver = WebDriverManager.getDriver();
    private static JavascriptExecutor jse = (JavascriptExecutor) driver;
    private static final Logger logger = LoggerFactory.getLogger(WebUtils.class);

    public static Logger getLog() {
        return logger;
    }

    public static void setDriver(WebDriver d) {
        driver = d;
        jse = (JavascriptExecutor) driver;
    }

//    public static boolean isElementPresent(String path) {
//        //https://stackoverflow.com/questions/12270092/best-way-to-check-that-element-is-not-present-using-selenium-webdriver-with-java
//        List<WebElement> ec = driver.findElements(By.xpath(path));
//        return ec.size() > 0;
//    }

    public static boolean isElementPresent(WebElement e) {
        //does not work for invisible elements. use isElementPresent(String xpath)
        try {
            return e.isDisplayed();
        } catch (Exception ex) {
            return false;
        }
    }

    public static boolean isElementPresent(String xpath) {
        return !driver().findElements(By.xpath(xpath)).isEmpty();
    }

    public static WebElement getElement(String path) {
        return driver.findElement(By.xpath(path));
    }

    public static WebElement scrollToElement(WebElement element) {
        jse.executeScript("arguments[0].scrollIntoView()", element);
        return element;
    }

    public static void click(WebElement e) {
        getClickWait().until(driver -> {
            e.click();
            return true;
        });
    }

    public static void complexClick(String path) {
        getClickWait().until(driver -> {
            WebElement e = driver.findElement(By.xpath(path));
            scrollToElement(e);
            e.click();
            return true;
        });
    }

    private static FluentWait<WebDriver> getClickWait() {
        return new WebDriverWait(driver, Timeouts.CLICK_TIMEOUT)
                .ignoring(StaleElementReferenceException.class)
                .ignoring(ElementClickInterceptedException.class)
                .ignoring(ElementNotInteractableException.class);
    }

    public static void pause(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException ignored) {
        }
    }

    public static void clickActions(WebElement element) {
        new Actions(driver).moveToElement(element).click(element).build().perform();
    }

    public static void complexClick(WebElement e) {
        waitUntilPageIsLoaded();
        getClickWait().withMessage("Click failed").until(driver -> {
            scrollToElement(e);
            clickJs(e);
            return true;
        });
    }

    public static void clickJs(WebElement element) {
        jse.executeScript("arguments[0].click()", element);
    }

    public static String getLocator(WebElement e) {
        String locator = ("" + e).split(" -> ")[1].split(": ")[1];
        return String.format("\"%s\"", locator.substring(0, locator.length() - 1));
    }

    public static void waitUntilPageIsLoaded() {
        WebDriverWait wait = new WebDriverWait(driver, Timeouts.PAGE_LOADING_TIMEOUT);
        wait.until(driver ->
                ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete"));
    }

    public static void saveScreen(String fileName) {
        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String targetPath = FileUtils.SCREENSHOTS_DIR_PATH
                + FileSystems.getDefault().getSeparator() + fileName
                + getTimeStamp("_yyyy.MM.dd_HH.mm.ss")
                + ".png";
        logger.info("saved screen: " + targetPath);
        FileUtils.copy(srcFile.toPath(), Paths.get(targetPath));
    }

    public static String getTimeStamp(String format) {
        DateFormat dateFormat = new SimpleDateFormat(format);  //почитать
        Date date = new Date();
        return dateFormat.format(date);
    }

    public static WebElement waitUntilElementVisible(WebElement e) {
        FluentWait<WebDriver> wait = new WebDriverWait(driver, Timeouts.ELEMENT_VISIBILITY_TIMEOUT)
                .ignoring(StaleElementReferenceException.class);
        wait.until(ExpectedConditions.visibilityOf(e));
        return e;
    }

    public static WebDriver driver() {
        return driver;
    }

    public static void waitUntil(String message, Callable<Boolean> c) {
        waitUntil(message, c, Timeouts.AFTER_CLICK);
    }

    public static void waitUntil(String message, Callable<Boolean> c, int timeout) {
        FluentWait<WebDriver> wait = new WebDriverWait(driver(), timeout).withMessage(message);
        wait.until(driver -> {
            try {
                return c.call();
            } catch (Exception ignored) {
                return false;
            }
        });
    }

    public static void waitNotStrict(String message, Callable<Boolean> c, int timeout) {
        try {
            waitUntil(message, c, timeout);
        } catch (Exception e) {
            logger.info(message);
        }
    }

//    public static WebElement getElement(String xpath) {
//        List<WebElement> asList = getElements(xpath);
//        if (asList.isEmpty())
//            throw new NoSuchElementException(Messages.noSuchElement(xpath));
//        return getElements(xpath).get(0);
//    }

    public static List<WebElement> getElements(String xpath) {
        return driver().findElements(By.xpath(xpath));
    }

    public static WebElement getElementUnder(WebElement e, String xpath) {
        return getElementsUnder(e, xpath).get(0);
    }

    public static List<WebElement> getElementsUnder(WebElement e, String xpath) {
        return e.findElements(By.xpath("." + xpath));
    }

    public static void clickElement(WebElement element) {
        StringBuilder log = new StringBuilder("Click " + WebUtils.getLocator(element));
        WebUtils.scrollToElement(element);
        WebUtils.pause(100);
        boolean isPrevClickedWasSuccessful = false;
        try {
            element.click();
            isPrevClickedWasSuccessful = true;
        } catch (Exception exSimple) {
            log.append("; Simple click failed; ");
        }
        if (!isPrevClickedWasSuccessful) {
            try {
                log.append("Try click by Actions; ");
                WebUtils.clickActions(element);
                log.append("Click by Actions successful.");
                isPrevClickedWasSuccessful = true;
            } catch (Exception exActions) {
                log.append("Click by Actions failed; ");
            }
        }
        if (!isPrevClickedWasSuccessful) {
            try {
                log.append("Try click by JS; ");
                WebUtils.clickJs(element);
                log.append("Click by JS successful.");
                isPrevClickedWasSuccessful = true;
            } catch (Exception exJx) {
                log.append("Click by JS failed; ");
            }
        }
        if (!isPrevClickedWasSuccessful) {
            try {
                log.append("Try complex click; ");
                WebUtils.complexClick(element);
                log.append("Complex click successful.");
            } catch (Exception exComplex) {
                log.append("Click failed.");
                logger.info(log.toString());
                throw new RuntimeException("click element '" + element + "' failed");
            }
        }
        logger.info(log.toString());
        WebUtils.pause(100);
    }

}
