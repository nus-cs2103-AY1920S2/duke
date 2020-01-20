public class CommandAddTask extends Command{

    private String description;

    public CommandAddTask(String description){
        this.description=description;
    }

    @Override
    public void execute(Common common, Ui ui) {
        ui.display(common.addTask(description));
    }
}
