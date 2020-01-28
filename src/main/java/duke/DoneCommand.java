package duke;

public class DoneCommand extends Command {

    DoneCommand(String input){
        super(input);
    }

    protected void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String[] strArr = input.split(" ");
        int index = Integer.parseInt(strArr[1]) - 1;
        Task taskToBeDone = tasks.get(index);
        taskToBeDone.setDone();
        ui.printWithFormat(taskToBeDone.toString(), "done", tasks);
        storage.saveData(tasks);
    }
}
