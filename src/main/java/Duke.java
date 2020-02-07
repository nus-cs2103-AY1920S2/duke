import Duke.*;

public class Duke {

    protected Ui ui;
    protected Storage storage;
    protected TaskList tasks;

    public Duke(String filepath){
        ui = new Ui();
        storage = new Storage(filepath);
        tasks = new TaskList(storage.load());

    }

    public void run() {
        ui.printLogo();
        ui.showWelcome();

        while (true) {
            String fullCommand = ui.readCommand();
            ui.printLine();
            Command c = Parser.parse(fullCommand);
            c.execute(ui, storage, tasks);
        }
    }

    public static void main(String[] args) {
        new Duke("dukeStorage").run();
    }
}
