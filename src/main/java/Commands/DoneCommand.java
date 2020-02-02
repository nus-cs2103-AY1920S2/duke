package duke.commands;

import duke.dukeException.DukeParseException;
import duke.Interpreter;
import duke.Storage;
import duke.TaskList;
import java.util.List;
import java.util.ArrayList;
import duke.DukeResponse;

public class DoneCommand extends Command {
	public DoneCommand(String commandText) {
		super(commandText);
	}

	static List<Integer> getDoneCommand(String command) throws DukeParseException {
		String[] tokens = command.split(" ");
		if (tokens.length < 1) {
			throw new DukeParseException("Done command needs more than 1 tokens");
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
		List<Integer> indexes = getDoneCommand(super.commandText);
		taskList.markAsDone(indexes);
		return Interpreter.printDoneList(taskList.getSubset(indexes));
	}

	@Override
	public boolean isExit() {
		return false;
	}
}