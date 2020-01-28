package tojava.test;

public class DukeException extends Throwable {
    protected String task;

    public String errorMsg(String task) {
        if (task.equals("todo") || task.equals("deadline") || task.equals("event")) {
            return "☹ OOPS!!! The description of a " +task+ " cannot be empty.";
        }
        else {
            return "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
        }
    }

    public String empty() {
        return "There is no task in the list.";
    }

    public String outOfBound() {
        return "No such task exist.";
    }
}
