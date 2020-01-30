import java.time.LocalDate;
import java.util.ArrayList;

/**
 * A list of tasks.
 */
public class TaskList {

    private ArrayList<Task> taskList = new ArrayList<>();

    /**
     * Adds a task into the list.
     *
     * @param task the new task.
     */
    public void add(Task task) {
        taskList.add(task);
    }

    /**
     * Complete a task at the specified index.
     *
     * @param index the index.
     * @throws InvalidListItemAelitaException if index is out of bound.
     * @throws DuplicateMarkAelitaException   if the task has already been completed.
     */
    public void complete(int index) throws InvalidListItemAelitaException, DuplicateMarkAelitaException {

        if (index >= taskList.size() || index < 0) {
            throw new InvalidListItemAelitaException();
        }
        taskList.get(index).markAsDone();
    }

    /**
     * Gets a task at the specified index.
     *
     * @param index the index.
     * @return the requested task.
     */
    public Task get(int index) {
        return taskList.get(index);
    }

    /**
     * Remove a specified task.
     *
     * @param task the task to be removed from the list.
     * @return if the tasks has been successfully removed.
     * @throws InvalidArgumentAelitaException if task list is empty.
     */
    public boolean remove(Task task) throws InvalidArgumentAelitaException {
        boolean done = taskList.remove(task);
        if (done) {
            Task.setTotalTaskCount(Task.getTotalTaskCount() - 1);
        }
        return done;

    }

    /**
     * Remove task at the specified index.
     *
     * @param index the index.
     * @return the task removed.
     * @throws InvalidListItemAelitaException if index is out of bound.
     * @throws InvalidArgumentAelitaException if task list is empty.
     */
    public Task remove(int index) throws InvalidListItemAelitaException, InvalidArgumentAelitaException {
        if (index >= taskList.size() || index < 0) {
            throw new InvalidListItemAelitaException();
        }
        Task.setTotalTaskCount(Task.getTotalTaskCount() - 1);
        return taskList.remove(index);
    }

    /**
     * Gets the size of the TaskList.
     *
     * @return the size.
     */
    public int getSize() {
        return taskList.size();
    }

    /**
     * Lists all the tasks in the TaskList.
     *
     * @param ui the ui to print on.
     * @throws EmptyListAelitaException if task list is empty.
     */
    public void list(Ui ui) throws EmptyListAelitaException {
        if (taskList.size() == 0) {
            throw new EmptyListAelitaException();
        } else {
            ui.printResponse(Response.LIST);
            for (int i = 0; i < taskList.size(); i++) {
                System.out.println("  " + (i + 1) + "." + taskList.get(i));
            }
        }
    }

    /**
     * List all task associated with the supplied date.
     *
     * @param date the date of interest.
     * @param ui   the ui to print on.
     */
    public void list(LocalDate date, Ui ui) {
        boolean hasTask = false;
        for (int i = 0; i < taskList.size(); i++) {
            if (taskList.get(i) instanceof Deadline && ((Deadline) taskList.get(i)).by.equals(date)) {
                if (!hasTask) {
                    ui.printResponse(Response.LIST);
                }
                ui.printTask(taskList.get(i), i + 1);
                hasTask = true;
            } else if (taskList.get(i) instanceof Event && ((Event) taskList.get(i)).date.equals(date)) {
                if (!hasTask) {
                    ui.printResponse(Response.LIST);
                }
                ui.printTask(taskList.get(i), i + 1);
                hasTask = true;
            }
        }
        if (!hasTask) {
            ui.printResponse(Response.NO_TASK_ON_DATE);
        }
    }
}
