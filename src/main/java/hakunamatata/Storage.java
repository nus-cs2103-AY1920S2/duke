package hakunamatata;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

import hakunamatata.task.Deadline;
import hakunamatata.task.Event;
import hakunamatata.task.Task;
import hakunamatata.task.TaskList;
import hakunamatata.task.ToDo;

/**
 * Represents a storage which stores the tasks details.
 *
 * @author Wang Yuting
 */
public class Storage {
    private String filepath;

    /**
     * Creates a <code>Storage</code> with given <code>filepath</code>.
     */
    public Storage(String filepath) {
        this.filepath = filepath;
    }

    /**
     * Loads saved tasks from the storage.
     *
     * @return An <code>ArrayList</code> of <code>Task</code> if there is any saved tasks, NULL if not.
     */
    public ArrayList<Task> load() {
        ArrayList<Task> records = new ArrayList<>();
        try {
            File f = new File(this.filepath);
            boolean isFileExists = f.exists();
            if (!isFileExists) {
                f.getParentFile().mkdirs();
                f.createNewFile();
            }
            Scanner s = new Scanner(f); // create a Scanner using the File as the source
            while (s.hasNextLine()) {
                String taskRecord = s.nextLine();
                String[] recordInfoParts = taskRecord.split("\\|");
                Task temp = null;
                boolean isDone = false;
                if (recordInfoParts[1].trim().equals("1")) {
                    isDone = true;
                }
                switch (recordInfoParts[0].trim()) {
                case "T":
                    temp = new ToDo(recordInfoParts[2].trim(), isDone);
                    break;
                case "D":
                    temp = new Deadline(recordInfoParts[2].trim(),
                            LocalDateTime.parse(recordInfoParts[3].trim(),
                                    Task.DATETIME_FORMAT), isDone);
                    break;
                case "E":
                    String[] eventStartAndEndDateTimes = recordInfoParts[3].split("-");
                    temp = new Event(recordInfoParts[2].trim(),
                            LocalDateTime.parse(eventStartAndEndDateTimes[0].trim(), Task.DATETIME_FORMAT),
                            LocalDateTime.parse(eventStartAndEndDateTimes[1].trim(), Task.DATETIME_FORMAT), isDone);
                    break;
                default:
                    break;
                }
                records.add(temp);
            }
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            return records;
        }
    }

    /**
     * Saves current tasks to the storage.
     *
     * @param taskList Current tasks list.
     * @throws HakunaMatataException If there is any IOException.
     */
    public void save(TaskList taskList) throws HakunaMatataException {
        try {
            FileWriter fw = new FileWriter(this.filepath, false);
            fw.write(taskList.getSavedString());
            fw.close();
        } catch (IOException ex) {
            throw new HakunaMatataException(ex.getMessage());
        }
    }
}
