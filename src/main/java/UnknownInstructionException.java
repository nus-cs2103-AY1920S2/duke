/**
 * An Exception to be generated when the input
 * to 4LC3N-BOT is not a recognisable instruction
 */
public class UnknownInstructionException extends Exception {
    public UnknownInstructionException(String command) {
        super("Linguistic circuits failing. " +
            command + " is unrecognised." +
            " Beep boop beep boop.");
    }
}
