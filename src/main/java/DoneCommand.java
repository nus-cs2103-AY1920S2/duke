import java.io.IOException;

public class DoneCommand implements Command {
    @Override
    public void execute(String task, MyList taskList, Ui ui, Storage storage) throws IOException, DukeException {

        String[] split = task.split(" ");
        int num = Integer.parseInt(split[1]);
        Task toComplete = taskList.getTask(num);
        toComplete.setCompleted(true);
        ui.showTaskDoneMessage(toComplete, num);

    }

    @Override
    public boolean isExit() {
        return false;
    }

}
