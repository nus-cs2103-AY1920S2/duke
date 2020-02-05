package duke.commands;

import duke.utilities.*;
import duke.Ui;
import duke.exceptions.DukeException;

/**
 * An abstract class that represents a Command, created by the Parser class when parsing user input.
 */
public abstract class Command {
    public abstract String execute(Storage storage, TaskList tasks, Ui ui) throws DukeException;
}