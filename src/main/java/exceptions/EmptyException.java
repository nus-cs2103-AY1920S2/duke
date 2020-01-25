package exceptions;

public class EmptyException extends Exception {
    public static final String SPACE = "     ";
    public EmptyException() {};

    public String toString() {
        return SPACE + " ☹ OOPS!!! The description of a Task cannot be empty.";
    }
}
