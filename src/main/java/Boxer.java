import java.util.ArrayList;
import java.util.Arrays;

public class Boxer {
    // Variables
    private ArrayList<Task> taskList = new ArrayList<Task>(100);

    public void greet() {
        String greeting = "Hey there, Red. Anything I can do for you?";
        print(greeting);
    }

    public void event(String task) {
        String[] split = task.split("/", 2);
        Event newTask = new Event(split[0], split[1]);
        taskList.add(newTask);
        print("added: " + task);
    }

    public void deadline(String task) {
        String[] split = task.split("/", 2);
        Deadline newTask = new Deadline(split[0], split[1]);
        print(newTask.toString());
        taskList.add(newTask);
        print("added: " + task);
    }

    public void todo(String task) {
        Todo newTask = new Todo(task);
        taskList.add(newTask);
        print("added: " + task);
    }

    public void list() {
        taskList.forEach(task -> Boxer.print(String.format(
                "%d. %s",
                (taskList.indexOf(task) + 1),
                task.toString())));
        print(String.format(
                "That's %d in the list.", taskList.size()));
    }

    public void done(int taskIndex) {
        Task task = taskList.get(taskIndex - 1);
        task.markAsDone();
        print("That's another one down. That'll be: ");
        print(task.toString());
    }

    public void exit() {
        String farewell = "Guess that's enough for now. I've got your back, so you keep me close.";
        print(farewell);
    }

    public static void print(String toPrint) {
        System.out.println(toPrint);
    }

    /* Defunct methods
    public void echo(String toEcho) {
        print(toEcho);
    }
     */

}
