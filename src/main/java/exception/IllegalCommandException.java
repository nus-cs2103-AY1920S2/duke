package exception;

public class IllegalCommandException extends Exception {

    @Override
    public String getMessage() {
        return "\u2639 OOPS!!! I'm sorry, but I don't know what that means :-(";
    }
}
