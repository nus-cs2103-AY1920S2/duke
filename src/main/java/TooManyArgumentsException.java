public class TooManyArgumentsException extends Exception {
    public TooManyArgumentsException(String command) {
        super("Beep boop boop! " +
            command + " does not need" +
            " so many instructions!");
    }
}
