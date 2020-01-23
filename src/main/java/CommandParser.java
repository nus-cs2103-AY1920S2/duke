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
    public Instruction parse(String command) throws UnknownInstructionException {
        if (command.equals(Command.BYE.word)) {
            return Instruction.TERMINATE;
        } else if (command.startsWith(Command.DONE.word)) {
            return Instruction.MARK_DONE;
        } else if (command.equals(Command.LIST.word)) {
            return Instruction.READ_STORAGE;
        } else if (command.startsWith(Command.DEADLINE.word)) {
            return Instruction.STORE_DDL;
        } else if (command.startsWith(Command.EVENT.word)) {
            return Instruction.STORE_EVENT;
        } else if (command.startsWith(Command.TODO.word)) {
            return Instruction.STORE_TODO;
        } else {
            throw new UnknownInstructionException(command);
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