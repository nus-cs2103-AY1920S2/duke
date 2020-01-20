public class CommandMarkAsDone extends Command{

    private int index;

    public CommandMarkAsDone(int index){
        this.index=index;
    }

    @Override
    public void execute(Common common, Ui ui) {
        ui.display(common.markTask(index));
    }
}
