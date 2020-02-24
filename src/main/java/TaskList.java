import exception.DukeException;
import exception.DuplicateTaskException;
import exception.InvalidCommandException;
import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.ToDo;

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
            throw new InvalidCommandException(":-( Oops!!! Please give me more details! :-(");
        }

        if (doesTaskExist(type, whole)) {
            throw new DuplicateTaskException(":-( Oops!!! That task already exists! :-(");
        }

        switch (type) {
            case "todo": {
                String desc = parser.getDesc(whole);
                task = new ToDo(desc);

                break;
            }
            case "event": {
                if (whole.split(" ").length < 4) {
                    throw new InvalidCommandException(":-( Oops!!! Please give me more details! :-(");
                }
                String desc = parser.getDesc(whole);
                LocalDate date = parser.getDate(whole);
                task = new Event(desc, date);

                break;
            }
            case "deadline": {
                if (whole.split(" ").length < 4) {
                    throw new InvalidCommandException(":-( Oops!!! Please give me more details! :-(");
                }
                String desc = parser.getDesc(whole);
                LocalDate date = parser.getDate(whole);
                task = new Deadline(desc, date);

                break;
            }
            default:
                assert !type.equals("todo") : "type should not be todo";
                throw new InvalidCommandException(":-( Oops!!! I'm sorry, but I don't know what that means :-(");
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
    public boolean doesTaskExist(String type, String whole) throws InvalidCommandException {
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
