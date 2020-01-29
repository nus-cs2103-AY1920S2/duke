/**
 * Generates an error message for the situation where user input cant be decoded.
 */
public class DECommand extends DukeException{
    public DECommand() {
        super.errorMsg = "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
    }
}
