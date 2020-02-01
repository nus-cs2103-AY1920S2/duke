package duke;

import java.io.IOException;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke() {
        ui = new Ui();
        try{
            storage = new Storage();
            this.tasks = storage.loadTasksFile();
        } catch (Exception e) {
            ui.printError(e);
        }
    }


    private void interact() {

        boolean byebye = false;
        ui.showWelcome();

        while (!byebye) {
            ui.printLine1();
            try {
                byebye = Parser.parse(tasks, ui);
                storage.writeTasksFile(tasks);
            } catch (DukeException e) {
                ui.printError(e);
            } catch (IOException e) {
                ui.printError(e);
                break;
            }
            ui.printLine2();
        }
        ui.showGoodbye();
    }


    public static void main(String[] args) {
        new Duke().interact();
    }
}
