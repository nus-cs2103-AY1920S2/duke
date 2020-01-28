import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.File;

public class Duke {
    private Storage storage;
    private Ui ui;
    private TaskList tasks;
    private Parser parser;

    /**
     *Initialises everything needed by Duke.
     *
     * @param filePath path used to access tasks in Hard Disk.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        parser = new Parser();
        try {
            tasks = new TaskList(storage.getTasksFromFile(filePath));
        } catch (FileNotFoundException e) {
            tasks = new TaskList();
        }
    }

    /**
     * Simulates running of Duke.
     */
    public void run() {
        ui.greetings();
        String input = ui.getInput();
        while (!input.equalsIgnoreCase("Bye")) {
            try {
                System.out.println("\t____________________________________________________________");
                if (input.equalsIgnoreCase("list")) {
                    tasks.list();
                } else if (input.startsWith("done")) {
                    int n = Integer.parseInt(parser.parse(input)[1]);
                    tasks.done(n);
                } else if (input.startsWith("delete")) {
                    int n = Integer.parseInt(parser.parse(input)[1]);
                    tasks.delete(n);
                } else if (input.startsWith("find")) {
                    String str = parser.parse(input)[1];
                    tasks.find(str);
                } else {
                    String[] splitInput = parser.parse(input);
                    if (input.startsWith("todo") || input.startsWith("deadline") || input.startsWith("event")) {
                        try {
                            tasks.add(splitInput[0], splitInput[1]);
                        } catch (ArrayIndexOutOfBoundsException arr) {
                            throw new EmptyTaskException("");
                        }
                    } else {
                        throw new InvalidRequestException();
                    }
                }
                System.out.println("\t____________________________________________________________");
                input = ui.getInput();
            } catch (EmptyTaskException empty) {
                System.out.println("\t" + empty.toString());
                System.out.println("\t____________________________________________________________");
                input = ui.getInput();
            } catch (InvalidRequestException invalid) {
                System.out.println("\t" + invalid.toString());
                System.out.println("\t____________________________________________________________");
                input = ui.getInput();
            }
        }
        System.out.println("\t____________________________________________________________");
        System.out.println("\tBye. Hope to see you again soon!");
        try {
            storage.addTasksToFile(tasks.tasks);
        } catch (IOException e) {
            System.out.println("Error in saving to file");
        }
        System.out.println("\t____________________________________________________________");
    }

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }
}
