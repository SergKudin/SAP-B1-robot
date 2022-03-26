package utils;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

public class TextInputs {

    protected TextInputs(WebElement input) {
        this.input = input;
    }

    protected WebElement input;

    private interface Path {
        String WRAPPER_BY_LABEL_INPUT = "//label[text()='%s']/preceding-sibling::input[1]";
    }

    public static TextInputs byLabel(String label) {
        try{
            return byPath(String.format(Path.WRAPPER_BY_LABEL_INPUT, label));
        } catch (Exception ignored) {
            throw new RuntimeException(Messages.noSuchElement(String.format(Path.WRAPPER_BY_LABEL_INPUT, label)));
        }
    }

    public static TextInputs byPath(String xpath) {
        return byElement(WebUtils.getElement(xpath));
    }

    public static TextInputs byElement(WebElement element) {
        return new TextInputs(element);
    }

    public TextInputs setValue(String text) {
        putValue(text);
        WebUtils.waitNotStrict(Messages.TEXT_FIELD_VALUE_WAS_NOT_APPLIED, () -> getValue().equals(text), Timeouts.TEXT_TYPE);
        if (!getValue().equals(text)) {
            WebUtils.getLog().info("Type all-at-once unsuccessful, try char-by-char");
            clearField();
            typeByChar(text);
        }
        WebUtils.waitUntil(Messages.TEXT_FIELD_VALUE_WAS_NOT_APPLIED, () -> getValue().equals(text), Timeouts.TEXT_TYPE);
        return this;
    }

    public TextInputs putValue(String text) {
        WebUtils.waitUntil(Messages.INPUT_NOT_ENABLED, () -> input.isDisplayed() && input.isEnabled());
        if (!isClean()) {
            clearField();
        }
        input.sendKeys(text);
        return this;
    }

    private void typeByChar(String text) {
        char[] asArr = text.toCharArray();
        for (char currentChar : asArr) {
            input.sendKeys(currentChar + "");
            WebUtils.pause(1);
            if (getValue().isEmpty() || getLastChar() != currentChar) {
                input.sendKeys(currentChar + "");
            }
        }
    }

    private char getLastChar() {
        char[] currentAsArr = getValue().toCharArray();
        return currentAsArr[currentAsArr.length - 1];
    }

    private boolean isClean() {
        return getValue().isEmpty();
    }

    private String getValue() {
        return input.getAttribute("value");
    }

    private TextInputs clearField() {
        WebUtils.waitUntil("Input not available for typing", () -> input.isDisplayed() && input.isEnabled());
        input.sendKeys(Keys.CONTROL + "a");
        input.sendKeys(Keys.BACK_SPACE);
        WebUtils.waitUntil("Field was not cleaned", this::isClean);
        return this;
    }
}
