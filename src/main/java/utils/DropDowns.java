package utils;

import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

public class DropDowns {

    private DropDowns(String label) {
        this.label = label;
//        try {
        input = WebUtils.getElement(String.format(Path.WRAPPER_BY_LABEL_INPUT, label));
//        } catch (Exception ignored) {
//            throw new RuntimeException(Messages.noSuchElement(String.format(Path.WRAPPER_BY_LABEL_INPUT2, label)));
//        }
    }

    private DropDowns(String label, String s) {
        this.label = label;
//        try {
        input = WebUtils.getElement(String.format(Path.WRAPPER_BY_LABEL_INPUT2, label));
//        } catch (Exception ignored) {
//            throw new RuntimeException(Messages.noSuchElement(String.format(Path.WRAPPER_BY_LABEL_INPUT2, label)));
//        }
    }


    private WebElement input;
    private String label;
    private String requiredOption;

    private interface Path {
        String WRAPPER_BY_LABEL_INPUT = "//label[text()='%s']/following-sibling::select[1]";
        String WRAPPER_BY_LABEL_INPUT2 = "//label[text()='%s']/preceding-sibling::select[1]";
        //        String WRAPPER_BY_LABEL_INPUT2 = "//*[text()='%s']/..";
        String OPTIONS = WRAPPER_BY_LABEL_INPUT + "//option";
        String VALUE = WRAPPER_BY_LABEL_INPUT + "/following-sibling::label[1]";
    }

    public static DropDowns byLabel(String label) {
        return new DropDowns(label);
    }

    public static DropDowns byLabel(String label, String s) {
        return new DropDowns(label, s);
    }


    public DropDowns set(String option) {
        open();
        selectOption(option);
        checkOptionApplied();
        WebUtils.waitUntilPageIsLoaded();
        return this;
    }

    public DropDowns submit () {
        Modals.clickButtons("Да");
        Modals.clickButtons("Обновить");
        return this;
    }

    public DropDowns open() {
        WebUtils.waitUntil(Messages.ELEMENT_NOT_VISIBLE, input::isDisplayed);
        WebUtils.waitUntil(Messages.DROPDOWN_NOT_CLICKABLE, input::isEnabled);
        WebUtils.clickElement(input);
        WebUtils.pause(Timeouts.ITEM_MASTER_DATE_WINDOW_UPLOAD);
        return this;
    }

    public DropDowns selectOption(String option) {
        requiredOption = option;
        List<WebElement> dropdownOptions = WebUtils.getElements(String.format(Path.OPTIONS, label));
        WebElement requiredOption = dropdownOptions.stream()
                .filter(o -> o.getAttribute("innerText").equals(option))
                .collect(Collectors.toList()).get(0);
        WebUtils.clickElement(requiredOption);
        if (Modals.isOpened()) {
            Modals.clickButtons("Да");
        }
        return this;
    }

    public DropDowns checkOptionApplied() {
        WebUtils.waitUntil(Messages.DROPDOWN_NOT_SELECTED,
                () -> getCurrentValue().equals(requiredOption));
        return this;
    }

    public String getCurrentValue() {
        List<WebElement> currentAsList = WebUtils.getElements(String.format(Path.VALUE, label));
        return (!currentAsList.isEmpty()) ? currentAsList.get(0).getAttribute("innerText") : "";
    }

    public DropDowns waitValue() {
        WebUtils.waitUntil(Messages.DROPDOWN_NOT_SELECTED, () -> getCurrentValue().isEmpty());
        return this;
    }


}
