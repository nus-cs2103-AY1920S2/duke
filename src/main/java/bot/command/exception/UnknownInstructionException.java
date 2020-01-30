package bot.command.exception;

/**
 * An Exception to be generated when the input
 * to 4LC3N-BOT is not a recognisable instruction
 */
public class UnknownInstructionException extends Exception {
    /**
     * Constructor for an UnknownInstructionException
     *
     * @param command The word that represents the
     *                original command that led to the
     *                generation of the Exception
     */
    public UnknownInstructionException(String command) {
        super("Linguistic circuits failing. " + command
                + " is unrecognised. Beep boop beep boop.");
    }
}
