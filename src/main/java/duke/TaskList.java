package duke;

import duke.exceptions.DeadlineException;
import duke.exceptions.DeleteException;
import duke.exceptions.DoneException;
import duke.exceptions.EmptyTaskListException;
import duke.exceptions.EventException;
import duke.exceptions.InputUnclearException;
import duke.exceptions.ToDoException;

import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.ToDo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * TaskList class which mainly stores all the Tasks in an ArrayList of Tasks and handles related methods.
 */
public class TaskList {

    /**
     * for storing Tasks.
     */
    private ArrayList<Task> allTasks;

    /**
     * Constructor for TaskList.
     */
    public TaskList() {
        this.allTasks = new ArrayList<>();
    }

    /**
     * Serves the user to do the bulk of the work in process individual commands.
     */
    public void serveUser() {

        // Scanner object to take in user input
        Scanner io = new Scanner(System.in);

        // Parser to make sense of various commands
        Parser logic = new Parser();

        // Accept and Respond to User Input
        while (io.hasNextLine()) {

            try {
                // obtain String command and split it to find the intention of the user (first word)
                String command = io.nextLine();
                String[] commandWords = command.split("\\s");

                // Depending on command, we process the command
                // Exceptions will be thrown respectively
                switch (commandWords[0]) {

                case "bye":
                    return;

                case "list":
                    printAllTasks();
                    break;

                case "delete":
                    deleteTask(command);
                    break;

                case "done":
                    // user inputs will be "done _____"
                    // Take only the very next token which must be an integer
                    if (logic.satisfiesMinimumDoneCommandLength(commandWords)) {
                        // this is a valid command
                        if (logic.determineIfValidDoneCommand(commandWords, this.sizeOf())) {
                            // this valid command has a valid number, so, mark as done
                            doTask(Integer.parseInt(commandWords[1]));
                        } else {
                            // invalid input: attempting to do a non-existent task
                            throw new InputUnclearException("");
                        }
                    } else {
                        // invalid input
                        throw new DoneException("");
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
                    throw new InputUnclearException("");

                }
            } catch (DoneException | DeadlineException | DeleteException | EmptyTaskListException
                | EventException | InputUnclearException | ToDoException e) {
                Ui.printExceptionMessage(e.toString());
            }

        }
    }

    /**
     * Deletes the given Task based on the command's keyword (a number representing index in ArrayList).
     *
     * @param command raw text containing all information given to delete the Task.
     * @throws InputUnclearException where deleting task not possible due to incorrect command/index entered by user.
     */
    private void deleteTask(String command) throws DeleteException {
        try {
            int indexToRemove = Integer.parseInt(command.substring("delete ".length())) - 1; // due to zero-indexing
            Task removedTask = allTasks.remove(indexToRemove);
            printTaskRemoval(indexToRemove, removedTask);
            Storage.saveChanges(this);
        } catch (Exception e) {
            throw new DeleteException("");
        }
    }

    /**
     * Adds a new Task for user to do, specifying the actual action as Task command.
     *
     * @param command basic raw information entered to create the Task.
     * @throws ToDoException Exception arising from creating To-Do Task due to wrong inputs.
     */
    private void addNewToDo(String command) throws ToDoException {
        if (command.length() < 6) {
            throw new ToDoException("");
        }
        String toDoCommand = command.substring("todo".length() + 1);
        addTaskToStored(new ToDo(toDoCommand));
        Storage.saveChanges(this);
    }

    /**
     * Adds a new Deadline Task, specifying the actual Task command and time limit.
     *
     * @param command basic raw information entered to create the Task.
     * @throws DeadlineException Exception arising from creating Deadline Task due to wrong inputs.
     */
    private void addNewDeadline(String command) throws DeadlineException {
        try {
            // need to identify deadline limit
            String dateDetails = getRestriction("/by", command);
            String deadlineLimit = getPresentableDate(dateDetails);

            // filter to obtain command
            String deadlineCommand = getCommand("deadline", "/by", command);

            addTaskToStored(new Deadline(deadlineCommand, deadlineLimit));
            Storage.saveChanges(this);
        } catch (Exception e) {
            throw new DeadlineException("");
        }
    }

    /**
     * Adds a new Event Task, specifying the actual Task command and the time duration.
     *
     * @param command basic raw information entered to create the Task.
     * @throws EventException Exception arising from Event Task creation due to wrong inputs.
     */
    private void addNewEvent(String command) throws EventException {
        try {
            // need to identify event time
            String dateDetails = getRestriction("/at", command);
            String eventTime = getPresentableDate(dateDetails);

            // filter to obtain command
            String eventCommand = getCommand("event", "/at", command);

            addTaskToStored(new Event(eventCommand, eventTime));
            Storage.saveChanges(this);
        } catch (Exception e) {
            throw new EventException("");
        }
    }

    /**
     * Provides the date in a presentable, "Month (in English) Day Year" format.
     */
    private String getPresentableDate(String dateDetails) {
        String punctuation = String.valueOf(dateDetails.charAt(4)); // due to "/at " or "/by "
        String[] date = dateDetails.split(punctuation); // 0: Year, 1: Month, 2: Day
        LocalDate ld = LocalDate.of(Integer.parseInt(date[0]),
                Integer.parseInt(date[1]),
                Integer.parseInt(date[2]));
        return ld.format(DateTimeFormatter.ofPattern("MMM d yyyy"));

    }

    /**
     * Gets Deadline limit or Event time based on keyword (which includes "/").
     * The result is simply the time/date in a String.
     *
     * @param keyword a String beginning with "/" and a keyword which separates the Task and Restriction of Time.
     * @param command basic raw information entered to create the Task.
     * @return the String representing the Restriction on the Task.
     */
    private String getRestriction(String keyword, String command) {
        int indexOfKeyword = indexSearchInString(keyword, command);
        return command.substring(indexOfKeyword + keyword.length() + 1); // +1 due to whitespace
    }

    /**
     * Gets Deadline or Event Task description to do.
     *
     * @param taskType type of task.
     * @param keyword a String beginning with "/" and a keyword which separates the Task and Restriction of Time.
     * @param command basic raw information entered to create the Task.
     * @return String representation of description of the command of the Task.
     */
    private String getCommand(String taskType, String keyword, String command) {
        int indexOfTimeKeyword = indexSearchInString(keyword, command) - 1; // due to whitespace
        int startIndex = taskType.length() + 1; // due to whitespace
        return command.substring(startIndex, indexOfTimeKeyword);
    }

    /**
     * Finds the first occurrence of keywords in a search space.
     *
     * @param keyword a String beginning with "/" and a keyword which separates the Task and Restriction of Time.
     * @param searchSpace the full String to search from.
     * @return index in which the keyword appears first.
     */
    private int indexSearchInString(String keyword, String searchSpace) {
        return searchSpace.indexOf(keyword);
    }

    /**
     * Marks task complete and prints completion message.
     *
     * @param i Index of the Task to be done.
     */
    private void doTask(int i) {
        Task t = allTasks.get(i - 1); // due to 0 indexing
        printTaskComplete(t);
        t.doTask(); // task marked as complete
        Storage.saveChanges(this);
    }

    /**
     * Adds the Task to store in the ArrayList, and prints added message.
     *
     * @param t Task to be stored.
     */
    private void addTaskToStored(Task t) {
        allTasks.add(t);
        t.taskAddedMessage();
    }

    /**
     * Adds the Task (previously saved) without printing the added message.
     *
     * @param t Task to be stored.
     */
    public void addSavedTaskToStored(Task t) {
        allTasks.add(t);
    }

    /**
     * Prints removal of a given task, of its index then and the current number of Tasks remaining.
     *
     * @param index index of current Task to remove.
     */
    private void printTaskRemoval(int index, Task removedTask) {
        printLine();
        print("Removed Task #" + (index + 1) + ": " + removedTask
                + "\nHope it's worth it!\nYou are now left with "
                + allTasks.size() + " tasks.");
        printLine();
    }

    /**
     * Prints all tasks, their number order, and their completion for list command.
     */
    private void printAllTasks() throws EmptyTaskListException {
        printLine();
        if (allTasks.isEmpty()) {
            throw new EmptyTaskListException("");
        }
        for (int i = 0; i < allTasks.size(); i++) {
            printTaskFromStored(i);
        }
        printLine();
    }

    /**
     * Prints a response to the done command after doTask completes.
     *
     * @param t Task that has been completed via doTask method.
     */
    private void printTaskComplete(Task t) {
        printLine();
        if (t.getIsDone()) {
            print("That's already done, try another. Or did you make a careless mistake? XD");
        } else {
            print("Nice! The following task has been marked completed:\n"
                    + "===> [V] " + t + " <===");
        }
        printLine();
    }

    /**
     * Prints individual task with current completion status.
     *
     * @param i index of storage of the Task in the container/collection.
     */
    private void printTaskFromStored(int i) {
        String tickOrCross = allTasks.get(i).obtainStatusIcon();
        print(String.valueOf(i + 1) + ". [" + tickOrCross + "] " + allTasks.get(i));
    }

    /**
     * Prints a horizontal formatting line.
     */
    private void printLine() {
        print(Constant.FORMAT_LINE);
    }

    /**
     * Prints a given String.
     *
     * @param s String to be printed.
     */
    private void print(String s) {
        System.out.println(s);
    }

    /**
     * Returns size of the TaskList's ArrayList.
     *
     * @return size of the list.
     */
    public int sizeOf() {
        return this.allTasks.size();
    }

    /**
     * Gets the task at index i.
     *
     * @param i index of Task in TaskList.
     * @return Task at index i of TaskList.
     */
    public Task getTask(int i) {
        return allTasks.get(i);
    }

}
