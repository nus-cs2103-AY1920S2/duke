package exceptions;

public class UnknownException extends Exception{
    private static final String SPACE = "     ";
    public UnknownException() {};

    public String toString() {
        return SPACE + " ☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
    }
}
