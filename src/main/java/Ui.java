import java.util.Scanner;
import DukeException.*;

public class Ui {
    Scanner sc = new Scanner(System.in);

    public String readCommand() {
        return sc.nextLine();
    }

    public void showList(TaskList tasks) {
        System.out.println("____________________________________________________________\n"
                + "Here are the tasks in your list:");
        for (int i = 0; i < tasks.getLength(); i++) {
            System.out.println(i+1 + "." + tasks.getTask(i));
        }
        System.out.println("____________________________________________________________");
    }

    public void showBye() {
        System.out.print("____________________________________________________________\n"
                + "Bye. Hope to see you again soon!\n"
                + "____________________________________________________________");
    }

    public void showHello() {
        System.out.println("____________________________________________________________\n"
                + "Hello! I'm Duke\nWhat can I do for you?\n"
                + "____________________________________________________________");
    }

    public void showMarkedDone(Task task) {
        System.out.println("____________________________________________________________\n"
                + "Nice! I've marked this task as done:\n"
                + task + "\n"
                + "____________________________________________________________");
    }

    public void showAdded(Task task, int numOfTasks) {
        System.out.println("____________________________________________________________\n"
                + "Got it. I've added this task:\n"
                + task + "\n"
                + "Now you have " + numOfTasks + " tasks in the list.\n"
                + "____________________________________________________________");
    }

    public void showDeleted(Task task, int numOfTasks) {
        System.out.println("____________________________________________________________\n"
                + "Noted. I've removed this task:\n"
                + task + "\n"
                + "Now you have " + numOfTasks + " tasks in the list.\n"
                + "____________________________________________________________");
    }

    public void showMissingTxtFile() {
        System.out.println("____________________________________________________________\n"
                + "duke.txt file doesn't exist, please create one in the file path\n"
                + "____________________________________________________________");
    }

    public void showError(DukeException e) {
        System.out.println("____________________________________________________________\n"
                + e.getMessage() + "\n"
                + "____________________________________________________________");
    }
}