public class ExitCommand implements Command {

    @Override
    public void execute(String task, Ui ui, Storage storage, TaskList taskList) {
        ui.setResponse("bye ciao adios");
        ui.exitDuke();
    }

}
