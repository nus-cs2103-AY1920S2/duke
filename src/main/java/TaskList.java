import java.util.ArrayList;
import java.time.LocalDate;

/**
 *
 */
public class TaskList {
    public ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Creates a new to-do task and adds it to tasks.
     *
     * @param details String containing to-do task's description.
     * @return String with new task's details.
     */
    public String addTodo(String details) {
        Todo newTask = new Todo(details);
        tasks.add(newTask);
        return ("added new todo: " + details);
    }

    /**
     * Creates a new event task and adds it to tasks.
     *
     * @param details String containing event task's description.
     * @param date LocalDate object representing date of the event.
     * @return String with new task's details.
     */
    public String addEvent(String details, LocalDate date) {
        Event newTask = new Event(details, date);
        tasks.add(newTask);
        return ("added new event: " + newTask);
    }

    /**
     * Creates a new deadline task and adds it to tasks.
     *
     * @param details String containing deadline task's description.
     * @param date LocalDate object representing do-by date of deadline task.
     * @return String with new task's details.
     */
    public String addDeadline(String details, LocalDate date) {
        Deadline newTask = new Deadline(details, date);
        print(newTask.toString());
        tasks.add(newTask);
        return ("added new deadline: " + newTask);
    }

    /**
     * Marks a task as "done", indicated by a tick symbol rather than a cross.
     *
     * @param taskIndex Integer representing the task's position in the task list.
     * @return String stating which task has been marked as done.
     */
    public String markDone(int taskIndex) {
        Task task = tasks.get(taskIndex - 1);
        task.markAsDone();
        return ("That's another one down. That'll be: " + task);
    }

    /**
     * Removes a task from the task list.
     *
     * @param taskIndex Integer representing the task's position in the task list.
     * @return String stating which task has been deleted from the list.
     */
    public String deleteTask(int taskIndex) {
        Task task = tasks.get(taskIndex - 1);
        tasks.remove(taskIndex - 1);
        return ("Don't need this here anymore eh? Off it goes." + task);
    }

    /**
     * Lists out all tasks in sequential order in the task list.
     *
     * @param taskList TaskList object containing all added tasks.
     * @return String containing each task's details on a separate line.
     */
    public String listTasks(TaskList taskList) {
        StringBuilder output = new StringBuilder();
        taskList.tasks.forEach(task -> output.append(String.format(
                "%d. %s \n",
                (taskList.tasks.indexOf(task) + 1),
                task.toString())));
        return (output.append(String.format(
                "That's %d in the list.", taskList.tasks.size())).toString());
    }

    /**
     * Searches for and retrieves all tasks in the task list that contain the given keyword
     *
     * @param keyword String that task(s) must contain.
     * @return String containing each relevant task's details on a separate line.
     */
    public String findTask(String keyword) {
        TaskList foundTasks = new TaskList();
        StringBuilder output = new StringBuilder();

        for (Task task : tasks) {
            if (task.getDescription().contains(keyword)) {
                foundTasks.tasks.add(task);
            }
        }
        foundTasks.tasks.forEach(task -> output.append(task));

        return output.toString();
    }

    /**
     * Adds descriptor tags to a specified task.
     *
     * @param taskIndex Integer denoting the task in the task list to be tagged.
     * @param tagDetails String of details that tag is to contain.
     * @return String stating the details of the new tag and the task.
     */
    public String tagTask(int taskIndex, String tagDetails) {
        Task task = tasks.get(taskIndex - 1);
        Tag tag = new Tag(tagDetails);

        task.tagList.add(tag);
        return String.format("Added: %s to %s", tag.getDetails(), task);
    }

    public void print(String string) {
        System.out.println(string);
    }
}
