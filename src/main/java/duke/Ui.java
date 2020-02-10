package duke;

import java.util.Scanner;

public class Ui {

    static final String COMMAND_NOT_FOUND = "Oops! Command not found!";
    static final String TASK_NEEDS_NAME = "Oops! This task needs a name!";
    static final String TASK_NEEDS_DATE_TIME = "Oops! This task needs a date/time!";
    static final String WRONG_DATE_TIME_FORMAT = "Oops! The date/time needs to be DD/MM/YYYY HHMM (24hr) format!";
    static final String NO_TASK_FOUND = "Oops! Task not found in the list!";
    static final String NO_TASK_IN_LIST = "Yay! There are no tasks in your list!";
    static final String DISPLAY_TASK_LIST = "Here are your tasks in your list:";
    static final String DISPLAY_MATCHING_TASK_LIST = "Here are your tasks that matched your query:";
    static final String NO_MATCHING_TASK_IN_LIST = "Oops! There are no tasks that matches your query!";
    static final String CHANGES_SAVED = "Changes saved!";
    static final String MARKED_AS_DONE = "Marked this task as done:\n    ";
    static final String TASK_ALREADY_DONE = "Oops! This task was already marked as done!";
    static final String ADDED_NEW_TASK = "New task has been added:\n    ";
    static final String DELETED_TASK = "Removed this task:\n    ";
    static final String WELCOME_MESSAGE = "-------------------------------------\nWelcome! What can I do for you today?";
    static final String EXIT_MESSAGE = "Thank you for using Duke.\nHave a nice day!\n";

    public static void showLogo() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
    }

    public static boolean askBeforeQuitting(Scanner scanner, TaskList tasklist) {
        System.out.println("Save before Quitting?\nY: Save Changes\nN: Discard Changes\n" +
                "Press any other key to Cancel and Return");
        String choice = scanner.nextLine();

        if (choice.equalsIgnoreCase("Y")) {
            try {
                Storage.save(tasklist);
                System.out.println(CHANGES_SAVED);
            } catch (Exception e) {
                System.out.println("Oops! Unable to write to file due to " + e + "!");
            }

            return false;
        } else {
            return !(choice.equalsIgnoreCase("N"));
        }
    }

    public static String processNextCommand(Scanner scanner, TaskList tasklist) {
        System.out.print("> ");
        String input = scanner.nextLine().strip();
        String[] command = Parser.parseInput(input);
        return Parser.processCommand(command, tasklist);
    }

    public static String processNextCommand(String input, TaskList tasklist) {
        String[] command = Parser.parseInput(input.strip());
        return Parser.processCommand(command, tasklist);
    }

    public static String taskCountMessage(int listSize) {
        return "\nNow you have "+ listSize + (listSize == 1 ? " task" : " tasks") + " in the list.";
    }

}