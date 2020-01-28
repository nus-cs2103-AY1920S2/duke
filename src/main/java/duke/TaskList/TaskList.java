package duke.TaskList;

import duke.Storage.Storage;
import duke.Task.*;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * @ version 1.0
 * @ since 2020-01-28
 */
public class TaskList {

    ArrayList<Task> taskList;
    Storage storage;

    public TaskList() {

        this.taskList = new ArrayList<Task>();

    }

    /**
     * Adds file storage to TaskList
     * @param storage
     */
    public void addStorage(Storage storage) {

        this.storage = storage;

    }

    /**
     * Prints all the tasks in taskList
     */
    public void list() {

        String start = "\nHere are the tasks in your list:";
        System.out.println(start);

        for (int i = 0; i < taskList.size(); i++) {
            System.out.println((i + 1) + "." + taskList.get(i).toString());
        }

    }

    /**
     * Adds task into taskList and calls method to update file storage
     * @param taskDescription
     * @param date
     * @param type
     * @return Task added to taskList and Storage
     * @throws IOException
     */
    public Task addTask(String taskDescription, LocalDateTime date, Task.Types type) throws IOException {

        System.out.println("Got it. I've added this task:");
        Task task = null;
        switch (type) {
            case ToDo:
                ToDo task1 = new ToDo(date, taskDescription);
                task = task1;
                break;
            case Deadline:
                Deadline task2 = new Deadline(date, taskDescription);
                task = task2;
                break;
            case Event:
                Event task3 = new Event(date, taskDescription);
                task = task3;
                break;
            default:
                System.out.println("Task type does'nt exist");
                break;
        }

        taskList.add(task);

        storage.updateFile("add");

        return task;
    }

    /**
     * Adds tasks from file storage to taskList
     * @param taskDescription
     * @param date
     * @param type
     * @param status
     * @return Task added to taskList
     * @throws IOException
     */
    public Task addTask(String taskDescription, LocalDateTime date, Task.Types type, Task.status status) throws IOException {

        Task task = null;
        switch (type) {
            case ToDo:
                ToDo task1 = new ToDo(date, taskDescription);
                task = task1;
                break;
            case Deadline:
                Deadline task2 = new Deadline(date, taskDescription);
                task = task2;
                break;
            case Event:
                Event task3 = new Event(date, taskDescription);
                task = task3;
                break;
            default:
                System.out.println("Task type does'nt exist");
                break;
        }

        if (status.equals(status.Y)) {

           task.changeStatus(status.Y);

        }

        taskList.add(task);

        return task;
    }

    /**
     * Deletes task from taskList and calls method to update file storage
     * @param index
     * @return Task deleted from taskList and Storage
     * @throws IOException
     */
    public Task deleteTask(int index) throws IOException {

        Task task = taskList.remove(index - 1);
        storage.updateFile("delete");
        System.out.println("Noted. I've removed this task:");
        return task;

    }

    /**
     * Prints out total number of tasks in taskList
     * @return String
     */
    public String reportTotal() {

        return "Now you have " + taskList.size() + " tasks in the list";

    }

    /**
     * Gets tasks from taskList specified at index
     * @param index
     * @return Task at index
     */
    public Task getTask(int index) {

        return taskList.get(index - 1);

    }

    /**
     * Gets taskList
     * @return taskList
     */
    public ArrayList getList() {

        return taskList;

    }

    /**
     * Marks a task as done and calls method to update file storage
     * @param task
     * @return Task that is marked done
     * @throws IOException
     */
    public Task markDone(Task task) throws IOException {

        task.changeStatus(Task.status.Y);
        storage.updateFile("done");
        String action = "Nice! I've marked this task as done:";
        System.out.println(action);
        return task;

    }

}



