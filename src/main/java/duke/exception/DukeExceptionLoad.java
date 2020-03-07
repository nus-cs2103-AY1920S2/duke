package duke.exception;

/**
 * Generates an error message for the situation where the txt file cannot be read from.
 */
public class DukeExceptionLoad extends DukeException {

    /**
     * Generates the correct error message for each load error.
     */
    public DukeExceptionLoad(String type) {
        switch (type) {
        case "filetype":
            super.errorMsg = "The file type is wrong, I cant read the file.";
            break;

        case "path":
            super.errorMsg = "Hmmm... I cant seem to locate the path where I have written the tasks at.";
            break;

        case "format":
            super.errorMsg = "My memory was formatted in the wrong manner.";
            break;

        default:
            super.errorMsg = "";
        }
    }
}
