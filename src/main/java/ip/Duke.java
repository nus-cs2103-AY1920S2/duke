package ip;

import ip.command.Command;
import ip.task.TaskList;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Parser parser;
    private Ui ui;

    public Duke() {
        storage = new Storage();
        tasks = storage.readFromFile();
        parser = new Parser();
        ui = new Ui();
    }

    public String getResponse(String input) {
        Command c = parser.parse(input);
        if (c == null){
            return "";
        } else {
            return c.execute(tasks, ui);
        }
    }

    public static class DukeException extends Exception {
        public DukeException(String msg) {
            super(msg);
        }
    }

    public void exit() {
        storage.writeToFile(tasks);
    }
}
