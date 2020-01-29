package com.duke.bot;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;
import com.duke.bot.command.Command;
import com.duke.bot.util.Parser;

/**
 * A personal note-taking assistant.
 */
public class Duke {
    private final Scanner scanner;
    private final View view;
    private final PersistentStorage persistentStorage;
    private TaskList tasks;

    /**
     * Initialize a Duke instance with a custom path to a flat-file database.
     * 
     * @param storagePath Path to the file intended to be used as a flat-file database
     */
    public Duke(Path storagePath) {
        scanner = new Scanner(System.in);
        view = new View();
        persistentStorage = new PersistentStorage(storagePath);

        try {
            tasks = persistentStorage.load();
        } catch (IOException exception) {
            tasks = new TaskList();
        }

        view.print(List.of("Hello! I'm Duke", "What can I do for you?"));
    }

    /**
     * Runs Duke's request-response cycle. Requests are given from the standard input, while
     * responses are printed out to the standard output.
     */
    public void run() {
        boolean hasNext = true;
        do {
            try {
                String input = scanner.nextLine();

                Command command = Parser.parse(input);
                Command.ExecuteResult result = command.execute(tasks);
                tasks = result.getTasks();
                hasNext = result.hasNextCommand();
                view.print(result.getMessages());

                persistentStorage.save(tasks);
            } catch (DukeException exception) {
                view.print(List.of(exception.getMessage() + "!"));
            } catch (IOException exception) {
                view.print(List.of("Failed to persist data!"));
            }
        } while (hasNext);
    }

    public static void main(String[] args) {
        Duke duke = new Duke(Path.of(System.getProperty("user.dir"), "data.txt"));
        duke.run();
    }
}
