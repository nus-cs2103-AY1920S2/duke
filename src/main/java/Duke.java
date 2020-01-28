import java.io.IOException;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Duke {

    public final static String NEWLINE = System.lineSeparator();
    public final static String INDENT = "    ";
    public final static String BORDER = INDENT + "____________________________________________________________";
    public final static String EXIT = "bye";
    public final static String GOODBYE_MESSAGE = INDENT + "  Goodbye and have a beautiful time!";


    private Storage storage;
    private TaskList tasks;
    private Ui ui;


    public Duke(String filePath) {
        ui = new Ui();
        try {
            storage = new Storage(filePath);
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError(e);
            tasks = new TaskList();
        } catch (IOException io) {
            ui.showStorageError();
        }
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            String fullCommand = ui.readCommand();
            try {
                Command currentCommand = Parser.parse(fullCommand);
                currentCommand.execute(tasks, ui, storage);
                isExit = currentCommand.isExit();
            } catch (DukeException dukeEx) {
                ui.showStandardError(dukeEx);
            } catch (DateTimeException dateEx) {
                ui.showDateTimeException();
            } catch (IndexOutOfBoundsException indexEx) {
                ui.showIndexOutOfBoundException(tasks.getSize());
            }
        }
    }

    public static void main(String[] args) {
        new Duke("data" + System.getProperty("file.separator") + "duke.txt").run();
    }
}