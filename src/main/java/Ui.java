import java.util.Scanner;
import java.util.ArrayList;

public class Ui {

    public Ui() {

    }

    public void showLoadingError(String message) {
        System.out.println("Loading error: " + message);
    }

    public void showCommandError(String message) {
        System.out.println("Command error: " + message);
    }

    public String showAddingError(String message) {
        return "Adding error: " + message;
    }

    public String showDeletingError(String message) {
        return "Deleting error: " + message;
    }

    public String showMarkingAsDoneError(String message) {
        return "Marking as done error: " + message;
    }

    public String printAddingMessage(TaskList tasks, Task task) {
        return "Got it dude! I've added this task:" + "\n"
                + task + "\n"
                + printTaskListSize(tasks);
    }

    public String printDoneMessage(Task task) {
        return "Got it dude! I've marked this task as done:" + "\n" + task;
    }

    public String printDeletingMessage(TaskList tasks, Task task) {
        return "Got it dude! I've deleted this task:" + "\n"
                + task + "\n"
                + printTaskListSize(tasks);
    }

    public String printTaskListSize(TaskList tasks) {
        int size = tasks.getListSize();
        return "Now you have " + size + " task(s) in the list.";
    }

    public String printEmptyListMessage() {
        return "Your list is currently empty dude.";
    }

    public String printTaskMessage() {
        return "Here's your list of tasks dude:";
    }

    public String printFoundTaskMessage() {
        return "Okay dude here are what I found:";
    }

    public String printNotFoundTaskMessage() {
        return "Sorry dude I found nothing :(";
    }

    public String printTask(int i, Task task) {
        return i + ". " + task;
    }

    public String exit() {
        return "Okay see ya dude!";
    }
}
