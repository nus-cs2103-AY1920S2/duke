package seedu.duke;

import seedu.duke.command.AddCommand;
import seedu.duke.command.Command;
import seedu.duke.command.DeleteCommand;
import seedu.duke.command.DoneCommand;
import seedu.duke.command.ExitCommand;
import seedu.duke.command.FindCommand;
import seedu.duke.command.ListCommand;
import seedu.duke.exception.DukeEmptyDescriptionException;
import seedu.duke.exception.DukeException;
import seedu.duke.exception.DukeNoKeywordException;
import seedu.duke.exception.DukeWrongCommandException;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.regex.Pattern;

class Parser {

    static Command parse(String fullCommand) throws DukeException {
        String[] splitInput = fullCommand.split(Pattern.quote(" "));
        // empty line would output string array of size 1, where the element is empty string
        String commandString = splitInput[0];
        int selectedTaskIndex;
        Command toReturn = null;

        try {
            switch (Command.Type.valueOf(commandString)) {
            case bye:
                toReturn = new ExitCommand();
                break;
            case list:
                toReturn = new ListCommand();
                break;
            case done:
                selectedTaskIndex = Integer.parseInt(splitInput[1]) - 1;
                toReturn = new DoneCommand(selectedTaskIndex);
                break;
            case delete:
                selectedTaskIndex = Integer.parseInt(splitInput[1]) - 1;
                toReturn = new DeleteCommand(selectedTaskIndex);
                break;
            case find:
            case todo:
                toReturn = createFindOrTodoCommand(splitInput, commandString);
                break;
            case deadline:
            case event:
                toReturn = createDeadLineOrEventCommand(splitInput, commandString);
                break;
            default:
                break;
            }
        } catch (IllegalArgumentException e) {
            throw new DukeWrongCommandException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }

        return toReturn;
    }

    private static void isSplitInputLengthLargerThanOne(String[] splitInput) throws DukeEmptyDescriptionException {
        if (splitInput.length == 1) {
            throw new DukeEmptyDescriptionException("OOPS!!! The description of a task cannot be empty.");
        }
    }

    private static Command createFindOrTodoCommand(String[] splitInput, String commandString)
            throws DukeEmptyDescriptionException {
        isSplitInputLengthLargerThanOne(splitInput);

        String[] inputWithoutCommand = Arrays.copyOfRange(splitInput, 1, splitInput.length);
        // empty string array would become empty string
        String taskDescription = String.join(" ", inputWithoutCommand);

        return commandString.equals(Command.Type.find.getCommand())
                ? new FindCommand(taskDescription)
                : new AddCommand(Command.Type.todo, taskDescription);
    }

    private static Command createDeadLineOrEventCommand(String[] splitInput, String commandString)
            throws DukeNoKeywordException, DukeEmptyDescriptionException {
        isSplitInputLengthLargerThanOne(splitInput);

        String keyword = commandString.equals(Command.Type.deadline.getCommand())
                ? Command.Type.deadline_by.getCommand()
                : Command.Type.event_at.getCommand();
        int keywordIndex = Arrays.asList(splitInput).indexOf(keyword);
        if (keywordIndex == -1) {
            throw new DukeNoKeywordException("OOPS!!! The description must contain a keyword.");
        }

        String taskDescription = String.join(" ",
                Arrays.copyOfRange(splitInput, 1, keywordIndex));
        String deadlineOrTime = String.join(" ",
                Arrays.copyOfRange(splitInput, keywordIndex + 1, splitInput.length));
        LocalDate date = LocalDate.parse(deadlineOrTime);

        return commandString.equals(Command.Type.deadline.getCommand())
                ? new AddCommand(Command.Type.deadline, taskDescription, date)
                : new AddCommand(Command.Type.event, taskDescription, date);
    }
}
