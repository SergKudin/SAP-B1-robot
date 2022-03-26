package utils;

public interface Timeouts {
    float FACTOR = 1.0f;
    int PAGE_LOADING = (int) (60 * FACTOR);
    int ELEMENT_VISIBILITY = (int) (15 * FACTOR);
    int CLICK = (int) (15 * FACTOR);
    int AFTER_CLICK = (int) (5 * FACTOR);
    int PROCESSING = (int) (25 * FACTOR);
    int ALERT = (int) (15 * FACTOR);
    int TEXT_TYPE = (int) (6 * FACTOR);
    int MODAL_TO_BE_SHOWN = (int) (7 * FACTOR);
    int DROPDOWN_TO_BE_OPENED = (int) (5 * FACTOR);
    public static final int PAGE_LOADING_TIMEOUT = 60;
    public static final int CLICK_TIMEOUT = 15;
    public static final int ELEMENT_VISIBILITY_TIMEOUT = 20;
    public static final int ITEM_MASTER_DATE_UPLOAD = 1000;
    public static final int ITEM_MASTER_DATE_WINDOW_UPLOAD = 400;

}
