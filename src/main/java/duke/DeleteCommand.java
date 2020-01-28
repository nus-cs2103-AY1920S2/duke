package duke;

public class DeleteCommand extends Command {

    DeleteCommand(String input){
        super(input);
    }

    protected void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String[] strArr = input.split(" ");
        int index = Integer.parseInt(strArr[1]) - 1;
        Task taskToBeDeleted = tasks.delete(index);
        ui.printWithFormat(taskToBeDeleted.toString(), "delete", tasks);
        storage.saveData(tasks);
    }

}
