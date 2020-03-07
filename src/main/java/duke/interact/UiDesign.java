package duke.interact;

/**
 * Consists of designs that will be used to respond to user input.
 */
public enum UiDesign {
    BORDER("                    -~~o~~- \n"),
    BYE("Bye! See you soon. \n"
            + BORDER.getString()),
    GREET("Hello, I'm Dory!\nWhat do you want to do today? \n"
            + BORDER.getString()),
    DONE_TOP_PART("Great job! I've marked it as done: \n"),
    REMOVE_TOP_PART("I've removed it: \n"),
    LIST_TOP_PART("Just keep swimming, swimming, swimming... \n"),
    ADD_TOP_PART("I will try to remember it: \n"),
    FIND_TOP_PART("Here's what I've found amongst all the marine litter: \n"),
    UPDATE_TOP_PART("I have updated it: \n");

    private final String text;

    UiDesign(final String text) {
        this.text = text;
    }

    public String getString() {
        return text;
    }

}
