public class Command_Delete extends Command{

    private int index;

    public Command_Delete(int index){
        this.index=index;
    }

    @Override
    public void execute(Common common, Ui ui) throws DukeException {
        ui.display(common.deleteTask(index));
    }
}
