package duke.task;

import duke.interaction.Ui;
import duke.util.DukeException;

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
     * @return the ArrayList collection of tasks.
     */
    public ArrayList<Task> getList() {
        return taskList;
    }

    /**
     * Adds to the task collection a task object generated
     * from the save-string representation.
     * @param taskString of a save-string representation of the task.
     */
    public void addSaveStringAsTask(String taskString) {
        // Takes in a string representation of a task and adds to list
        String taskInfo;
        String[] sep;
        switch (taskString.charAt(0)) {
        case 'T':
            taskList.add(new ToDo(taskString.substring(2, taskString.length())));
            break;
        case 'D':
            taskInfo = taskString.substring(2, taskString.length());
            sep = taskInfo.split("@");
            taskList.add(new Deadline(sep[0], LocalDateTime.parse(sep[1])));
            break;
        case 'E':
            taskInfo = taskString.substring(2, taskString.length());
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
     * @param newTask name of the new task to add.
     * @param taskType for the type of task to add.
     * @return a reference to the new task object.
     */
    public Task addTask(String newTask, Task.TaskType taskType) {
        String[] strArr;
        String taskName;
        try {
            switch (taskType) {
            case TODO:
                if (newTask.isBlank()) {
                    throw new DukeException.EmptyToDo();
                }
                taskList.add(new ToDo(newTask));
                break;
            case DEADLINE:
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
                taskList.add(new Deadline(taskName, deadline));
                break;
            case EVENT:
                // newTask string consists of "<actual task name> /at <datetime>"
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
                taskList.add(new Event(taskName, eventTime));
                break;
            default:
                break;
            }
            Ui.showTaskAdded(taskList.get(taskList.size() - 1), taskList.size());
            return taskList.get(taskList.size() - 1);
        } catch (DukeException.EmptyToDo | DukeException.EmptyDeadlineName
                | DukeException.NoDeadlineTime | DukeException.EmptyEvent
                | DukeException.NoEventDatetime e) {
            // currently all exceptions are handled just by relaying a message. Nothing special, yet.
            Ui.showError(e);
            return null;
        }
    }

    /**
     * Updates a task object in collection to "Done".
     * @param taskIndex of the task (in collection) to be set "Done".
     * @return a boolean representing if task was found (set to done), else false
     */
    public boolean doneTask(int taskIndex) {
        if (taskIndex < taskList.size() && taskIndex >= 0) {
            taskList.get(taskIndex).markAsDone();
            Ui.showTaskDone(taskList.get(taskIndex));
            return true;
        } else {
            // Task does not exist
            Ui.showTaskNotFound();
            return false;
        }
    }

    /**
     * Deletes a task object from collection.
     * @param taskIndex of the task (in collection) to be deleted.
     * @return a boolean representing if task was found (then deleted), else false
     */
    public boolean deleteTask(int taskIndex) {
        if (taskIndex < taskList.size() && taskIndex >= 0) {
            String tasktoRemove = taskList.get(taskIndex).toString();
            taskList.remove(taskIndex);
            Ui.showTaskDelete(tasktoRemove, taskList.size());
            return true;
        } else {
            // Task does not exist
            Ui.showTaskNotFound();
            return false;
        }
    }
}
