import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * The Duke program implements an application that is able to keep track of a task list. A user can populate the list
 * with tasks inputted through the standard output, mainly Todo tasks, Deadline tasks and Event tasks, or delete tasks
 * from the list.
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException | IOException exception) {
            ui.printLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() throws IOException {
        ui.printWelcome();
        Scanner sc = new Scanner(System.in);
        String command = sc.nextLine();
        Parser parser = new Parser(tasks.getList());
        while (!command.equals("bye")) {
            try {
                parser.parse(command);
                command = sc.nextLine();
            } catch (DukeException exception) {
                ui.printError(exception);
                command = sc.nextLine();
            }
        }
        // update task list before exiting
        BufferedWriter writer = new BufferedWriter(new FileWriter(storage.getFile()));
        for (Task task: tasks.getList()) {
            writer.write(task.updateFile() + "\n");
        }
        writer.flush();
        ui.printExitLine();
    }

    public static void main(String[] args) throws IOException {
        new Duke("./src/main/data/duke.txt").run();
    }
}
