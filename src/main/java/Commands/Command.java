package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.dukeException.DukeParseException;

public abstract class Command {
	String commandText;

	public Command(String commandText) {
		this.commandText = commandText;
	} 

	public abstract void execute(Storage storage, TaskList taskList) throws DukeParseException;

	public abstract boolean isExit();
}