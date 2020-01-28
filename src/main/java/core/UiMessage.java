package core;

/**
 * Stores all the essential normal text to be display by Ui.
 */
public enum UiMessage {

    GREETING("Hello! I'm Duke",
            "What can I do for you?"),
    FAREWELL("Bye. Hope to see you again soon!"),
    RESET("The system has been reset.");

    String[] msg;

    UiMessage(String... msg){
        this.msg=msg;
    }

    /**
     * get the message of the operation.
     * @return the message of the operation in string array.
     */
    public String[] getMsg() {
        return msg;
    }
}
