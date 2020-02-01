import java.util.Scanner;

/**
 * Parser class describes the behaviour of a
 * parser that parses commands entered by the client.
 */
public class Parser {

    /**
     * Constructs a Parser instance.
     */
    public Parser() {
    }

    private Scanner sc = new Scanner(System.in);

    /**
     * Verifies whether the string entered
     * is a valid command. It will throw an exception if
     * the command is not valid (i.e. if the argument has no description
     * or the format of the command is not proper).
     *
     * @param command The command string entered by the client.
     * @return The String, regarding the type of the command entered.
     * @throws DukeInvalidCommandException If the command is not valid.
     */
    public String checkCommand(String command) throws DukeInvalidCommandException {
        if (command.split(" ").length == 1) {
            String wrongCmd = command.split(" ")[0];
            if (wrongCmd.equals("todo") || wrongCmd.equals("deadline") || wrongCmd.equals("event")) {
                throw new DukeInvalidCommandException("☹ OOPS!!! The description of a " + wrongCmd + " cannot be empty.");
            } else {
                throw new DukeInvalidCommandException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        }
        return command.split(" ")[0];
    }

    /**
     * Returns the next command entered by the client.
     *
     * @return The String, regarding the command entered.
     */
    public String getCommand() {
        return sc.nextLine();
    }

    /**
     * Removes redundant blank spaces before and/or after a string command.
     *
     * @param command The command string entered by the client.
     * @return The String, regarding the command entered.
     */
    public String trimCommand(String excepted, String command) {
        return command.replace(excepted, "").trim();
    }

}


