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
            super.errorMsg = "... What were the tasks? I cant seem to recall them.";
            break;

        case "path":
            super.errorMsg = "Hmmm... I cant seem to locate where I have written the tasks at.";
            break;

        case "format":
            super.errorMsg = "da@#slkf? My memory was formatted in the wrong manner.";
            break;

        default:
            super.errorMsg = "";
        }
    }
}
