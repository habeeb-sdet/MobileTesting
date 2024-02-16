package com.epam.utils.file;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class FileUtils {

    public static void createDir(String path){
        File dir = new File(path);
        if(dir.exists()){
            dir.delete();
        }
        dir.mkdir();
    }

    public static void writeToFile(String filePath, List<?> list){
        try(FileWriter fileWriter = new FileWriter(filePath);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)){
            for(Object entry : list){
                bufferedWriter.write(String.valueOf(entry));
                bufferedWriter.write("\n");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
