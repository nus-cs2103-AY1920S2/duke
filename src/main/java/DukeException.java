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
        }
        return message;
    }


}
