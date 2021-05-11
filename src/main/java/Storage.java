/**
 * Storage acts to load and save the task list of the user.
 * It contains methods to load upon the start of Duke and
 * methods to save after every method call that changes
 * the TaskList object.
 * */

import java.io.*;

public class Storage {

    private String FILEPATH;

    /**
     * Default constructor. Takes in a String that is the filepath of the saved file.
     * @param FILEPATH refers to the path of the saved file.
     */
    public Storage (String FILEPATH) {
        this.FILEPATH = FILEPATH;
    }

    /**
     * Returns a File object with the given filepath.
     * */
    public File load() {
        File f = new File(FILEPATH);
        return f;
    }

    /**
     * Saves the current TextList into the saved file
     * @param text is the text that is to be written into the save file.
     * */
    public void saveFile(String text) {
        try {
            File f = new File(FILEPATH);

            FileWriter fw = new FileWriter(f.getAbsoluteFile());
            fw.write(text);
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
