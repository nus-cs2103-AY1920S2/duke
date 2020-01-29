package seedu.duke;

import java.io.IOException;
import java.util.Scanner;

public class Duke {
    private Ui ui;
    private TaskList taskList;
    private Storage storage;

    /**
     * Constructor for Duke.
     *
     * @param filePath path of the file being opened
     * @throws IOException if an input or output exception occurred
     */
    public Duke(String filePath) throws IOException {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            taskList = new TaskList(storage.load(), storage);
        } catch (DukeException e) {
            ui.showLoadingError();
            taskList = new TaskList(null, null);
        }
    }

    /**
     * Reads the user command and passes it to Ui to be processed accordingly.
     */
    private void runDuke() {
        ui.greet();
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String input = sc.nextLine();
            String[] inputs = input.split(" ", 2);
            String command = inputs[0];
            if (command.equalsIgnoreCase("bye")) {
                sayBye();
                break;
            } else {
                ui.handleCommands(inputs, taskList);
            }
        }
    }

    private void sayBye() {
        System.out.println("Stop procrastinating. See you!");
    }

    /**
     * Main method to run Duke.
     */
    public static void main(String[] args) throws IOException {
        Duke duke;
        String path = "data/duke.txt";
        duke = new Duke(path);
        duke.runDuke();
    }
}
