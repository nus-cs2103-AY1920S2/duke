public class InvalidCommand implements Command{
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        System.out.println("invalid command execute being called");
    }

    @Override
    public boolean isExit() {
        System.out.println("invalid command isExit being called");
        return false;
    }
}
