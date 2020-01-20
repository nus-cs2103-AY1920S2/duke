public class Command_MarkAsDone extends Command{

    private int index;

    public Command_MarkAsDone(int index){
        this.index=index;
    }

    @Override
    public void execute(Common common, Ui ui) throws DukeException {
        ui.display(common.markTask(index));
    }
}
