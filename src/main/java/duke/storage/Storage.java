package duke.storage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import duke.exception.DukeException;
import duke.exception.DukeFileNotFoundException;
import duke.exception.DukeReadFileException;
import duke.exception.DukeWriteFileException;
import duke.utils.TaskList;

public class Storage {
    public static final String DEFAULT_FILE_PATH = "data/data";

    private String filePath;

    public Storage() {
        this.filePath = Storage.DEFAULT_FILE_PATH;
    }

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public String getFilePath() {
        return filePath;
    }

    public TaskList loadTaskList() throws DukeException {
        try {
            FileInputStream fis = new FileInputStream(filePath);
            ObjectInputStream ois = new ObjectInputStream(fis);
            TaskList tasks = (TaskList) ois.readObject();
            ois.close();
            return tasks;
        } catch (FileNotFoundException e) {
            throw new DukeFileNotFoundException(e);
        } catch (IOException | ClassNotFoundException e) {
            throw new DukeReadFileException(e);
        }
    }

    public void saveTaskList(TaskList tasks) throws DukeException {
        try {
            FileOutputStream fos = new FileOutputStream(filePath);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(tasks);
            oos.close();
        } catch (FileNotFoundException e) {
            throw new DukeFileNotFoundException(e);
        } catch (IOException e) {
            throw new DukeWriteFileException(e);
        }
    }
}