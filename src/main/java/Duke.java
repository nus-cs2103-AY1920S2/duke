import java.io.*;

public class Duke {

    Ui ui;
    Storage storage;
    TaskList tasks;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);

        try {
            tasks = new TaskList();
            storage.readFile(tasks.taskList);

        } catch (FileNotFoundException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.showWelcome();
        ui.readCommands();

    }



    public static void main(String[] args) {

        new Duke("data/tasks.txt").run();

    }


}
