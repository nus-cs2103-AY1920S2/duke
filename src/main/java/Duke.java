import java.util.Scanner;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private  Parser parser;

    public Duke(String filePath) {
        this.ui = new Ui(new Scanner(System.in));
        this.storage = new Storage(filePath);
        this.tasks = storage.load();
        this.parser = new Parser();
    }

    public void run() {
        ui.showWelcome();
        runUntilExit();
        ui.showExit();
    }

    private void runUntilExit() {
        while (true) {
            String commandText = ui.getUserCommand();
            if (commandText.equals("bye")) {
                break;
            }
            parser.handleTaskCommand(commandText, tasks);
            ui.showLine();
        }
        this.storage.save(tasks);
    }

    public static void main(String[] args) {
        new Duke("data\\duke.txt").run();
    }
}
