public class DoneCommand extends Command {
    public DoneCommand(String command, String description) {
        super(command, description);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        int num = Integer.parseInt(description);

        tasks.setDone(num);
    }
}
