package duke;

/**
 * Main class file for 'Duke' program for the individual project in CS2103T, AY19/20 Semester 2.
 * Duke serves as a reminder/scheduler for tasks that the user has to complete.
 *
 * @author Dargo
 */
public class Duke {

    private final static String filepath =
            "..\\..\\..\\duke.txt";

    public static void main(String[] args) throws DukeException{

        // DukeUserInterface acts as a driver class for Duke
        DukeUserInterface UI = new DukeUserInterface(filepath);
        UI.runDuke();
    }
}
