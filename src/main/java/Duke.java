public class Duke {)
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            this.tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            this.tasks = new TaskList();
        }
    }

    public void run() {
        this.parser = new Parser(ui, tasks, storage);
        parser.run();
    }

    public static void main(String[] args) {
        new Duke("C:\\Users\\h1430\\Documents\\CS2103T\\duke\\src\\main\\java\\data\\duke.txt").run();
    }
}