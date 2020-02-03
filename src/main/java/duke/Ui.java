package duke;

import duke.task.Task;
import duke.task.TaskList;

import java.util.Scanner;

/**
 * Represents the interaction between the User and
 * the Personal Assistant Chatbot EXE.
 *
 * @author Kenny Ho
 */
public class Ui {

    /**
     * Common Strings used for enhancing UI with user
     */
    public final static String NEWLINE = System.lineSeparator();
    public final static String INDENT = "    ";
    public final static String BORDER = INDENT + "____________________________________________________________";

    public Ui() {
    }

    /**
     * Print welcome message upon boot up.
     */
    public String showWelcome() {
        StringBuilder stringBuilder = new StringBuilder();
        String logo = INDENT + "  _____  __    __  _____" + NEWLINE
                + INDENT + " |  ___||  \\  /  ||  ___|" + NEWLINE
                + INDENT + " | |__   \\  \\/  / | |__" + NEWLINE
                + INDENT + " |  __|   |    |  |  __|" + NEWLINE
                + INDENT + " | |___  /  /\\  \\ | |___" + NEWLINE
                + INDENT + " |_____||__/  \\__\\|_____|" + NEWLINE;
        stringBuilder.append(logo);
        stringBuilder.append(INDENT + "  Hello! I'm EXE, I'll execute anything on your command! :)" + NEWLINE);
        stringBuilder.append(INDENT + "  What do you want to exe?");
        return stringBuilder.toString();
    }

    /**
     * Print error message whenever storage text file or directory cannot be
     * automatically generated.
     * Error message prompts user to create respective directory and file.
     */
    public void showStorageError() {
        System.out.println(addBorder("Unable to create storage file/directory\n" +
                "Please create a data directory and duke.Duke.txt in it."));
    }

    /**
     * Print loading error if an error occurs upon booting up Chatbot.
     *
     * @param e duke.DukeException class used to handle various specific Chatbot error.
     */
    public void showLoadingError(DukeException e) {
        System.out.println(addBorder(e.toString()));
    }

    /**
     * Print error message when user give wrong Date Time format.
     * Error message states the correct format user supposed to give.
     */
    public String showDateTimeException() {
        return (INDENT + "Please type date in this format yyyy-MM-dd," + " including dashes");
    }

    /**
     * Print error message whenever use give a non-existence task number.
     * Error message states size of user tasks list to aid user in
     * typing in range task number.
     *
     * @param listSize Size of actual user tasks list.
     */
    public String showIndexOutOfBoundException(int listSize) {
        return (INDENT + "duke.command.List only have " + listSize + " of tasks, please choose within range.");
    }

    /**
     * Print error message whenever user key in unsupported command.
     * Error message describes the problem with input and prompts user
     * to input in correct format.
     *
     * @param dukeEx duke.DukeException targetted at specific error faced using Chatbot.
     */
    public String showStandardError(DukeException dukeEx) {
        return (dukeEx.toString());
    }

    /**
     * Print message notifying user of task deleted from existing list of tasks.
     *
     * @param deletedTask duke.task.Task object of the task removed from existing list.
     * @param listSize    Total number of remaining task left in the list.
     */
    public String showDeleteMessage(Task deletedTask, int listSize) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("      Noted. I've removed this task:\n");
        stringBuilder.append(INDENT + "   " + deletedTask.toString() + "\n");
        stringBuilder.append(String.format("      Now you have %d task(s) in the list.", listSize));
        return (stringBuilder.toString());
    }

    /**
     * A wrapper method to read user input from terminal.
     *
     * @return user input in the form of String object.
     */
    public String readCommand() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    /**
     * Returns a message with top and bottom border appended to it.
     * Enhancing UI.
     *
     * @param message Message in String object to be appended with border.
     * @return String object of the final message to be printed.
     */
    public String addBorder(String message) {
        return BORDER + NEWLINE + message + NEWLINE + BORDER;
    }

    /**
     * Print all the duke.task.Task objects in the duke.command.List and numbering them according
     * to the order of task being added into the list. First in first out
     * format.
     *
     * @param listOfTask duke.task.TaskList object of user existing tasks.
     */
    public String showListMessage(TaskList listOfTask) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(INDENT + "  Here are the tasks in your list:" + NEWLINE);
        Task currentTask;
        for (int i = 0; i < listOfTask.getSize(); i++) {
            currentTask = listOfTask.getTask(i);
            stringBuilder.append(INDENT + "  ");
            stringBuilder.append(String.format(" %d. %s" + NEWLINE, i + 1, currentTask.toString()));
        }
        stringBuilder
                .append(String.format("%s  In case you can't count! you have %d task(s) in your list",
                        INDENT, listOfTask.getSize()));
        return (stringBuilder.toString());
    }

    /**
     * Print a customised message whenever user adds a duke.task.Task into the
     * list of user tasks.
     *
     * @param message String of duke.task.Task describing it.
     * @param size    Number of tasks in the list after adding the current task.
     */
    public String showCustomiseMessage(String message, int size) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(INDENT + " ");
        stringBuilder.append(" Got it. I've added this task:" + NEWLINE);
        stringBuilder.append(INDENT + " ");
        stringBuilder.append("  " + message + NEWLINE);
        stringBuilder.append(INDENT + " ");
        stringBuilder.append(String.format(" Now you have %d tasks in list.", size));
        return (stringBuilder.toString());
    }

    /**
     * Print a message notifying user the task that have been mark as done.
     *
     * @param markedIcon String representing duke.task.Task have been done.
     * @param task       duke.task.Task object that have been marked as done.
     */
    public String showDoneMessage(String markedIcon, Task task) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(INDENT);
        stringBuilder.append("  Very productive! I've slayed this task:" + NEWLINE);
        stringBuilder.append(INDENT);
        stringBuilder
                .append(String.format("   [%s] %s", markedIcon, task.getDescription() + " " + task.getTime()));
        return stringBuilder.toString();
    }

    /**
     * Print a Good bye message when user exiting Chatbot.
     */
    public String showGoodByeMessage() {
        return (INDENT + "  Goodbye and have a beautiful time!");
    }


}
