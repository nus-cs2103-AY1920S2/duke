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

    public static void greet() {
        System.out.println(String.format("%s%s", padding, uselessLine));
        System.out.println(String.format("%sGreetings! This is %s, and I am your friend!", padding, botName));
        System.out.println(String.format("%sYou don't have to be formal. Relax and tell me how I can help you", padding));
        System.out.println(String.format("%s%s", padding, uselessLine));
    }

    private void echo(String str) {
        System.out.println(String.format("%s%s", padding, uselessLine));
        System.out.println(String.format("%s%s", padding, str));
        System.out.println(String.format("%s%s", padding, uselessLine));
    }

    public static void byeBye() {
        System.out.println(String.format("%s%s", padding, uselessLine));
        System.out.println(String.format("%sBye-bye. It was nice talking to you. See ya soon!", padding));
        System.out.println(String.format("%s%s", padding, uselessLine));
    }

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

    public static void storeUserInput(Task task, ArrayList<Task> storedItems) {
        System.out.println(String.format("%s%s", padding, uselessLine));
        System.out.println(String.format("%s%s%s", padding, addedPhrase, task));
        System.out.println(String.format("%sNow you have %d tasks", padding, storedItems.size()));
        System.out.println(String.format("%s%s", padding, uselessLine));
    }

    public static void markItemAsDone(int pos, ArrayList<Task> storedItems) {
        System.out.println(String.format("%s%s", padding, uselessLine));
        System.out.println(String.format("%sNice nice. I've marked the task as done for you", padding));
        System.out.println(String.format("%s   %s", padding, storedItems.get(pos)));
        System.out.println(String.format("%s%s", padding, uselessLine));
    }

    public static void deleteItem(Task t, ArrayList<Task> storedItems) {
        System.out.println(String.format("%s%s", padding, uselessLine));
        System.out.println(String.format("%sI've removed this task for you", padding));
        System.out.println(String.format("%s   %s", padding, t));
        System.out.println(String.format("%sYou have %d tasks left", padding, storedItems.size()));
        System.out.println(String.format("%s%s", padding, uselessLine));
    }

    public static void blankInput() {
        System.out.println(String.format("%s%s", padding, uselessLine));
        System.out.println(String.format("%sPlease type something. Don't leave it blank, plsss!", padding));
        System.out.println(String.format("%s%s", padding, uselessLine));
    }
}
