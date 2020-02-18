package core;

/**
 * Stores all the essential normal text to be display by Ui.
 */
public enum UiMessage {

    GREETING("A long time ago in a galaxy far, far away...",
            "Hello! I'm Luke",
            "How may i serve you."),
    FAREWELL("Bye. Someday I will be the most powerful Jedi ever."),
    RESET("Train yourself to let go of everything you fear to lose.",
            "The system has been reset.");

    String[] msg;

    UiMessage(String... msg) {
        this.msg = msg;
    }

    /**
     * Gets the message of the operation.
     *
     * @return the message of the operation in string array.
     */
    public String[] getMsg() {
        return msg;
    }
}
