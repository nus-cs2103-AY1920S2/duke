import java.util.ArrayList;

public class TaskList {
    public ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTodo(String task) {
        Todo newTask = new Todo(task);
        tasks.add(newTask);
        print("added new todo: " + task);
    }

    public void addEvent(String[] task) {
        Event newTask = new Event(task[0], task[1]);
        tasks.add(newTask);
        print("added new event: " + task);
    }

    public void addDeadline(String[] task) {
        Deadline newTask = new Deadline(task[0], task[1]);
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

    public void findTask(String keyword) {
        TaskList foundTasks = new TaskList();
        for (Task task : tasks) {
            if (task.getDescription().contains(keyword)) {
                foundTasks.tasks.add(task);
            }
        }
        foundTasks.tasks.forEach(task -> print(task.toString()));
    }

    public void print(String string) {
        System.out.println(string);
    }
}
