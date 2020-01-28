package dukeApp.duke;
import dukeApp.files.*;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Duke {
    private TaskList tasks;
    private Ui ui;
    private Storage storage;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);

        try {
            tasks = new TaskList(storage.load());
        } catch (FileNotFoundException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.input(tasks);
        try {
            storage.appendToFile(tasks.aList);
        } catch (IOException e) {
            System.out.println("Something went wrong: " +e.getMessage());
        }
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you?");
        new Duke("data\\duke.txt").run();
    }
}