package duke;

import duke.command.Command;

import java.util.Optional;
import java.util.Scanner;

/**
 * The Duke program
 */
public class Duke {
    private String filePath;
    private Storage storageController;
    private Controller controller;

    /**
     * Constructs a Duke instance with the specified file path
     *
     * @param filePath a String value of the file path.
     */
    public Duke(String filePath) {
        this.filePath = filePath;
        this.storageController = new Storage(this.filePath);
        this.controller = new Controller(storageController);
    }

    private void run() {
        Scanner scan = new Scanner(System.in);
        Ui.greet();
        Ui.printTaskList();
        while (scan.hasNext()) {
            try {
                Optional<Command> parsed = Parser.parse(scan.nextLine());
                if (parsed.isPresent()) {
                    Command command = parsed.get();
                    if (controller.execute(command)) {
                        break;
                    }
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        Duke bot = new Duke("src/data/data.csv");
        bot.run();
    }

}
