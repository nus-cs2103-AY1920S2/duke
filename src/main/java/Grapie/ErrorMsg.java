package Grapie;

public class ErrorMsg {
    /**
     * All Error Messages thrown by Grapie.Exceptions.GrapieExceptions are under this class.
     */
    public ErrorMsg() {
    }

    public static String emptyDescriptionError = "OOPS!!! The description cannot be empty.";
    public static String invalidNumberError = "OOPS!!! Please input a valid number behind!!";
    public static String wakarimasenError = "OOPS!!! I'm sorry, but I don't know what that means :-(";
    public static String eventFormatError = "OOPS!!! Grapie.Event in wrong format. Please use: event your_event "
            + "/at YYYY-MM-DD TTTT";
    public static String deadlineFormatError = "OOPS!!! Grapie.Deadline in wrong format. Please use: deadline "
            + "your_deadline /by YYYY-MM-DD TTTT";
    public static String emptyKeywordError = "OOPS!!! Please input a valid word behind find!!!";
    public static String noSpaceError = "OOPS!!! Please leave a space between command and details!!";

    public static String taskNumIsAlreadyDone(int num) {
        String errorMsg = "OOPS!!! Grapie.Task number " + num + " is already done!!!";
        return errorMsg;
    }

    public static String numberDoNotExistError(int num) {
        String errorMsg = "OOPS!!! Grapie.Task number " + num + " do not exist!!!";
        return errorMsg;
    }

}
