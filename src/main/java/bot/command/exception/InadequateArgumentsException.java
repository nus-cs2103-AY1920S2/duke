package bot.command.exception;

/**
 * An Exception to be generated that signifies
 * too little instructions given to 4LC3N-BOT
 * in a single line
 */
public class InadequateArgumentsException extends Exception {
    /**
     * Constructor for an InadequateArgumentsException
     *
     * @param command The word that represents the
     *                original command that led to the
     *                generation of the Exception
     */
    public InadequateArgumentsException(String command) {
        super("Beep beep beep! I expect more" +
            " information for " + command + "!");
    }
}
