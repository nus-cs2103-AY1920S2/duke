package duke;

import duke.task.TaskList;
import duke.exception.DukeException;
import duke.command.Command;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            this.tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            this.ui.showLoadingError();
            this.tasks = new TaskList();
        }
    }

    public void run() {
        ui.printWelcomeMessageAndGetName();
        boolean isActive = true;

        while (isActive) {
            try {
                String instruction = ui.getInstruction();
                Command c = Parser.parse(instruction);
                isActive = c.execute(tasks, ui);
            } catch (DukeException e) {
                ui.showError(e);
            }
        }
        storage.save(tasks.getList());
    }

    public static void main(String[] args) {
        new Duke("data\\duke.txt").run();
    }
}