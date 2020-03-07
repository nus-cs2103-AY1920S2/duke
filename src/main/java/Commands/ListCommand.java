package com.duke.commands;

import com.duke.Interpreter;
import com.duke.Storage;
import com.duke.TaskList;
import com.duke.DukeResponse;

/**
 * This command list all items in the task list. 
 */
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