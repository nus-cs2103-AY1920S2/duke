import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Contains the task list e.g., it has operations to add/delete tasks in the list
 */
public class TaskList {
    private static ArrayList<Task> tasks = new ArrayList<>();

    public Task createAndAddTask(String type, String whole) throws DukeException {
        Task task;
        if (whole.split(" ").length == 1) {
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
            String desc = whole.substring(6).split(" /at ")[0];
            String dateAndTime = whole.substring(6).split(" /at ")[1];
            LocalDate date = LocalDate.parse(dateAndTime.split(" ")[0]);
            task = new Event(desc, date);

        } else if (type.equals("deadline")) {
            String desc = whole.substring(9).split(" /by ")[0];
            String dateAndTime = whole.substring(9).split(" /by ")[1];
            LocalDate date = LocalDate.parse(dateAndTime.split(" ")[0]);
            task = new Deadline(desc, date);
        } else {
            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
        tasks.add(task);
        return task;
    }

    public boolean isListEmpty() {
        return tasks.isEmpty();
    }

    public Task getTask(int index) {
        return tasks.get(index);
    }

    public int numOfTasks() {
        return tasks.size();
    }

    public void removeTask(int index) {
        tasks.remove(index);
    }
}
