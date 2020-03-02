package handlers;

import java.util.Scanner;
import java.util.ArrayList;

import tasks.Task;
import tasks.TaskList;

/**
 * Represents the Ui of Duke,
 * printing out the appropriate message for each of the different commands.
 */

public class Ui {
    private Scanner sc;
    private String response;

    public Ui() {
        this.sc = new Scanner(System.in);
        this.response = "";
    }

    public void showFarewell() {
        this.sc.close();
        String bye = "Meow! Hope to see you again soon!";
        setResponse(bye);
        System.out.println(this.response);
    }

    public void showAddedTask(Task taskAdded, int numOfTasks) {
        setResponse("Meow. I've added this task:");
        setResponse("  " + taskAdded.toString());
        if (numOfTasks == 1 || numOfTasks == 0) {
            setResponse("Now you have " + numOfTasks + " task in the list");
        } else {
            setResponse("Now you have " + numOfTasks + " tasks in the list");
        }
        System.out.println(this.response);
    }

    public void showDeletedTask(Task taskDeleted, int numOfTasks) {
        setResponse("Meow. I've removed this task: ");
        setResponse("  " + taskDeleted.toString());
        if (numOfTasks == 1 || numOfTasks == 0) {
            setResponse("Now you have " + numOfTasks + " task in the list");
        } else {
            setResponse("Now you have " + numOfTasks + " tasks in the list");
        }
        System.out.println(this.response);
    }

    public void showList(ArrayList<Task> tasks) {
        setResponse("Here are the tasks in your list:");
        for (int i = 1; i <= tasks.size(); i++) {
            Task t = tasks.get(i - 1);
            setResponse(i + "." + t);
        }
        System.out.println(this.response);
    }

    public void showDoneTask(Task taskDone) {
        setResponse("Meow! I've marked this task as done: ");
        setResponse("  " + taskDone.toString());
        System.out.println(this.response);
    }

    public void showFindTasks(TaskList matching) {
        setResponse("Here are the matching tasks in your list:");
        for (int j = 1; j <= matching.numOfTasks(); j++) {
            Task t = matching.get(j - 1);
            setResponse(j + "." + t);
        }
        System.out.println(this.response);
    }

    public void showAddedNote(Task noteAddedTask) {
        setResponse("Meow! I've added a note to this task: ");
        setResponse("  " + noteAddedTask.toString());
        System.out.println(this.response);
    }

    public void showGetNote(Task taskGetNote, String note) {
        setResponse("Note for " + taskGetNote.toString() + ": ");
        setResponse("  " + note);
        System.out.println(this.response);
    }

    public void setResponse(String line) {
        this.response += line + "\n";
    }

    public String getResponse() {
        return this.response;
    }

    public void clearResponse() {
        this.response = "";
    }

    public void showError(String error) {
        clearResponse();
        setResponse(error);
        System.out.println(this.response);
    }

    public void showLoadingError() {
        System.out.println("☹ OOPS!!! File could not be found.");
    }

    public String readCommand() {
        return sc.nextLine();
    }
}
