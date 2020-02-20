package duke;

import duke.task.Event;
import duke.task.Deadline;
import duke.task.Task;
import duke.task.Todo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import java.util.ArrayList;

/**
 * Class representing a Store, to save and load from file.
 */
public class Storage {
    String path;

    /**
     * Creates a Storage object.
     *
     * @param path path of file.
     */
    public Storage(String path) {
        this.path = path;
    }

    /**
     * Saves the task list to the file.
     *
     * @param taskList list of tasks to save to file.
     * @throws IOException If there is a error when writing to file.
     */
    public void saveBaby(ArrayList<Task> taskList) throws IOException {
        BufferedWriter taskWriter = new BufferedWriter(new FileWriter(path));
        StringBuilder tasks = new StringBuilder();
        for (Task task: taskList) {
            assert task.toSaveString().split(" \\|\\| ").length >= 3 : "Save failure";
            tasks.append(task.toSaveString()).append("\n");
        }
        taskWriter.write(tasks.toString());
        taskWriter.close();
    }

    /**
     * Loads saved tasks from file to task list.
     *
     * @param taskList list of tasks to save to file.
     * @param parser parser object.
     * @throws IOException If there is a error when reading from file.
     */
    public void loadBaby(TaskList taskList, Parser parser) throws IOException {
        File file = new File(path);
        if (file.createNewFile()) {
            return;
        }
        BufferedReader taskLoader = new BufferedReader(new FileReader(path));
        String longCommand = taskLoader.readLine();
        while (longCommand != null) {
            String[] keywords = longCommand.split(" \\|\\| ");
            assert keywords.length >= 3 : "Load tasks failure";
            String taskDone = keywords[0];
            String taskType = keywords[1];
            String taskDesc = keywords[2];
            Task cur = null;
            switch (taskType) {
            case "todo":
                assert (keywords.length == 3) : "Todo load failure";
                cur = new Todo(taskDesc);
                taskList.getTaskList().add(cur);
                break;
            case "deadline":
                assert (keywords.length == 4) : "Deadline load failure";
                cur = new Deadline(taskDesc, parser.stringToTime(keywords[3]));
                taskList.getTaskList().add(cur);
                break;
            case "event":
                assert (keywords.length == 4) : "Deadline load failure";
                cur = new Event(taskDesc, parser.stringToTime(keywords[3]));
                taskList.getTaskList().add(cur);
                break;
            default:
                System.out.println("error");
                break;
            }
            if (taskDone.equals("1")) {
                assert cur != null;
                cur.done();
            }
            longCommand = taskLoader.readLine();
        }
        taskLoader.close();
    }
}
