package commands;

import storage.Storage;
import taskList.TaskList;
import java.util.ArrayList;
import task.Task;

public class FindCommand extends Command{
	public static final String COMMAND_WORD = "find";
	private String key;

	public FindCommand(String key) {
		this.key = key;
	}

	@Override
	public void execute(TaskList tasks, Storage storage) {
		try {
			ArrayList<Task> matchedTasks = new ArrayList<>();
			for(Task t : tasks.getList()) {
				if(t.contains(key)) {
					matchedTasks.add(t);
				}
			}
			System.out.println("Search Results as follows: ");
			for(Task t: matchedTasks) {
				System.out.println(t);
			}
		} catch (IndexOutOfBoundsException ie) {
			System.out.println("Index does not exist");
		}
	}
}
