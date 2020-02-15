/**
 * Parser class takes care of the parsing of full commands that the user typed.
 */
public class Parser {

    /**
     * Parses the command recognised by the bot.
     * @param fullCommand Command that the user typed.
     * @return Command from enum.
     * @throws DukeException Exception is thrown when the user typed other commands that the bot do not understand.
     */
    public Command parseCommand(String fullCommand) throws DukeException {
        String[] splitBySpace;
        splitBySpace = fullCommand.split(" ");
        String command = splitBySpace[0];

        if (fullCommand.equals("")) {
            return Command.ENTERCOMMAND;
        }

        switch (command) {
        case "l":
        case "list":
            return Command.LIST;
        case "d":
        case "done":
            return Command.DONE;
        case "del":
        case "delete":
            return Command.DELETE;
        case "t":
        case "todo":
            return Command.TODO;
        case "e":
        case "event":
            return Command.EVENT;
        case "dl":
        case "deadline":
            return Command.DEADLINE;
        case "f":
        case "find":
            return Command.FIND;
        default:
            return Command.DONTUNDERSTAND;
        }
    }

    /**
     * Parses the description of todo command.
     * @param fullCommand Command that the user typed.
     * @return The description of todo command.
     */
    public String parseDescription(String fullCommand) throws DukeException {
        String[] splitBySpace;
        splitBySpace = fullCommand.split(" ");
        String description = "";
        String command = splitBySpace[0];

        if (splitBySpace.length == 1) {
            throw new EmptyDescriptionException(command);
        }

        for (int i = 1; i < splitBySpace.length - 1; i++) {
            description = description + splitBySpace[i] + " ";
        }
        description = description + splitBySpace[splitBySpace.length - 1];

        return description;
    }

    /**
     * Parses the description of event and deadline command.
     * @param fullCommand Command that the user typed.
     * @return Description of event and deadline command.
     */
    public String parseDescOfEventDeadline(String fullCommand) throws DukeException {
        String[] splitBySpace;
        String[] splitBySlash;
        splitBySpace = fullCommand.split(" ");
        String description = "";
        String command = splitBySpace[0];

        if (splitBySpace.length == 1) {
            throw new EmptyDescriptionException(command);
        }

        splitBySlash = fullCommand.split("/");

        String[] splitBySpace2 = splitBySlash[0].split(" ");

        for (int i = 1; i < splitBySpace2.length - 1; i++) {
            description = description + splitBySpace2[i] + " ";
        }
        description = description + splitBySpace2[splitBySpace2.length - 1];

        return description;
    }

    /**
     * Parses the "deadline" typed by the user. Function will only be called if the command is
     * event or deadline.
     * @param fullCommand Command that the user typed.
     * @return The description of what the user typed to be deadline.
     */
    public String parseBy(String fullCommand, Command command) throws DukeException {
        String[] splitBySlash;

        splitBySlash = fullCommand.split("/");
        if (splitBySlash.length != 2) {
            if (command == Command.EVENT) {
                throw new DukeException("Please provide a valid deadline. " +
                        "For example, 「event read book /by 2020-09-20」.");
            } else {
                throw new DukeException("Please provide a valid deadline. " +
                        "For example, 「deadline read book /by 2020-09-20」.");
            }
        }

        return splitBySlash[1];
    }

    /**
     * Parses the number in the command. Function is for delete and done command.
     * @param fullCommand Command that the user typed.
     * @param tasks List of tasks.
     * @return The parsed number.
     * @throws DukeException Exception is thrown if the criteria does not fit what delete or done command
     * is looking for.
     */
    public int parseNum(String fullCommand, TaskList tasks) throws DukeException {
        String[] splitBySpace;
        splitBySpace = fullCommand.split(" ");
        String command = splitBySpace[0];
        int num;

        if (splitBySpace.length == 1) {
            throw new EmptyDescriptionException(command);
        } else if (splitBySpace.length != 2) {
            if (command.equals("done")) {
                throw new DukeException("Please only provide one argument to mark as done.");
            } else if (command.equals("delete")) {
                throw new DukeException("Please provide a valid number to delete.");
            }
        }

        try {
            num = Integer.parseInt(splitBySpace[1]);
        } catch (NumberFormatException e) {
            if (command.equals("done")) {
                throw new DukeException("Sorrymasen, I only accept number to mark task as done :(");
            } else {
                throw new DukeException("Sorrymasen, I only accept number to delete :(");
            }
        }

        if ((num > 0 && num > tasks.getSize()) || num == 0) {
            throw new DukeException("Unable to mark task #" + num +
                    " as done. Please try again with a valid task number.");
        }

        return num;
    }
}
