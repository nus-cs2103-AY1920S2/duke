package duke.commands;

import static duke.commands.Parser.FORMATTER;

import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.ToDo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * Deals with loading tasks from the file and saving tasks in the file.
 */
public class Storage {

    /**
     * The path where duke.txt is located.
     */
    private String filePath;
    /**
     * The TaskList.
     */
    private TaskList taskList;

    /**
     * Creates a new Storage.
     *
     * @param path the specified path to duke.txt
     * @param list the TaskList
     */
    public Storage(String path, TaskList list) {
        this.filePath = path;
        this.taskList = list;
    }

    /**
     * the index in the input array where the description is located.
     */
    public static final int DESC = 2;

    /**
     * the index in the input array where the time is located.
     */
    public static final int TIME = 3;

    /**
     * Retrieves all the tasks entered previously by the user from duke.txt and
     * adds them to the TaskList.
     *
     * @throws IOException if the file cannot be found
     */
    public void retrieveInfo() throws IOException {
        File data = new File(filePath);
        if (!data.exists()) {
            data.createNewFile();
        }

        Scanner scanner = new Scanner(data);
        while (scanner.hasNextLine()) {
            String[] arr  = scanner.nextLine().split("[|]");
            Task newTask = null;

            if  (arr[0].trim().equals("T")) {
                newTask = new ToDo(arr[DESC].trim());
            } else if (arr[0].trim().equals("D")) {
                newTask = new Deadline(arr[DESC].trim(), arr[TIME].trim(),
                        FORMATTER);
            } else if (arr[0].trim().equals("E")) {
                newTask = new Event(arr[DESC].trim(), arr[TIME].trim(),
                        FORMATTER);
            }

            if (arr[1].trim().equals("Y")) {
                newTask.markAsDone();
            }
            taskList.add(newTask, false);
            assert newTask != null : "No task to retrieve";
            assert newTask.getDescription() != null
                    : "No description for this task";
        }
    }

    /**
     * Stores all the tasks the user has entered into the Tasklist in duke.txt.
     *
     * @throws IOException if the file cannot be written
     */
    public  void  updateInfo() throws IOException {
        String  fileString = "";
        for (int i = 0; i < taskList.size(); i += 1) {
            fileString += taskList.get(i).fileString() + "\n";
            assert taskList.get(i) != null : "No task to update";
            assert taskList.get(i).getDescription() != null
                    : "No description for this task";
        }
        new File(filePath).createNewFile();
        FileWriter fw = new FileWriter(filePath);
        fw.write(fileString);
        fw.close();
    }
}
