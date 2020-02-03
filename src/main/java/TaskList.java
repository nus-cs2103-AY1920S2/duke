import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TaskList {
    private List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(List<Task> tasks) {
        this.tasks = new ArrayList<>(tasks);
    }

    public TaskList(Storage storage) {
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
    public void makeTaskDone(int taskNumber) {
        tasks.get(taskNumber).markAsDone();
        System.out.println("Nice! I've marked this task as done: ");
        System.out.println(tasks.get(taskNumber));
    }

    public void deleteTask(int taskNumber) {
        System.out.println("Noted. I've removed this task: ");
        System.out.println(tasks.get(taskNumber));
        tasks.remove(taskNumber);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    public void addTask(Task task) {
        tasks.add(task);
        System.out.println("Got it. I've added this task: ");
        System.out.println(task);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }


    public void printTasks() {
        System.out.println("Here are the tasks in your list: ");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + "." + tasks.get(i).toString());
        }
    }

    public TaskList filterBySpecificDate(String date) {
        return new TaskList(tasks.stream().filter(task -> task.getDate().equals(LocalDate.parse(date))).collect(Collectors.toList()));
    }

    public TaskList filterBySpecificYear(int year) {
        return new TaskList(tasks.stream().filter(task -> task.getDate().getYear() == year).collect(Collectors.toList()));
    }

    public TaskList filterBySpecificMonth(int month) {
        return new TaskList(tasks.stream().filter(task -> task.getDate().getMonthValue() == month).collect(Collectors.toList()));
    }
}
