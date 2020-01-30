package duke;

/**
 * Exception that returns the error message according to errorType given.
 */
public class DukeException extends Exception {
    String errorType;
    String inputType;

    DukeException(String errorType, String inputType) {
        super();
        this.errorType = errorType;
        this.inputType = inputType;
    }

    @Override
    public String getMessage() {
        String message = "";
        switch (errorType) {
            case "empty":
                message = "OOPS!!! The description of a " + inputType + " cannot be empty.";
                break;
            case "invalid":
                message = "OOPS!!! I'm sorry, but I don't know what that means :-(";
                break;
            case "date":
                message = "OOPS!!! Your date needs to be in yyyy-mm-dd format!";
                break;
            case "file":
                message = "OOPS!!! The save file cannot be found!";
                break;
            case "read":
                message = "OOPS!!! The save file cannot be read. A new task list will be created!";
                break;
            case "emptyLoad":
                message = "OOPS!!! The save file is empty. A new task list will be created!";
                break;
        }
        return message;
    }


}
