package duke;

import java.util.Scanner;

/**
 *Main class.
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructor that takes in path of file to be read.
     * Initializes UI, Storage and TaskList classes.
     *
     * @param filePath
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.load());
    }

    /**
     * Method to start program and read user input.
     */
    public void run() {
        ui.showGreeting();
        Scanner sc = new Scanner(System.in);

        while (sc.hasNext()) {
            String input = sc.nextLine();
            Parser parser = new Parser(input, tasks);
            parser.readCommand();
        }
    }

    public static void main(String[] args) {
        new Duke("../../../data/duke.txt").run();
    }
}