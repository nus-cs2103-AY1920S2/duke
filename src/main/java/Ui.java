import java.util.*;
public class Ui {
    public static final String string = "    ____________________________________________________________";
    public static final String space = "     ";
    Scanner sc;

    public Ui() {
        sc = new Scanner(System.in);
    }

    public void showLine() {
        System.out.println(string);
    }

    public void showWelcome() {
        String greeting = string + "\n"+ space +
                "Hello! I'm Duke\n" + space +
                "What can I do for you?\n" +
                string;
        System.out.println(greeting);
    }

    public void showFarewell() {
        String bye = space + "Bye. Hope to see you again soon!";
        System.out.println(bye);
    }

    public void showAddedTask(Task taskAdded, int numOfTasks) {
        System.out.println(space + "Got it. I've added this task:");
        System.out.println(space + "  " + taskAdded.toString());
        //System.out.println("Now you have " + numOfTasks + " tasks in the list");
        if (numOfTasks == 1 || numOfTasks == 0) {
            System.out.println(space + "Now you have " + numOfTasks + " task in the list");
        } else {
            System.out.println(space + "Now you have " + numOfTasks + " tasks in the list");
        }
    }

    public void showDeletedTask(Task taskDeleted, int numOfTasks) {
        System.out.println(space + "Noted. I've removed this task: ");
        System.out.println(space + "  " + taskDeleted.toString());
        if (numOfTasks == 1 || numOfTasks == 0) {
            System.out.println(space + "Now you have " + numOfTasks + " task in the list");
        } else {
            System.out.println(space + "Now you have " + numOfTasks + " tasks in the list");
        }
    }

    public void showList(ArrayList<Task> tasks) {
        System.out.println(space + "Here are the tasks in your list:");
        for (int i = 1; i <= tasks.size(); i++) {
            //System.out.println(" " + i + ". " + lst.get(i-1));
            Task t = tasks.get(i - 1);
            System.out.println(space + i + "." + t);
        }
    }

    public void showDoneTask(Task taskDone) {
        System.out.println(space + "Nice! I've marked this done as done: ");
        System.out.println(space + "  " + taskDone.toString());
    }

    public void showError(String error) {
        System.out.println(error);
    }

    public void showLoadingError() {
        System.out.println("☹ OOPS!!! File could not be found.");
    }

    public String readCommand() {
        return sc.nextLine();
    }
}
