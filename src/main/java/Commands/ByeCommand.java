package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.Interpreter;
import duke.DukeResponse;

public class ByeCommand extends Command {
	static final String goodByeMessage = "Au revoir!";
	public ByeCommand(String commandText) {
		super(commandText);
	}

	@Override
	public DukeResponse execute(Storage storage, TaskList taskList) {
		DukeResponse result = Interpreter.printMessage(goodByeMessage);
		storage.saveData(taskList);
		return result;
	}

	@Override
	public boolean isExit() {
		return true;
	}
}