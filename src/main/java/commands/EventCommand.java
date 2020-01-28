package commands;

import storage.Storage;
import task.Event;
import taskList.TaskList;

import java.time.LocalDate;

public class EventCommand extends Command {
	public static final String COMMAND_WORD = "event";

	private final Event ev;

	/**
	 * Convenience constructor using raw values.
	 */
	public EventCommand(String desc,
						LocalDate date) {
		this.ev = new Event(desc, date);
	}

	public EventCommand(Event ev) {
		this.ev = ev;
	}

	public Event getEvent() {
		return ev;
	}

	@Override
	public void execute(TaskList tasks, Storage storage) {
		tasks.add(ev);
		System.out.println("Successfully added: " + ev);
		System.out.println("You now have " + (tasks.getList().size()) + " number of tasks in the list");
		storage.save(tasks);
		//return new CommandResult(String.format("Successfully added:", dl));
	}
}

