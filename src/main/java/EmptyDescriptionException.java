public class EmptyDescriptionException extends DukeException {

    public EmptyDescriptionException(String type) {
        super("The description of 「" + type + "」 cannot be empty!!");
    }
}
