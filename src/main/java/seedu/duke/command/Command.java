package seedu.duke.command;

import seedu.duke.Storage;
import seedu.duke.TaskList;
import seedu.duke.Ui;
import seedu.duke.exception.DukeIoException;

public abstract class Command {

    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeIoException;

    public enum Type {
        bye("bye"),
        list("list"),
        done("done"),
        todo("todo"),
        delete("delete"),
        find("find"),

        deadline("deadline"),
        deadline_by("/by"),

        event("event"),
        event_at("/at");

        private final String command;

        Type(String command) {
            this.command = command;
        }

        public String getCommand() {
            return command;
        }
    }
}
