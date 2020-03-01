import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * TaskList is a wrapper class for a List of Tasks.
 */
public class TaskList {
    protected LocalDate DEFAULT_DATE = LocalDate.parse("2099-12-31", DateTimeFormatter.ofPattern("yyyy-MM-dd"));

    private List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(List<Task> tasks) {
        this.tasks = new ArrayList<>(tasks);
    }

    public TaskList(Storage storage) throws FileNotFoundException {
        this.tasks = storage.getAllTasksFromFile();
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public int getTasksLength() {
        return tasks.size();
    }

    public Task getTask(int taskNumber) {
        return tasks.get(taskNumber);
    }

    public String makeTaskDone(int taskNumber) {
        tasks.get(taskNumber).markAsDone();
        return "Nice! I've marked this task as done: " + "\n" + tasks.get(taskNumber);
    }

    public String deleteTask(int taskNumber) {
        StringBuilder message = new StringBuilder();
        message.append("Noted. I've removed this task: \n");
        message.append(tasks.get(taskNumber)+"\n");
        tasks.remove(taskNumber);
        message.append("Now you have " + tasks.size() + " tasks in the list. \n");
        return message.toString();
    }

    public String addTask(Task task) {
        StringBuilder message = new StringBuilder();
        tasks.add(task);
        message.append("Got it. I've added this task: \n");
        message.append(task + "\n");
        message.append("Now you have " + tasks.size() + " tasks in the list.");
        return message.toString();
    }

    public String printListTasks() {
        return "Here are the tasks in your list: " + this.printTasks();
    }

    public String printTasks() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < tasks.size(); i++) {
            result.append((i + 1) + ". " + tasks.get(i).toString() + "\n");
        }
        return result.toString();
    }

    public String showFilteredBySpecificDate(String date) {
        TaskList filteredTasks = new TaskList(tasks.stream()
                .filter(task -> !task.getDate().equals(DEFAULT_DATE))
                .filter(task -> task.getDate().equals(LocalDate.parse(date)))
                .collect(Collectors.toList()));
        return "Here are the tasks on date " + date + "\n" + filteredTasks.printTasks();
    }

    public String showFilteredBySpecificYear(int year) {
        TaskList filteredTasks = new TaskList(tasks.stream()
                .filter(task -> !task.getDate().equals(DEFAULT_DATE))
                .filter(task -> task.getDate().getYear() == year)
                .collect(Collectors.toList()));
        return "Here are the tasks in the year " + year + "\n" + filteredTasks.printTasks();
    }

    public String showFilteredBySpecificMonth(int month) {
        TaskList filteredTasks = new TaskList(tasks.stream()
                .filter(task -> !task.getDate().equals(DEFAULT_DATE))
                .filter(task -> task.getDate().getMonthValue() == month)
                .collect(Collectors.toList()));
        return "Here are the tasks in the month " + month + "\n" + filteredTasks.printTasks();
    }

    public String showFilteredByName(String word) {
        TaskList filteredTasks = new TaskList(tasks.stream()
                .filter(task -> !task.getDate().equals(DEFAULT_DATE))
                .filter(task -> task.getDescription().contains(word))
                .collect(Collectors.toList()));
        return "Here are the matching tasks in your list:" + "\n" + filteredTasks.printTasks();
    }

    public String sortDeadlinesByTime() {
        TaskList filteredTasks = new TaskList(tasks.stream()
                .filter(task -> task.getTypeName() == "D")
                .filter(task -> task.getDate().compareTo(LocalDate.now()) > 0)
                .sorted()
                .collect(Collectors.toList()));
        return "These are the nearest upcoming deadlines. Take note!" + "\n" + filteredTasks.printTasks();
    }
}
