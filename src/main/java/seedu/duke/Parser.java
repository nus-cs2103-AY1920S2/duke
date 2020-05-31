package seedu.duke;

import seedu.duke.command.AddCommand;
import seedu.duke.command.Command;
import seedu.duke.command.DeleteCommand;
import seedu.duke.command.DoneCommand;
import seedu.duke.command.ExitCommand;
import seedu.duke.command.FindCommand;
import seedu.duke.command.ListCommand;
import seedu.duke.command.SortCommand;
import seedu.duke.exception.DukeEmptyDescriptionException;
import seedu.duke.exception.DukeException;
import seedu.duke.exception.DukeNoKeywordException;
import seedu.duke.exception.DukeWrongCommandException;

import java.time.LocalDate;
import java.util.regex.Pattern;

class Parser {

    static Command parse(String fullCommand) throws DukeException {
        String[] splitInput = fullCommand.split(Pattern.quote(" "));
        // empty line would output string array of size 1, where the element is empty string
        String commandString = splitInput[0];

        try {
            switch (Command.Type.valueOf(commandString)) {
            case bye:
                return new ExitCommand();
            case list:
                return new ListCommand();
            case sort:
                return new SortCommand();
            case done:
                return createDoneCommand(splitInput);
            case delete:
                return createDeleteCommand(splitInput);
            case todo:
                return createTodoAddCommand(fullCommand, splitInput);
            case find:
                return createFindCommand(fullCommand, splitInput);
            case deadline: // Fallthrough
            case event:
                return createDeadLineOrEventAddCommand(fullCommand, splitInput, commandString);
            default:
                assert false : "Class Parser: parse method, reached default case.";
                return null;
            }
        } catch (IllegalArgumentException e) {
            throw new DukeWrongCommandException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    private static void isSplitInputLengthLargerThanOne(String[] splitInput) throws DukeEmptyDescriptionException {
        if (splitInput.length == 1) {
            throw new DukeEmptyDescriptionException("OOPS!!! The description of a task cannot be empty.");
        }
    }

    private static Command createDoneCommand(String[] splitInput) throws DukeEmptyDescriptionException {
        int selectedTaskIndex;
        isSplitInputLengthLargerThanOne(splitInput);
        selectedTaskIndex = Integer.parseInt(splitInput[1]) - 1;
        return new DoneCommand(selectedTaskIndex);
    }

    private static Command createDeleteCommand(String[] splitInput) throws DukeEmptyDescriptionException {
        int selectedTaskIndex;
        isSplitInputLengthLargerThanOne(splitInput);
        selectedTaskIndex = Integer.parseInt(splitInput[1]) - 1;
        return new DeleteCommand(selectedTaskIndex);
    }

    private static Command createTodoAddCommand(String fullCommand, String[] splitInput)
            throws DukeEmptyDescriptionException {
        isSplitInputLengthLargerThanOne(splitInput);
        String taskDescription = fullCommand.substring(Command.Type.todo.getCommand().length()).strip();
        return new AddCommand(Command.Type.todo, taskDescription);
    }

    private static Command createFindCommand(String fullCommand, String[] splitInput)
            throws DukeEmptyDescriptionException {
        isSplitInputLengthLargerThanOne(splitInput);
        String[] keywords = fullCommand
                .substring(Command.Type.find.getCommand().length())
                .strip()
                .split(Pattern.quote(" "));
        return new FindCommand(keywords);
    }

    private static Command createDeadLineOrEventAddCommand(
            String fullCommand, String[] splitInput, String commandString)
            throws DukeEmptyDescriptionException, DukeNoKeywordException {
        isSplitInputLengthLargerThanOne(splitInput);
        Command.Type type = Command.Type.valueOf(commandString);
        Command.Type keyword = type == Command.Type.deadline
                ? Command.Type.deadline_by
                : Command.Type.event_at;

        int keywordIndex = fullCommand.indexOf(keyword.getCommand());
        if (keywordIndex == -1) {
            throw new DukeNoKeywordException("OOPS!!! The description must contain a keyword.");
        }

        String taskDescription = fullCommand.substring(type.getCommand().length(), keywordIndex).strip();
        String deadlineOrTime = fullCommand.substring(keywordIndex + keyword.getCommand().length()).strip();
        LocalDate date = LocalDate.parse(deadlineOrTime);

        return new AddCommand(type, taskDescription, date);
    }
}
