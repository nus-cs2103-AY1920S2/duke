import duke.util.CommandHandler;

import java.io.File;
import java.io.IOException;

import duke.util.Parser;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

/**
 * Represents the chat bot Duke.
 *
 * <p>CS2103T AY19/20 Semester 2
 * Individual Duke project
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
        ui = new Ui();
        storage = new Storage("data/duke.txt");
        tasks = new TaskList(storage, ui);
        cmd = new CommandHandler(tasks);
        parser = new Parser(cmd, ui);

        try {
            storage.loadTasks();
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    /**
     * Driver method for CLI version of Duke.
     * 
     * @param args Command line arguments supplied.
     */
    public static void main(String[] args) {
        (new Duke()).run();
        String input = ui.getInput();
        while (!input.equals("bye")) {
            System.out.println(parser.parseLine(input));
            input = ui.getInput();
        }

        if (input.equals("bye")) {
            System.out.println(ui.bye());
        }
    }

    /**
     * Introduces Duke.
     */
    public void run() {
        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        System.out.println("____________________________________________________________");
    }

    /**
     * Gets Duke's response given input from user.
     * 
     * @param input User's input.
     * @return Duke's response as a string.
     */
    public String getResponse(String input) {
        return parser.parseLine(input);
    }
}
