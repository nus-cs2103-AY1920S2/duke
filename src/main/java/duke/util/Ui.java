package duke.util;

import duke.task.Task;
import java.util.ArrayList;

public class Ui {
    public static final String botName = "Duke";
    public static final String listCommand = "list";
    public static final String byeCommand = "bye";

    private static String padding = "       ";
    private static String uselessLine = "-------------------------------------------------------------------------------------";
    private static String addedPhrase = "added: ";

    /**
     * Print the greeting lines to the screen.
     * The first thing the user will see.
     */
    public static void greet() {
        System.out.println(String.format("%s%s", padding, uselessLine));
        System.out.println(String.format("%sGreetings! This is %s, and I am your friend!", padding, botName));
        System.out.println(String.format("%sYou don't have to be formal. Relax and tell me how I can help you", padding));
        System.out.println(String.format("%s%s", padding, uselessLine));
    }

    /**
     * Repeat whatever the input is, print it to the screen.
     * @param str
     */
    private void echo(String str) {
        System.out.println(String.format("%s%s", padding, uselessLine));
        System.out.println(String.format("%s%s", padding, str));
        System.out.println(String.format("%s%s", padding, uselessLine));
    }

    /**
     * Print the bye-bye line to the screen.
     * Last thing the user will see before the application shuts off.
     */
    public static void byeBye() {
        System.out.println(String.format("%s%s", padding, uselessLine));
        System.out.println(String.format("%sBye-bye. It was nice talking to you. See ya soon!", padding));
        System.out.println(String.format("%s%s", padding, uselessLine));
    }

    /**
     * Print all the tasks in storedItems to the screen.
     * @param storedItems
     */
    public static void listStoredItems(ArrayList<Task> storedItems) {
        System.out.println(String.format("%s%s", padding, uselessLine));

        if (storedItems.isEmpty()) {
            System.out.println(String.format("%sYour list is empty!", padding));
        } else {
            System.out.println(String.format("%sHere is your list", padding));
            int i = 1;
            for (Task task : storedItems) {
                System.out.println(String.format("%s%d. %s", padding, i, task));
                i++;
            }
        }

        System.out.println(String.format("%s%s", padding, uselessLine));
    }

    /**
     * Print the status after successfully storing a task
     * @param task
     * @param storedItems
     */
    public static void storeUserInput(Task task, ArrayList<Task> storedItems) {
        System.out.println(String.format("%s%s", padding, uselessLine));
        System.out.println(String.format("%s%s%s", padding, addedPhrase, task));
        System.out.println(String.format("%sNow you have %d tasks", padding, storedItems.size()));
        System.out.println(String.format("%s%s", padding, uselessLine));
    }

    /**
     * Print the status after successfully marking a task as Done
     * @param pos
     * @param storedItems
     */
    public static void markItemAsDone(int pos, ArrayList<Task> storedItems) {
        System.out.println(String.format("%s%s", padding, uselessLine));
        System.out.println(String.format("%sNice nice. I've marked the task as done for you", padding));
        System.out.println(String.format("%s   %s", padding, storedItems.get(pos)));
        System.out.println(String.format("%s%s", padding, uselessLine));
    }

    /**
     * Print the status after successfully deleting a task
     * @param t
     * @param storedItems
     */
    public static void deleteItem(Task t, ArrayList<Task> storedItems) {
        System.out.println(String.format("%s%s", padding, uselessLine));
        System.out.println(String.format("%sI've removed this task for you", padding));
        System.out.println(String.format("%s   %s", padding, t));
        System.out.println(String.format("%sYou have %d tasks left", padding, storedItems.size()));
        System.out.println(String.format("%s%s", padding, uselessLine));
    }

    /**
     * React to a blank user input
     */
    public static void blankInput() {
        System.out.println(String.format("%s%s", padding, uselessLine));
        System.out.println(String.format("%sPlease type something. Don't leave it blank, plsss!", padding));
        System.out.println(String.format("%s%s", padding, uselessLine));
    }

    /**
     * Print the resulting foundList of a find request
     * @param foundList
     */
    public static void printFoundList(ArrayList<Task> foundList) {
        System.out.println(String.format("%s%s", padding, uselessLine));
        System.out.println(String.format("%sHere are the matching tasks in your list:", padding));
        if (foundList.isEmpty()) {
            System.out.println(String.format("%s~~~~~Oops! There are no matching tasks!", padding));
        } else {
            int i = 1;
            for (Task task : foundList)
                System.out.println(String.format("%s%d. %s", padding, i++, task));
        }
        System.out.println(String.format("%s%s", padding, uselessLine));
    }
}
