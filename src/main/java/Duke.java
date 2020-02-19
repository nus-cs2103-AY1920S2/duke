import e0148811.duke.DukeException;
import e0148811.duke.Logic;
import e0148811.duke.Parser;
import e0148811.duke.Storage;
import e0148811.duke.TaskList;
import e0148811.duke.Ui;

public class Duke {
    private boolean isClosed = false;
    private Logic logic;
    private TaskList tasks;
    private Parser parser;
    private Ui ui;

    public Duke() {
        ui = new Ui();
        Storage storage = new Storage("data/duke.txt", ui);
        parser = new Parser(ui);
        try {
            ui.showLogo();
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList(ui);
        } finally {
            logic = new Logic(storage, tasks, ui);
            ui.linkToTaskList(tasks);
            Ui.greet();
        }
    }

    public void run() {
        while (!(parser.readInputLine().equals("bye"))) {
            System.out.println(logic.execute(parser.breakIntoWords()));
        }
        System.out.println(ui.sayGoodbye());
    }

    public static void main(String[] args) {
        new Duke().run();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    String getResponse(String input) {
        return "test";
    }
}