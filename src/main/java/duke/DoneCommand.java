package duke;

public class DoneCommand extends Command {

    DoneCommand(String str) {
        super(str);
    }

    String execute(TaskList lst, Storage storage, Ui ui, int tasks) {
        int space = Integer.parseInt(str.split(" ")[1]) -1;
        Task task = lst.getTask(space);
        task.doAct();
        tasks--;
        return ui.taskDone(task, tasks);
    }
}