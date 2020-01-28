package duke.main;

import java.time.format.DateTimeParseException;
import duke.command.*;
import duke.exception.*;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    public Duke(String filePath){
        ui = new Ui();
        storage = new Storage(filePath);
        parser = new Parser();
        try{
            tasks = new TaskList(storage.loadFromSave());
        } catch(UnableToLoadException e){
            ui.showDukeError(e);
            storage.retryLocation(ui.getInput());
        }
    }

    public void run(){
        // initialise scanner
        ui.sayHi();
        boolean isExit = false;

        while (!isExit) {
            try {
                String input = ui.getCommand();
                Command command = parser.parse(input);
                command.execute(tasks, ui, storage);
                isExit = command.isExit();
            } catch (DukeException e) {
                ui.showDukeError(e);
            } catch (DateTimeParseException e) {
                ui.showDateTimeError();
            }
        }

    }
    public static void main(String[] args) {
        new Duke(System.getProperty("user.dir")).run();       
    }
}
