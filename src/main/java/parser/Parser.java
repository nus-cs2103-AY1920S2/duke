package parser;

import commands.*;

import java.time.LocalDate;

/**
 * The class that handles making sense of the user commands, and acts accordingly if the user commands
 * are invalid.
 */
public class Parser {

	/**
	 * The main command that handles making sense of user commands. Will return custom exception messages upon
	 * encounter.
	 *
	 * @param userInput the user's input into the program, i.e. their various commands.
	 * @return a Command based on the input by the user.
	 */
	public static Command parse(String userInput) {
		String[] substrings = userInput.split(" ", 2);
		String commandWord = substrings[0];
		String arguments = "";
		if (substrings.length > 1) {
			arguments = substrings[1];
		}

		switch (commandWord) {

			case TodoCommand.COMMAND_WORD:
				return prepareTodo(arguments);

			case EventCommand.COMMAND_WORD:
				return prepareEvent(arguments);

			case DeadlineCommand.COMMAND_WORD:
				return prepareDeadline(arguments);

			case DeleteCommand.COMMAND_WORD:
				return prepareDelete(arguments);

//            case FindCommand.COMMAND_WORD:
//                return prepareFind(arguments);

			case ListCommand.COMMAND_WORD:
				return new ListCommand();

			case DoneCommand.COMMAND_WORD:
				return prepareDone(arguments);

			case ExitCommand.COMMAND_WORD:
				return new ExitCommand();

//            case HelpCommand.COMMAND_WORD: // Fallthrough
			default:
				return new ExitCommand();
		}
	}

	private static Command prepareTodo(String arguments) {
		return new TodoCommand(arguments);
	}

	/**
	 * Handles the LocalDate objects first before sending it into Event object.
	 *
	 * @param arguments the desc and date objects.
	 * @return the EventCommand with the relevant Event object.
	 */
	private static Command prepareEvent(String arguments) {
		//event x /at 2020-01-13
		String[] items = arguments.split(" /at ");
		String desc = "";
		LocalDate date = null;

		try {
			desc = items[0];
			date = LocalDate.parse(items[1]);
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("Incorrect arguments please try again.");
		}

		return new EventCommand(desc, date);
	}

	/**
	 * Handles the LocalDate objects first before sending it into Deadline object.
	 *
	 * @param arguments the desc and date objects.
	 * @return the DeadlineCommand with the relevant Deadline object.
	 */
	private static Command prepareDeadline(String arguments) {
		//event x /at 2020-01-13
		String[] items = arguments.split(" /by ");
		String desc = "";
		LocalDate date = null;

		try {
			desc = items[0];
			date = LocalDate.parse(items[1]);
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("Incorrect arguments please try again.");
		}

		return new DeadlineCommand(desc, date);
	}

	private static Command prepareDone(String arguments) {
		final int targetIndex = Integer.parseInt(arguments);
		return new DoneCommand(targetIndex);
	}

	private static Command prepareDelete(String arguments) {
		final int targetIndex = Integer.parseInt(arguments);
		return new DeleteCommand(targetIndex);
	}

	/**
	 * Signals that the user input could not be parsed.
	 */
	public static class ParseException extends Exception {
		ParseException(String message) {
			super(message);
		}
	}
}
