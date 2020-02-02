package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.dukeException.DukeParseException;
import java.util.ArrayList;
import java.util.List;
import duke.Interpreter;
import duke.task.Task;
import duke.DukeResponse;

public class FindCommand extends Command {
	private String keyWord;

	public FindCommand(String commandText, String keyWord) {
		super(commandText);
		this.keyWord = keyWord;
	}

	@Override 
	public DukeResponse execute(Storage storage, TaskList taskList) throws DukeParseException {
		List<Integer> candidates = new ArrayList<>();
		List<Task> tasks = taskList.getList();
		for (int i = 0; i < tasks.size(); i++) {
			if (tasks.get(i).description.contains(this.keyWord)) {
				candidates.add(i);
			}
		}
		return Interpreter.printFind(taskList.getList(), candidates);
	}

	@Override 
	public boolean isExit() {
		return false;
	}
}