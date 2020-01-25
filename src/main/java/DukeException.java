// Illegal argument exceptions for Event, Deadline , To-do

class DukeException extends Exception {


    DukeException(String errorMessage, String task_type) {
        super("OOPS!! The description of a " + Task.Task_Type.valueOf(task_type) + " cannot be empty" + errorMessage);
    }

    DukeException(String errorMessage) {
        super("OOPS!! " + errorMessage);
    }


}
