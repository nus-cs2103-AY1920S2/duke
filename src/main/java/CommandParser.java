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
     *         it what to do next
     */
    public Instruction parse(String command) {
        if (command.equals("bye")) {
            return Instruction.TERMINATE;
        } else if (command.startsWith("done ")) {
            return Instruction.MARK_DONE;
        } else if (command.equals("list")) {
            return Instruction.READ_STORAGE;
        } else if (command.startsWith("deadline ")) {
            return Instruction.STORE_DDL;
        } else if (command.startsWith("event ")) {
            return Instruction.STORE_EVENT;
        } else if (command.startsWith("todo ")) {
            return Instruction.STORE_TODO;
        } else {
            return Instruction.AWAIT;
        }
    }


    /**
     * Method to throw a command back to
     * the user
     *
     * @param command The command, as a String
     */
    public void echo(String command) {
        System.out.println(command);
    }
}