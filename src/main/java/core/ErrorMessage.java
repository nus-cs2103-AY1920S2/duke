package core;

/**
 * Stores all the essential error text to be display by Ui.
 */
public enum ErrorMessage {

    INDEX_OUT_OF_BOUND("Index out of bound"),
    SINGLETON("There can be only one"),
    LACK_DESCRIPTION("The description cannot be empty."),
    LACK_INPUT("The input must contain"),
    LACK_TIME("The time cannot be empty."),
    LACK_NUMBER("The input must contain a number"),
    UNKNOWN_INPUT("I'm sorry, but I don't know what that means :-("),
    EMPTY_LIST("Task list is empty."),
    UNRECOGNISED_DATE_FORMAT("Unrecognised date format."),
    DONE_TASK("The task is already done.");

    String msg;
    ErrorMessage(String msg){
        this.msg="☹ OOPS!!! "+msg;
    }

    public String ofType(String type){
        assert this.equals(SINGLETON);
        msg+=type+".";
        return msg;
    }

    @Override
    public String toString() {
        return msg;
    }
}
