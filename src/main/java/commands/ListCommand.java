package commands;

import storage.Storage;
import task.Task;
import taskList.TaskList;

/**
 * Class definition for the "list" command.
 * To be used as the intermediate between the Parser and the UI.
 */
public class ListCommand extends Command {
	public static final String COMMAND_WORD = "list";

	/**
	 * Executes the command. Will print out the list of all events currently in the list.
	 *
	 * @param tasks   the tasks that is currently being referenced in Duke.
	 * @param storage the storage tool for loading and saving the state of the list after every command.
	 */
	public void execute(TaskList tasks, Storage storage) {
		String printList = "";
		System.out.println("Here are the current tasks in your list:");
		int listStart = 1;
		for (Task task : tasks.getList()) {
			printList = printList + listStart + ". " + task + "\n";
			listStart++;
		}
		System.out.println(printList.trim());
	}
}
