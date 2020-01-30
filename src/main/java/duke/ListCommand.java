package duke;

/**
 * Lists all the available tasks.
 */
public class ListCommand extends Command{
    ListCommand(String input){
        super(input);
    }

    protected void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ui.printWithFormat("", "list", tasks);
    }
}
