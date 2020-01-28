package commands;

import storage.Storage;
import taskList.TaskList;

public class DoneCommand extends Command {
	public static final String COMMAND_WORD = "done";
	private int index;

	public DoneCommand(int index) {
		super(index);
		this.index = index;
	}

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
