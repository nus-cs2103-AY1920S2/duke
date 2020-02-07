import java.time.format.DateTimeParseException;

/**
 * Parser class takes care of the parsing of full commands that the user typed.
 */
public class Parser {

    /**
     * This function handles the parsing of command recognised by the bot.
     * @param fullCommand Command that the user typed.
     * @return Command from enum.
     * @throws DukeException Exception is thrown when the user typed other commands that the bot do not understand.
     */
    public Command parseCommand(String fullCommand) throws DukeException {
        String[] splitBySpace;
        splitBySpace = fullCommand.split(" ");

        if (fullCommand.equals("")) {
            return Command.ENTERCOMMAND;
        }

        switch (splitBySpace[0]) {
        case "list":
            return Command.LIST;
        case "done":
            return Command.DONE;
        case "delete":
            return Command.DELETE;
        case "todo":
            return Command.TODO;
        case "event":
            return Command.EVENT;
        case "deadline":
            return Command.DEADLINE;
        case "find":
            return Command.FIND;
        default:
            return Command.DONTUNDERSTAND;
        }

    }

    /**
     * This function will parse the description of todo command.
     * @param fullCommand Command that the user typed.
     * @return The description of todo command.
     */
    public String parseDescription(String fullCommand) {
        String[] splitBySpace;
        splitBySpace = fullCommand.split(" ");
        String newString = "";

        if (splitBySpace.length == 1) {
            return "-1Error:0b9d4e";
        }

        for (int i = 1; i < splitBySpace.length - 1; i++) {
            newString = newString + splitBySpace[i] + " ";
        }
        newString = newString + splitBySpace[splitBySpace.length - 1];

        return newString;
    }

    /**
     * This function will parse the description of event and deadline command.
     * @param fullCommand Command that the user typed.
     * @return Description of event and deadline command.
     */
    public String parseDescOfEventDeadline(String fullCommand) {
        String[] splitBySpace;
        String[] splitBySlash;
        splitBySpace = fullCommand.split(" ");
        String newString = "";

        if (splitBySpace.length == 1) {
            return "-1Error:21006a";
        }

        splitBySlash = fullCommand.split("/");

        String[] splitBySpace2 = splitBySlash[0].split(" ");

        for (int i = 1; i < splitBySpace2.length - 1; i++) {
            newString = newString + splitBySpace2[i] + " ";
        }
        newString = newString + splitBySpace2[splitBySpace2.length - 1];

        return newString;
    }

    /**
     * This function will parse the "deadline" typed by the user. Function will only be called if the command is
     * event or deadline.
     * @param fullCommand Command that the user typed.
     * @return The description of what the user typed to be deadline.
     */
    public String parseBy(String fullCommand) {
        String[] splitBySpace;
        String[] splitBySlash;
        splitBySpace = fullCommand.split(" ");
        String newString = "";

        splitBySlash = fullCommand.split("/");
        if (splitBySlash.length != 2) {
            return "-2error:21f3ad";
        }

        return splitBySlash[1];
    }

    /**
     * This function will parse the number in the command. Function is for delete and done command.
     * @param fullCommand Command that the user typed.
     * @param tasks List of tasks.
     * @return The parsed number.
     * @throws DukeException Exception is thrown if the criteria does not fit what delete or done command
     * is looking for.
     */
    public int parseNum(String fullCommand, TaskList tasks) {
        String[] splitBySpace;
        splitBySpace = fullCommand.split(" ");

        if (splitBySpace.length == 1) {
            if (splitBySpace[0].equals("done")) {
                return -1;
            } else if (splitBySpace[0].equals("delete")) {
                return -1;
            }
        } else if (splitBySpace.length != 2) {
            if (splitBySpace[0].equals("done")) {
                return -2;
            } else if (splitBySpace[0].equals("delete")) {
                return -2;
            }
        }

        int num = Integer.parseInt(splitBySpace[1]);

        return num;
    }


}
