import java.io.*;
import java.util.Scanner;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException | IOException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() throws IOException {
        ui.showWelcome();
        Scanner sc = new Scanner(System.in);
        String command = sc.nextLine();
        Parser parser = new Parser(tasks.getList());
        while (!command.equals("bye")) {
            try {
                parser.parse(command);
                command = sc.nextLine();
            } catch (DukeException e) {
                ui.showError(e);
                command = sc.nextLine();
            }
        }
        // update task list before exiting
        BufferedWriter writer = new BufferedWriter(new FileWriter(storage.getFile()));
        for (Task task: tasks.getList()) {
            writer.write(task.updateFile() + "\n");
        }
        writer.flush();
        ui.showExitLine();
    }

    public static void main(String[] args) throws IOException {
        new Duke("./src/main/data/duke.txt").run();
    }
}
