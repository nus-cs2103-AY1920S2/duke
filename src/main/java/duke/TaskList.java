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

/**
 * TaskList class which mainly stores all the Tasks in an ArrayList of Tasks and handles related methods.
 */
public class TaskList {

    /**
     * For storing Tasks.
     */
    private ArrayList<Task> allTasks;


    /**
     * Parser to make sense of various commands.
     */
    Parser logic = new Parser();

    /**
     * Constructor for TaskList.
     */
    public TaskList() {
        this.allTasks = new ArrayList<>();
    }

    /**
     * Gives a String reply for each time it is called to Serve the user in processing individual commands.
     *
     * @param command the full, unedited command entered by the user.
     */
    public String serveUser(String command) {

        String commandType = logic.findKeywordFromString(command);

        try {

            // Depending on command, we process the command
            // Exceptions will be thrown respectively
            switch (commandType) {

            case "help":
                return Ui.welcome();

            case "bye":
                return Ui.goodbye();

            case "list":
                return Ui.listAllTasksMessage(this);

            case "delete":
                return deleteTask(command);

            case "done":
                return doneTask(command);

            case "todo":
                return addNewToDo(command);

            case "deadline":
                return addNewDeadline(command);

            case "event":
                return addNewEvent(command);

            case "find":
                return findTask(command);

            default:
                throw new InputUnclearException("");

            }
        } catch (DoneException | DeadlineException | DeleteException | EmptyTaskListException
            | EventException | InputUnclearException | ToDoException e) {
            return Ui.exceptionMessage(e.toString());
        }

    }

    /**
     * Gives a String representation of the result of completing a selected Task, and marks the Task as done.
     *
     * @param command the full, unedited command entered by the user.
     * @return String representation of a message indicating the selected Task is completed.
     * @throws DoneException possible Exception when running a done command.
     * @throws InputUnclearException an Exception given when the input is unfitting.
     */
    private String doneTask(String command) throws DoneException, InputUnclearException {
        // user inputs will be "done _____"
        // Take only the very next token which must be an integer
        String[] commandWords = command.split("\\s"); // 0: "find", 1: keyword
        if (logic.satisfiesMinimumDoneCommandLength(commandWords)) {
            // this is a valid command
            if (logic.determineIfValidDoneCommand(commandWords, this.sizeOf())) {
                // this valid command has a valid number, so, mark as done
                return doTask(Integer.parseInt(commandWords[1]));
            } else {
                // invalid input: attempting to do a non-existent task
                throw new InputUnclearException("");
            }
        } else {
            // invalid input
            throw new DoneException("");
        }
    }

    /**
     * Gives a String representation of the result of a find command and finds the Tasks which contain the keyword.
     *
     * @param command the full, unedited command entered by the user.
     * @return String representation of the result of finding Tasks with given keyword(s).
     * @throws EmptyTaskListException an Exception given when TaskList is empty.
     */
    private String findTask(String command) throws EmptyTaskListException {
        try {
            // Find the keyword for search
            String[] commandWords = command.split("\\s"); // 0: "find", 1: keyword
            String keywords = commandWords[1];
            for (int i = 2; i < commandWords.length; i++) {
                keywords = " " + commandWords[i];
            }
            keywords = keywords.toLowerCase();

            // For temporary storage and printing
            TaskList result = new TaskList();

            // Search
            for (Task t : allTasks) {
                if (t.getCommand().toLowerCase().contains(keywords)) {
                    result.addSavedTaskToStored(t);
                }
            }

            // Print all options
            return Ui.listAllTasksMessage(result);
        } catch (Exception e) {
            throw new EmptyTaskListException("");
        }

    }

    /**
     * Gives String representation of the result of a delete command and Deletes the given Task.
     * This is based on the command's keyword (a number representing index in ArrayList).
     *
     * @param command the full, unedited command entered by the user.
     * @return String representation of the result of a delete command.
     * @throws DeleteException where deleting task not possible due to incorrect command/index entered by user.
     */
    private String deleteTask(String command) throws DeleteException {
        try {
            int indexToRemove = Integer.parseInt(command.substring("delete ".length())) - 1; // due to zero-indexing
            Task removedTask = allTasks.remove(indexToRemove);
            Storage.saveChanges(this);
            return Ui.taskRemovalMessage(indexToRemove, removedTask, this);
        } catch (Exception e) {
            throw new DeleteException("");
        }
    }

