package commands;

//import seedu.addressbook.data.person.ReadOnlyPerson;

/**
 * Represents the result of a command execution.
 */
public class CommandResult {

    /**
     * The feedback message to be shown to the user. Contains a description of the execution result
     */
    public final String feedbackToUser;

    public CommandResult(String feedbackToUser) {
        this.feedbackToUser = feedbackToUser;
    }
}