package utils;


public interface Messages {

    String CONTINUE_IMPOSSIBLE = " It's impossible to continue scenario.";
    String DROPDOWN_NOT_OPENED = " DropDown was not opened.";
    String DROPDOWN_NOT_SELECTED = " DropDown option was not selected.";
    String DROPDOWN_NOT_CLICKABLE = " DropDown is not clickable.";
    String CHECKBOX_NOT_CHECKED = " Checkbox was not checked.";
    String RADIO_BUTTON_NOT_SELECTED = " RadioButton was not selected.";
    String TEXT_FIELD_VALUE_WAS_NOT_APPLIED = " Text was not typed successfully.";
    String ELEMENT_NOT_VISIBLE = " Element is not displayed.";
    String ELEMENT_NOT_CLICKABLE = " Element is not clickable.";
    String INPUT_NOT_ENABLED = " Input not available for typing.";
    String MODAL_NOT_OPENED = " Modal window was not opened.";
    String CALENDAR_NOT_OPENED = " Calendar window was not opened.";
    String CALENDAR_NOT_CLOSED = " Calendar window was not closed.";
    String VALUE_WAS_NOT_APPLIED = " New value was not applied.";
    String NO_SUCH_OPTION = " No such option.";
    String BAD_ARGUMENT = " Bad argument.";
    String CONDITION_NOT_REACHABLE = " Condition not reachable.";

    String TEST_TARGET_ENV_ERR = " Looks like test not tagged @Prod run on prod.";

    static String noSuchElement(String xpath) {
        return String.format("NoSuchElement '%s'", xpath);
    }

}
