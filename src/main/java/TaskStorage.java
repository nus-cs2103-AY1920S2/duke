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
                taskList.add(TaskDispatch.dispatchTask(scanner.nextLine().split(" ")));
            } catch (IllegalCommandException e) {
                // Assumption is that data is checked to be valid before adding to the file
                // Hence, when reading from it, the data should result in valid Task objects
                e.printStackTrace();
            }
        }
    }

    // Getter and Setter for taskList
    public ArrayList<Task> getTaskList() {
        return this.taskList;
    }

    public void addToTaskList(Task newTask, String nextInput) {
        this.taskList.add(newTask);
        try {
            FileWriter fw = new FileWriter(filePath, true);
            fw.write(nextInput);
            fw.close();
        } catch (IOException e) {
            // default behaviour is that nothing happens
        }
    }

    // nb: current code does not support task done. remember to fix it.
    /*
    public void modifyTaskList(Task oldTask, Task newTask) {
        for (int i = 0; i< taskList.size(); i++) {
            if (taskList.get(i).equals(oldTask)) {
                taskList.set(i, newTask);
                break;
            }
        }
    }
    */

    public void deleteFromTaskList(int taskNumber) {
        this.taskList.remove(taskNumber - 1);

        // Add all lines except line to be removed in an auxillary array
        ArrayList<String> temp = new ArrayList<String>();
        int tempCounter = 1;
        Scanner scanner = null;
        try {
            scanner = new Scanner(this.file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while (scanner.hasNext()) {
            if (tempCounter == taskNumber) {
                scanner.nextLine();
                continue;
            }
            temp.add(scanner.nextLine());
            tempCounter++;
        }

        // add the contents of auxillary array to the file
        try {
            FileWriter fw = new FileWriter(filePath, false);
            fw.write(temp.get(0));
            fw.close();

            fw = new FileWriter(filePath, true);
            for (int i = 1; i < temp.size(); i++) {
                fw.write(temp.get(i));
            }
            fw.close();
        } catch (IOException e) {
            // default behaviour is that nothing happens
        }

    }
}
