package duke;

import duke.command.Command;
import duke.task.TaskList;

import java.io.IOException;
import java.util.Scanner;

/**
 * The type Duke.
 */
public class Duke {
    private Ui ui;
    private Parser parser;
    private Storage storage;
    private TaskList taskList = new TaskList();

    /**
     * The constant breakChecker.
     */
    public static boolean breakChecker = false;

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     * @throws DukeException the duke exception
     * @throws IOException   the io exception
     */
    public static void main(String[] args) throws DukeException, IOException {
        new Duke("data/duke.txt").run();
    }

    /**
     * Instantiates a new Duke.
     *
     * @param filePath the file path
     */

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
    }

    private void run() throws DukeException, IOException {
        Scanner sc = new Scanner(System.in);
        // Welcome message for the user
        ui.showWelcome();

        while (sc.hasNext()) {
            String input = sc.nextLine();
            String fullCommand = ui.readCommand(input);
            Command c = Parser.parse(fullCommand,input);
            c.execute(storage, ui,taskList);
            if (breakChecker) {
                break;
            }
        }
    }



}