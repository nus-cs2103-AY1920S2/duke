public class DoneException extends Exception{
    public static final String string = "    ____________________________________________________________";
    public static final String space = "     ";
    public DoneException() {};

    public String toString() {
        return string + "\n" + space + " ☹ OOPS!!! You must specify a Task to be done!\n" + string;
    }
}
