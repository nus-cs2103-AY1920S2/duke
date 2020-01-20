public class CommandList extends Command{

    @Override
    public void execute(Common common, Ui ui) {
        ui.display(common.printList());
    }
}
