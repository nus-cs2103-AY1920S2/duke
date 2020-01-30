import java.io.*;
import java.util.*;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (Exception e) {
            ui.showLoadingError();
            tasks = new TaskList(new ArrayList<>());
        }
    }

    public void run() {
        ui.showWelcome();
        while (ui.hasInputs()) {
            String input = ui.readInput();
            String[] inputArr = Parser.parse(input);
            if (input.equals("bye")) {
                ui.endDuke();
                break;
            } else if (input.equals("list")) {
                ui.printList(tasks);
            } else if (inputArr[0].equals("done")) {
                int option = Integer.parseInt(inputArr[1]);
                tasks.markAsDone(option);
                storage.writeFile(tasks);
            } else if (inputArr[0].equals("delete")) {
                int option = Integer.parseInt(inputArr[1]);
                tasks.deleteTask(option);
                storage.writeFile(tasks);
            } else {
                tasks.addAndWriteTask(inputArr, storage);
            }
        }
    }

    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }
}
