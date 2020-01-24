public class EmptyException extends Exception {
    public static final String space = "     ";
    public EmptyException() {};

    public String toString() {
        return space + " ☹ OOPS!!! The description of a Task cannot be empty.";
    }
}
