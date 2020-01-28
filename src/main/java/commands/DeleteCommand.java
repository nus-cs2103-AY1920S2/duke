package commands;

import storage.Storage;
import taskList.TaskList;

/**
 * Class definition for the "delete" command.
 * To be used as the intermediate between the Parser and the UI.
 */
public class DeleteCommand extends Command {
	public static final String COMMAND_WORD = "delete";
	private int index;

	/**
	 * Constructor using the index of the Task to be deleted from the list.
	 *
	 * @param index the index of the Task to be deleted from the list.
	 */
	public DeleteCommand(int index) {
		super(index);
		this.index = index;
	}

	/**
	 * Executes the command.
	 *
	 * @param tasks   the tasks that is currently being referenced in Duke.
	 * @param storage the storage tool for loading and saving the state of the list after every command.
	 */
	@Override
	public void execute(TaskList tasks, Storage storage) {
		try {
			System.out.println("Successfully Removed: " + tasks.getList().get(this.index - 1));
			tasks.remove(this.index - 1);
			storage.save(tasks);
		} catch (IndexOutOfBoundsException ie) {
			System.out.println("Index does not exist");
		}
	}
}
