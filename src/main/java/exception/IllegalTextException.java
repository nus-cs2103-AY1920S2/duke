package exception;

public class IllegalTextException extends Throwable {

    @Override
    public String getMessage() {
        return "Your input text is invalid, please try again!";
    }
}
