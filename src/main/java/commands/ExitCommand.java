package commands;

import storage.Storage;
import taskList.TaskList;

public class ExitCommand extends Command {
	public static final String COMMAND_WORD = "bye";

	public static final String MESSAGE_EXIT_ACKNOWEDGEMENT = "Closing the todo list now....";

	@Override
	public void execute(TaskList tasks, Storage storage) {
		System.out.println(MESSAGE_EXIT_ACKNOWEDGEMENT);
	}

	@Override
	public boolean isExit() {
		return true;
	}

	public static boolean isExit(Command command) {
		return command instanceof ExitCommand; // instanceof returns false if it is null
	}
}
