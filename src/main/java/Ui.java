import java.util.Scanner;

public class Ui {
    Scanner sc = new Scanner(System.in);

    public String readCommand() {
        return sc.nextLine();
    }
    public void showMissingDescription() {
        System.out.println("____________________________________________________________\n"
                + "OOPS!!! The description cannot be empty.\n"
                + "____________________________________________________________");
    }

    public void showUnknownInput() {
        System.out.println("____________________________________________________________\n"
                + "OOPS!!! I'm sorry, but I don't know what that means :<\n"
                + "____________________________________________________________");
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
}