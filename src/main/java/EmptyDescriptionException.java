/**
 * EmptyDescriptionException handles the exception in the case where event,
 * deadline and todo task's description is empty.
 */
public class EmptyDescriptionException extends DukeException {

    /**
     * Constructs EmptyDescription object in the event that the user typed command without description.
     * @param type Type of task.
     */
    public EmptyDescriptionException(String type) {
        super("The description of 「" + type + "」 cannot be empty!!");
    }
}
