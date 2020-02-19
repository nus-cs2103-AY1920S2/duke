package duke;

public class DeleteCommand extends Command {

    DeleteCommand(String str) {
        super(str);
    }

    String execute(TaskList lst, Storage storage, Ui ui, int tasks) {
        int i = Integer.parseInt(str.split(" ")[1]) - 1;
        Task task = lst.getTask(i);
        try {
            lst.deleteTask(task);
        } catch (DukeException e) {
            System.out.println(e);
        }
        if (!task.getStatus()) {
            tasks--;
        }
        return ui.taskDelete(task, tasks);
    }
}