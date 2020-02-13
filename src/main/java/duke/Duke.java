package duke;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import duke.exceptions.IncorrectArgumentException;
import duke.exceptions.InvalidCommandException;


/**
 * Duke class represents the main chatbot entity; it contains classes that manage different components of the chatbot.
 */
public class Duke {
    /** the path where the saved Tasklist file is stored. */
    private static final Path filePath = Paths.get(System.getProperty("user.dir"), "data.duke");

    private Storage storage;
    private TaskList tasks = new TaskList();
    private Ui ui;
    private Parser parser;

    public static void main(String[] args) {
        new Duke().run();
    }

    /**
     * Initializes Duke and loads the TaskList from the files in the save directory.
     */
    public Duke() {
        storage = new Storage(filePath);
        parser = new Parser(storage, ui, tasks);
        try {
            storage.loadFromFile(tasks);
            System.out.println("    Tasklist loaded!");
        } catch (FileNotFoundException e) {
            System.out.println("    No prior tasklist found...");
        } catch (IOException e) {
            System.out.println("    Existing tasklist cannot be read...");
        } catch (ClassNotFoundException e) {
            System.out.println("    Existing tasklist cannot be read...");
        }
    }

    /** Starts Duke and begins reading commands from input. */
    private void run() {       
        ui = new Ui();
        ui.out("Hello! I'm Duke", "What can I do for you?");
        boolean isShutdown = false;
        assert (ui != null);
        while (!isShutdown) {
            String input = ui.getLine();
            isShutdown = input.equals("bye");
            try {
                ui.out(parser.parse(input));
                storage.saveToFile(tasks);
            } catch (InvalidCommandException | IncorrectArgumentException e) {
                ui.out(e.getMessage());
            } catch (IOException | NumberFormatException e) {
                e.printStackTrace();
            }
        }
        ui.out("Bye. Hope to see you again soon!");
        ui.close();
    }

    public String getResponse(String input) {
        String response; 
        try {
            response = ui.respond(parser.parse(input));
            storage.saveToFile(tasks);
        } catch (InvalidCommandException | IncorrectArgumentException e) {
            response = e.getMessage();
            System.out.println(e.getMessage());
        } catch (IOException | NumberFormatException e) {
            response = e.getMessage();
            e.printStackTrace();
        }
        return response;
    }
}
