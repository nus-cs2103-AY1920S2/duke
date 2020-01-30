public class DukeException{
    private Ui ui = new Ui();

    public DukeException(){
    }

    /**
     * This method prints the Error Message for Incorrect Input.
     */
    public void IncorrectInputTodo(){
        System.out.print(ui.line() + "☹ OOPS!!! The description of a todo cannot be empty.\nPlease insert Action\n" + ui.line());
    }

    /**
     * This method prints the Error Message for Incorrect Input for a Deadline Task.
     */
    public void IncorrectInputDeadline(){
        System.out.print(ui.line() + "☹ OOPS!!! The description of a Deadline cannot be empty.\nPlease insert Action\n" + ui.line());
    }

    /**
     * This method prints the Error Message for Incorrect Input for a Event Task.
     */
    public void IncorrectInputEvent(){
        System.out.print(ui.line() + "☹ OOPS!!! The description of a Event cannot be empty.\nPlease insert Action\n" + ui.line());
    }

    /**
     * This method prints the Error Message for Missing Date in Deadline Task.
     */
    public void DeadlineMissingDate() {
        System.out.print(ui.line() + "☹ OOPS!!! Deadline is missing a Date!\nPlease input Date\n" + ui.line());
    }

    /**
     * This method prints the Error Message for Missing Date in Event Task.
     */
    public void EventMissingDate(){
        System.out.print(ui.line() + "☹ OOPS!!! Event is missing a Date!\nPlease input Date\n" + ui.line());
    }

    /**
     * This method prints the Error Message for Invalid Input.
     */
    public void InvalidInput(){
        System.out.print(ui.line() + "☹ OOPS!!! I'm sorry, but I don't know what that means.\n" + ui.line());
    }

    /**
     * This method prints the Error Message for Invalid Number.
     */
    public void ExceedList(){
        System.out.print(ui.line() + "☹ Invalid Number. Please Enter a valid number.\n" + ui.line());
    }

    /**
     * This method prints the Error Message for Invalid Date Format.
     */
    public void InvalidDateFormat(){
        System.out.print(ui.line() + "☹ Invalid Date Format! Enter in /by yyyy-mm-dd\n" + ui.line());
    }

    /**
     * This method prints the Error Message for Invalid Time Format.
     */
    public void InvalidTimeFormat(){
        System.out.print(ui.line() + "☹ Invalid Date Format! Enter in hh:mm\n" + ui.line());
    }


}
