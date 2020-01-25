public class ExceptionGenerator {
    public static void checkInputLength(String[] input) throws DukeException{
        if(input.length == 1 && input[0].equals("todo")) {
            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
        } else if(input.length == 1 && input[0].equals("deadline")){
            throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
        }
        else if(input.length == 1 && input[0].equals("event")){
            throw new DukeException("☹ OOPS!!! The description of a event cannot be empty.");
        }

    }
    public static void checkInputAction(String[] input) throws DukeException {
        if(!input[0].equals("todo") && !input[0].equals("list")
                && !input[0].equals("delete") && !input[0].equals("event")
                    && !input[0].equals("deadline") && !input[0].equals("done")){
            throw new DukeException("  ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

}
