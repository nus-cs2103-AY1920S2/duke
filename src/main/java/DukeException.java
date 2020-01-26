public class DukeException extends Exception {
    public DukeException(String s)
    {
        // Call constructor of parent Exception
        super("OOPS! " + s);
    }
}
