package com.duke.commands;

import com.duke.Storage;
import com.duke.TaskList;
import com.duke.dukeException.DukeParseException;
import com.duke.Interpreter;
import com.duke.task.Task;
import com.duke.DukeResponse;
import java.util.ArrayList;
import java.util.List;

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