package utils;

import core.Main;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class ReadFile {
    private ArrayList<String> listDataFile = new ArrayList<>();


    public ReadFile ReadFileToList(String file) {
        try (BufferedReader br = Files.newBufferedReader(Paths.get( file))) { //C:\jdk\Project\StartDate\
            listDataFile = (ArrayList<String>) br.lines().collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return this;
    }

    public Integer ReadFileData() {
        Integer data = 1;
        try (BufferedReader br = Files.newBufferedReader(Paths.get("ADS.csv"))) {
           data = Integer.valueOf(br.lines().collect(Collectors.toList()).get(0));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return data;
    }

    public String getData(Integer n) {
        return listDataFile.get(n);
    }
}