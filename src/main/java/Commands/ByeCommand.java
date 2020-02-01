package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.Interpreter;

public class ByeCommand extends Command {
	static final String goodByeMessage = "Au revoir!";
	public ByeCommand(String commandText) {
		super(commandText);
	}

	@Override
	public void execute(Storage storage, TaskList taskList) {
		Interpreter.printMessage(goodByeMessage);
		storage.saveData(taskList);
	}

	@Override
	public boolean isExit() {
		return true;
	}
}