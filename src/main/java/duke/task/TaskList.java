package duke.task;

import duke.exception.DukeNoSuchTaskException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Represents a TaskList.
 */
public class TaskList {

    private ArrayList<Task> tasks;

    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    /**
     * Constructs a TaskList when loading from disk.
     *
     * @param tasks the ArrayList of tasks from the disk.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Gets the list of tasks from TaskList.
     *
     * @return the ArrayList of Tasks.
     */
    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    /**
     * Adds task to the task list.
     *
     * @param task the task to add.
     * @return acknowledgement message by Duke.
     */
    public String addTask(Task task) {
        String output = "     Got it. I've added this task:\n";
        output += "       " + task + "\n";
        tasks.add(task);
        output += "     Now you have " + tasks.size() + " tasks in the list.";
        return output;
    }

    /**
     * Deletes task from the task list.
     *
     * @param taskNo the index to delete from the ArrayList of tasks.
     * @return acknowledgement message by Duke.
     * @throws DukeNoSuchTaskException if the given index is out of bound of the ArrayList.
     */
    public String deleteTask(int taskNo) throws DukeNoSuchTaskException {
        try {
            Task task = tasks.get(taskNo);
            tasks.remove(task);
            return "     Noted. I've removed this task:\n       " + task
                    + "\n     Now you have " + tasks.size() + " tasks in the list.";
        } catch (IndexOutOfBoundsException e) {
            throw new DukeNoSuchTaskException();
        }
    }

    /**
     * Finds and lists all tasks that contains the keyword in the description.
     *
     * @param keyword the keyword to search for.
     * @return the list of tasks that contains the keyword.
     */
    public String find(String keyword) {
        List<Task> foundTasks =
                tasks.parallelStream()
                        .filter(task -> task.getDescription().contains(keyword))
                        .collect(Collectors.toList());
        String output = "     Here are the matching tasks in your list:";
        output += iterateTasks(foundTasks);
        return output;
    }

    /**
     * Lists all the tasks.
     *
     * @return the list of tasks.
     */
    public String listTasks() {
        String output = "     Here are the tasks in your list:";
        output += iterateTasks(tasks);
        return output;
    }

    private String iterateTasks(List<? extends Task> listOfTasks) {
        String output = "";
        for (int i = 0; i < listOfTasks.size(); i++) {
            output += "\n     " + (i + 1) + ". " + listOfTasks.get(i);
        }
        return output;
    }

    /**
     * Returns a list of overdue deadlines and a list of upcoming deadline.
     *
     * @return the reminder for deadlines.
     */
    public String getDeadlineReminder() {
        List<Deadline> unDoneDeadlines =
                tasks.parallelStream()
                        .filter(task -> task instanceof Deadline)
                        .filter(task -> !task.isDone)
                        .map(task -> (Deadline) task)
                        .collect(Collectors.toList());
        List<Deadline> overdueDeadlines =
                unDoneDeadlines.parallelStream()
                        .filter(deadline -> deadline.by.compareTo(LocalDate.now()) < 0)
                        .sorted()
                        .collect(Collectors.toList());
        List<Deadline> upcomingDeadlines =
                unDoneDeadlines.parallelStream()
                        .filter(deadline -> deadline.by.compareTo(LocalDate.now()) >= 0)
                        .sorted()
                        .collect(Collectors.toList());

        String output = "";

        if (!overdueDeadlines.isEmpty()) {
            output += "     Here are your overdue deadlines:";
            output += iterateTasks(overdueDeadlines);
        }

        if (!upcomingDeadlines.isEmpty()) {
            output += "\n     Here are your upcoming deadlines:";
            output += iterateTasks(upcomingDeadlines);
        }
        return output;
    }
}
