package commands;

import storage.Storage;
import task.Deadline;
import taskList.TaskList;

import java.time.LocalDate;

/**
 * Class definition for the "deadline" command.
 * To be used as the intermediate between the Parser and the UI.
 */
public class DeadlineCommand extends Command {
	public static final String COMMAND_WORD = "deadline";

	private final Deadline dl;

	/**
	 * Convenience constructor using raw values.
	 *
	 * @param date the LocalDate object that references the deadline day.
	 * @param desc the desc that represents the action/activity of the Deadline object.
	 */
	public DeadlineCommand(String desc,
						   LocalDate date) {
		this.dl = new Deadline(desc, date);
	}

	/**
	 * Constructor using a complete Deadline object.
	 *
	 * @param dl the Deadline object to be acted upon.
	 */
	public DeadlineCommand(Deadline dl) {
		this.dl = dl;
	}

	public Deadline getDeadline() {
		return dl;
	}

	/**
	 * Executes the command.
	 *
	 * @param tasks   the tasks that is currently being referenced in Duke.
	 * @param storage the storage tool for loading and saving the state of the list after every command.
	 */
	@Override
	public void execute(TaskList tasks, Storage storage) {
		tasks.add(dl);
		System.out.println("Successfully added: " + dl);
		System.out.println("You now have " + (tasks.getList().size()) + " number of tasks in the list");
		storage.save(tasks);
		//return new CommandResult(String.format("Successfully added:", dl));
	}
}
