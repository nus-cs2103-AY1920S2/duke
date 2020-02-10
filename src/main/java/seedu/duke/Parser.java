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
                isSplitInputLengthLargerThanOne(splitInput);
                selectedTaskIndex = Integer.parseInt(splitInput[1]) - 1;
                toReturn = new DoneCommand(selectedTaskIndex);
                break;
            case delete:
                isSplitInputLengthLargerThanOne(splitInput);
                selectedTaskIndex = Integer.parseInt(splitInput[1]) - 1;
                toReturn = new DeleteCommand(selectedTaskIndex);
                break;
            case todo:
                isSplitInputLengthLargerThanOne(splitInput);
                String taskDescription = fullCommand.substring(Command.Type.todo.getCommand().length()).strip();
                toReturn = new AddCommand(Command.Type.todo, taskDescription);
                break;
            case find:
                isSplitInputLengthLargerThanOne(splitInput);
                String[] keywords = fullCommand
                        .substring(Command.Type.find.getCommand().length())
                        .strip()
                        .split(Pattern.quote(" "));
                toReturn = new FindCommand(keywords);
                break;
            case deadline:
            case event:
                isSplitInputLengthLargerThanOne(splitInput);
                toReturn = createDeadLineOrEventCommand(fullCommand, commandString);
                break;
            default:
                assert false : "Class Parser: parse method, reached default case.";
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

    private static Command createDeadLineOrEventCommand(String fullCommand, String commandString)
            throws DukeNoKeywordException {
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
