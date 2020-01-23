public class UnknownInstructionException extends Exception {
    public UnknownInstructionException(String command) {
        super("Linguistic circuits failing. " +
            command + " is unrecognised." +
            " Beep boop beep boop.");
    }
}
