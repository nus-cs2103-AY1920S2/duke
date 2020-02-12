package parser;

import exception.EmptyDescriptionException;
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.stream.Stream;

/**
 * Represents a program for parsing userInput into different components depending on user taskCommand.
 * Extracts taskCommand, taskAction and taskIndex for "done" and "delete" task.
 * Extracts taskCommand and taskAction for "todo" and "find" task.
 * Extracts taskCommand, taskAction and timeline for "deadline" and "event" task.
 */
public class Parser {

    private String userInput;
    private String taskCommand;
    private String[] inputAsArray;

    public Parser(String userInput) {
        this.userInput = userInput;
    }

    /**
     * Returns the command such as "todo", "deadline", "event".
     *
     * @return String command.
     */
    public String getCommand() {
        inputAsArray = userInput.split(" ", 2);
        taskCommand = inputAsArray[0];
        return taskCommand;
    }

    /**
     * Gets the task action depending on the command.
     *
     * @return String representing task.
     */
    public String getTaskAction() {
        switch (taskCommand) {
        case "todo":
            return inputAsArray[1];
        case "find":
            return inputAsArray[1];
        case "deadline":
            return userInput.split(" /by")[0].split(" ", 2)[1];
        case "event":
            return userInput.split(" /at")[0].split(" ", 2)[1];
        default:
            return "";
        }
    }

    /**
     * Gets the date depending on whether task is "deadline" or "event".
     *
     * @return LocalDateTime object.
     */
    public LocalDateTime getTaskDate() throws DateTimeException {
        if (inputAsArray[0].equals("deadline")) {
            String time = userInput.split(" /by")[1].substring(1);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
            return LocalDateTime.parse(time, formatter);
        } else if (inputAsArray[0].equals("event")) {
            String time = userInput.split(" /at")[1].substring(1);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
            return LocalDateTime.parse(time, formatter);
        } else {
            return null;
        }
    }

    /**
     * Returns an array of task indexes that user requests to delete.
     *
     * @return an array of task indexes.
     * @throws EmptyDescriptionException throws exception of index is not provided.
     */
    public int[] getTaskIndexArray() throws EmptyDescriptionException {
        if (userInput.split(" ").length == 1) {
            throw new EmptyDescriptionException("You forgot to mention the index!");
        }

        String[] arrayOfIndexStrings = userInput.split(" ", 2)[1].split(" ");

        int[] arrayOfIndexes = Stream.of(arrayOfIndexStrings)
                                     .mapToInt(Integer::parseInt)
                                     .map(index -> index - 1)
                                     .toArray();
        return arrayOfIndexes;
    }


}