    /**
     * Gives the result of adding a Task and adds the Task to store in the ArrayList, and prints added message.
     * This is a helper method for adding Tasks.
     *
     * @param t Task to be stored.
     * @return String result for adding a Task.
     */
    private String addTaskToStored(Task t) {
        allTasks.add(t);
        Storage.saveChanges(this);
        return Ui.customMessage(t.taskAddedMessage());
    }

    /**
     * Gives the String to indicate adding a new Task and adds the new Task for user to do.
     *
     * @param command the full, unedited command entered by the user.
     * @return String to indicate adding a new Task.
     * @throws ToDoException Exception arising from creating To-Do Task due to wrong inputs.     *
     */
    private String addNewToDo(String command) throws ToDoException {
        if (command.length() < 6) {
            throw new ToDoException("");
        }
        String toDoCommand = command.substring("todo".length() + 1);
        return addTaskToStored(new ToDo(toDoCommand));
    }

    /**
     * Gives a String to indicate adding a Deadline Task and adds the new Deadline Task.
     * There is a need to specify the actual Task command and time limit.
     *
     * @param command the full, unedited command entered by the user.
     * @return String to indicate adding a Deadline Task.
     * @throws DeadlineException Exception arising from creating Deadline Task due to wrong inputs.
     */
    private String addNewDeadline(String command) throws DeadlineException {
        try {
            // need to identify deadline limit
            String dateDetails = getRestriction("/by", command);
            String deadlineLimit = getPresentableDate(dateDetails);

            // filter to obtain command
            String deadlineCommand = getCommand("deadline", "/by", command);

            return addTaskToStored(new Deadline(deadlineCommand, deadlineLimit));
        } catch (Exception e) {
            throw new DeadlineException("");
        }
    }

    /**
     * Gives a String to indicate adding a new Event Task and adds the new Event Task.
     * There is a need to specify the actual Task command and the time duration.
     *
     * @param command the full, unedited command entered by the user.
     * @return String to indicate adding a new Event Task.
     * @throws EventException Exception arising from Event Task creation due to wrong inputs.
     */
    private String addNewEvent(String command) throws EventException {
        try {
            // need to identify event time
            String dateDetails = getRestriction("/at", command);
            String eventTime = getPresentableDate(dateDetails);

            // filter to obtain command
            String eventCommand = getCommand("event", "/at", command);

            return addTaskToStored(new Event(eventCommand, eventTime));
        } catch (Exception e) {
            throw new EventException("");
        }
    }

    /**
     * Provides the date in a presentable, "Month (in English) Day Year" format.
     *
     * @param dateDetails date in the form YYYY-MM-DD.
     * @return a more presentable form of date in Month Day, Year.
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
     * @param command the full, unedited command entered by the user.
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
     * @param command the full, unedited command entered by the user.
     * @return String representation of description of the command of the Task.
     */
    private String getCommand(String taskType, String keyword, String command) {
        int indexOfTimeKeyword = indexSearchInString(keyword, command) - 1; // due to whitespace
        int startIndex = taskType.length() + 1; // due to whitespace
        return command.substring(startIndex, indexOfTimeKeyword);
    }

    /**
     * Finds the first occurrence index of keywords in a search space.
     *
     * @param keyword a String beginning with "/" and a keyword which separates the Task and Restriction of Time.
     * @param searchSpace the full String to search from.
     * @return index in which the keyword appears first.
     */
    private int indexSearchInString(String keyword, String searchSpace) {
        return searchSpace.indexOf(keyword);
    }

    /**
     * Marks task complete and gives a completion message.
     *
     * @param i Index of the Task to be done.
     * @return String to indicate the Task is done.
     */
    private String doTask(int i) {
        Task t = allTasks.get(i - 1); // due to 0 indexing
        t.doTask(); // task marked as complete
        Storage.saveChanges(this);
        return Ui.taskCompleteMessage(t);
    }

    /**
     * Adds the Task (previously saved) without giving any message indication.
     * Can be used to simply to add without announcing, from one TaskList to another such as a temporary one.
     * Useful in reading in Tasks from last save.
     *
     * @param t Task to be stored.
     */
    public void addSavedTaskToStored(Task t) {
        allTasks.add(t);
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

    /**
     * Checks whether current TaskList is empty.
     *
     * @return whether the current TaskList object is empty.
     */
    public boolean isEmpty() {
        return allTasks.isEmpty();
    }

}
