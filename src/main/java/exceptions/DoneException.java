package exceptions;

public class DoneException extends Exception{
    //private static final String SPACE = "     ";

    public DoneException() {};

    public String toString() {
        return "☹ OOPS!!! You must specify a Task to be done!";
    }
}
