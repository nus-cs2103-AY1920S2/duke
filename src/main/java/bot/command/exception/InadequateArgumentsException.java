package bot.command.exception;

/**
 * An Exception to be generated that signifies
 * too little instructions given to 4LC3N-BOT
 * in a single line
 */
public class InadequateArgumentsException extends Exception {
    public InadequateArgumentsException(String command) {
        super("Beep beep beep! I expect more" +
            " information for " + command + "!");
    }
}
