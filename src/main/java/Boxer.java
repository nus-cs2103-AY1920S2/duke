import java.util.ArrayList;

public class Boxer {
    // Variables
    private ArrayList<Task> taskList = new ArrayList<Task>(100);

    public void greet() {
        String greeting = "Hey there, Red. Anything I can do for you?";
        print(greeting);
    }

    public void add(String task) {
        Task newTask = new Task(task);
        taskList.add(newTask);
        print("added: " + task);
    }

    public void list() {
        taskList.forEach(task -> printTask(task));
    }

    public void done(int taskIndex) {
        Task task = taskList.get(taskIndex - 1);
        task.markAsDone();
        print("That's another one down. That'll be: ");
        printTask(task);

    }

    public void exit() {
        String farewell = "Guess that's enough for now. I've got your back, so you keep me close.";
        print(farewell);
    }

    public void print(String toPrint) {
        System.out.println(toPrint);
    }

    public void printTask(Task task) {
        print(String.format(
                "%d. %s %s", taskList.indexOf(task) + 1,
                task.getStatusIcon(),
                task.getDescription()));
    }

    /* Defunct methods
    public void echo(String toEcho) {
        print(toEcho);
    }
     */

}
