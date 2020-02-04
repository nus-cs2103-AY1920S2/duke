package duke;

import duke.command.Command;
import duke.exception.DukeException;
import duke.io.Parser;
import duke.io.Serializer;
import duke.io.Ui;
import duke.stream.GuiPrintStream;
import duke.task.TaskList;

public class Duke {

    protected TaskList tasks;
    protected Ui ui;
    protected Parser parser;

    public Duke() {
        System.setOut(new GuiPrintStream(System.out));
        ui = new Ui(System.in);
        tasks = new TaskList(Serializer.deserialize());
        parser = new Parser();
    }

    public void sendMessage(String input) {
        try {
            Command command = parser.parse(input);
            if (command != null) {
                command.execute(tasks, ui);
            }
        } catch (DukeException e) {
            ui.printError(e.getMessage());
        }
    }
}
