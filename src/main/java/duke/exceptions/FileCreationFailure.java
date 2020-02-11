package duke.exceptions;

public class FileCreationFailure extends DukeException {
    @Override
    public String toString() {
        return "File was not created successfully. Please check your file structure.";
    }
}
