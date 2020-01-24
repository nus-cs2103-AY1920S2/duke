/*
 * @author leslieharland
 */

package duke.storage;

import duke.task.Deadline;
import duke.task.EventObj;
import duke.task.TaskList;
import duke.task.Todo;
import duke.task.SearchTask;
import duke.task.Task;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


/**
 * The Class Storage.
 */
public class Storage {
    public String filePath;

    /**
     * Instantiates a new storage.
     *
     * @param filePath the file path
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Write to file.
     *
     * @param mycontent the mycontent
     */
    public void writeToFile(String mycontent) {
        BufferedWriter writer = null;
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file);
            writer = new BufferedWriter(fw);
            writer.write(mycontent);
            writer.flush();

        } catch (IOException ioe) {
            ioe.printStackTrace();
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                }
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    /**
     * Load tasks.
     *
     * @return the task list
     */
    public TaskList loadTasks() {
        TaskList tasks = new TaskList();
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
            BufferedReader rd = new BufferedReader(new FileReader(filePath));
            String line;
            while ((line = rd.readLine()) != null) {
                String type = line.split("|")[0];
                if (type.equals("E")) {
                    tasks.addTask(EventObj.parse(line));
                } else if (type.equals("D")) {
                    tasks.addTask(Deadline.parse(line));
                } else if (type.equals("T")) {
                    tasks.addTask(Todo.parse(line));
                } else {
                    //add code
                }
            }
            rd.close();

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

        return tasks;
    }

    /**
     * Find tasks containing the user search term.
     *
     * @return the task list
     */
    public TaskList findTasks(String searchTerm) {
        TaskList tasks = loadTasks();
        TaskList temp = new TaskList();
        int pos = 1;
        for (Task task : tasks.getTasks()) {
            if (task.getDescription().contains(searchTerm)) {
                temp.addTask(new SearchTask(pos, task.isDone(), task.getDescription(), task.getType()));
            }
            pos++;
        }
        return temp;
    }
}