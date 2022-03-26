package utils;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

public class CheckBoxes {

    private CheckBoxes(WebElement input) {
        this.input = input;
    }

    private WebElement input;

    private static final String CHECKBOX_BY_LABEL = "//label[@title = '%s']/preceding-sibling::input";

    public static CheckBoxes byLabel(String label) {
        String path = String.format(CHECKBOX_BY_LABEL, label);
        try {
            return byElement(WebUtils.getElement(path));
        } catch (NoSuchElementException e) {
            throw new RuntimeException(Messages.noSuchElement(path));
        }
    }

    public static CheckBoxes byElement(WebElement input) {
        return new CheckBoxes(input);
    }

    public CheckBoxes check() {
        set(true);
        return this;
    }

    public CheckBoxes uncheck() {
        set(false);
        return this;
    }

    public void set(boolean requiredState) {
        if (requiredState != isChecked()) {
            WebUtils.waitUntil(Messages.ELEMENT_NOT_CLICKABLE, input::isEnabled);
            WebUtils.clickElement(input);
            WebUtils.waitUntilPageIsLoaded();
            WebUtils.waitUntil(Messages.CHECKBOX_NOT_CHECKED, () -> requiredState == isChecked());
        }
    }

    public boolean isChecked() {
        return input.isSelected();
    }

}
