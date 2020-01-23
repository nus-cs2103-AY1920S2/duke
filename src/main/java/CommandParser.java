import java.util.ArrayList;

/**
 * Class to contain logic for commands
 * given to bot
 */
public class CommandParser {
    /**
     * Main method to accept a command, and
     * generate the corresponding instruction
     * for the bot to execute
     *
     * @param command The command, as a String
     * @return Instruction to the bot, telling
     *      it what to do next
     */
    public Instruction parse(String command) {
        switch (command) {
            case "bye":
                return Instruction.TERMINATE;
            case "list":
                return Instruction.READ_STORAGE;
            default:
                // store the item
                return Instruction.STORE;
        }
    }


    /**
     * Method to throw a command back to
     * the user
     *
     * @param command The command, as a String
     */
    private void echo(String command) {
        System.out.println(command);
    }
}