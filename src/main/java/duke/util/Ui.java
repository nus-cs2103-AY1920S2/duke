package duke.util;

import duke.task.Task;
import java.util.ArrayList;

public class Ui {
    public static final String BOT_NAME = "Duke";
    public static final String LIST_COMMAND = "list";
    public static final String BYE_COMMAND = "bye";

    private static String PADDING = "       ";
    private static String USELESS_LINE = "-------------------------------------------------------------------------------------";
    private static String ADDED_PHRASE = "added: ";

    /**
     * Print the greeting lines to the screen.
     * The first thing the user will see.
     */
    public static void greet() {
        System.out.println(String.format("%s%s", PADDING, USELESS_LINE));
        System.out.println(String.format("%sGreetings! This is %s, and I am your friend!", PADDING, BOT_NAME));
        System.out.println(String.format("%sYou don't have to be formal. Relax and tell me how I can help you", PADDING));
        System.out.println(String.format("%s%s", PADDING, USELESS_LINE));
    }

    /**
     * Repeat whatever the input is, print it to the screen.
     * @param str
     */
    private void echo(String str) {
        System.out.println(String.format("%s%s", PADDING, USELESS_LINE));
        System.out.println(String.format("%s%s", PADDING, str));
        System.out.println(String.format("%s%s", PADDING, USELESS_LINE));
    }

    /**
     * Print the bye-bye line to the screen.
     * Last thing the user will see before the application shuts off.
     */
    public static void byeBye() {
        System.out.println(String.format("%s%s", PADDING, USELESS_LINE));
        System.out.println(String.format("%sBye-bye. It was nice talking to you. See ya soon!", PADDING));
        System.out.println(String.format("%s%s", PADDING, USELESS_LINE));
    }

    /**
     * Print all the tasks in storedItems to the screen.
     * @param storedItems
     */
    public static void listStoredItems(ArrayList<Task> storedItems) {
        System.out.println(String.format("%s%s", PADDING, USELESS_LINE));

        if (storedItems.isEmpty()) {
            System.out.println(String.format("%sYour list is empty!", PADDING));
        } else {
            System.out.println(String.format("%sHere is your list", PADDING));
            int i = 1;
            for (Task task : storedItems) {
                System.out.println(String.format("%s%d. %s", PADDING, i, task));
                i++;
            }
        }

        System.out.println(String.format("%s%s", PADDING, USELESS_LINE));
    }

    /**
     * Print the status after successfully storing a task
     * @param task
     * @param storedItems
     */
    public static void storeUserInput(Task task, ArrayList<Task> storedItems) {
        System.out.println(String.format("%s%s", PADDING, USELESS_LINE));
        System.out.println(String.format("%s%s%s", PADDING, ADDED_PHRASE, task));
        System.out.println(String.format("%sNow you have %d tasks", PADDING, storedItems.size()));
        System.out.println(String.format("%s%s", PADDING, USELESS_LINE));
    }

    /**
     * Print the status after successfully marking a task as Done
     * @param pos
     * @param storedItems
     */
    public static void markItemAsDone(int pos, ArrayList<Task> storedItems) {
        System.out.println(String.format("%s%s", PADDING, USELESS_LINE));
        System.out.println(String.format("%sNice nice. I've marked the task as done for you", PADDING));
        System.out.println(String.format("%s   %s", PADDING, storedItems.get(pos)));
        System.out.println(String.format("%s%s", PADDING, USELESS_LINE));
    }

    /**
     * Print the status after successfully deleting a task
     * @param t
     * @param storedItems
     */
    public static void deleteItem(Task t, ArrayList<Task> storedItems) {
        System.out.println(String.format("%s%s", PADDING, USELESS_LINE));
        System.out.println(String.format("%sI've removed this task for you", PADDING));
        System.out.println(String.format("%s   %s", PADDING, t));
        System.out.println(String.format("%sYou have %d tasks left", PADDING, storedItems.size()));
        System.out.println(String.format("%s%s", PADDING, USELESS_LINE));
    }

    /**
     * React to a blank user input
     */
    public static void blankInput() {
        System.out.println(String.format("%s%s", PADDING, USELESS_LINE));
        System.out.println(String.format("%sPlease type something. Don't leave it blank, plsss!", PADDING));
        System.out.println(String.format("%s%s", PADDING, USELESS_LINE));
    }

    /**
     * Print the resulting foundList of a find request
     * @param foundList
     */
    public static void printFoundList(ArrayList<Task> foundList) {
        System.out.println(String.format("%s%s", PADDING, USELESS_LINE));
        System.out.println(String.format("%sHere are the matching tasks in your list:", PADDING));
        if (foundList.isEmpty()) {
            System.out.println(String.format("%s~~~~~Oops! There are no matching tasks!", PADDING));
        } else {
            int i = 1;
            for (Task task : foundList)
                System.out.println(String.format("%s%d. %s", PADDING, i++, task));
        }
        System.out.println(String.format("%s%s", PADDING, USELESS_LINE));
    }
}
