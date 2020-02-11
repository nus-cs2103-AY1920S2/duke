import duke.util.CommandHandler;

import java.io.IOException;

import duke.util.Parser;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

/**
 * Duke
 *
 * CS2103T AY19/20 Semester 2
 * Individual project
 * Duke project
 *
 * 11 Feb 2020
 *
 * @author Jel
 */
public class Duke {
    private static Parser parser;
    private static Storage storage;
    private static TaskList tasks;
    private static CommandHandler cmd;
    private static Ui ui;

    /**
     * Constructs a Duke instance.
     */
    public Duke() {
        String filePath = "data/duke.txt";
        storage = new Storage(filePath);
        ui = new Ui();
        tasks = new TaskList(storage, ui);
        cmd = new CommandHandler(tasks);
        parser = new Parser(cmd, ui);

        try {
            storage.loadTasks();
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    public static void main(String[] args) {
        new Duke();
        ui.run();
        String input = ui.getInput();
        while (!input.equals("bye")) {
            System.out.println(parser.parseLine(input));
            input = ui.getInput();
        }

        if (input.equals("bye")) {
            ui.bye();
        }
    }

    /**
     * Gets Duke's response given input from user.
     * @param input User's input.
     * @return Duke's response as a string.
     */
    public String getResponse(String input) {
        return parser.parseLine(input);
    }
}
