package utils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class SaveToFileCSV {

    private Object Exception;


    public SaveToFileCSV() {
    }

    public void saveList2ToFile(ArrayList<ArrayList<String>> list, String nameFile) {
        try {
            FileOutputStream writer = new FileOutputStream(nameFile+".csv");
            for (ArrayList<String> news : list) {
                String s = news.stream().map(n -> String.valueOf(n)).collect(Collectors.joining(";"))
                        + System.getProperty("line.separator");
                writer.write(s.getBytes("Cp1251"));
            }
            writer.close();
            System.out.println("File " + nameFile+".csv save OK");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("ERROR. File " + nameFile+".csv not save");
        }
    }

    public Object saveDateToFile(Integer dataSave, String nameFile) {
        try {
            FileOutputStream writer = new FileOutputStream(nameFile);
                String s = dataSave + System.getProperty("line.separator");
                writer.write(s.getBytes("Cp1251"));
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
            Exception=e;
        }
        return Exception;
    }

    public Object saveDateToFile(Integer dataSave) {
        return saveDateToFile(dataSave, "ADS.csv");
    }


    }



