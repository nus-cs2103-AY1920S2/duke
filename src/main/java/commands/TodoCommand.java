package commands;

import storage.Storage;
import task.Todo;
import taskList.TaskList;

/**
 * Class definition for the "todo" command.
 * To be used as the intermediate between the Parser and the UI.
 */
public class TodoCommand extends Command {
	public static final String COMMAND_WORD = "todo";

	private final Todo td;

	/**
	 * Convenience constructor using raw values.
	 *
	 * @param desc the desc that represents the action/activity of the Todo object.
	 */
	public TodoCommand(String desc) {
		this.td = new Todo(desc);
	}

	/**
	 * Constructor using a complete Todo object.
	 *
	 * @param td the Todo object to be acted upon.
	 */
	public TodoCommand(Todo td) {
		this.td = td;
	}

	public Todo getTodo() {
		return td;
	}

	/**
	 * Executes the command.
	 *
	 * @param tasks   the tasks that is currently being referenced in Duke.
	 * @param storage the storage tool for loading and saving the state of the list after every command.
	 */
	@Override
	public void execute(TaskList tasks, Storage storage) {
		tasks.add(td);
		System.out.println("Successfully added: " + td);
		System.out.println("You now have " + (tasks.getList().size()) + " number of tasks in the list");
		storage.save(tasks);
		//return new CommandResult(String.format("Successfully added:", dl));
	}
}
