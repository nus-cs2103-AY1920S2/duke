import java.io.IOException;

public class DeleteCommand implements Command {

    @Override
    public void execute(String task, MyList taskList, Ui ui, Storage storage) throws IOException, DukeException {

        String[] split = task.split(" ");
        int num = Integer.parseInt(split[1]);
        taskList.deleteTask(num);
        ui.showTaskDeletedMessage(num);

    }

    @Override
    public boolean isExit() {
        return false;
    }

}
