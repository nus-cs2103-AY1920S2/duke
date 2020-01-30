package ip.command;

import ip.Ui;
import ip.task.TaskList;

public class DoneCommand extends Command{
    private int index;
    public DoneCommand(int i){
        this.index = i;
    }
    @Override
    public void execute(TaskList tasks, Ui ui) {
        if (tasks.size() == 0) {
            new InvalidCommand("There are no tasks in the list :(").execute(tasks, ui);
            return;
        }
        if (index < 0 || index >= tasks.size()) {
            new InvalidCommand("Please enter a valid index, from 1 to " + tasks.size()).execute(tasks, ui);
            return;
        }
        ui.displayTaskMarkedDone(tasks.markTaskDone(index));
    }
}
