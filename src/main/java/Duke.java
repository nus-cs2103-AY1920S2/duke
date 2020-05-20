import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Duke {
    /**
     * This method reads user input and process it.
     */
    static TaskList list;
    static Storage storage;
    static Ui ui;
    Parser parser;

    Duke() {
        this.storage = new Storage();
        this.ui = new Ui();
        this.parser = new Parser();
        this.list = storage.loadTxt(ui);
    }

    String getResponse(String input) {
        return parser.parse(ui, list, storage, input);
    }

}