package utils;

import core.WebDriverManager;
import org.openqa.selenium.support.PageFactory;

public class Pages {
    public static <T> T initPage(Class<T> page) {
        return PageFactory.initElements(WebDriverManager.getDriver(), page);
    }
}

