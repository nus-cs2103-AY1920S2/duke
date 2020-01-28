package handlers;

import java.util.Scanner;
import java.util.ArrayList;

import tasks.Task;

/**
 * Represents the Ui of Duke,
 * printing out the appropriate message for each of the different commands.
 */

public class Ui {
    private static final String STRING= "    ____________________________________________________________";
    private static final String SPACE = "     ";
    Scanner sc;

    public Ui() {
        sc = new Scanner(System.in);
    }

    public void showLine() {
        System.out.println(STRING);
    }

    public void showWelcome() {
        String greeting = STRING + "\n"+ SPACE +
                "Hello! I'm Duke\n" + SPACE +
                "What can I do for you?\n" +
                STRING;
        System.out.println(greeting);
    }

    public void showFarewell() {
        String bye = SPACE + "Bye. Hope to see you again soon!";
        System.out.println(bye);
    }

    public void showAddedTask(Task taskAdded, int numOfTasks) {
        System.out.println(SPACE + "Got it. I've added this task:");
        System.out.println(SPACE + "  " + taskAdded.toString());
        if (numOfTasks == 1 || numOfTasks == 0) {
            System.out.println(SPACE + "Now you have " + numOfTasks + " task in the list");
        } else {
            System.out.println(SPACE + "Now you have " + numOfTasks + " tasks in the list");
        }
    }

    public void showDeletedTask(Task taskDeleted, int numOfTasks) {
        System.out.println(SPACE + "Noted. I've removed this task: ");
        System.out.println(SPACE + "  " + taskDeleted.toString());
        if (numOfTasks == 1 || numOfTasks == 0) {
            System.out.println(SPACE + "Now you have " + numOfTasks + " task in the list");
        } else {
            System.out.println(SPACE + "Now you have " + numOfTasks + " tasks in the list");
        }
    }

    public void showList(ArrayList<Task> tasks) {
        System.out.println(SPACE + "Here are the tasks in your list:");
        for (int i = 1; i <= tasks.size(); i++) {
            Task t = tasks.get(i - 1);
            System.out.println(SPACE + i + "." + t);
        }
    }

    public void showDoneTask(Task taskDone) {
        System.out.println(SPACE + "Nice! I've marked this done as done: ");
        System.out.println(SPACE + "  " + taskDone.toString());
    }

    public void showFindTasks(TaskList matching) {
        System.out.println(space + "Here are the matching tasks in your list:");
        for (int j = 1; j <= matching.numOfTasks(); j++) {
            Task t = matching.get(j - 1);
            System.out.println(space + j + "." + t);
        }
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
