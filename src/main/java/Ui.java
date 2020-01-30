import java.util.Scanner;

public class Ui {

    static String commandNotFound = "Oops! Command not found!";
    static String taskNeedsName = "Oops! This task needs a name!";
    static String taskNeedsDateTime = "Oops! This task needs a date/time!";
    static String wrongDateTimeFormat = "Oops! The date/time needs to be DD/MM/YYYY HHMM (24hr) format!";
    static String noTaskFound = "Oops! Task not found in the list!";
    static String noTaskInList = "Yay! There are no tasks in your list!";
    static String displayTaskList = "Here are your tasks in your list:";
    static String displayMatchingTaskList = "Here are your tasks that matched your query:";
    static String noMatchingTaskInList = "Oops! There are no tasks that matches your query!";
    static String changesSaved = "Changes saved!";
    static String markedTaskDone = "Marked this task as done:\n    ";
    static String taskAlreadyDone = "Oops! This task was already marked as done!";
    static String newTaskMessage = "New task has been added:\n    ";
    static String deleteTaskMessage = "Removed this task:\n    ";
    static String welcomeMessage = "-------------------------------------\nWelcome! What can I do for you today?";
    static String exitMessage = "Thank you for using Duke.\nHave a nice day!\n";

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

        if (choice.equals("Y") || choice.equals("y")) {
            try {
                Storage.save(tasklist);
                System.out.println(changesSaved);
            } catch (Exception e) {
                System.out.println("Oops! Unable to write to file due to " + e + "!");
            }

            return false;
        } else {
            return !(choice.equals("N") || choice.equals("n"));
        }
    }

    public static String getNextCommand(Scanner scanner, TaskList tasklist) {
        System.out.print("> ");
        String input = scanner.nextLine().strip();
        String[] command = Parser.parseInput(input);
        return Parser.processCommand(command, tasklist);
    }

    public static String taskCountMessage(int listSize) {
        return "\nNow you have "+ listSize + (listSize == 1 ? " task" : " tasks") + " in the list.";
    }

}