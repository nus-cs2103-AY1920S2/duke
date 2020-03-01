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

    public String addTodo(String details) {
        Todo newTask = new Todo(details);
        tasks.add(newTask);
        return ("added new todo: " + details);
    }

    public String addEvent(String details, LocalDate date) {
        Event newTask = new Event(details, date);
        tasks.add(newTask);
        return ("added new event: " + newTask);
    }

    public String addDeadline(String details, LocalDate date) {
        Deadline newTask = new Deadline(details, date);
        print(newTask.toString());
        tasks.add(newTask);
        return ("added new deadline: " + newTask);
    }

    public String markDone(int taskIndex) {
        Task task = tasks.get(taskIndex - 1);
        task.markAsDone();
        return ("That's another one down. That'll be: " + task);
    }

    public String deleteTask(int taskIndex) {
        Task task = tasks.get(taskIndex - 1);
        tasks.remove(taskIndex - 1);
        return ("Don't need this here anymore eh? Off it goes." + task);
    }

    public String listTasks(TaskList taskList) {
        StringBuilder output = new StringBuilder();
        taskList.tasks.forEach(task -> output.append(String.format(
                "%d. %s \n",
                (taskList.tasks.indexOf(task) + 1),
                task.toString())));
        return (output.append(String.format(
                "That's %d in the list.", taskList.tasks.size())).toString());
    }

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

    public String tagTask(int taskIndex, String tagDetails) {
        Task task = tasks.get(taskIndex - 1);
        Tag tag = new Tag("|" + tagDetails);

        task.tagList.add(tag);
        return String.format("Added: %s to %s", tag.getDetails(), task);
    }

    public void print(String string) {
        System.out.println(string);
    }
}
