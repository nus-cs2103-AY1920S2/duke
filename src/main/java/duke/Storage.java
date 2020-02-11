package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

import duke.tasks.*;

public class Storage {

    private File tasksFile;

    public Storage() throws DukeException, IOException {

        File tasksFileDirectory = new File(System.getProperty("user.dir") + "/dukeData");
        if (!tasksFileDirectory.isDirectory() && !tasksFileDirectory.mkdir()) {
            throw new DukeException("Error creating tasks file directory");
        }

        this.tasksFile = new File(System.getProperty("user.dir") + "/dukeData/tasksFile.txt");
        tasksFile.createNewFile();
    }


    /**
     * Loads data from the tasks data file.
     *
     * @return TaskList. A TaskList object containing all parseable Task objects in the data file
     * @throws FileNotFoundException if Scanner object is not able to find the tasks data file
     */
    public TaskList loadTasksFile() throws FileNotFoundException {

        Scanner sc = new Scanner(tasksFile);
        TaskList taskData = new TaskList();

        while (sc.hasNextLine()) {

            String[] taskString = sc.nextLine().split(":;:");

            switch(taskString[0]) {
            case "T":
                Task tdTask = new ToDo(taskString[2]);
                if (taskString[1].equals("1")) {
                    tdTask.markAsDone();
                }
                taskData.addTask(tdTask);
                break;

            case "D":
                try {
                    Task dlTask = new Deadline(taskString[2], taskString[3]);
                    if (taskString[1].equals("1")) {
                        dlTask.markAsDone();
                    }
                    taskData.addTask(dlTask);
                } catch (DukeException e) {
                    System.out.println("Error loading data from tasksFile.txt. Skipping the following line:");
                    System.out.println(Arrays.asList(taskString).toString());
                }

                break;

            case "E":
                Task evTask = new Event(taskString[2], taskString[3]);
                if (taskString[1].equals("1")) {
                    evTask.markAsDone();
                }
                taskData.addTask(evTask);
                break;

            default:
                break;
            }
        }
        return taskData;
    }

    /**
     * Writes all Tasks in the TaskList object to the file at the specified file location.
     *
     * @param tasks TaskList. TaskList object storing all current Tasks.
     * @throws IOException if FileWriter object do not work as expected
     */
    public void writeTasksFile(TaskList tasks) throws IOException {
        FileWriter fw = new FileWriter(tasksFile);
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.getTask(i);
            fw.write(task.toDataString() + "\n");
        }
        fw.close();
    }
}
