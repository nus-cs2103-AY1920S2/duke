package duke.task;

import duke.util.DateTimeUtil;
import duke.util.DukeException;
import duke.util.Storage;

import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * Handles the data structure where the tasks are stored.
 * Contains various methods to add, delete and "done" a task.
 *
 * @author  Hardy Shein
 * @version 0.1
 */
public class TaskList {

    private ArrayList<Task> taskList;

    /**
     * TaskList constructor initializes array list of tasks
     * with initial capacity 100.
     */
    public TaskList() {
        taskList = new ArrayList<>(100);
    }

    /**
     * Getter for a reference to the task list data structure.
     *
     * @return the ArrayList collection of tasks.
     */
    public ArrayList<Task> getList() {
        return taskList;
    }

    /**
     * Adds to the task collection a task object generated
     * from the save-string representation.
     *
     * @param taskString of a save-string representation of the task.
     */
    public void addSaveStringAsTask(String taskString) {
        // Takes in a string representation of a task and adds to list
        String taskInfo;
        String[] sep;
        switch (taskString.charAt(0)) {
        case 'T':
            taskList.add(new ToDo(taskString.substring(2)));
            break;
        case 'D':
            taskInfo = taskString.substring(2);
            sep = taskInfo.split("@");
            taskList.add(new Deadline(sep[0], LocalDateTime.parse(sep[1])));
            break;
        case 'E':
            taskInfo = taskString.substring(2);
            sep = taskInfo.split("@");
            taskList.add(new Event(sep[0], LocalDateTime.parse(sep[1])));
            break;
        default:
            break;
        }
        if (taskString.charAt(1) == '1') {
            taskList.get(taskList.size() - 1).markAsDone();
        }
    }

    /**
     * Adds to the task collection a new task object.
     *
     * @param index within the list of this new task.
     * @param task object representing the new task.
     * @return a String representing the task that was added.
     */
    public String addTaskAt(int index, Task task) {
        taskList.add(index, task);
        return task.toString();
    }

    /**
     * Adds to the task collection a new task object.
     *
     * @param newTask name of the new task to add.
     * @param taskType for the type of task to add.
     * @param storage for saving the new task to file.
     * @return a String representing the task that was added, else an error message.
     */
    public String addTask(String newTask, Task.TaskType taskType, Storage storage) throws Exception {
        try {
            switch (taskType) {
            case TODO:
                addTodo(newTask);
                break;
            case DEADLINE:
                addDeadline(newTask);
                break;
            case EVENT:
                addEvent(newTask);
                break;
            default:
                break;
            }
            Task t = taskList.get(taskList.size() - 1);
            storage.saveTaskToFile(t);
            return t.toString();
        } catch (DukeException.EmptyToDo | DukeException.EmptyDeadlineName
                | DukeException.NoDeadlineTime | DukeException.EmptyEvent
                | DukeException.NoEventDatetime e) {
            // currently all exceptions are handled just by relaying a message. Nothing special, yet.
            throw e;
        }
    }

    /**
     * Adds to the task collection a new Todo task.
     *
     * @param newTask name of the new task to add.
     */
    private void addTodo(String newTask) throws DukeException.EmptyToDo {
        if (newTask.isBlank()) {
            throw new DukeException.EmptyToDo();
        }
        taskList.add(new ToDo(newTask));
    }

    /**
     * Adds to the task collection a new Deadline task.
     *
     * @param newTask name of the new task to add.
     */
    private void addDeadline(String newTask) throws DukeException.EmptyDeadlineName,
            DukeException.NoDeadlineTime, DukeException.InvalidDateTime {
        String taskName;
        String[] strArr;

        // newTask string consists of "<actual task name> /by <deadline>"
        strArr = newTask.split("/by");
        taskName = strArr[0].trim();
        if (taskName.isBlank()) {
            throw new DukeException.EmptyDeadlineName();
        }
        String deadline;
        try {
            deadline = strArr[1].trim();
        } catch (ArrayIndexOutOfBoundsException e) {
            // This will occur when user did not use a /by command
            throw new DukeException.NoDeadlineTime();
        }
        // /by was used but is followed by blank
        if (deadline.isBlank()) {
            throw new DukeException.NoDeadlineTime();
        }

        // Check if valid date
        try {
            DateTimeUtil.checkStringValidDate(deadline);
            taskList.add(new Deadline(taskName, deadline));
        } catch (Exception e) {
            throw new DukeException.InvalidDateTime();
        }
    }

    /**
     * Adds to the task collection a new Event task.
     *
     * @param newTask name of the new task to add.
     */
    private void addEvent(String newTask) throws DukeException.EmptyEvent,
            DukeException.NoEventDatetime, DukeException.InvalidDateTime {
        String taskName;
        String[] strArr;

        // newTask string consists of "<actual task name> /at <date>"
        strArr = newTask.split("/at");
        taskName = strArr[0].trim();
        if (taskName.isBlank()) {
            throw new DukeException.EmptyEvent();
        }
        String eventTime;
        try {
            eventTime = strArr[1].trim();
        } catch (ArrayIndexOutOfBoundsException e) {
            // This will occur when user did not use a /at command
            throw new DukeException.NoEventDatetime();
        }
        // /at was used but is followed by blank
        if (eventTime.isBlank()) {
            throw new DukeException.NoEventDatetime();
        }

        // Check if valid date
        try {
            DateTimeUtil.checkStringValidDate(eventTime);
            taskList.add(new Event(taskName, eventTime));
        } catch (Exception e) {
            throw new DukeException.InvalidDateTime();
        }
    }

    /**
     * Updates a task object in collection to "Done".
     *
     * @param taskIndex of the task (in collection) to be set "Done".
     * @return a boolean representing if task was found (set to done), else false
     */
    public boolean doneTask(int taskIndex) {
        if (taskIndex < taskList.size() && taskIndex >= 0) {
            taskList.get(taskIndex).markAsDone();
            return true;
        } else {
            // Task does not exist
            return false;
        }
    }

    /**
     * Updates a task object in collection to "Not Done".
     *
     * @param taskIndex of the task (in collection) to be set "Done".
     * @return a boolean representing if task was found (set to done), else false
     */
    public boolean undoneTask(int taskIndex) {
        if (taskIndex < taskList.size() && taskIndex >= 0) {
            taskList.get(taskIndex).markAsUndone();
            return true;
        } else {
            // Task does not exist
            return false;
        }
    }

    /**
     * Deletes a task object from collection.
     *
     * @param taskIndex of the task (in collection) to be deleted.
     * @return a String representing the task that was deleted, else null.
     */
    public String deleteTask(int taskIndex) {
        if (taskIndex < taskList.size() && taskIndex >= 0) {
            String taskToRemove = taskList.get(taskIndex).toString();
            taskList.remove(taskIndex);
            return taskToRemove;
        } else {
            // Task does not exist
            return null;
        }
    }

    /**
     * Getter for a reference to the task at an index.
     *
     * @return the Task object at the index of the task list.
     */
    public Task getTask(int index) {
        return taskList.get(index);
    }
}
