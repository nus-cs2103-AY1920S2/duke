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
        System.out.println(padding + uselessLine + "\n" +
                padding + "Greetings! This is " + botName + ", and I am your friend!\n" +
                padding + "You don't have to be formal. Relax and tell me how I can help you\n" +
                padding + uselessLine);
    }

    /**
     * Repeat whatever the input is, print it to the screen.
     * @param str
     */
    private void echo(String str) {
        System.out.println(padding + uselessLine + "\n" +
                padding + str + "\n" +
                padding + uselessLine);
    }

    /**
     * Print the bye-bye line to the screen.
     * Last thing the user will see before the application shuts off.
     */
    public static void byeBye() {
        System.out.println(padding + uselessLine + "\n" +
                padding + "Bye-bye. It was nice talking to you. See ya soon!\n" +
                padding + uselessLine);
    }

    /**
     * Print all the tasks in storedItems to the screen.
     * @param storedItems
     */
    public static void listStoredItems(ArrayList<Task> storedItems) {
        System.out.println(padding + uselessLine);
        if (storedItems.isEmpty()) {
            System.out.println(padding + "Your list is empty!");
        } else {
            System.out.println(padding + "Here is your list:");
            int i = 1;
            for (Task task : storedItems) {
                System.out.println(padding + i + ". " + task);
                i++;
            }
        }
        System.out.println(padding + uselessLine);
    }

    /**
     * Print the status after successfully storing a task
     * @param task
     * @param storedItems
     */
    public static void storeUserInput(Task task, ArrayList<Task> storedItems) {
        System.out.println(padding + uselessLine + "\n" +
                padding + addedPhrase + task + "\n" +
                padding + "Now you have " + storedItems.size() + " tasks\n" +
                padding + uselessLine);
    }

    /**
     * Print the status after successfully marking a task as Done
     * @param pos
     * @param storedItems
     */
    public static void markItemAsDone(int pos, ArrayList<Task> storedItems) {
        System.out.println(padding + uselessLine + "\n" +
                padding + "Nice nice. I've marked the task as done for you.\n" +
                padding + "   " + storedItems.get(pos) + "\n" +
                padding + uselessLine);
    }

    /**
     * Print the status after successfully deleting a task
     * @param t
     * @param storedItems
     */
    public static void deleteItem(Task t, ArrayList<Task> storedItems) {
        System.out.println(String.format("%s%s\n%sI've removed this task for you\n%s   %s\n%sYou have %d tasks left\n%s%s",
                padding, uselessLine, padding, padding, t.toString(),padding, storedItems.size(), padding, uselessLine));
    }

    /**
     * React to a blank user input
     */
    public static void blankInput() {
        System.out.println(padding + uselessLine + "\n" +
                padding + "Please type something. Don't leave it blank, plsss!\n" +
                padding + uselessLine);
    }
}
