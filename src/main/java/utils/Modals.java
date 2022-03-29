package utils;


import org.openqa.selenium.WebElement;

import java.util.List;

public class Modals {

    private static WebElement window;

    protected interface Path {

        String MODAL = "//div[@role='dialog']";
        String ANY_BY_TEXT = "//*[text()='%s']";
    }

    public static void clickButton(String buttonName) {
        waitForItSelf();
        try {
            Buttons.byLabel(buttonName).clickIt();
        } catch (Exception ignored) {
            Buttons
                    .byPath(String.format(Path.ANY_BY_TEXT, buttonName))
                    .clickIt();
        }
    }

    public static void close() {
        waitForItSelf();
        Buttons.close(window);
    }

    public static boolean isOpened() {
        return WebUtils.getElements(Path.MODAL)
                .stream()
                .filter(WebElement::isDisplayed).count() == 3;
    }

    public static void waitForItSelf() {
        WebUtils.waitUntil(Messages.MODAL_NOT_OPENED, Modals::isOpened, Timeouts.MODAL_TO_BE_SHOWN);
        List<WebElement> asList = WebUtils.getElements(Path.MODAL);
        window = asList.get(asList.size() - 1);
    }

//    public static void typeByLabel(String fieldLabel, String text) {
//        waitForItSelf();
//        TextInputs.byLabel(fieldLabel).setValue(text);
//    }

//    public static void selectRadiobuttonByLabel(String label) {
//        RadioButtons.byLabel(label).select();
//    }

}
