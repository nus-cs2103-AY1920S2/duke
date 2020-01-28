package commands;

import storage.Storage;
import taskList.TaskList;

/**
 * Class definition for the "bye" command.
 * To be used as the intermediate between the Parser and the UI.
 */
public class ExitCommand extends Command {
	public static final String COMMAND_WORD = "bye";

	public static final String MESSAGE_EXIT_ACKNOWEDGEMENT = "Closing the todo list now....";

	/**
	 * Executes the command. Slightly different from other commands since it just prints a closing message.
	 *
	 * @param tasks   the tasks that is currently being referenced in Duke.
	 * @param storage the storage tool for loading and saving the state of the list after every command.
	 */
	@Override
	public void execute(TaskList tasks, Storage storage) {
		System.out.println(MESSAGE_EXIT_ACKNOWEDGEMENT);
	}

	/**
	 * Checks if this is an exit command.
	 *
	 * @return true since it is an exit command.
	 */
	@Override
	public boolean isExit() {
		return true;
	}

	public static boolean isExit(Command command) {
		return command instanceof ExitCommand; // instanceof returns false if it is null
	}
}
