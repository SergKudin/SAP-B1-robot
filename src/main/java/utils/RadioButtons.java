package utils;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

public class RadioButtons {

    private RadioButtons(WebElement input) {
        this.input = input;
    }

    private WebElement input;

    public static final String RADIOBUTTON_BY_LABEL = "//label[@title = '%s']/preceding-sibling::input";

    public static RadioButtons byLabel(String label) {
        try {
            return byPath(String.format(RADIOBUTTON_BY_LABEL, label));
        } catch (NoSuchElementException e) {
            throw new RuntimeException("Can't find such labeled checkbox");
        }
    }

    public static RadioButtons byPath(String xpath) {
        return byElement(WebUtils.getElement(xpath));
    }

    public static RadioButtons byElement(WebElement input) {
        return new RadioButtons(input);
    }

    public void select() {
        WebUtils.waitUntil(Messages.ELEMENT_NOT_CLICKABLE, input::isEnabled);
        WebUtils.clickElement(input);
        WebUtils.waitUntilPageIsLoaded();
        WebUtils.waitUntil(Messages.RADIO_BUTTON_NOT_SELECTED, input::isSelected);
    }

    public boolean isSelected() {
        return input.isSelected();
    }

}
