package com.duke.bot;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Represents the permanent storage of the list of tasks managed by Duke Bot into the hard drive.
 */
public class Storage {
    private static final String DESTINATION_PATH = "/tasks.txt";
    private static final String ARCHIVE_PATH = "/archive.txt";

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
     * Arhives the current task list.
     *
     * @throws DukeException When the archive process is unsuccessful.
     */
    public void archive() throws DukeException{
        try {
            File archiveFile = new File(ARCHIVE_PATH);
            File oldFile = new File(DESTINATION_PATH);
            boolean success = oldFile.renameTo(archiveFile);
            FileWriter fw = new FileWriter(oldFile);
            fw.close();
            if (!success) {
                throw new DukeException("Archive file already exists!");
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }


    /**
     * Returns the path in which the output is saved.
     *
     * @return The path of the task list.
     */
    public String getDestinationPath() {
        return DESTINATION_PATH;
    }

    /**
     * Returns the path of the archive file.
     *
     * @return The path of the archive file.
     */
    public String getArchivePath() {
        return ARCHIVE_PATH;
    }

}
