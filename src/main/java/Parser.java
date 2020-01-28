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
                case todo:
                case deadline:
                case event:
                    String taskDescription;

                    if (splitInput.length == 1) {
                        throw new DukeEmptyDescriptionException("OOPS!!! The description of a task cannot be empty.");
                    }
                    String[] inputWithoutCommand = Arrays.copyOfRange(splitInput, 1, splitInput.length);

                    switch (Command.Type.valueOf(commandString)) {
                        case todo:
                            // empty string array would become empty string
                            taskDescription = String.join(" ", inputWithoutCommand);
                            toReturn = new AddCommand(Command.Type.todo, taskDescription);
                            break;
                        case deadline:
                        case event:
                            String keyword = commandString.equals(Command.Type.deadline.getCommand())
                                    ? Command.Type.deadline_by.getCommand()
                                    : Command.Type.event_at.getCommand();
                            int keywordIndex = Arrays.asList(splitInput).indexOf(keyword);
                            if (keywordIndex == -1) {
                                throw new DukeNoKeywordException("OOPS!!! The description must contain a keyword.");
                            }

                            taskDescription = String.join(" ",
                                    Arrays.copyOfRange(splitInput, 1, keywordIndex));
                            String deadlineOrTime = String.join(" ",
                                    Arrays.copyOfRange(splitInput, keywordIndex + 1, splitInput.length));
                            LocalDate date = LocalDate.parse(deadlineOrTime);

                            toReturn = commandString.equals(Command.Type.deadline.getCommand())
                                    ? new AddCommand(Command.Type.deadline, taskDescription, date)
                                    : new AddCommand(Command.Type.event, taskDescription, date);
                    }
                    break;
                default:
                    break;
            }
        } catch (IllegalArgumentException e) {
            throw new DukeWrongCommandException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }

        return toReturn;
    }
}
