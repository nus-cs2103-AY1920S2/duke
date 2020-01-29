package duke.tasklist;

import duke.storage.Storage;

import duke.task.Deadline;
import duke.task.Task;
import duke.task.ToDo;
import duke.task.Event;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * The TaskList program manages the taskList. It is able to add, delete, list and mark tasks in
 * taskList as done. It also contains Storage.
 *
 * @version 1.0
 * @since 2020-01-28
 */
public class TaskList {

    ArrayList<Task> taskList;
    Storage storage;

    /**
     * Constructor.
     */
    public TaskList() {

        this.taskList = new ArrayList<Task>();

    }

    /**
     * Adds file storage to TaskList.
     *
     * @param storage refers to storage object required for file storage.
     */
    public void addStorage(Storage storage) {

        this.storage = storage;

    }

    /**
     * Prints all the tasks in taskList.
     */
    public void list() {

        String start = "\nHere are the tasks in your list:";
        System.out.println(start);

        for (int i = 0; i < taskList.size(); i++) {
            System.out.println((i + 1) + "." + taskList.get(i).toString());
        }

    }

    /**
     * Adds task into taskList and calls method to update file storage.
     *
     * @param taskDescription refers to the contents of the task.
     * @param dateTime refers to the date and time of the task.
     * @param type refers to the type of the task.
     * @return Task added to taskList and Storage.
     * @throws IOException as a result of updateFile throwing exception.
     */
    public Task addTask(String taskDescription, LocalDateTime[] dateTime, Task.Types type) throws IOException {

        System.out.println("Got it. I've added this task:");
        Task task = null;
        switch (type) {
        case ToDo:
            ToDo task1 = new ToDo(dateTime, taskDescription);
            task = task1;
            break;
        case Deadline:
            Deadline task2 = new Deadline(dateTime, taskDescription);
            task = task2;
            break;
        case Event:
            Event task3 = new Event(dateTime, taskDescription);
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
     * Adds tasks from file storage to taskList.
     *
     * @param taskDescription refers to the contents of the task.
     * @param dateTimeStart refers to the start date and time of the task.
     * @param dateTimeEnd   refers to the end date and time of the task.
     * @param type refers to the type of the task.
     * @param status refers to the status of the task.
     * @return Task added to taskList.
     */
    public Task addTask(String taskDescription, LocalDateTime dateTimeStart, LocalDateTime dateTimeEnd,
                        Task.Types type, Task.Status status)  {

        LocalDateTime[] dateTime = new LocalDateTime[2];
        dateTime[0] = dateTimeStart;
        dateTime[1] = dateTimeEnd;
        Task task = null;
        switch (type) {
        case ToDo:
            ToDo task1 = new ToDo(dateTime, taskDescription);
            task = task1;
            break;
        case Deadline:
            Deadline task2 = new Deadline(dateTime, taskDescription);
            task = task2;
            break;
        case Event:
            Event task3 = new Event(dateTime, taskDescription);
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
     * Deletes task from taskList and calls method to update file storage.
     *
     * @param index refers to the position of task in taskList.
     * @return Task deleted from taskList and Storage.
     * @throws IOException as a result of exception in updateFile method.
     */
    public Task deleteTask(int index) throws IOException {

        Task task = taskList.remove(index - 1);
        storage.updateFile("delete");
        System.out.println("Noted. I've removed this task:");
        return task;

    }

    /**
     * Prints out total number of tasks in taskList.
     *
     * @return total number of tasks in taskList.
     */
    public String reportTotal() {

        return "Now you have " + taskList.size() + " tasks in the list";

    }

    /**
     * Gets tasks from taskList specified at index.
     *
     * @param index refers to the position of task in taskList.
     * @return Task at index.
     */
    public Task getTask(int index) {

        return taskList.get(index - 1);

    }

    /**
     * Gets taskList.
     *
     * @return taskList.
     */
    public ArrayList getList() {

        return taskList;

    }

    /**
     * Marks a task as done and calls method to update file storage.
     *
     * @param task refers to the task to be marked as done.
     * @return Task that is marked done.
     * @throws IOException as a result of exception in updateFile method.
     */
    public Task markDone(Task task) throws IOException {

        task.changeStatus(Task.Status.Y);
        storage.updateFile("done");
        String action = "Nice! I've marked this task as done:";
        System.out.println(action);
        return task;

    }

}



