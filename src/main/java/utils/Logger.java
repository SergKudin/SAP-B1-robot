package utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;

public final class Logger {
    private static int counter;
    private static boolean saveON=false;
    private static ArrayList<ArrayList<String>> log = new ArrayList<>();

    private Logger() {
        throw new RuntimeException();
    }

    public static void logSaveOn() {
        saveON = true;
    }

    public static void logInfo(String str) {
        logInternal(str, false, false);
    }

    public static void logErr(String str) {
        logInternal(str, true, false);
    }

    public static void log(String str, boolean isErr, boolean screenshot) {
        logInternal(str, isErr, screenshot);
    }

    private static void logInternal(String str, boolean isErr, boolean screenshot) {
        final String methodName = Thread.currentThread().getStackTrace()[3].getMethodName();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss SSS");
        String dateStr = java.time.LocalTime.now().format(dtf);
        dateStr = dateStr.substring(0, dateStr.indexOf(' ')) + ":" + dateStr.substring(dateStr.indexOf(' ') + 1);
        if (isErr) {
            System.err.printf("%d) %s [%s]: %s\n", ++counter, dateStr, methodName, str);
        } else {
            System.out.printf("%d) %s [%s]: %s\n", ++counter, dateStr, methodName, str);
        }
        if (saveON) {log.add(new ArrayList(Arrays.asList(counter + ")", dateStr, "[" + methodName + "]:", str)));}
        if (screenshot) {
            String saveS;
            Boolean sOk = saveScreenshots("/" + counter + ") " + dateStr.replace(":", "."));
            if (sOk) {
                saveS = "Screenshot saved";
            } else {
                saveS = "Error: Screenshot not saved";
            }
            System.out.println("                " + saveS);
        }
    }

    private static Boolean saveScreenshots(String fileName) {
        try {
            File screenshots = ((TakesScreenshot) core.WebDriverManager.getDriver()).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(screenshots, new File(utils.FileUtils.SCREENSHOTS_DIR_PATH + fileName + ".png"));
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void saveLog() {
        if (saveON) {
            SaveToFileCSV save = new SaveToFileCSV();
            save.saveList2ToFile(log, "log");
        } else {
            System.out.println("log.csv save disabled!");
        }
    }
}
