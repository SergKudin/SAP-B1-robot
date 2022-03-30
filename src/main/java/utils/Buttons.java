package utils;

import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

public class Buttons {

    private WebElement input;

    private final static String PATH_TEMPLATE = "//a[@title = '%s']";
    private final static String PATH_TEMPLATE_2 = "//button[@title = '%s']";

    public final static String X_BUTTON = "//span[contains(@class, 'icon-close')]";
    private final static String CANT_DEFINE = "Can't define x-button";

    private Buttons(WebElement input) {
        this.input = input;
    }

    public static Buttons byLabel(String text) {
        try {
            return byPath(String.format(PATH_TEMPLATE, text));
        } catch (Exception e) {
            return byPath(String.format(PATH_TEMPLATE_2, text));
        }
    }

    public static Buttons byPath(String xpath) {
        return byElement(WebUtils.getElement(xpath));
    }

    public static Buttons byElement(WebElement element) {
        return new Buttons(element);
    }

    public static void modalsOpened () {
        if (Modals.isOpened()) {
            Modals.clickButton("Да");
        }
        if (Modals.isOpened()) {
            Modals.clickButton("OK");
        }
    }

    public static void close() {
        try {
        List<WebElement> xButtons = WebUtils.getElements(X_BUTTON).stream().filter(WebElement::isDisplayed).collect(Collectors.toList());
        if (xButtons.size() != 1)
            throw new RuntimeException(CANT_DEFINE);
        WebElement xButton = xButtons.get(0);
        WebUtils.waitUntil(Messages.ELEMENT_NOT_CLICKABLE, xButton::isEnabled);
        WebUtils.clickElement(xButton);
        modalsOpened ();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void close(WebElement e) {
        List<WebElement> xButtons = WebUtils.getElementsUnder(e, X_BUTTON).stream().filter(WebElement::isDisplayed).collect(Collectors.toList());
        if (xButtons.size() != 1)
            throw new RuntimeException(CANT_DEFINE);
        WebElement xButton = xButtons.get(0);
        WebUtils.waitUntil(Messages.ELEMENT_NOT_CLICKABLE, xButton::isEnabled);
        WebUtils.clickElement(xButton);
        modalsOpened();
    }

    public void clickIt() {
        WebUtils.waitUntil(Messages.ELEMENT_NOT_CLICKABLE, input::isEnabled);
        WebUtils.clickElement(input);
        WebUtils.pause(Timeouts.ITEM_MASTER_DATE_UPLOAD);
        modalsOpened();
    }
}
