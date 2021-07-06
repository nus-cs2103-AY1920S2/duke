public class UndoCommand extends Command {

    public UndoCommand(String command, String description) {
            super(command, description);
        }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        String result = "";

        Command lastCommand = tasks.getLastCommand();

        boolean hasLastCommand = lastCommand != null;

        if(hasLastCommand) {
            result = lastCommand.undo();

            tasks.setLastCommand(this);
        } else {
            return "Nothing to undo";
        }

        return result;
    }

    @Override
    public String undo() {
        return "Nothing to undo";
    }
}
