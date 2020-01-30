package main.java;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Ui class that deals with interactions with the user.
 */
public class Ui {

    /**
     * Ui class constructor.
     */
    public Ui () {}

    /**
     * Method to print Duke's name and a welcome message to the user.
     */
    public void printWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Greetings, I am\n" + logo);
        System.out.println("How may I be of assistance today?");
    }

    /**
     * Method to print the completion of a task.
     * @param task Task to be marked as done.
     */
    public void printDone(Task task) {
        System.out.println("You have completed this task.");
        System.out.println(task);
    }

    /**
     * Method to print the deletion of a task and the new list size.
     * @param task Task to be deleted.
     * @param newListSize New size of the task list.
     */
    public void printDelete(Task task, int newListSize) {
        System.out.println(task + " has been removed.");
        System.out.println("Number of items in the list: " + newListSize);
    }


    /**
     * Method to print the addition of a task and the new list size.
     * @param task Task to be added.
     * @param listSize Size of the task list.
     */
    public void printAdd(Task task, int listSize) {
        System.out.println("Understood. I have added: " + task);
        System.out.println("Number of items in the list: " + listSize);
    }

    /**
     * Method to print a farewell message before closing the Duke bot.
     */
public void printBye() {
        System.out.println("It was my pleasure to help you.\n");
    }

    public void printFind(ArrayList<Task> matchedTasks) {
        System.out.println("These are the tasks that matched what you were looking for.");
        for (Task tasks : matchedTasks) {
            System.out.println(tasks);
        }
    }
}
