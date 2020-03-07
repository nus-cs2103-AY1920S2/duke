package com.duke.commands;

import com.duke.Storage;
import com.duke.TaskList;
import com.duke.dukeException.DukeParseException;
import com.duke.DukeResponse;

/**
 * An abstract class abstracts commands.
 */
public abstract class Command {
	String commandText;

	public Command(String commandText) {
		this.commandText = commandText;
	} 

	public abstract DukeResponse execute(Storage storage, TaskList taskList) throws DukeParseException;

	public abstract boolean isExit();
}