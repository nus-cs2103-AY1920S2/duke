import java.time.format.DateTimeParseException;

/**
 * Parser class takes care of the parsing of full commands that the user typed.
 */
public class Parser {
    public Parser() {

    }

    /**
     * This function handles the parsing of command recognised by the bot.
     * @param fullCommand Command that the user typed.
     * @return Command from enum.
     * @throws DukeException Exception is thrown when the user typed other commands that the bot do not understand.
     */
    public Command parseCommand(String fullCommand) throws DukeException {
        String[] splitBySpace;
        splitBySpace = fullCommand.split(" ");

        try {
            if (fullCommand.equals("")) {
                throw new NullPointerException("");
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
                throw new DukeException("Don't understand");
            }
        } catch (DukeException e) {
            System.out.println(e);
        } catch (NullPointerException e) {
            System.out.println("Please enter a command.");
        }

        return null;
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

        try {
            if (splitBySpace.length == 1) {
                throw new DukeException(splitBySpace[0]);
            }

            for (int i = 1; i < splitBySpace.length - 1; i++) {
                newString = newString + splitBySpace[i] + " ";
            }
            newString = newString + splitBySpace[splitBySpace.length - 1];

            return newString;
        } catch (DukeException e) {
            System.out.println(e);
        }

        return null;
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

        try {
            if (splitBySpace.length == 1) {
                throw new DukeException(splitBySpace[0]);
            }

            splitBySlash = fullCommand.split("/");
            String[] splitBySpace2 = splitBySlash[0].split(" ");

            for (int i = 1; i < splitBySpace2.length - 1; i++) {
                newString = newString + splitBySpace2[i] + " ";
            }
            newString = newString + splitBySpace2[splitBySpace2.length - 1];

            return newString;

        } catch (DukeException e) {
            System.out.println(e);
        }

        return null;
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

        try {
            splitBySlash = fullCommand.split("/");
            if (splitBySlash.length != 2) {
                throw new DukeException(splitBySlash[0], "", "no slash");
            }

            return splitBySlash[1];

        } catch (DukeException e) {
            System.out.println(e);
        }

        return null;
    }

    /**
     * This function will parse the number in the command. Function is for delete and done command.
     * @param fullCommand Command that the user typed.
     * @param tasks List of tasks.
     * @return The parsed number.
     * @throws DukeException Exception is thrown if the criteria does not fit what delete or done command
     * is looking for.
     */
    public int parseNum(String fullCommand, TaskList tasks) throws DukeException {
        String[] splitBySpace;
        splitBySpace = fullCommand.split(" ");

        try {
            if (splitBySpace.length == 1) {
                if (splitBySpace[0].equals("done")) {
                    throw new DukeException("done");
                } else if (splitBySpace[0].equals("delete")) {
                    throw new DukeException("delete");
                }
            } else if (splitBySpace.length != 2) {
                if (splitBySpace[0].equals("done")) {
                    throw new DukeException("done argument too much");
                } else if (splitBySpace[0].equals("delete")) {
                    throw new DukeException("delete argument not found");
                }
            }

            int num = Integer.parseInt(splitBySpace[1]);
            if (num == 0 || num > tasks.getSize()) {
                if (splitBySpace[0].equals("done")) {
                    throw new DukeException("unable to mark done", num);
                } else if (splitBySpace[0].equals("delete")) {
                    throw new DukeException("unable to delete from list", num);
                }
            }

            return num;
        } catch (DukeException e) {
            System.out.println(e);
        }

        return -1;
    }


}
