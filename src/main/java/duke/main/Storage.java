package duke.main;

import duke.exception.CannotReadFileException;
import duke.exception.CannotSaveFileException;
import duke.task.TaskList;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

//Suppressing warnings here as file consistency is already being caught and checked
@SuppressWarnings("all")

public class Storage {

    private String filepath;

    public Storage(String filepath) {
        this.filepath = filepath;
    }

    /**
     * dataRead Method attemps to read saved data from file.
     *
     * @return  the populated TaskList (if there is any readable data), if not null
     * @throws CannotReadFileException  when the file cannot be found or the data within is unreadable
     */
    public TaskList load() throws CannotReadFileException {
        try {
            File file = new File(filepath);
            if (!file.exists()) {
                file.createNewFile();
            }
            FileInputStream dataFile = new FileInputStream(filepath);
            ObjectInputStream in = new ObjectInputStream(dataFile);
            TaskList taskList = (TaskList) in.readObject();
            in.close();
            dataFile.close();
            return taskList;
        } catch (IOException | ClassNotFoundException ignored) {
            throw new CannotReadFileException();
        }
    }

    /**
     * dataSave Method attempts to save data to file.
     *
     * @param taskList  the populated TaskList (if there is any readable data), if not null
     * @throws CannotSaveFileException  when the file cannot be saved or the data cannot be written
     */
    public void save(TaskList taskList) throws CannotSaveFileException {
        try {
            File file = new File(filepath);
            if (file.exists()) {
                file.delete();
            }
            file.createNewFile();
            FileOutputStream dataFile = new FileOutputStream(filepath);
            ObjectOutputStream out = new ObjectOutputStream(dataFile);
            out.writeObject(taskList);
            out.close();
            dataFile.close();
        } catch (IOException ignored) {
            throw new CannotSaveFileException();
        }
    }
}
