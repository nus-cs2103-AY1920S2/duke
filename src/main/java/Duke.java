import java.io.IOException;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (IOException ioe) {
            //ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.printLogo();
        ui.greetDuke();
        Parser.handle(tasks, ui);

        try {
            storage.save(tasks);
        } catch (IOException ioe) {
            System.out.println(ioe);
        }
    }

    public static void main(String[] args) {
        Duke duke = new Duke("savedata.txt");
        duke.run();
    }
}
