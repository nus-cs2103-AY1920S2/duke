package duke.commands;

import duke.dukeException.DukeParseException;
import duke.Storage;
import duke.TaskList;
import duke.Interpreter;
import duke.DukeResponse;

public class DeleteCommand extends Command {
	public DeleteCommand(String commandText) {
		super(commandText);
	}

	private int getDeleteCommand(String command) throws DukeParseException {
		String[] tokens = command.split(" ");
		if (tokens.length != 2) {
			throw new DukeParseException("Delete command requires 2 tokens!");
		}
		try {
			int index = Integer.parseInt(tokens[1]);
			return index;
		} catch (NumberFormatException e) {
			throw new DukeParseException(tokens[1] + " is not an integer!");
		}
	}

	@Override
	public DukeResponse execute(Storage storage, TaskList taskList) throws DukeParseException {
		int index = getDeleteCommand(commandText);
		int realIndex = index - 1;
		DukeResponse result = Interpreter.printDelete(taskList.getTask(realIndex), taskList.getNum());
		taskList.deleteAction(realIndex);
		return result;
	}

	@Override 
	public boolean isExit() {
		return false;
	}
}