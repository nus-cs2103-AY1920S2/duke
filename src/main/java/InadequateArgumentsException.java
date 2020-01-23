public class InadequateArgumentsException extends Exception {
    public InadequateArgumentsException(String command) {
        super("Beep beep beep! I expect more" +
            " information for " + command + "!");
    }
}
