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
            if (whole.equals("todo") || whole.equals("event") || whole.equals("deadline")) {
                throw new DukeException("☹ OOPS!!! The description of a " + type + " cannot be empty.");
            } else {
                throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        }

        if (type.equals("todo")) {
            String desc = whole.substring(5);
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
            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
        tasks.add(task);
        return task;
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
