package commands;

import storage.Storage;
import task.Deadline;
import taskList.TaskList;

import java.time.LocalDate;

public class DeadlineCommand extends Command {
	public static final String COMMAND_WORD = "deadline";

	private final Deadline dl;

	/**
	 * Convenience constructor using raw values.
	 */
	public DeadlineCommand(String desc,
						   LocalDate date) {
		this.dl = new Deadline(desc, date);
	}

	public DeadlineCommand(Deadline dl) {
		this.dl = dl;
	}

	public Deadline getDeadline() {
		return dl;
	}

	@Override
	public void execute(TaskList tasks, Storage storage) {
		tasks.add(dl);
		System.out.println("Successfully added: " + dl);
		System.out.println("You now have " + (tasks.getList().size()) + " number of tasks in the list");
		storage.save(tasks);
		//return new CommandResult(String.format("Successfully added:", dl));
	}
}
