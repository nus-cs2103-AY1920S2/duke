package storage;

import task.Deadline;
import task.Event;
import task.Task;
import task.TaskList;
import task.Todo;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.File;
import java.util.Scanner;
import java.time.LocalDateTime;

/**
 * Represents a storage program that stores tasks into and loads tasks from a file "data/duke.txt".
 * When the main() program starts, this class reads and loads current tasks stored in file into TaskList object.
 * After each modification to TaskList, this class saves by re-writing all updating tasks into file.
 */
public class Storage {

    private String storageFilePath;

    /**
     * Constructs a storage.
     *
     * @param filePath is the path to file.
     * @throws IOException for file exceptions.
     */
    public Storage(String filePath) throws IOException {
        this.storageFilePath = filePath;
    }

    /**
     * Saves the tasks by over-writing into the targeted file.
     *
     * @param currentTasks list of tasks.
     * @throws IOException file exceptions.
     */
    public void saveTasksIntoFile(TaskList currentTasks) throws IOException {
        FileWriter fw = new FileWriter(this.storageFilePath);
        for (Task task: currentTasks.getCurrentTasks()) {
            fw.write(task.toStringForFileStorage() + "\n");
        }
        fw.close();
    }

    /**
     * Loads existing tasks in the file into an array list of tasks.
     *
     * @return an array list of current tasks stored in file.
     * @throws FileNotFoundException if the filePath is wrong.
     */
    public ArrayList<Task> loadExistingFileTasks() throws IOException {
        ArrayList<Task> taskListFromFile = new ArrayList<>();

        try {
            File storageFile = new File(this.storageFilePath);
            Scanner sc = new Scanner(storageFile);
            while (sc.hasNext()) {
                String line = sc.nextLine();
                char taskCommand = line.charAt(0);
                char taskDoneStatus = line.charAt(4);
                String taskAction = line.substring(8);
                String[] taskActionAndTime = taskAction.split(" \\| ");

                if (taskCommand == 'T') {
                    taskListFromFile.add(new Todo(taskAction));
                } else if (taskCommand == 'D') {
                    taskListFromFile.add(new Deadline(taskActionAndTime[0], LocalDateTime.parse(taskActionAndTime[1])));
                } else if (taskCommand == 'E') {
                    taskListFromFile.add(new Event(taskActionAndTime[0], LocalDateTime.parse(taskActionAndTime[1])));
                }

                //if this task from file has status '1', mark this task status in the current task list as done.
                if (taskDoneStatus == '1') {
                    int currentTaskListSize = taskListFromFile.size();
                    int taskIndexToUpdate = currentTaskListSize - 1;
                    taskListFromFile.get(taskIndexToUpdate).markAsDone();
                }
            }
        } catch (FileNotFoundException ex) {
            Path pathToStorageFile = Paths.get(this.storageFilePath);
            Files.createDirectories(pathToStorageFile.getParent());
            Files.createFile(pathToStorageFile);
        }
        return taskListFromFile;
    }
}
