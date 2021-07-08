package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * Storage handles the reading and writing of tasks to and from a file. It handles converting of file data into
 * a list of Task Objects.
 */
public class Storage {
    private Path filePath;
    private File file;

    /**
     * Constructs a Storage object which store and read tasks saved in the specific file path.
     *
     * @param filePath Path of file to write and read saved tasks from.
     */
    public Storage(Path filePath) {
        this.filePath = filePath;
        this.file = new File(this.filePath.toString());
    }

    /**
     * Writes new tasks to file. If Storage was previously unable to read in saved data due to invalid data or
     * corrupted file, it wil overwrite the file.
     *
     * @param tasks List of tasks.
     */
    public void update(TaskList tasks) {
        try {
            FileWriter fw = new FileWriter(file);
            for (int i = 0; i < tasks.getSize(); i++) {
                fw.write(String.format("%s%n", tasks.getTask(i + 1).toFileFormat()));
            }
            fw.close();
        } catch (IOException | DukeException e) {
            e.printStackTrace();
        }
    }

    /**
     * Reads saved tasks from file and convert them into Task objects and store them into a list.
     *
     * @return List of saved tasks from file.
     * @throws DukeException If file is unable to be read properly or if data is invalid.
     */
    public ArrayList<Task> load() throws DukeException {
        ArrayList<Task> tempList = new ArrayList<>();
        try {
            List<String> lines = Files.readAllLines(this.filePath);
            for (String line : lines) {
                try {
                    Task t = Task.getTaskFromMemory(line);
                    tempList.add(t);
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                    break;
                }
            }
        } catch (IOException e) {
            throw new DukeException(e.getMessage());
        }
        return tempList;
    }

    /**
     * Writes task to archive. If Storage was previously unable to read in saved data due to invalid data or
     * corrupted file, it wil overwrite the file.
     * @param tasks List of tasks to be archived.
     * @param archiveFilePath Path of archival file.
     */
    public void archive(TaskList tasks, Path archiveFilePath) {
        File archiveFile = new File(archiveFilePath.toString());
        try {
            FileWriter fw = new FileWriter(archiveFile, true);
            for (int i = 0; i < tasks.getSize(); i++) {
                fw.append(String.format("%s%n", tasks.getTask(i + 1).toFileFormat()));
            }
            fw.close();
        } catch (IOException | DukeException e) {
            e.printStackTrace();
        }
    }
}
