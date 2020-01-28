package commands;

import storage.Storage;
import task.Task;
import taskList.TaskList;

public class ListCommand extends Command {
	public static final String COMMAND_WORD = "list";

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
