package com.duke.bot;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;
import com.duke.bot.command.Command;
import com.duke.bot.util.Parser;

public class Duke {
    private final Scanner scanner;
    private final PersistentStorage persistentStorage;
    private TaskList tasks;

    public Duke() {
        scanner = new Scanner(System.in);
        persistentStorage = new PersistentStorage(
                Path.of(System.getProperty("user.dir"), "data.txt")
        );

        try {
            tasks = persistentStorage.load();
        } catch (IOException exception) {
            tasks = new TaskList();
        }

        print(List.of("Hello! I'm Duke", "What can I do for you?"));
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
                print(result.getMessages());

                persistentStorage.save(tasks);
            } catch(DukeException exception) {
                print(List.of(exception.getMessage() + "!"));
            } catch(IOException exception) {
                print(List.of("Failed to persist data!"));
            }
        } while (hasNext);
    }

    private void print(List<String> lines) {
        System.out.println("    ____________________________________________________________");
        lines.forEach(line -> System.out.println("     " + line));
        System.out.println("    ____________________________________________________________");
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.run();
    }
}
