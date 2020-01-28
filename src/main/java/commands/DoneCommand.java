package commands;

import storage.Storage;
import taskList.TaskList;

/**
 * Class definition for the "done" command.
 * To be used as the intermediate between the Parser and the UI.
 */
public class DoneCommand extends Command {
	public static final String COMMAND_WORD = "done";
	private int index;

	/**
	 * Constructor using the index of the Task to be done on the list.
	 *
	 * @param index the index of the Task to be done on the list.
	 */
	public DoneCommand(int index) {
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
			tasks.getList().get(this.index - 1).taskDone();
			System.out.println("Successfully Completed: " + tasks.getList().get(this.index - 1));
			storage.save(tasks);
		} catch (IndexOutOfBoundsException ie) {
			System.out.println("Index does not exist");
		}
	}
}
