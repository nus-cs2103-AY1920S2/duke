public class DukeException{
    String line = "____________________________________________________________";
    public DukeException(){
    }
    public void IncorrectInputTodo(){
        System.out.println(line);
        System.out.println("☹ OOPS!!! The description of a todo cannot be empty.\nPlease insert Action");
        System.out.println(line);
    }
    public void IncorrectInputDeadline(){
        System.out.println(line);
        System.out.println("☹ OOPS!!! The description of a Deadline cannot be empty.\nPlease insert Action");
        System.out.println(line);
    }
    public void DeadlineMissingDate(){
        System.out.println(line);
        System.out.println("☹ OOPS!!! Deadline is missing a Date!\nPlease input Date");
        System.out.println(line);
    }
    public void IncorrectInputEvent(){
        System.out.println(line);
        System.out.println("☹ OOPS!!! The description of a Event cannot be empty.\nPlease insert Action");
        System.out.println(line);
    }
    public void EventMissingDate(){
        System.out.println(line);
        System.out.println("☹ OOPS!!! Event is missing a Date!\nPlease input Date");
        System.out.println(line);
    }
    public void InvalidInput(){
        System.out.println(line);
        System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        System.out.println(line);
    }
    public void ExceedList(){
        System.out.println(line);
        System.out.println("☹ Invalid Number. Please Enter a valid number.");
        System.out.println(line);
    }
    public void InvalidDateFormat(){
        System.out.println(line);
        System.out.println("☹ Invalid Date Format! Enter in /by yyyy-mm-dd");
        System.out.println(line);
    }
    public void InvalidTimeFormat(){
        System.out.println(line);
        System.out.println("☹ Invalid Date Format! Enter in hh:mm");
        System.out.println(line);
    }


}
