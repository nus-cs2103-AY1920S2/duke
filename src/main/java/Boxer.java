import java.util.ArrayList;

public class Boxer {
    // Variables
    private ArrayList<String> taskList = new ArrayList<String>(100);

    public void greet() {
        String greeting = "Hey there, Red. Anything I can do for you?";
        print(greeting);
    }

    public void add(String task) {
        taskList.add(task);
        print("added: " + task);
    }

    public void list() {
        taskList.forEach(
                task -> print((taskList.indexOf(task) + 1) + ". " + task));
    }

    public void exit() {
        String farewell = "Guess that's enough for now. I've got your back, so you keep me close.";
        print(farewell);
    }

    public void print(String toPrint) {
        System.out.println(toPrint);
    }

    /* Defunct methods
    public void echo(String toEcho) {
        print(toEcho);
    }
     */

}
