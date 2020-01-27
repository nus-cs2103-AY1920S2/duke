package duke;

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
                message = "OOPS!!! The save file cannot be found";
                break;
        }
        return message;
    }


}
