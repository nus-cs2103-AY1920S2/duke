package storage;

import task.Deadline;
import task.Event;
import task.TaskList;
import task.Todo;
import task.Task;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    protected String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }


    /**
     * Represents a method which copies file inputs into a new task list.
     * @return returns a Task List in the form of an ArrayList.
     * @throws IOException throws an exception when file is not found.
     */
    public ArrayList<Task> load() throws IOException {
        ArrayList<Task> tasks = new ArrayList<>();
        File f = new File(filePath);
        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            String nextLine = s.nextLine();
            String[] split = nextLine.split("/");
            String indicator = split[0];
            switch (indicator) {
            case "D":
                Deadline deadline = new Deadline(split[2], split[3]);
                if (split[1].equals("Y")) {
                    deadline.setCheck();
                }
                tasks.add(deadline);
                break;

            case "E":
                Event event = new Event(split[2], split[3]);
                if (split[1].equals("Y")) {
                    event.setCheck();
                }
                tasks.add(event);
                break;

            case "T":
                Todo todo = new Todo(split[2]);
                if (split[1].equals("Y")) {
                    todo.setCheck();
                }
                tasks.add(todo);
                break;

            default:
                break;
            }
        }
        return tasks;
    }

    /**
     * Represents a method which saves the stored task list into a .txt file.
     * @param taskList task list containing tasks to be saved.
     * @throws IOException throws an exception when .txt file is not found.
     */
    public void save(TaskList taskList) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        for (Task t : taskList.tasks) {
            fw.write(t.toStringTxt() + System.lineSeparator());
        }
        fw.close();
    }
}