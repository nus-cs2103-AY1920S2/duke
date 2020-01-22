// Illegal argument exceptions for Event, Deadline , To-do

class DukeException extends Exception {

    // Exception when task type is empty. Eg: when deadline is typed
    // But there is no task at hand.
    DukeException(String errorMessage, String task_type) {
        super("OOPS!! The description of a " + Task.Task_Type.valueOf(task_type) + " cannot be empty" + errorMessage);
    }

    DukeException(String errorMessage) {
        super(errorMessage);
    }


}
