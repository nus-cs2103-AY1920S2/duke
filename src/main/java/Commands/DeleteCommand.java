package duke.commands;

import java.util.List;
import java.util.ArrayList;
import duke.dukeException.DukeParseException;
import duke.Storage;
import duke.TaskList;
import duke.Interpreter;
import duke.DukeResponse;

public class DeleteCommand extends Command {
	public DeleteCommand(String commandText) {
		super(commandText);
	}

	private List<Integer> getDeleteCommand(String command) throws DukeParseException {
		String[] tokens = command.split(" ");
		if (tokens.length < 2) {
			throw new DukeParseException("Delete command requires more than tokens!");
		}
		List result = new ArrayList<Integer>();
		for (int i = 1; i < tokens.length; i++) {
			try {
				int index = Integer.parseInt(tokens[i]);
				result.add(index - 1);
			} catch (NumberFormatException e) {
				throw new DukeParseException(tokens[i] + " is not a valid integer");
			}
		}
		return result;
	}

	@Override
	public DukeResponse execute(Storage storage, TaskList taskList) throws DukeParseException {
		List<Integer> indexes = getDeleteCommand(super.commandText);
		DukeResponse response = Interpreter.printMultipleDelete(taskList.getSubset(indexes),
			taskList.getNum() - indexes.size());
		taskList.deleteTask(indexes);
		return response;
	}

	@Override 
	public boolean isExit() {
		return false;
	}
}