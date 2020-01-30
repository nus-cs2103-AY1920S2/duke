import java.util.Scanner;
import java.io.File;
import java.io.IOException;

public class Duke {
    private TaskList tasks;
    private Storage storage;
    private String fileName;
    private Parser parser;
    private Ui ui;

    public Duke(String fileName) {
        this.fileName = fileName;
        storage = new Storage(fileName);
        tasks = new TaskList(storage.loadTasks());
        parser = new Parser();
        ui = new Ui();
        File file = new File(this.fileName);

        try {
            file.createNewFile();
        } catch (IOException e) {
            Ui.printLines("File creation failed.");
        }
    }
    public static void main(String[] args) {
        new Duke("../data/tasks.txt").run();
    }

    private void run() {
        ui.printIntro();
        Scanner sc = new Scanner(System.in);

        while (sc.hasNextLine()) {
            try {
                String input = sc.nextLine();
                String[] split = input.split(" ");
                String command = split[0];

                if (parser.parseCommand(command, "list")) {
                    System.out.println(tasks);
                } else if (parser.parseCommand(command, "bye")) {
                    ui.printGoodbye();
                    break;
                } else if (parser.parseCommand(command, "delete")) {
                    int idx = Integer.parseInt(split[1]);
                    tasks.deleteTask(idx - 1);
                    storage.deleteTask(idx);
                } else if (parser.parseCommand(command, "done")) {
                    int idx = Integer.parseInt(split[1]);
                    tasks.doTask(idx - 1);
                    storage.doTask(idx);
                } else if (parser.parseCommand(command, "todo")) {
                    tasks.manageTodo(storage, input, fileName);
                } else if (parser.parseCommand(command, "event")) {
                    tasks.manageEvent(storage, input, fileName);
                } else if (parser.parseCommand(command, "deadline")) {
                    tasks.manageDeadline(storage, input, fileName);
                } else {
                    throw new InvalidCommandException();
                }
            } catch (InvalidCommandException e) {
                Ui.printLines("Sorry, invalid command. Try again with the following:\n     todo, deadline, event");
            } catch (ArrayIndexOutOfBoundsException e) {
                Ui.printLines("Sorry, invalid syntax or command. Please try again!");
            }
        }

        sc.close();
    }
}