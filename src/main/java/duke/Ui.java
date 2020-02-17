package duke;

import duke.command.CommandList;
import duke.command.Delete;
import duke.command.Done;
import duke.command.Exit;
import duke.command.List;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.ToDo;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;
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
        stringBuilder.append(INDENT + "  Type help to see what I can do for you!" + NEWLINE);
        stringBuilder.append(INDENT + "  Type help follow by command keyword to see input format! :D");
        return stringBuilder.toString();
    }

    public String showGuiWelcome() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Hello! I'm EXE, I'll execute anything on your command! :)"
                + NEWLINE);
        stringBuilder.append("Type help to see what I can do for you!" + NEWLINE);
        stringBuilder.append("Type help follow by command keyword to see input format! :D");
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
        return ("Please type date in this format yyyy-MM-dd," + " including dashes");
    }

    /**
     * Print error message whenever use give a non-existence task number.
     * Error message states size of user tasks list to aid user in
     * typing in range task number.
     *
     * @param listSize Size of actual user tasks list.
     */
    public String showIndexOutOfBoundException(int listSize) {
        return ("duke.command.List only have " + listSize + " of tasks, please choose within range.");
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
        stringBuilder.append("Noted. I've removed this task:\n");
        stringBuilder.append(deletedTask.toString() + "\n");
        stringBuilder.append(String.format("Now you have %d task(s) in the list.", listSize));
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
        stringBuilder.append("Here are the tasks in your list:" + NEWLINE);
        Task currentTask;
        for (int i = 0; i < listOfTask.getSize(); i++) {
            currentTask = listOfTask.getTask(i);
            stringBuilder.append(String.format("%d. %s" + NEWLINE, i + 1, currentTask.toString()));
        }
        stringBuilder
                .append(String.format("In case you can't count! you have %d task(s) in your list",
                        listOfTask.getSize()));
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
        stringBuilder.append("Got it. I've added this task:" + NEWLINE);
        stringBuilder.append(message + NEWLINE);
        stringBuilder.append(String.format("Now you have %d tasks in list.", size));
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
        stringBuilder.append("Very productive! I've slayed this task:" + NEWLINE);
        stringBuilder
                .append(String.format("[%s] %s", markedIcon, task.getDescription() + " " + task.getTime()));
        return stringBuilder.toString();
    }

    /**
     * Print a Good bye message when user exiting Chatbot.
     */
    public String showGoodByeMessage() {
        return ("Goodbye and have a beautiful time!");
    }

    public String showHelpMessage() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Below are the command(s) available:");
        stringBuilder.append(NEWLINE);
        stringBuilder.append(getListOfCommandsName());
        return stringBuilder.toString();
    }

    public String showSpecificHelpMessage(String specificHelp) throws DukeException {
        CommandList commandValue;
        try {
            commandValue = CommandList.valueOf(specificHelp.toUpperCase());
        } catch (IllegalArgumentException ex) {
            throw new DukeException("Invalid command", DukeErrorType.INVALID_COMMAND);
        }
        switch (commandValue) {
        case BYE:
            return new Exit().getHelpFormat();
        case DEADLINE:
            return new Deadline().getHelpFormat();
        case DONE:
            return new Done().getHelpFormat();
        case EVENT:
            return new Event().getHelpFormat();
        case LIST:
            return new List().getHelpFormat();
        case TODO:
            return new ToDo().getHelpFormat();
        case DELETE:
            return new Delete().getHelpFormat();
        default:
            throw new DukeException("Invalid command", DukeErrorType.INVALID_COMMAND);
        }
    }

    public String getListOfCommandsName() {
        String newline = System.lineSeparator();
        StringBuilder stringBuilder = new StringBuilder();
        CommandList[] commandsList = CommandList.values();
        for (CommandList commands : commandsList) {
            stringBuilder.append(commands.toString().toLowerCase());
            stringBuilder.append(newline);
        }
        return stringBuilder.toString();
    }

}
