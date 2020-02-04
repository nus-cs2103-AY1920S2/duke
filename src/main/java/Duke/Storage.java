package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.time.LocalDateTime;
import java.util.Scanner;
import java.io.IOException;

/**
 * <h1> Storage </h1>
 * The Storage class handles the loading of tasks from the disk and saving tasks to the disk. The Storage class contains
 * a TaskList object as its attribute. This class has two methods: load() and save().
 */
public class Storage {
    private TaskList taskList;

    public Storage(TaskList taskList) {
        this.taskList = taskList;
    }

    /**
     * This method loads the existing tasks from disk into the TaskList object.
     * @throws FileNotFoundException Error in file path
     */
    public void load() throws FileNotFoundException {
        String filePath = "Data/Duke.txt";
        File file = new File(filePath);
        Scanner sc = new Scanner(file);
        while (sc.hasNext()) {
            String str = sc.nextLine();
            String[] splitStr = str.split("\\s+" + "\\|" + "\\s+");
            String taskType = splitStr[0];
            String taskStatus = splitStr[1];
            if (taskType.equals("T")) {
                ToDo t = new ToDo(splitStr[2]);
                this.taskList.addTask(t);
            } else if (taskType.equals("E")) {
                LocalDateTime dateTime = LocalDateTime.parse(splitStr[3].trim());
                Event t = new Event(splitStr[2], dateTime);
                this.taskList.addTask(t);
            } else if (taskType.equals("D")) {
                LocalDateTime dateTime = LocalDateTime.parse(splitStr[3].trim());
                DeadLine t = new DeadLine(splitStr[2], dateTime);
                this.taskList.addTask(t);
            }
            if (taskStatus.equals("1")) {
                this.taskList.get(taskList.size() - 1).markAsDone();
            }
        }
    }

    /**
     * This method loads the tasks from the TaskList object into the disk.
     * @throws IOException IOException
     */
    public void save() throws IOException {
        String filePath = "Data/Duke.txt";
        FileWriter fw = new FileWriter(filePath);
        for (int i = 0; i < taskList.size(); i++) {
            Task t = taskList.get(i);
            fw.write(t.toFileString() + "\n");
        }
        fw.close();
    }
}
