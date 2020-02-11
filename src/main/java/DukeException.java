import javafx.scene.image.Image;

public class DukeException{

    public DukeException(){
    }

    /**
     * This method return the Error Message for Incorrect Input.
     */
    public String IncorrectInputTodo(){
        return "OOPS!!! The description of a todo cannot be empty.\nPlease insert Action\n";
    }

    /**
     * This method return the Error Message for Incorrect Input for a Deadline Task.
     */
    public String IncorrectInputDeadline(){
        return "OOPS!!! The description of a Deadline cannot be empty.\nPlease insert Action\n" ;
    }

    /**
     * This method return the Error Message for Incorrect Input for a Event Task.
     */
    public String IncorrectInputEvent(){
        return "OOPS!!! The description of a Event cannot be empty.\nPlease insert Action\n";
    }

    /**
     * This method return the Error Message for Missing Date in Deadline Task.
     */
    public String DeadlineMissingDate() {
        return "OOPS!!! Deadline is missing a Date!\nPlease input Date\n";
    }

    /**
     * This method return the Error Message for Missing Date in Event Task.
     */
    public String EventMissingDate(){
        return "OOPS!!! Event is missing a Date!\nPlease input Date\n";
    }

    /**
     * This method return the Error Message for Invalid Input.
     */
    public String InvalidInput(){
        return "OOPS!!! I'm sorry, but I don't know what that means.\n";
    }

    /**
     * This method return the Error Message for Invalid Number.
     */
    public String ExceedList(){
        return "Invalid Number. Please Enter a valid number.\n";
    }

    /**
     * This method return the Error Message for Invalid Date Format.
     */
    public String InvalidDateFormat(){
        return "Invalid Date Format! Enter in /by yyyy-mm-dd\n";
    }

    /**
     * This method return the Error Message for Invalid Time Format.
     */
    public String InvalidTimeFormat(){
        return "Invalid Date Format! Enter in hh:mm\n";
    }

    /**
     * This method return Error Message for no matched found.
     */
    public String NoMatchesFound(){
        return "What you are looking for does not exist.\n";
    }

    public String MissingDoneIndex() {
        return "OOPS you're missing an Index.";
    }


}
