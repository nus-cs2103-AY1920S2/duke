package duke.commands;

import duke.enums.TaskTypes;
import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.tasks.ToDo;
import duke.ui.Ui;

public class AddCommand extends Command {
    private TaskTypes taskType;
    private String inputArgs;

    public AddCommand(TaskTypes taskType, String inputArgs) {
        this.taskType = taskType;
        this.inputArgs = inputArgs;
    }

	@Override
	public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task newTask = null;
        String taskPrompt = "";

        switch (taskType) {
            case DEADLINE:
                newTask = new Deadline(inputArgs);
                taskPrompt = "Oooh, important deadline eh, boss? Don't worry, I got it";
                break;
            case TODO:
                newTask = new ToDo(inputArgs);
                taskPrompt = "Got it, boss. I'll write this one down";
                break;
            case EVENT:
                newTask = new Event(inputArgs);
                taskPrompt = "A special event I see. Don't worry boss, I'll remind you";
                break;
        }
        tasks.addTask(newTask);
        ui.dukePrompt(new String[]{taskPrompt,
            "\n",
            newTask.toString(),
            "\n",
            tasks.printTasksTotal()});
        storage.save(tasks);
	}

	@Override
	public boolean isExit() {
		return false;
	}
}