import java.util.ArrayList;
import java.util.Scanner;

/**
 * Ui class that deals with interactions with the user.
 */
public class Ui {

    /**
     * Ui class constructor.
     */
    public Ui() {

    }

    /**
     * Method to print the completion of a task.
     * @param task Task to be marked as done.
     */
    public String printDone(Task task) {
        return ("You have completed this task.\n" + task.toString());
    }

    /**
     * Method to print the deletion of a task and the new list size.
     * @param task Task to be deleted.
     * @param newListSize New size of the task list.
     */
    public String printDelete(Task task, int newListSize) {
        return (task.toString() + " has been removed.\n" + "Number of items in the list: " + newListSize);
    }


    /**
     * Method to print the addition of a task and the new list size.
     * @param task Task to be added.
     * @param listSize Size of the task list.
     */
    public String printAdd(Task task, int listSize) {
        return ("Understood. I have added: " + task.toString() + "\n" + "Number of items in the list: " + listSize);
    }

    /**
     * Method to print a farewell message before closing the Duke bot.
     */
    public String printBye() {
        return ("It was my pleasure to help you.\n");
    }

    /**
     * Method to print all the matched tasks.
     * @param matchedTasks ArrayList of all matched tasks.
     */
    public String printFind(TaskList matchedTasks) {
        return ("These are the tasks that matched what you were looking for.\n" + matchedTasks.printList());
    }

    /**
     * Method to print which task has been snoozed and for how many days.
     */
    public String printSnooze(Task task, int noDays) {
        return (task + " has been snoozed for " + noDays + " days.\n");
    }

    public void printHello() {
        System.out.println("Hello, I am DukeBot.");
    }
}
