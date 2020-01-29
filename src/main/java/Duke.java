// java imports
import java.util.Scanner;
import java.io.FileNotFoundException;

// packages imports
import ui.Ui;
import tasks.*;

public class Duke {
    private Ui ui;
    private Storage storage;
    private TaskList taskList;

    public Duke() {
        ui = new Ui();
        storage = new Storage();
        taskList = new TaskList();
        try {
            storage.readSaveFile(taskList);
        } catch (FileNotFoundException ex) {
            ui.printFormattedOutput("No saved task list found. Creating a new one...");
        }
    }

    public void run() {
        Scanner sc = new Scanner(System.in);
        Parser parser = new Parser(taskList, storage, ui, sc);

        ui.printFormattedOutput("Hello! I'm Duke\n    What can I do for you?");

        // Input-Response logic
        String input = sc.nextLine();
        while (!input.equals("bye")) {
            parser.parse(input);
            input = sc.nextLine();
        }

        ui.printFormattedOutput("Bye. Hope to see you again soon!");
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}