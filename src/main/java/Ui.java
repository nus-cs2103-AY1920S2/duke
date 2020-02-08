import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Ui {

    Scanner sc;

    public Ui() {
        sc = new Scanner(System.in);
    }

    public void showWelcome() {
        System.out.println("Hello I'm Duke." + "\n" + "What can I do for you?");
    }

    public void showLine() {
        System.out.println("____________________________________________________________");
    }

    public String readCommand() {
        String line = sc.nextLine();
        return line;
    }

    public void showLoadingError() {
        System.out.println("no data to load, new list is empty.");
    }

    public void showError(String message) {
        System.out.println(message);
    }

    public void printList(List<Task> list) {
        if (!list.isEmpty()) {
            for (int i = 0; i < list.size(); i++) {
                Task t = list.get(i);
                System.out.println((i+1) + "." + t.toString());
            }
        }
    }

    public void printDone(TaskList tasks, int doneTask) {
        System.out.println("Nice! I've marked this task as done:");
        Task t = tasks.getList().get(doneTask);
        System.out.println(t.toString());
    }

    public void printDelete(TaskList tasks, int deleteTask) {
        List<Task> list = tasks.getList();
        System.out.println("Noted. I've removed this task:");
        Task t = list.get(deleteTask);
        System.out.println(t.toString());
        System.out.println("Now you have " + list.size() + " tasks in the list.");
    }

    public void printAddTask(TaskList tasks) {
        List<Task> list = tasks.getList();
        Task t = list.get(list.size() - 1);
        System.out.println("Got it. I've added this task:");
        System.out.println(t.toString());
        System.out.println("Now you have " + list.size() + " tasks in the list.");
    }
}