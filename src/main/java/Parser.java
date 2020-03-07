package com.duke;

import com.duke.dukeException.DukeParseException;
import com.duke.task.Task;
import com.duke.task.Event;
import com.duke.task.ToDo;
import com.duke.task.Deadline;
import com.duke.commands.*;

/**
 * This class parse raw command string into commands. 
 */
public class Parser {

	/**
	 * [parse description]
	 * @param  commandText        [description]
	 * @return                    [description]
	 * @throws DukeParseException [description]
	 */
	public Command parse(String commandText) throws DukeParseException {
		String[] tokens = commandText.split(" ");

		if (tokens.length < 1) {
			throw new DukeParseException("A command requires at least 1 token!");
		}


		assert(tokens.length >= 1);
		if (tokens[0].equals("bye")) {
			if (tokens.length > 1) {
				throw new DukeParseException("If you want to say good-bye, please type bye");
			} else {
				return new ByeCommand(commandText);
			}
		}

		if (tokens[0].equals("done")) {
			if (tokens.length < 1) {
				throw new DukeParseException("Done command requires numbers!");
			} else {
				return new DoneCommand(commandText);
			}
		}

		if (tokens[0].equals("delete")) {
			if (tokens.length < 1) {
				throw new DukeParseException("Delete command requires numbers!");
			} else {
				return new DeleteCommand(commandText);
			}
		}

		if (tokens[0].equals("list")) {
			if (tokens.length > 1) {
				throw new DukeParseException("If you want to list things, please type bye");
			} else {
				return new ListCommand(commandText);
			}
		}

		if (tokens[0].equals("event")) {
			int positionOfSeparator = -1;
			for (int i = 0; i < tokens.length; i++) {
				if (tokens[i].equals(Event.separator)) {
					positionOfSeparator = i;
				}
			}
			if (positionOfSeparator == -1) {
				throw new DukeParseException("We need a separator " + Event.separator);
			}
			Task task = new Event(commandText);
			return new AddCommand(commandText, task);
		}

		if (tokens[0].equals("deadline")) {
			int positionOfSeparator = -1;
			for (int i = 0; i < tokens.length; i++) {
				if (tokens[i].equals(Deadline.separator)) {
					positionOfSeparator = i;
				}
			}
			if (positionOfSeparator == -1) {
				throw new DukeParseException("We need a separator " + Deadline.separator);
			}
			Task task = new Deadline(commandText);
			return new AddCommand(commandText, task);
		}

		if (tokens[0].equals("todo")) {
			if (tokens.length <= 1) {
				throw new DukeParseException("Todo need description!");
			}
			Task task = new ToDo(commandText);
			return new AddCommand(commandText, task);
		}

		if (tokens[0].equals("find")) {
			if (tokens.length <= 1) {
				throw new DukeParseException("Find requires a keyword");
			}
			if (tokens.length > 2) {
				throw new DukeParseException("Now I only support find a single keyword");
			}
			return new FindCommand(commandText, tokens[1]);
		}

		throw new DukeParseException(tokens[0] + " is not a valid command!");
	}
}