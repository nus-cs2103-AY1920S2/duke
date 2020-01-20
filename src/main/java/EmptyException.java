public class EmptyException extends Exception {
    public static final String string = "    ____________________________________________________________";
    public static final String space = "     ";
    public EmptyException() {};

    public String toString() {
        return string + "\n" + space + " ☹ OOPS!!! The description of a Task cannot be empty.\n" + string;
    }
}
