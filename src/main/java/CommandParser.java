import java.util.ArrayList;

/**
 * Class to contain logic for commands
 * given to bot
 */
public class CommandParser {
    private final ArrayList<String> storage = new ArrayList<String>();

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
                readStorage();
                return Instruction.AWAIT;
            default:
                // store the item
                store(command);
                return Instruction.AWAIT;
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

    /**
     * Adds a String to the stored items
     *
     * @param toStore The String to be stored
     */
    private void store(String toStore) {
        this.storage.add(toStore);
    }

    /**
     * Prints out all the stored items,
     * in order which they were stored
     */
    private void readStorage() {
        int length = this.storage.size();
        for (int i = 1; i <= length; i++) {
            System.out.println(i + ". " + this.storage.get(i));
        }
    }
}