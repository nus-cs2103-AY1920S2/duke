package duke;

/**
 * Main class file for 'Duke' program for the individual project in CS2103T, AY19/20 Semester 2.
 * Duke serves as a reminder/scheduler for tasks that the user has to complete.
 *
 * @author Dargo
 */
public class Duke {

    private static DukeUserInterface USER_INTERFACE;

    private final static String filepath =
            "..\\..\\..\\duke.txt";

    public Duke() throws DukeException {
        this.USER_INTERFACE = new DukeUserInterface(filepath);
    }

    public static void main(String[] args) {

        USER_INTERFACE.runDuke();
    }

    /**
     * Generates response for Duke GUI.
     *
     * @param input Input command.
     * @return Output response message of Duke program.
     */
    public String getResponse(String input) {

        String response = this.USER_INTERFACE.runDukeWithInterface(input);

        return response;
    }
}
