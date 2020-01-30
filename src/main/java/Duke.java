import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;

public class Duke {
    private String filePath;
    private ArrayList<Task> storage;
    private Storage storageController;
    private Ui ui;
    private Controller controller;

    public Duke(String filePath) {
        Ui ui = new Ui();
        this.filePath = filePath;
        this.storageController = new Storage(this.filePath);
        this.controller = new Controller(storageController);
    }

    private void run() {
        Scanner scan = new Scanner(System.in);
        Ui.greet();
        Controller.printTaskList();
        while (scan.hasNext()) {
            Optional<Command> parsed = Parser.parse(scan.nextLine());
            if (parsed.isPresent()) {
                Command command = parsed.get();
                if (controller.execute(command)) {
                    break;
                }
            }
        }

    }

    public static void main(String[] args) {
        Duke bot = new Duke("src/main/data/data.csv");
        bot.run();
    }

}
