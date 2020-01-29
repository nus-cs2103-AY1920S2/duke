/**
 * Consists of designs that will be used to respond to user input.
 */
public enum UiDesign {
    BORDER("    ____________________________________________________________\n"),
    LOGO("      ____        _        \n"
            + "     |  _ \\ _   _| | _____ \n"
            + "     | | | | | | | |/ / _ \\\n"
            + "     | |_| | |_| |   <  __/\n"
            + "     |____/ \\__,_|_|\\_\\___|\n"),
    BYE("    ____________________________________________________________\n"
            + "     Bye. Hope to see you again soon!\n"
            + "    ____________________________________________________________\n"),
    GREET("    ____________________________________________________________\n"
            + LOGO.getString()
            + "\n     Hello! I'm Duke\n     What can I do for you?\n"
            + "    ____________________________________________________________\n"),
    DONE_TOP_PART("    ____________________________________________________________\n"
            + "     Nice! I've marked this task as done: \n"),
    REMOVE_TOP_PART("    ____________________________________________________________\n"
            + "     Noted. I've removed this task: \n"),
    LIST_TOP_PART("    ____________________________________________________________\n"
            + "     Here are the tasks in your list: \n"),
    ADD_TOP_PART("    ____________________________________________________________\n"
            + "     Got it. I've added this task: \n")
    ;

    private final String text;

    UiDesign(final String text) {
        this.text = text;
    }

    public String getString() {
        return text;
    }

}
