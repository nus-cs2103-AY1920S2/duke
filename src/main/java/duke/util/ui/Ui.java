package duke.util.ui;

import duke.task.Task;
import java.util.ArrayList;

public class Ui {
    public static final String BOT_NAME = "Duke";

    private static String PADDING = "       ";
    private static String USELESS_LINE = "-------------------------------------------------------------------------------------";

    /**
     * Print the greeting lines to the screen.
     * The first thing the user will see.
     * @return The printable Ui greeting
     */
    public static String greet() {
        StringBuilder returnString = new StringBuilder();

        returnString.append(String.format("Greetings! This is %s, and I am your friend!\n", BOT_NAME));
        returnString.append(String.format("You don't have to be formal. Relax and tell me how I can help you\n"));

        return returnString.toString();
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
     * @return The printable Ui good-bye.
     */
    public static String byeBye() {
        return "Bye-bye. It was nice talking to you. See ya soon!";
    }

    /**
     * Print all the tasks in storedItems to the screen.
     * @param storedItems
     * @return The String with all the stored tasks.
     */
    public static String listStoredItems(ArrayList<Task> storedItems) {
        if (storedItems.isEmpty()) {
            return "Your list is empty!";
        } else {
            StringBuilder returnString = new StringBuilder();
            returnString.append("Here is your list\n");
            int i = 1;
            for (Task task : storedItems) {
                returnString.append(String.format("%d. %s\n", i, task));
                i++;
            }

            return returnString.toString();
        }
    }

    /**
     * Print the status after successfully storing a task.
     * @param task
     * @param storedItems
     * @return The Ui String that signals storing job done.
     */
    public static String storeUserInput(Task task, ArrayList<Task> storedItems) {
        StringBuilder returnString = new StringBuilder();
        returnString.append(String.format("%s\n", task));
        returnString.append(String.format("Now you have %d tasks", storedItems.size()));
        return returnString.toString();
    }

    /**
     * Print the status after successfully marking a task as Done.
     * @param pos
     * @param storedItems
     * @param alreadyDone
     */
    public static String markItemAsDone(int pos, ArrayList<Task> storedItems, boolean alreadyDone) {
        StringBuilder returnString = new StringBuilder();

        if (alreadyDone) {
            returnString.append(String.format("Task %d has already been done!\n", pos + 1));
        } else {
            returnString.append(String.format("Nice nice. I've marked the task as done for you\n"));
        }
        returnString.append(String.format("   %s", storedItems.get(pos)));

        return returnString.toString();
    }

    /**
     * Print the status after successfully deleting a task
     * @param t
     * @param storedItems
     * @return The Ui String that signals deleting job done.
     */
    public static String deleteItem(Task t, ArrayList<Task> storedItems) {
        StringBuilder returnString = new StringBuilder();
        returnString.append(String.format("I've removed this task for you\n"));
        returnString.append(String.format("    %s\n", t));
        returnString.append(String.format("You have %d tasks left", storedItems.size()));
        return returnString.toString();
    }

    /**
     * React to a blank user input.
     * @return The Ui String telling the user to type something.
     */
    public static String blankInput() {
        return "Please type something. Don't leave it blank, plsss!";
    }

    /**
     * Print the resulting foundList of a find request.
     * @param foundList
     * @return The Ui String containing all found results.
     */
    public static String printFoundList(ArrayList<Task> foundList) {
        StringBuilder returnString = new StringBuilder();
        returnString.append(String.format("Here are the matching tasks in your list:\n"));
        if (foundList.isEmpty()) {
            returnString.append("~~~~~Oops! There are no matching tasks!");
        } else {
            int i = 1;
            for (Task task : foundList) {
                returnString.append(String.format("%d. %s\n", i++, task));
            }
        }
        return  returnString.toString();
    }
}
