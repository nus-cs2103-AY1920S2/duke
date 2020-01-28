package duke.pack;

public class ListCommand extends Command {
    public ListCommand() {

    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.printList();
    }

    public Boolean isExit() {
        return false;
    }

}
