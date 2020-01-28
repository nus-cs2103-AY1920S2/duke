public class DukeException extends Exception {
    public DukeException() {
    }
    public DukeException(String message) {
        super(message);
    }
    public DukeException(String message, Throwable cause) {
        super(message, cause);
    }
    public DukeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
    public DukeException(Throwable cause) {
        super(cause);
    }

    public static void checkCommand(String command, String insert, int listSize) throws DukeException {
        String[] arr = command.split(" ");
        if (arr.length < 2) {
            throw new DukeException("The task to be marked as " + insert + " must be specified");
        }
        if (Integer.valueOf(arr[1]) - 1 >= listSize) {
            throw new DukeException("Task " + arr[1] + " does not exist");
        }
    }

    public static void checkDescription (String[] description, String insert) throws DukeException {
        if (description.length < 2) {
            throw new DukeException("The description of a " + insert + " cannot be empty.");
        }
    }

    public static void checkTime (String[] arr, String insert) throws DukeException {
        if (arr.length < 2) {
            throw new DukeException("The time of a " + insert + " cannot be empty.");
        }
        String[] time = (arr[1].split(" ", 2));
        if (time.length < 2) {
            throw new DukeException("The time of a " + insert + " cannot be empty.");
        }
    }

}