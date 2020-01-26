public class ErrorMsg {
    public ErrorMsg() {}


    public static String emptyDescriptionError = "OOPS!!! The description cannot be empty.";
    public static String invalidNumberError = "OOPS!!! Please input a valid number behind!!";
    public static String wakarimasenError = "OOPS!!! I'm sorry, but I don't know what that means :-(";

    public static String taskNumIsAlreadyDone(int num) {
        String errorMsg = "OOPS!!! Task number " + num + " is already done!!!";
        return errorMsg;
    }

    public static String numberDoNotExistError(int num) {
        String errorMsg = "OOPS!!! Task number " + num + " do not exist!!!";
        return  errorMsg;
    }


    //public static String eventFormatError = "OOPS!!! Event in wrong format. Please use: event your_event /at YYYY-MM-DD TTTT";
    public static String wrongFormatError(String correctFormat) {
        String errorMsg = "OOPS!!! Event in wrong format. Please use: " + correctFormat;
        return errorMsg;
    }

}
