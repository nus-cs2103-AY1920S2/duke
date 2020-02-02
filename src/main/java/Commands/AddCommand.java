package duke.commands;

import duke.task.Task;
import duke.Storage;
import duke.TaskList;
import duke.Interpreter;
import duke.dukeException.DukeParseException;
import duke.DukeResponse;

public class AddCommand extends Command {
	private Task task;
	public AddCommand(String commandText, Task task) {
		super(commandText);
		this.task = task;
	}

	@Override 
	public DukeResponse execute(Storage storage, TaskList taskList) throws DukeParseException {
		taskList.addAction(this.task);
		return Interpreter.printAdd(this.task, taskList.getNum());
	}

	@Override 
	public boolean isExit() {
		return false;
	}
}