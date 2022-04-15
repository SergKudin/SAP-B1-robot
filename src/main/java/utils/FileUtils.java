package utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class FileUtils {
    public static final Path SCREENSHOTS_DIR_PATH = Paths.get("target/results/screenshots").toAbsolutePath();
    public static final Path DOWNLOADS_PATH = Paths.get("target/results/downloads").toAbsolutePath();
    public static void copy(Path original, Path target) {
        try {
            Files.copy(original, target, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ignored) {
        }
    }

    public static boolean cleanScreenshots() {
        if (!isScreenshotsDirNotEmpty()) return true;
        File screenshotsDir = SCREENSHOTS_DIR_PATH.toFile();
        boolean result = true;
        for (File file : screenshotsDir.listFiles()) {
            result &= file.delete();
        }
        return result;
    }

    public static boolean isScreenshotsDirNotEmpty() {
        File dir = SCREENSHOTS_DIR_PATH.toFile();
        return dir.listFiles() != null && dir.listFiles().length > 0;
    }

    public static Path getPathScreenshots () {return SCREENSHOTS_DIR_PATH;}

    public static void dataSave (ReadFileXLSX readFileXLSX, String path) {
        SaveToFileXLSX save = new SaveToFileXLSX();
        SaveToFileCSV save1 = new SaveToFileCSV();
        save1.saveList2ToFile(readFileXLSX.getList(), path + "Data");
        if (save.saveListToXLSX(readFileXLSX.getList(), path + "Data")) {
            Logger.logInfo("File" + path + "Data.xlsx save OK");
        } else {
            Logger.logErr("ERROR. File " + path + "Data.xlsx not save");
        }

    }
}
