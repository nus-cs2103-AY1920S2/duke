package duke.pack;

import java.time.LocalDate;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;

/**
 * Handles the loading and updating of tasks to hard disk
 */
public class Storage {
    protected String filePath;
    protected File file;

    public Storage(String filePath) throws DukeException {
        this.filePath = filePath;

        // file creation following code from
        // https://stackoverflow.com/questions/6142901/how-to-create-a-file-in-a-directory-in-java/6143076#6143076
        file = new File(filePath);
        try {
            file.getParentFile().mkdirs();
            file.createNewFile();
        } catch (IOException e) {
            throw new DukeException("    Oh no, file could not be created!\n");
        }
    }

    /**
     * loads tasks from the hard disk
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
                    Task todo = new Todo(taskArr[2].trim(), taskArr[2].trim());
                    if (taskArr[1].trim().equals("1")) {
                        todo.setDone(true);
                    }
                    arrList.add(todo);

                } else if (taskType.equals("E")) {
                    LocalDate date = LocalDate.parse(taskArr[4].trim());
                    String fullDesc = taskArr[2].trim() + " " + taskArr[3].trim() + " " + taskArr[4].trim();

                    Task event = new Event(taskArr[2].trim(), taskArr[3].trim(), date, fullDesc);
                    if (taskArr[1].trim().equals("1")) {
                        event.setDone(true);
                    }
                    arrList.add(event);

                } else if (taskType.equals("D")) {
                    LocalDate date = LocalDate.parse(taskArr[4].trim());

                    String fullDesc = taskArr[2].trim() + " " + taskArr[3].trim() + " " + taskArr[4].trim();

                    Task deadline = new Deadline(taskArr[2].trim(), taskArr[3].trim(), date, fullDesc);
                    if (taskArr[1].trim().equals("1")) {
                        deadline.setDone(true);
                    }
                    arrList.add(deadline);
                }
            }

        } catch (FileNotFoundException e) {
            throw new DukeException("    Oh no, file could not be found, please check your file path!\n");
        }

        return arrList;
    }

    /**
     * saves tasks to the hard disk
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
