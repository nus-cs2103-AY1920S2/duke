public class AddCommand extends Command {

    public AddCommand(String command, String description) {
        super(command, description);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException{
        if(command.equals("todo")) {
            if(description.equals("")) {
                throw new DukeException("todo");
            } else {
                tasks.addToDo(description);
            }
        } else if (command.equals("deadline")) {
            if(description.equals("")) {
                throw new DukeException("deadline");
            } else {
                String[] array = description.split(" /by ");
                tasks.addDeadline(array[0], array[1]);
            }
        } else {
            if(description.equals("")) {
                throw new DukeException("event");
            } else {
                String[] array = description.split(" /at ");
                tasks.addEvent(array[0], array[1]);
            }
        }
    }
}
