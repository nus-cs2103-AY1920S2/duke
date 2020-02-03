package duke;

import java.io.*;
import java.util.ArrayList;

/** Class representing a Store, to save and load from file. */
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
        for (Task task : taskList) {
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
        if (file.createNewFile()) return;
        BufferedReader taskLoader = new BufferedReader(new FileReader(path));
        String longCommand = taskLoader.readLine();
        while (longCommand != null) {
            String[] keywords = longCommand.split(" \\|\\| ");
            Task cur = null;
            switch (keywords[1]) {
                case "todo":
                    cur = new Todo(keywords[2]);
                    taskList.getTaskList().add(cur);
                    break;
                case "deadline":
                    cur = new Deadline(keywords[2], parser.stringToTime(keywords[3]));
                    taskList.getTaskList().add(cur);
                    break;
                case "event":
                    cur = new Event(keywords[2], parser.stringToTime(keywords[3]));
                    taskList.getTaskList().add(cur);
                    break;
                default:
                    System.out.println("error");
                    break;
            }
            if (keywords[0].equals("1")) {
                assert cur != null;
                cur.done();
            }
            longCommand = taskLoader.readLine();
        }
        taskLoader.close();
    }
}
