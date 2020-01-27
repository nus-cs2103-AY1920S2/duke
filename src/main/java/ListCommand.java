public class ListCommand extends Command {

    public ListCommand(){}

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage){
        ui.showList(tasks.getTasks());
    }


}
