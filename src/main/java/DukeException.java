/**
 * throws exception specifically to duke
 */
public class DukeException extends Exception {
    public DukeException(String message) {
        super(message);
    }

    /**
     * checks whether done/delete command entered by used is valid
     * @param command the line input by the user
     * @param insert the name of the command (either done or delete)
     * @param listSize the current size of Tasklist
     * @throws DukeException throws an exception is the command is invalid
     */
    public static void checkCommand(String command, String insert, int listSize) throws DukeException {
        String[] arr = command.split(" ");
        if (arr.length < 2) {
            throw new DukeException("The task to be marked as " + insert + " must be specified");
        }
        if (Integer.valueOf(arr[1]) - 1 >= listSize) {
            throw new DukeException("Task " + arr[1] + " does not exist");
        }
    }

    /**
     * checks if the description for the task entered by the user is valid
     * @param description the input
     * @param insert the type of task (either todo, deadline or event)
     * @throws DukeException throws an exception is the command is invalid
     */
    public static void checkDescription (String[] description, String insert) throws DukeException {
        if (description.length < 2) {
            throw new DukeException("The description of a " + insert + " cannot be empty.");
        }
    }

    /**
     * checks if the time for the task entered by the user is valid
     * @param arr the input
     * @param insert the type of task (either deadline or event)
     * @throws DukeException throws an exception is the command is invalid
     */
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