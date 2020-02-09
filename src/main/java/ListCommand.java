import java.io.IOException;

public class ListCommand implements Command {

    @Override
    public void execute(String task, MyList taskList, Ui ui, Storage storage) throws IOException, DukeException {
        ui.showTaskList(taskList);
    }

    @Override
    public boolean isExit() {
        return false;
    }

}
