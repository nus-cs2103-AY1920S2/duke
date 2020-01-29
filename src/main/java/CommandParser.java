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
        String firstWord = command.split("\\s+", 2)[0];
        if (firstWord.equals(Command.BYE.word)) {
            return Instruction.TERMINATE;
        } else if (firstWord.equals(Command.DELETE.word)) {
            return Instruction.DELETE;
        } else if (firstWord.equals(Command.DONE.word)) {
            return Instruction.MARK_DONE;
        } else if (firstWord.equals(Command.LIST.word)) {
            return Instruction.READ_STORAGE;
        } else if (firstWord.equals(Command.DEADLINE.word)) {
            return Instruction.STORE_DDL;
        } else if (firstWord.equals(Command.EVENT.word)) {
            return Instruction.STORE_EVENT;
        } else if (firstWord.equals(Command.TODO.word)) {
            return Instruction.STORE_TODO;
        } else if (firstWord.equals(Command.SEARCH.word)) {
            return Instruction.SEARCH_STORAGE;
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