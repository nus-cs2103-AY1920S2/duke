package com.duke.bot;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Represents the permanent storage of the list of tasks managed by Duke Bot into the hard drive.
 */
public class Storage {
    private static final String DESTINATION_PATH = "C:\\tasks.txt";

    private Storage() {
    }

    /**
     * Creates a Storage File.
     */
    public static Storage createSrorageFile() {
        return new Storage();
    }

    /**
     * Saves the input into a .txt file.
     *
     * @param input The message to be saved.
     */
    public void saveToFile(String input) {
        try {
            File file = new File(DESTINATION_PATH);
            FileWriter fw = new FileWriter(file);
            fw.write(input);
            fw.close();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }


    /**
     * Returns the path in which the output is saved.
     */
    public String getDestinationPath() {
        return DESTINATION_PATH;
    }

}
