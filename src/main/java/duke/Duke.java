package duke;

import java.util.Scanner;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.load());
    }

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