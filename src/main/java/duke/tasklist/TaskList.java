package duke.tasklist;

import duke.storage.Storage;

import duke.task.Deadline;
import duke.task.Task;
import duke.task.ToDo;
import duke.task.Event;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * The TaskList program manages the taskList. It is able to add, delete, list and mark tasks in
 * taskList as done. It also contains Storage.
 *
 * @version 1.2
 * @since 17/2/2020
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
     *
     * @return String list of task in string format.
     */
    public String list() {

        String output = "Here are the tasks in your list:\n";

        for (int i = 0; i < taskList.size(); i++) {
            output = output + (i + 1) + "." + taskList.get(i).toString() + "\n";
        }

        return output;
    }

    /**
     * Adds task into taskList and calls method to update file storage.
     *
     * @param taskDescription refers to the contents of the task.
     * @param dateTime        refers to the date and time of the task.
     * @param type            refers to the type of the task.
     * @return Task added to taskList and Storage.
     * @throws IOException as a result of updateFile throwing exception.
     */
    public Task addTask(String taskDescription, LocalDateTime[] dateTime,
                        Task.Types type) throws IOException {

        Task task = null;
        switch (type) {
        case T:
            ToDo task1 = new ToDo(dateTime, taskDescription);
            task = task1;
            break;
        case D:
            Deadline task2 = new Deadline(dateTime, taskDescription);
            task = task2;
            break;
        case E:
            Event task3 = new Event(dateTime, taskDescription);
            task = task3;
            break;
        default:
            System.out.println("Task type doesn't exist");
            break;
        }

        taskList.add(task);

        storage.updateFile();

        return task;
    }

    /**
     * Adds tasks from file storage to taskList.
     *
     * @param taskDescription refers to the contents of the task.
     * @param dateTimeStart   refers to the start date and time of the task.
     * @param dateTimeEnd     refers to the end date and time of the task.
     * @param type            refers to the type of the task.
     * @param status          refers to the status of the task.
     * @return Task added to taskList.
     */
    public Task addTask(String taskDescription, LocalDateTime dateTimeStart,
                        LocalDateTime dateTimeEnd, Task.Types type, Task.Status status) {

        LocalDateTime[] dateTime = new LocalDateTime[2];
        dateTime[0] = dateTimeStart;
        dateTime[1] = dateTimeEnd;
        Task task = null;
        switch (type) {
        case T:
            ToDo task1 = new ToDo(dateTime, taskDescription);
            task = task1;
            break;
        case D:
            Deadline task2 = new Deadline(dateTime, taskDescription);
            task = task2;
            break;
        case E:
            Event task3 = new Event(dateTime, taskDescription);
            task = task3;
            break;
        default:
            System.out.println("Task type doesn't exist");
            break;
        }

        if (status.equals(status.Y)) {

            task.changeStatus(status.Y);

        }

        assert task != null : "Task should not be null";

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
        storage.updateFile();
        return task;

    }


    /**
     * Prints out tasks in taskList with words corresponding to keyword.
     *
     * @param keyword corresponds to the tasks with same word.
     * @return String list of tasks with relevant keywords in string format.
     */
    public String findTask(String keyword) {

        String output = "Here are the matching tasks in your list:\n";

        String taskString = null;
        int numOfMatchingTask = 0;

        for (int i = 0; i < taskList.size(); i++) {

            taskString = taskList.get(i).toString();

            if (taskString.contains(keyword)) {
                numOfMatchingTask++;
                output = output + numOfMatchingTask + "." + taskString + "\n";
            }
        }

        return output;

    }

    /**
     * Prints out total number of tasks in taskList.
     *
     * @return total number of tasks in taskList.
     **/
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
        storage.updateFile();
        return task;

    }

}



