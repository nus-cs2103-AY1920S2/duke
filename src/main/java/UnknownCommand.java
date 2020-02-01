public class UnknownCommand implements Command {

    @Override
    public void execute(String task, Ui ui, Storage storage, TaskList taskList) {
        ui.setResponse("I don't understand what you want");
    }

}
