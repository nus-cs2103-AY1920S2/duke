package duke.commands;

import java.util.ArrayList;

import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.ui.Ui;

public class FindCommand extends Command {
    private String keyword = "";
    private ArrayList<Task> filteredTasks;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

	@Override
	public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        filteredTasks = tasks.filterTasks(keyword);
        String[] temp;
        if (filteredTasks.size() == 0) {
            temp = new String[]{"I couldn't find any tasks containing that keyword, boss. Try something else?"};
        } else {
            temp = new String[tasks.getTaskList().size() + 1];
            temp[0] = "I found these tasks that match what you might be looking for, boss!";
            for (int i = 0; i < filteredTasks.size(); i++) {
                temp[i + 1] = (i + 1) + ". " + filteredTasks.get(i).toString();
            }
        }
        ui.dukePrompt(temp);
	}

	@Override
	public boolean isExit() {
		return false;
	}

}