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
        System.out.println(padding + uselessLine + "\n" +
                padding + "Greetings! This is " + botName + ", and I am your friend!\n" +
                padding + "You don't have to be formal. Relax and tell me how I can help you\n" +
                padding + uselessLine);
    }

    private void echo(String str) {
        System.out.println(padding + uselessLine + "\n" +
                padding + str + "\n" +
                padding + uselessLine);
    }

    public static void byeBye() {
        System.out.println(padding + uselessLine + "\n" +
                padding + "Bye-bye. It was nice talking to you. See ya soon!\n" +
                padding + uselessLine);
    }

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

    public static void storeUserInput(Task task, ArrayList<Task> storedItems) {
        System.out.println(padding + uselessLine + "\n" +
                padding + addedPhrase + task + "\n" +
                padding + "Now you have " + storedItems.size() + " tasks\n" +
                padding + uselessLine);
    }

    public static void markItemAsDone(int pos, ArrayList<Task> storedItems) {
        System.out.println(padding + uselessLine + "\n" +
                padding + "Nice nice. I've marked the task as done for you.\n" +
                padding + "   " + storedItems.get(pos) + "\n" +
                padding + uselessLine);
    }

    public static void deleteItem(Task t, ArrayList<Task> storedItems) {
        System.out.println(String.format("%s%s\n%sI've removed this task for you\n%s   %s\n%sYou have %d tasks left\n%s%s",
                padding, uselessLine, padding, padding, t.toString(),padding, storedItems.size(), padding, uselessLine));
    }

    public static void blankInput() {
        System.out.println(padding + uselessLine + "\n" +
                padding + "Please type something. Don't leave it blank, plsss!\n" +
                padding + uselessLine);
    }

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
