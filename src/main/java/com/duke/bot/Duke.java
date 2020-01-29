package com.duke.bot;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;
import com.duke.bot.command.Command;
import com.duke.bot.util.Parser;

public class Duke {
    private final Scanner scanner;
    private final View view;
    private final PersistentStorage persistentStorage;
    private TaskList tasks;

    public Duke() {
        scanner = new Scanner(System.in);
        view = new View();
        persistentStorage = new PersistentStorage(
                Path.of(System.getProperty("user.dir"), "data.txt")
        );

        try {
            tasks = persistentStorage.load();
        } catch (IOException exception) {
            tasks = new TaskList();
        }

        view.print(List.of("Hello! I'm Duke", "What can I do for you?"));
    }

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
            } catch(DukeException exception) {
                view.print(List.of(exception.getMessage() + "!"));
            } catch(IOException exception) {
                view.print(List.of("Failed to persist data!"));
            }
        } while (hasNext);
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }
}
