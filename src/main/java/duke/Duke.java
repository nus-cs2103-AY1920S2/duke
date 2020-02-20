package duke;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

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

    // public static void main(String[] args) {
    //     new Duke().run();
    // }

    /**
     * Initializes Duke and loads the TaskList from the files in the save directory.
     */
    public Duke() {
        ui = new Ui();
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
    // private void run() {       
    //     ui.out("Hello! I'm Duke", "What can I do for you?");
    //     assert (ui != null);
    //     while (true) {
    //         String input = ui.getLine();
    //         if(input.equals("bye")) {
    //             break;
    //         }
    //         try {
    //             if (input.equals("undo")) {
    //                 storage.loadFromFile(tasks);
    //             } else {
    //                 storage.saveToFile(tasks);
    //                 ui.out(parser.parse(input));
    //             }
    //         } catch (InvalidCommandException | IncorrectArgumentException e) {
    //             ui.out(e.getMessage());
    //         } catch (IOException | NumberFormatException | ClassNotFoundException e) {
    //             e.printStackTrace();
    //         }
    //     }
    //     ui.out("Bye. Hope to see you again soon!");
    //     ui.close();
    // }

    public String getResponse(String input) {
        String response = ""; 
        try {
            if (input.equals("undo")) {
                storage.loadFromFile(tasks);
                response = "Last Command Undone!";
            } else if (input.equals("bye")) {
                shutdown();
                response = "Bye!";
            } else {
                System.out.println(Arrays.toString(tasks.list()));
                storage.saveToFile(tasks);
                response = ui.respond(parser.parse(input));
            }
        } catch (InvalidCommandException | IncorrectArgumentException e) {
            response = e.getMessage();
            System.out.println(e.getMessage());
        } catch (IOException | NumberFormatException | ClassNotFoundException e) {
            response = e.getMessage();
            e.printStackTrace();
        }
        return response;
    }

    public void shutdown() {
        try {
            storage.saveToFile(tasks);
            ui.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
