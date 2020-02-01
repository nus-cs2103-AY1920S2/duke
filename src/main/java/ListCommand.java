public class ListCommand implements Command  {

    @Override
    public void execute(String task, Ui ui, Storage storage, TaskList taskList) {
        ui.setResponse(taskList.toString());
    }

}
