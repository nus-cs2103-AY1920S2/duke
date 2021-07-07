package duke.pack;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import java.time.LocalDate;
import java.time.LocalTime;

import java.util.ArrayList;
import java.util.Scanner;


/**
 * Handles the loading and updating of tasks to hard disk.
 */
public class Storage {
    protected String filePath;
    protected File file;

    /**
     * Creates a storage.
     * @param filePath String representing the path of data file
     * @throws DukeException if file cannot be created
     */
    public Storage(String filePath) throws DukeException {
        this.filePath = filePath;

        // file creation following code from
        // https://stackoverflow.com/questions/6142901/how-to-create-a-file-in-a-directory-in-java/6143076#6143076
        file = new File(filePath);
        try {
            file.getParentFile().mkdirs();
            file.createNewFile();
        } catch (IOException e) {
            throw new DukeException("Oh no, file could not be created!\n");
        }
    }

    /**
     * Loads tasks from the hard disk.
     * @return an ArrayList of the tasks
     * @throws DukeException if tasks cannot be loaded from hard disk
     */
    public ArrayList<Task> load() throws DukeException {
        ArrayList<Task> arrList = new ArrayList<>();

        try {
            Scanner fileScanner = new Scanner(file);
            while (fileScanner.hasNext()) {
                String task = fileScanner.nextLine();
                String[] taskArr = task.split("\\|");
                String taskType = taskArr[0].trim();

                if (taskType.equals("T")) {
                    Task todo = processTodo(taskArr);
                    arrList.add(todo);

                } else if (taskType.equals("E")) {
                    Task event = processEvent(taskArr);
                    arrList.add(event);

                } else if (taskType.equals("D")) {
                    Task deadline = processDeadline(taskArr);
                    arrList.add(deadline);
                }
            }

        } catch (FileNotFoundException e) {
            throw new DukeException("Oh no, file could not be found, please check your file path!\n");
        }

        return arrList;
    }

    /**
     * Converts data from file into a To-do task.
     * @param taskArr String array of the data
     * @return a To-do object
     */
    public Task processTodo(String[] taskArr) {
        Task todo = new Todo(taskArr[2].trim(), "0000-00-00", "00:00");
        if (taskArr[1].trim().equals("1")) {
            todo.setDone(true);
        }
        return todo;
    }

    /**
     * Converts data from file into an Event task.
     * @param taskArr String array of the data
     * @return an Event object
     */
    public Task processEvent(String[] taskArr) {
        LocalDate date = LocalDate.parse(taskArr[4].trim());
        LocalTime time = LocalTime.parse(taskArr[3].trim());

        Task event = new Event(taskArr[2].trim(), time, date, date.toString(), time.toString());
        if (taskArr[1].trim().equals("1")) {
            event.setDone(true);
        }

        return event;
    }

    /**
     * Converts data from file into a Deadline task.
     * @param taskArr String array of the data
     * @return a Deadline object
     */
    public Task processDeadline(String[] taskArr) {
        LocalDate date = LocalDate.parse(taskArr[4].trim());
        LocalTime time = LocalTime.parse(taskArr[3].trim());

        Task deadline = new Deadline(taskArr[2].trim(), time, date, date.toString(), time.toString());
        if (taskArr[1].trim().equals("1")) {
            deadline.setDone(true);
        }

        return deadline;
    }

    /**
     * Saves tasks to the hard disk.
     * @param tasks TaskList of all the tasks
     * @throws DukeException if tasks cannot be saved to hard disk
     */
    public void save(TaskList tasks) throws DukeException {
        try {
            FileWriter fw = new FileWriter(file);
            String text = "";

            for ( Task task: tasks.getList()) {
                text = text + task.formatForFile();
            }

            fw.write(text);
            fw.close();

        } catch (IOException e) {
            throw new DukeException("Oh no, error in saving tasks to hard disk!");
        }
    }
}
