package exceptions;

public class DoneException extends Exception{
    public DoneException() {};

    public String toString() {
        return "☹ OOPS!!! You must specify a Task to be done!";
    }
}
