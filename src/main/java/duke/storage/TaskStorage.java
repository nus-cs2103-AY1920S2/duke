package duke.storage;

import duke.task.Priority;
import exception.IllegalCommandException;
import duke.task.Task;
import duke.task.TaskDispatch;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents the Storage object for user's Tasks.
 * Consists of an ArrayList representing in-app memory as well as methods to
 * write to a file for persistent memory, simulating the hard disk.
 */
public class TaskStorage {
    /**
     * Represents the hard disk to which reads and writes occur.
     */
    private File file;

    /**
     * Represents the path to the text file used for persistent storage
     */
    private String filePath;

    /**
     * Represents the in-memory task list.
     */
    private ArrayList<Task> taskList;

    /**
     * Constructor for a Storage object.
     * The Storage object is initialised such that on start up, its in-memory
     * ArrayList gets populated by the persisted data from previous sessions.
     *
     * @param filePath the path of the text file, as a string.
     * @throws FileNotFoundException
     */
    public TaskStorage(String filePath) throws FileNotFoundException {
        this.taskList = new ArrayList<Task>();
        this.file = new File(filePath);
        this.filePath = filePath;
        populateTaskList();
    }

    public TaskStorage() {
        this.taskList = new ArrayList<Task>();
        this.file = new File("textDukeIP.txt");
        this.filePath = "textDukeIP.txt";
        try {
            populateTaskList();
        } catch (FileNotFoundException e) {
        }
    }

    /**
     * Reads all the Tasks stored in persistent memory, and clones them into
     * in-memory storage.
     *
     * @throws FileNotFoundException
     */
    private void populateTaskList() throws FileNotFoundException {
        Scanner scanner = new Scanner(this.file);
        while (scanner.hasNext()) {
            try {
                taskList.add(TaskDispatch.dispatchTaskFromStorage(scanner.nextLine().split("\\s+")));
            } catch (IllegalCommandException e) {
                // Assumption is that data is checked to be valid before adding to the file
                // Hence, when reading from it, the data should result in valid duke.task.Task objects
                e.printStackTrace();
            }
        }
    }

    /**
     * Returns a snapshot of the tasks in memory as a list.
     *
     * @return tasks in memory as an ArrayList.
     */
    public ArrayList<Task> getTaskList() {
        return this.taskList;
    }

    /**
     * Adds a new Task to the list of tasks in the memory and persistent storage.
     *
     * @param newTask new task.
     */
    public void addToTaskList(Task newTask) {
        this.taskList.add(newTask);
        try {
            FileWriter fw = new FileWriter(filePath, true);
            if (this.taskList.size()!=1) {
                fw.write(System.lineSeparator());
            }
            fw.write(newTask.toString());
            fw.close();
        } catch (IOException e) {
            // default behaviour is that nothing happens
        }
    }

    /**
     * Marks a task as done from both in-memory and persistent storage.
     *
     * @param taskNumber task number as it appears in the list order.
     */
    public void markTaskAsDone(int taskNumber) {
        this.taskList.get(taskNumber - 1).markAsDone();
        ArrayList<String> tempArray = new ArrayList<>();
        int tempCounter = 0;
        Scanner scanner = null;
        try {
            scanner = new Scanner(this.file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        while (scanner.hasNextLine()) {
            tempCounter++;
            if (tempCounter == taskNumber) {
                String lineToModify = scanner.nextLine();
                tempArray.add(lineToModify.replace(Task.CROSS, Task.TICK));
                continue;
            }
            tempArray.add(scanner.nextLine());
        }

        // add the contents of auxillary array to the file
        replaceTasksInFile(tempArray);

    }

    /**
     * Deletes the task corresponding to the taskNumber from both the in-memory
     * and persistent storage.
     *
     * @param taskNumber task number of the task to be deleted, in list order.
     */
    public void deleteFromTaskList(int taskNumber) {
        System.out.println(this.taskList.size());
        this.taskList.remove(taskNumber - 1);
        System.out.println(this.taskList.size());
        // Add all lines except line to be removed in an auxillary array
        ArrayList<String> tempArray = new ArrayList<>();
        int tempCounter = 0;
        Scanner scanner = null;
        try {
            scanner = new Scanner(this.file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // guard condition
        if (this.taskList.size() == 0) {
            System.out.println("entered guard clause");
            replaceTasksInFile(tempArray);
            return;
        }

        while (scanner.hasNextLine()) {
            tempCounter++;
            if (tempCounter == taskNumber) {
                scanner.nextLine();
                continue;
            }
            tempArray.add(scanner.nextLine());
        }

        // add the contents of auxillary array to the file
        replaceTasksInFile(tempArray);
    }

    /**
     * Replace the contents of the File with new contents.
     *
     * @param tasksArray an ArrayList containing the tasks to rewrite the file.
     */
    private void replaceTasksInFile(ArrayList<String> tasksArray) {
        try {
            FileWriter fw = new FileWriter(filePath, false);
            fw.write(tasksArray.get(0));
            fw.close();

            fw = new FileWriter(filePath, true);
            for (int i = 1; i < tasksArray.size(); i++) {
                fw.write(System.lineSeparator());
                fw.write(tasksArray.get(i));
            }
            fw.close();
        } catch (IOException e) {
            // default behavior is that nothing happens
        } catch (IndexOutOfBoundsException e) {
            System.out.println("detected an error");
            return;
            // default behavior is that nothing happens
        }
    }

    /**
     * Updates the priority of the task corresponding to the given taskNumber,
     * with a new priority.
     *
     * @param priority
     * @param taskNumber
     */
    public void updateTaskPriority(Priority priority, int taskNumber) {
        this.taskList.get(taskNumber - 1).setPriority(priority);
        ArrayList<String> tempArray = new ArrayList<>();
        int tempCounter = 0;
        Scanner scanner = null;
        try {
            scanner = new Scanner(this.file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        while (scanner.hasNextLine()) {
            tempCounter++;
            if (tempCounter == taskNumber) {
                String lineToModify = scanner.nextLine();
                tempArray.add(lineToModify.replace(Task.CROSS, Task.TICK));
                continue;
            }
            tempArray.add(scanner.nextLine());
        }

        // add the contents of auxillary array to the file
        replaceTasksInFile(tempArray);

    }
}
