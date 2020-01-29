package duke;

public class Main {

    private Storage storage;
    private TaskList tasks;
    private Duke ui;

    public Main(String filepath) {
        ui = new Duke();
        storage = new Storage(filepath);
        try {
            tasks = new TaskList(storage.loadData());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.echo(tasks, storage);
    }

    public static void main(String[] args) {
        new Main("../../../data/duke.txt").run();
    }
}
