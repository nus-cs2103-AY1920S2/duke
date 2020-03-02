package exceptions;

public class DeleteException extends Exception {
    public DeleteException() {};

    public String toString() {
        return "☹ OOPS!!! You must specify a Task to be deleted!";
    }
}
