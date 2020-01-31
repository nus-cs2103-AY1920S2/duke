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
    public void showWelcome() {
        String logo = INDENT + "  _____  __    __  _____" + NEWLINE
                + INDENT + " |  ___||  \\  /  ||  ___|" + NEWLINE
                + INDENT + " | |__   \\  \\/  / | |__" + NEWLINE
                + INDENT + " |  __|   |    |  |  __|" + NEWLINE
                + INDENT + " | |___  /  /\\  \\ | |___" + NEWLINE
                + INDENT + " |_____||__/  \\__\\|_____|" + NEWLINE;
        System.out.println(BORDER);
        System.out.println(logo);
        System.out.println(INDENT + "  Hello! I'm EXE, I'll execute anything on your command! :)");
        System.out.println(INDENT + "  What do you want to exe?");
        System.out.println(BORDER);
    }

    /**
     * Print error message whenever storage text file or directory cannot be
     * automatically generated.
     * Error message prompts user to create respective directory and file.
     */
    public void showStorageError() {
        System.out.println(addBorder("Unable to create storage file/directory\n" +
                "Please create a data directory and Duke.txt in it."));
    }

    /**
     * Print loading error if an error occurs upon booting up Chatbot.
     *
     * @param e DukeException class used to handle various specific Chatbot error.
     */
    public void showLoadingError(DukeException e) {
        System.out.println(addBorder(e.toString()));
    }

    /**
     * Print error message when user give wrong Date Time format.
     * Error message states the correct format user supposed to give.
     */
    public void showDateTimeException() {
        System.out.println(addBorder(INDENT + "Please type date in this format yyyy-MM-dd," + " including dashes"));
    }

    /**
     * Print error message whenever use give a non-existence task number.
     * Error message states size of user tasks list to aid user in
     * typing in range task number.
     *
     * @param listSize Size of actual user tasks list.
     */
    public void showIndexOutOfBoundException(int listSize) {
        System.out.println(addBorder(INDENT + "List only have " + listSize + " of tasks, please choose within range."));
    }

    /**
     * Print error message whenever user key in unsupported command.
     * Error message describes the problem with input and prompts user
     * to input in correct format.
     *
     * @param dukeEx DukeException targetted at specific error faced using Chatbot.
     */
    public void showStandardError(DukeException dukeEx) {
        System.out.println(addBorder(dukeEx.toString()));
    }

    /**
     * Print message notifying user of task deleted from existing list of tasks.
     *
     * @param deletedTask Task object of the task removed from existing list.
     * @param listSize    Total number of remaining task left in the list.
     */
    public void showDeleteMessage(Task deletedTask, int listSize) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("      Noted. I've removed this task:\n");
        stringBuilder.append(INDENT + "   " + deletedTask.toString() + "\n");
        stringBuilder.append(String.format("      Now you have %d task(s) in the list.", listSize));
        System.out.println(addBorder(stringBuilder.toString()));
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
     * Print all the Task objects in the List and numbering them according
     * to the order of task being added into the list. First in first out
     * format.
     *
     * @param listOfTask TaskList object of user existing tasks.
     */
    public void showListMessage(TaskList listOfTask) {
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
        System.out.println(addBorder(stringBuilder.toString()));
    }

    /**
     * Print a customised message whenever user adds a Task into the
     * list of user tasks.
     *
     * @param message String of Task describing it.
     * @param size    Number of tasks in the list after adding the current task.
     */
    public void showCustomiseMessage(String message, int size) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(INDENT + " ");
        stringBuilder.append(" Got it. I've added this task:" + NEWLINE);
        stringBuilder.append(INDENT + " ");
        stringBuilder.append("  " + message + NEWLINE);
        stringBuilder.append(INDENT + " ");
        stringBuilder.append(String.format(" Now you have %d tasks in list.", size));
        System.out.println(addBorder(stringBuilder.toString()));
    }

    /**
     * Print a message notifying user the task that have been mark as done.
     *
     * @param markedIcon String representing Task have been done.
     * @param task       Task object that have been marked as done.
     */
    public void showDoneMessage(String markedIcon, Task task) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(BORDER + NEWLINE);
        stringBuilder.append(INDENT);
        stringBuilder.append("  Very productive! I've slayed this task:" + NEWLINE);
        stringBuilder.append(INDENT);
        stringBuilder
                .append(String.format("   [%s] %s" + NEWLINE, markedIcon, task.getDescription() + " " + task.getTime()));
        stringBuilder.append(BORDER);
        System.out.println(stringBuilder.toString());
    }

    /**
     * Print a Good bye message when user exiting Chatbot.
     */
    public void showGoodByeMessage() {
        System.out.println(addBorder(INDENT + "  Goodbye and have a beautiful time!"));
    }


}
