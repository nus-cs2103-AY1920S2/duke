package main.java;

import java.io.FileWriter;
import java.io.IOException;

public class Storage{

    String FILEPATH; 

    public Storage(String filepath) {
        this.FILEPATH = filepath;
    }

    /**
     * add task to storage
     * @param filepath
     * @param textToAdd
     * @throws IOException
     */
    public void writeToFile(String filepath, String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(filepath);
        fw.write(textToAdd);
        fw.close(); 
    }

}