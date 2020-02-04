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
    public static String greet() {
//        System.out.println(String.format("%s%s", PADDING, USELESS_LINE));
//        System.out.println(String.format("%sGreetings! This is %s, and I am your friend!", PADDING, BOT_NAME));
//        System.out.println(String.format("%sYou don't have to be formal. Relax and tell me how I can help you", PADDING));
//        System.out.println(String.format("%s%s", PADDING, USELESS_LINE));
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
     */
    public static String byeBye() {
//        System.out.println(String.format("%s%s", PADDING, USELESS_LINE));
//        System.out.println(String.format("%sBye-bye. It was nice talking to you. See ya soon!", PADDING));
//        System.out.println(String.format("%s%s", PADDING, USELESS_LINE));

        return "Bye-bye. It was nice talking to you. See ya soon!";
    }

    /**
     * Print all the tasks in storedItems to the screen.
     * @param storedItems
     */
    public static String listStoredItems(ArrayList<Task> storedItems) {
//        System.out.println(String.format("%s%s", PADDING, USELESS_LINE));

        if (storedItems.isEmpty()) {
//            System.out.println(String.format("%sYour list is empty!", PADDING));
            return "Your list is empty!";
        } else {
//            System.out.println(String.format("%sHere is your list", PADDING));
            StringBuilder returnString = new StringBuilder();
            returnString.append("Here is your list\n");
            int i = 1;
            for (Task task : storedItems) {
//                System.out.println(String.format("%s%d. %s", PADDING, i, task));
                returnString.append(String.format("%d. %s\n", i, task));
                i++;
            }

            return returnString.toString();
        }
//        System.out.println(String.format("%s%s", PADDING, USELESS_LINE));
    }

    /**
     * Print the status after successfully storing a task
     * @param task
     * @param storedItems
     */
    public static String storeUserInput(Task task, ArrayList<Task> storedItems) {
//        System.out.println(String.format("%s%s", PADDING, USELESS_LINE));
//        System.out.println(String.format("%s%s%s", PADDING, ADDED_PHRASE, task));
//        System.out.println(String.format("%sNow you have %d tasks", PADDING, storedItems.size()));
//        System.out.println(String.format("%s%s", PADDING, USELESS_LINE));

        StringBuilder returnString = new StringBuilder();
        returnString.append(String.format("%s\n", task));
        returnString.append(String.format("Now you have %d tasks", storedItems.size()));
        return returnString.toString();
    }

    /**
     * Print the status after successfully marking a task as Done
     * @param pos
     * @param storedItems
     */
    public static String markItemAsDone(int pos, ArrayList<Task> storedItems) {
//        System.out.println(String.format("%s%s", PADDING, USELESS_LINE));
//        System.out.println(String.format("%sNice nice. I've marked the task as done for you", PADDING));
//        System.out.println(String.format("%s   %s", PADDING, storedItems.get(pos)));
//        System.out.println(String.format("%s%s", PADDING, USELESS_LINE));

        StringBuilder returnString = new StringBuilder();
        returnString.append(String.format("Nice nice. I've marked the task as done for you\n"));
        returnString.append(String.format("   %s", storedItems.get(pos)));
        return returnString.toString();
    }

    /**
     * Print the status after successfully deleting a task
     * @param t
     * @param storedItems
     */
    public static String deleteItem(Task t, ArrayList<Task> storedItems) {
//        System.out.println(String.format("%s%s", PADDING, USELESS_LINE));
//        System.out.println(String.format("%sI've removed this task for you", PADDING));
//        System.out.println(String.format("%s   %s", PADDING, t));
//        System.out.println(String.format("%sYou have %d tasks left", PADDING, storedItems.size()));
//        System.out.println(String.format("%s%s", PADDING, USELESS_LINE));

        StringBuilder returnString = new StringBuilder();
        returnString.append(String.format("I've removed this task for you\n"));
        returnString.append(String.format("    %s\n", t));
        returnString.append(String.format("You have %d tasks left", storedItems.size()));
        return returnString.toString();
    }

    /**
     * React to a blank user input
     */
    public static String blankInput() {
//        System.out.println(String.format("%s%s", PADDING, USELESS_LINE));
//        System.out.println(String.format("%sPlease type something. Don't leave it blank, plsss!", PADDING));
//        System.out.println(String.format("%s%s", PADDING, USELESS_LINE));

        return "Please type something. Don't leave it blank, plsss!";
    }

    /**
     * Print the resulting foundList of a find request
     * @param foundList
     */
    public static String printFoundList(ArrayList<Task> foundList) {
        StringBuilder returnString = new StringBuilder();

//        System.out.println(String.format("%s%s", PADDING, USELESS_LINE));
//        System.out.println(String.format("%sHere are the matching tasks in your list:", PADDING));
        returnString.append(String.format("Here are the matching tasks in your list:\n"));
        if (foundList.isEmpty()) {
//            System.out.println(String.format("%s~~~~~Oops! There are no matching tasks!", PADDING));
            returnString.append("%s~~~~~Oops! There are no matching tasks!");
        } else {
            int i = 1;
            for (Task task : foundList) {
//                System.out.println(String.format("%s%d. %s", PADDING, i++, task));
                returnString.append(String.format("%d. %s\n", i++, task));
            }
        }
//        System.out.println(String.format("%s%s", PADDING, USELESS_LINE));
        return  returnString.toString();
    }
}
