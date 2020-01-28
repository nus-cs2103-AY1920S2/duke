package storage;

import task.Task;
import taskList.TaskList;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.io.BufferedWriter;

/**
 * The class that deals with loading tasks from file and saving tasks in file.
 */
public class Storage {
    protected String filePath;
    protected Path path;

    public Storage(String filePath) {
        path = Paths.get(filePath);
        // will need to deal with errorneous paths
    }

    /**
     * Get the path of the relevant file that data is stored in.
     *
     * @return the relative path that data is stored in.
     */
    public String getPath() {
        return path.toString();
    }

    /**
     * This method writes and saves all the current tasks in the list to the dedicated data file.
     *
     * @param tasks The tasks to be saved.s
     */
    public void save(TaskList tasks) {
//            List<String> encodedAddressBook = AddressBookEncoder.encodeAddressBook(addressBook);
        String s = "";
        for (Task t: tasks.getList()) {
            s = s + t.format() + "\n";
        }
        write(s, path);
//      Files.write(path, encodedAddressBook);
    }

    /**
     * This method loads the old tasks that have been saved in the dedicated data file previously.
     */
    public TaskList load(){

        if (!Files.exists(path) || !Files.isRegularFile(path)) {
            return new TaskList();
        }
        TaskList tasks = new TaskList();
        try {
            List<String> loadedTasks = Files.readAllLines(path);
            for (String s : loadedTasks) {
                if(!s.equals("")) {
                    tasks.add(Task.load(s));
                }
            }
        } catch (FileNotFoundException fnfe) {
            throw new AssertionError("A non-existent file scenario is already handled earlier.");
            // other errors
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return tasks;
    }

    /**
     * This method performs the writing to the data file that is specified.
     *
     * @param s The tasks to be written to the file.
     * @param path The relative path that the data will be written to.
     */
    public static void write(String s, Path path) {
        if(!Files.exists(path)) {
            try {
                Files.createDirectory(path);
            } catch (FileAlreadyExistsException e) {

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try (BufferedWriter writer = Files.newBufferedWriter(path)){
            writer.write(s);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
