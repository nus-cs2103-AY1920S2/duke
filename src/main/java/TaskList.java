import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a list of tasks. A <code>TaskList</code> object corresponds to a
 * list of <code>Task</code> objects which it represents, a <code>Ui</code> object
 * to facilitate user input and output and a <code>Storage</code> object to access
 * storage file in the data folder.
 */
public class TaskList {
    private Ui ui;
    private Storage storage;
    private List<Task> taskList;

    public TaskList(Ui ui, Storage storage) {
        this.ui = ui;
        this.storage = storage;
    }

    /**
     * Sets task list.
     *
     * @param list Task list.
     */
    public void setList(List<Task> list) { taskList = list; }

    /**
     * Adds task described in user input to task list.
     *
     * @param s Task to add.
     * @return Display message.
     * @throws DukeException If string format is invalid.
     */
    public String add(String s) throws DukeException {
        String[] taskParts = s.split(" ", 2);
        String taskType = taskParts[0];
        Task toAdd = new Task();
        if (taskType.equals("todo")) {
            try {
                String[] taskInfo = taskParts[1].split(" /between " );
                if (taskInfo.length == 1) {
                    toAdd = new Todo(taskInfo[0]);
                } else {
                    String task = taskInfo[0];
                    String[] betweenAndDates = taskInfo[1].split(" /and ");
                    String betweenDate = betweenAndDates[0];
                    String AndDate = betweenAndDates[1];
                    toAdd = new TodoWithinPeriod(task, betweenDate, AndDate);
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new EmptyDescriptionException();
            }
        } else if (taskType.equals("event")) {
            try {
                String[] taskInfo = taskParts[1].split(" /at ");
                toAdd = new Event(taskInfo[0], taskInfo[1]);
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new EmptyDescriptionException();
            }
        } else if (taskType.equals("deadline")) {
            try {
                String[] taskInfo = taskParts[1].split(" /by ");
                toAdd = new Deadline(taskInfo[0], taskInfo[1]);
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new EmptyDescriptionException();
            }
        }
        taskList.add(toAdd);
        String msg = "Quack! Added this task:\n"
                + "  " + toAdd + "\n"
                + "You now have " + taskList.size() + " tasks in the list, master.";
        try {
            storage.writeTaskList(taskList);
        } catch (IOException e) {
            msg = ui.showError(e);
        }
        return msg;
    }

    /**
     * Deletes task at index i (starts from 1) from task list.
     *
     * @param i Index of task to remove.
     * @return Display message.
     */
    public String delete(int i) {
        Task t = taskList.remove(i-1);
        String msg = "Quack-poof! This task is now gone:\n"
                +  "  " + t + "\n"
                + "You now have " + taskList.size() + " tasks in the list, master.";
        try {
            storage.writeTaskList(taskList);
        } catch (IOException e) {
            msg = ui.showError(e);
        }
        return msg;
    }

    /**
     * Displays tasks in task list.
     *
     * @return Display message.
     */
    public String showList() {
        String msg = "Here are the tasks in your list, master:";
        int count = 1;
        for (Task t : taskList) {
            msg += "\n" + count + "." + t;
            count++;
        }
        return msg;
    }

    /**
     * Finds and displays tasks that contain the query string.
     *
     * @param query The query string.
     * @return Display message.
     */
    public String find(String query) {
        String msg = "Here's what I found in your list, master:";
        int count = 1;
        for (Task t : taskList) {
            if (t.toString().contains(query)) {
                msg += "\n" + count + "." + t;
                count++;
            }
        }
        return msg;
    }

    /**
     * Marks task at index i (starts at 1) in task list as done.
     *
     * @param i Index of task to mark as done.
     * @return Display message.
     */
    public String done(int i) {
        taskList.get(i-1).markAsDone();
        String msg = "Quack! I've marked this task as done, master: \n" +
                "  " + taskList.get(i-1);
        try {
            storage.writeTaskList(taskList);
        } catch (IOException e) {
            msg = ui.showError(e);
        }
        return msg;
    }
}
