import duke.exception.DukeException;
import duke.exception.DuplicateTaskException;
import duke.exception.InvalidCommandException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.ToDo;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Contains the task list e.g., it has operations to add/delete tasks in the list
 */
public class TaskList {
    private static ArrayList<Task> tasks = new ArrayList<>();
    Parser parser = new Parser();

    /**
     * Creates the task and adds it to the ArrayList of tasks.
     * @param type The type of task.
     * @param whole The command.
     * @return The newly created task.
     * @throws DukeException The command provided does not fit the required format.
     */
    public Task createAndAddTask(String type, String whole) throws DukeException {
        Task task;
        if (parser.numOfParts(whole) == 1) {
            throw new InvalidCommandException("\u2639 OOPS!!! The description of a " + type + " cannot be empty.");
        }

        if (doesTaskExist(type, whole)) {
            throw new DuplicateTaskException("\u2639 OOPS!!! That task already exists!");
        }

        if (type.equals("todo")) {
            String desc = parser.getDesc(whole);
            task = new ToDo(desc);

        } else if (type.equals("event")) {
            String desc = parser.getDesc(whole);
            LocalDate date = parser.getDate(whole);
            task = new Event(desc, date);

        } else if (type.equals("deadline")) {
            String desc =parser.getDesc(whole);
            LocalDate date = parser.getDate(whole);
            task = new Deadline(desc, date);

        } else {
            assert !type.equals("todo") : "type should not be todo";
            throw new InvalidCommandException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
        tasks.add(task);
        return task;
    }

    /**
     * Checks if the task already exists.
     * @param type The type of task.
     * @param whole The whole command provided by the user.
     * @return True if it exists already and false if it is new.
     */
    //ASSERT TYPE IS VALID
    public boolean doesTaskExist(String type, String whole) {
        switch (type) {
            case "todo" :
                for (Task task : tasks) {
                    if (task instanceof ToDo && task.getDescription().equals(parser.getDesc(whole))) {
                        return true;
                    }
                }
                break;
            case "event" :
                for (Task task : tasks) {
                    if (task instanceof Event && task.getDescription().equals(parser.getDesc(whole))
                            && task.getDate().isEqual(parser.getDate(whole))) {
                        return true;
                    }
                }
                break;
            case "deadline" :
                for (Task task : tasks) {
                    if (task instanceof Deadline && task.getDescription().equals(parser.getDesc(whole))
                            && task.getDate().isEqual(parser.getDate(whole))) {
                        return true;
                    }
                }
                break;
            default :
                assert parser.numOfParts(whole) > 1 : "should be a command with > 1 parts";
                break;
        }
        return false;
    }

    /**
     * Check if the ArrayList of tasks is empty.
     * @return A boolean which states if the list is empty.
     */
    public boolean isListEmpty() {
        return tasks.isEmpty();
    }

    /**
     * Gets a task from the ArrayList based on the index.
     * @param index The index related to the ArrayList.
     * @return The task that is found.
     */
    public Task getTask(int index) {
        return tasks.get(index);
    }

    /**
     * Checks the number of tasks in the ArrayList.
     * @return The number of tasks.
     */
    public int numOfTasks() {
        return tasks.size();
    }

    /**
     * Removes the task from the ArrayList.
     * @param index The index related to the ArrayList.
     */
    public void removeTask(int index) {
        tasks.remove(index);
    }
}
