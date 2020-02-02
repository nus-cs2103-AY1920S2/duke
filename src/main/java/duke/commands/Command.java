package duke.commands;
import duke.utilities.*;
import duke.Ui;
import duke.exceptions.DukeException;

public abstract class Command {
    public abstract boolean execute (Storage storage, TaskList tasks, Ui ui) throws DukeException;
}