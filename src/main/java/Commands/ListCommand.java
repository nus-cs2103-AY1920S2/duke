package duke.commands;

import duke.Interpreter;
import duke.Storage;
import duke.TaskList;
import duke.DukeResponse;

public class ListCommand extends Command {
	public ListCommand(String commandText) {
		super(commandText);
	}

	@Override
	public DukeResponse execute(Storage storage, TaskList taskList) {
		return Interpreter.printList(taskList.getList());
	}

	@Override
	public boolean isExit() {
		return false;	
	}
}