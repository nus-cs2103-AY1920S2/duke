public class InvalidCommandException extends Exception {
    public InvalidCommandException(String msg) {
        super(String.format("\\'\\%s\\'\\ is an invalid command\nPlease type something legit", msg));
    }
}
