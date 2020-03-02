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

public class TaskStorage {
    // Attributes
    private File file;
    private String filePath;
    private ArrayList<Task> taskList;

    public TaskStorage(String filePath) throws FileNotFoundException {
        this.taskList = new ArrayList<Task>();
        this.file = new File(filePath);
        this.filePath = filePath;
        populateTaskList();
    }

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

    // Getter and Setter for taskList
    public ArrayList<Task> getTaskList() {
        return this.taskList;
    }

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

    public void deleteFromTaskList(int taskNumber) {
        this.taskList.remove(taskNumber - 1);
        // Add all lines except line to be removed in an auxillary array
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
                scanner.nextLine();
                continue;
            }
            tempArray.add(scanner.nextLine());
        }

        // add the contents of auxillary array to the file
        replaceTasksInFile(tempArray);
    }

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
        }
    }

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
