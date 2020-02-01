package duke.commands;

import duke.Interpreter;
import duke.Storage;
import duke.TaskList;

public class ListCommand extends Command {
	public ListCommand(String commandText) {
		super(commandText);
	}

	@Override
	public void execute(Storage storage, TaskList taskList) {
		Interpreter.printList(taskList.getList());
	}

	@Override
	public boolean isExit() {
		return false;	
	}
}