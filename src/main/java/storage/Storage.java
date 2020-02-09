package storage;

import task.*;

import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.File;
import java.util.Scanner;
import java.time.LocalDateTime;

public class Storage {
    private String filePath;
    private File file;

    /**
     * Constructs a storage.
     * @param filePath is the path to file.
     * @throws IOException for file exceptions.
     */
    public Storage(String filePath) throws IOException {
        this.filePath = filePath;
        this.file = new File(filePath);
    }

    /**
     * Saves the tasks into targeted file.
     * @param tasks list of tasks.
     * @throws IOException file exceptions.
     */
    public void save(TaskList tasks) throws IOException {
        FileWriter fw = new FileWriter(this.filePath);
        for (Task task: tasks.getTasks()) {
            fw.write(task.toFormatString() + "\n");
        }
        fw.close();
    }

    /**
     * Loads existing tasks in the file into array list of tasks.
     * @return an array list of current tasks stored in file.
     * @throws FileNotFoundException if the filePath is wrong.
     */
    public ArrayList<Task> load() throws FileNotFoundException {
        Scanner sc = new Scanner(file);
        ArrayList<Task> taskList = new ArrayList<>();
        while (sc.hasNext()) {
            String line = sc.nextLine();
            char taskType = line.charAt(0);
            char taskCondition = line.charAt(4);
            String taskContent = line.substring(8);
            String[] taskAndTime = taskContent.split("\\| ");
            if (taskType == 'T') {
                taskList.add(new Todo(taskContent));
            } else if (taskType == 'D') {
                taskList.add(new Deadline(taskAndTime[0], LocalDateTime.parse(taskAndTime[1])));
            } else if (taskType == 'E') {
                taskList.add(new Event(taskAndTime[0], LocalDateTime.parse(taskAndTime[1])));
            }

            if (taskCondition == '1') {
                taskList.get(taskList.size() - 1).finishTask();
            }
        }
        return taskList;
    }
}
