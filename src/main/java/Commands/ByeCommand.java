package com.duke.commands;

import com.duke.Storage;
import com.duke.TaskList;
import com.duke.Interpreter;
import com.duke.DukeResponse;

/**
 * This is termination command.
 */
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