package duke.parser;

import duke.commands.AddCommand;
import duke.commands.Command;
import duke.commands.DeleteCommand;
import duke.commands.DoneCommand;
import duke.commands.DoneSomeCommand;
import duke.commands.ExitCommand;
import duke.commands.FindCommand;
import duke.commands.ListCommand;
import duke.exceptions.DukeException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Todo;
import java.util.ArrayList;

/**
 * Parser deals with making sense of the user inputs.
 */
public class Parser {
    /**
     * Parses the user input and tag it to the respective command.
     * @param  input User input.
     * @return Appropriate command that executes the input.
     * @throws DukeException thrown when input is unknown to Duke.
     */
    public static Command parse(String input) throws DukeException {
        assert input.length() != 0 : "Error: User input not found.";
        String[] inputArray = input.split(" ", 2);
        String type = inputArray[0];
        switch (type) {
            case "bye":
                return new ExitCommand();

            case "list":
                return new ListCommand();

            case "done":
                if (inputArray.length != 1) {
                    if (isNumber(inputArray[1])) {
                        int doneID = Integer.parseInt(inputArray[1]);
                        return new DoneCommand(doneID);
                    } else {
                        ArrayList<Integer> indexList = new ArrayList<>();
                        String[] indexArray = inputArray[1].split(" ");
                        for (String id : indexArray) {
                            int index = Integer.parseInt(id);
                            indexList.add(index);
                        }
                        return new DoneSomeCommand(indexList);
                    }
                } else {
                    throw new DukeException("Parker! Try: done {INDEX} or done {INDEX1 INDEX2 ...}");
                }

            case "delete":
                if (inputArray.length != 1) {
                    int deleteID = Integer.parseInt(inputArray[1]);
                    return new DeleteCommand(deleteID);
                } else {
                    throw new DukeException("Parker.. Try: delete {INDEX}");
                }

            case "todo":
                if (inputArray.length != 1) {
                    return new AddCommand(new Todo(inputArray[1]));
                } else {
                    throw new DukeException("Parker.. Try: todo {DESCRIPTION}");
                }

            case "deadline":
                if (inputArray.length != 1) {
                    String[] byArray = inputArray[1].split("/by ");
                    return new AddCommand(new Deadline(byArray[0].trim(), byArray[1]));
                } else {
                    throw new DukeException("Parker.. Try: deadline {DESCRIPTION} /by YYYY-MM-DD");
                }

            case "event":
                if (inputArray.length != 1) {
                    String[] atArray = inputArray[1].split("/at ");
                    String[] dateTimeArray = atArray[1].split(" ");
                    return new AddCommand(new Event(atArray[0].trim(), dateTimeArray[0], dateTimeArray[1]));
                } else {
                    throw new DukeException("Parker.. Try: event {DESCRIPTION} /at YYYY-MM-DD HH:mm");
                }

            case "find":
                if (inputArray.length != 1) {
                    String key = inputArray[1];
                    return new FindCommand(key);
                } else {
                    throw new DukeException("Parker.. Try: find {KEYWORD}");
                }

            default:
                throw new DukeException("You are not making sense, Parker!!! >:(");
        }
    }

    /**
     * Returns whether the String is an Integer.
     * @param input Input string.
     * @return true if Integer, false if not.
     */
    public static boolean isNumber(String input) {
        if (input == null) {
            return false;
        }
        try {
            Integer.parseInt(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}

