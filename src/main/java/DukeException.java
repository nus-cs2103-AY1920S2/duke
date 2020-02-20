public class DukeException extends Exception {
    private String type;

    public DukeException (String type) {
        this.type = type;
    }

    public String getMessage() {
        if (type.equals("EmptyToDo")) {
            return "OOPS!!! The description of a todo cannot be empty.";
        }
        if (type.equals("EmptyDeadline")) {
            return "OOPS!!! The description of a deadline cannot be empty.";
        }
        if (type.equals("EmptyEvent")) {
            return "OOPS!!! The description of a event cannot be empty.";
        }
        return "OOPS!!! I'm sorry, but I don't know what that means :-(";
    }
}