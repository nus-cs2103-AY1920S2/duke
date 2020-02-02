import java.lang.reflect.Array;
import java.util.ArrayList;

public class TaskList {
    public ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    public void addTodo(String task) {
        Todo newTask = new Todo(task);
        tasks.add(newTask);
        print("added new todo: " + task);
    }

    public void addEvent(String task) {
        String[] split = Parser.prepareDeadlineEvent(task);
        Event newTask = new Event(split[0], split[1]);
        tasks.add(newTask);
        print("added new event: " + task);
    }

    public void addDeadline(String task) {
        String[] split = Parser.prepareDeadlineEvent(task);
        Deadline newTask = new Deadline(split[0], split[1]);
        print(newTask.toString());
        tasks.add(newTask);
        print("added new deadline: " + task);
    }

    public void markDone(int taskIndex) {
        Task task = tasks.get(taskIndex - 1);
        task.markAsDone();
        print("That's another one down. That'll be: ");
        print(task.toString());
    }

    public void deleteTask(int taskIndex) {
        Task task = tasks.get(taskIndex - 1);
        tasks.remove(taskIndex - 1);
        print("Don't need this here anymore eh? Off it goes.");
        print(task.toString());
    }

    public void print(String string) {
        System.out.println(string);
    }
}
