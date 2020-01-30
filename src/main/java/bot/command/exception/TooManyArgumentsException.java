package bot.command.exception;

/**
 * An Exception to be generated that signifies
 * too many instructions given to 4LC3N-BOT
 * in a single line
 */
public class TooManyArgumentsException extends Exception {
    public TooManyArgumentsException(String command) {
        super("Beep boop boop! " + command
                + " does not need so many instructions!");
    }
}
