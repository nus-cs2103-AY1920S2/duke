package seedu.java.util;

import java.util.Scanner;

/**
 * The main class. Contains Storage, TaskList & Ui class. Relies on Parser class to handle user's input.
 * run()
 * main()
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    public static String CLOSE = "0";

    /**
     * A Duke constructor that loads up a file based on a file path.
     * @param filePath - a path to take data from, .txt format
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (Exception e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Boots the program, interacts with the user & auto-save to storage.
     */
    public void run() {
        ui.intro();
        ui.output(tasks.listToPrint());
        while (true) {
            String userInput = ui.input();
            String dukeReply = tasks.read(userInput);
            if (dukeReply.equals(CLOSE)) {
                ui.output("Bye. See you soon! :)");
                break;
            } else {
                ui.output(dukeReply);
                storage.save(tasks.getTaskArr());
            }
        }
    }

    public static void main(String[] args) {
        Duke duke = new Duke("data/duke.txt");
        duke.run();
    }

}

