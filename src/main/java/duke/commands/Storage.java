package duke.commands;

import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.ToDo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import static duke.commands.Parser.FORMATTER;

/**
 * deals with loading tasks from the file and saving tasks in the file.
 */
public class Storage {

    /**
     * the path where duke.txt is located.
     */
    private String filePath;
    /**
     * the TaskList.
     */
    private TaskList taskList;
    /**
     * index in arr containing the description.
     */
    private static final int DESC = 2;
    /**
     * index in arr containing the time.
     */
    private static final int TIME = 3;

    /**
     * creates new Storage.
     * @param filePath the path to the duke.txt file where previous user input
     *                 has been stored.
     * @param taskList the TaskList
     */
    public Storage(String filePath, TaskList taskList) {
        this.filePath = filePath;
        this.taskList = taskList;
    }

    /**
     * retrieves all the tasks entered previously by the user from duke.txt and
     * adds them to the TaskList.
     */
    public void retrieveInfo() {
        try {
            Scanner scanner = new Scanner(new File(filePath));
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
                taskList.add(newTask, "");
            }
        } catch (FileNotFoundException e) {
            System.out.println("Something went wrong: " + e.getMessage());
            return;
        }
    }

    /**
     * stores all the tasks the user has entered into the Tasklist in duke.txt.
     */
    public  void  updateInfo() {
        String  fileString = "";
        for (int i = 0; i < taskList.size(); i += 1) {
            fileString += taskList.get(i).fileString() + "\n";
        }

        try {
            new File(filePath).createNewFile();
            FileWriter fw = new FileWriter(filePath);
            fw.write(fileString);
            fw.close();
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }
}
