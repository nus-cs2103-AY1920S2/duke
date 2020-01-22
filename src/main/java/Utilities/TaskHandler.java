package Utilities;

import Tasks.Deadline;
import Tasks.Event;
import Tasks.Task;
import Tasks.ToDo;

import java.util.Scanner;
import java.util.ArrayList;

public class TaskHandler {

    /**
     * for storing Tasks
     */
    private ArrayList<Task> allTasks = new ArrayList<>();

    /**
     * Utilised in printing
     */
    private final String horizontalLine = "____________________________________________________________";

    /**
     * Serves the user to do the bulk of the work in process individual commands
     */
    public void serveUser() {

        // Scanner object to take in user input
        Scanner io = new Scanner(System.in);

        while (io.hasNextLine()) {

            // obtain String command and split it to find the intention of the user (first word)
            String command = io.nextLine();
            String[] commandWords = command.split("\\s");

            switch (commandWords[0]) {

            case "bye":
                return;

            case "list":
                printAllTasks();
                break;

            case "done":
                // user inputs will be "done _____"
                // Take only the very next token which must be an integer
                if (commandWords.length > 1) {
                    // this is a valid command
                    if (Integer.valueOf(commandWords[1]) <= allTasks.size()) {
                        // this valid command has a valid number
                        Task currentTaskDone = allTasks.get(Integer.valueOf(commandWords[1]) - 1);
                        doTask(currentTaskDone);
                    } else {
                        printUnsure();
                    }
                } else {
                    printAlreadyDone();
                }
                break;

            case "todo":
                addNewToDo(command);
                break;

            case "deadline":
                addNewDeadline(command);
                break;

            case "event":
                addNewEvent(command);
                break;

            default:
                printUnsure();

            }

        }
    }

    /**
     * Adds a new Task for user to do
     *
     * @param command basic raw information entered to create the Task
     */
    private final void addNewToDo(String command) {
        String toDoCommand = command.substring("todo".length() + 1);
        addTaskToStored(new ToDo(toDoCommand));
    }

    /**
     * Adds a new Deadline Task
     *
     * @param command basic raw information entered to create the Task
     */
    private final void addNewDeadline(String command) {
        // need to identify deadline limit
        String deadlineLimit = getRestriction("/by", command);
        String deadlineCommand = getCommand("deadline", "/by", command);
        addTaskToStored(new Deadline(deadlineCommand, deadlineLimit));
    }

    /**
     * Adds a new Event Task
     *
     * @param command basic raw information entered to create the Task
     */
    private final void addNewEvent(String command) {
        // need to identify event time
        String eventTime = getRestriction("/at", command);
        String eventCommand = getCommand("event", "/at", command);
        addTaskToStored(new Event(eventCommand, eventTime));
    }

    /**
     * Gets Deadline limit or Event time based on keyword (which includes "/")
     *
     * @param keyword a String beginning with "/" and a keyword which separates the Task and Restriction of Time
     * @param command basic raw information entered to create the Task
     * @return the String representing the Restriction on the Task
     */
    private final String getRestriction(String keyword, String command) {
        int indexOfKeyword = indexSearchInString(keyword, command);
        return command.substring(indexOfKeyword + keyword.length() + 1);
    }

    /**
     * Gets Deadline or Event Task description to do
     *
     * @param taskType type of task
     * @param keyword a String beginning with "/" and a keyword which separates the Task and Restriction of Time
     * @param command basic raw information entered to create the Task
     * @return String representation of description of the command of the Task
     */
    private final String getCommand(String taskType, String keyword, String command) {
        int indexOfTimeKeyword = indexSearchInString(keyword, command) - 1; // due to whitespace
        int startIndex = taskType.length() + 1; // due to whitespace
        return command.substring(startIndex, indexOfTimeKeyword);
    }

    /**
     * Finds the first occurrence of keywords in a search space
     *
     * @param keyword a String beginning with "/" and a keyword which separates the Task and Restriction of Time
     * @param searchSpace the full String to search from
     * @return index in which the keyword appears first
     */
    private final int indexSearchInString(String keyword, String searchSpace) {
        return searchSpace.indexOf(keyword);
    }

    /**
     * Marks task complete and prints completion message
     *
     * @param t Task to be done
     */
    private final void doTask(Task t) {
        printTaskComplete(t);
        t.doTask(); // task marked as complete
    }

    /**
     * Adds the Task to store in the ArrayList, and prints added message
     *
     * @param t Task to be stored
     */
    private final void addTaskToStored(Task t) {
        allTasks.add(t);
        t.taskAddedMessage();
    }

    /**
     * Prints all tasks, their number order, and their completion for list command
     */
    private final void printAllTasks() {
        printLine();
        if (allTasks.isEmpty()) {
            print("No tasks! You're good to go!\n"
                + "Please exit using 'bye' command. :)");
        }
        for (int i = 0; i < allTasks.size(); i++) {
            printTaskFromStored(i);
        }
        printLine();
    }

    /**
     * Prints a response to the done command after doTask completes
     *
     * @param t Task that has been completed via doTask method
     */
    private final void printTaskComplete(Task t) {
        printLine();
        if (t.getIsDone()) {
            print("That's already done, try another. Or did you make a careless mistake? XD");
        } else {
            print("Nice! The following task has been marked completed:\n"
                    + "===> [\u2713] " + t + " <===");
        }
        printLine();
    }

    /**
     * Prints individual task with current completion status
     *
     * @param i index of storage of the Task in the container/collection
     */
    private final void printTaskFromStored(int i) {
        String tickOrCross = allTasks.get(i).obtainStatusIcon();
        print(String.valueOf(i+1) + ". [" + tickOrCross + "] " + allTasks.get(i));
    }

    /**
     * Prompts user to re-enter command input
     */
    private final void printUnsure() {
        printLine();
        print("Please enter another command, this time, with "
                + "a known command word/valid number;\n"
                + "I'm not quite sure I understand you :(");
        printLine();
    }

    /**
     * Prompts user to enter another command as the current Task is done
     */
    private final void printAlreadyDone() {
        printLine();
        print("Seems like you are kinda tired. Please remember to define a Task Number!"
                + "Or, you could also take a break. :)");
        printLine();
    }

    /**
     * Prints a horizontal formatting line
     */
    private final void printLine() {
        print(horizontalLine);
    }

    /**
     * Prints a given String
     *
     * @param s String to be printed
     */
    private final void print(String s) {
        System.out.println(s);
    }

}
