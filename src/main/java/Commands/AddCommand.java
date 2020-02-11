package com.duke.commands;

import com.duke.task.Task;
import com.duke.Storage;
import com.duke.TaskList;
import com.duke.Interpreter;
import com.duke.dukeException.DukeParseException;
import com.duke.DukeResponse;

/**
 * this command adds tasks into task list.
 */
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