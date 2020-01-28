package duke;

public class FindCommand extends Command {

    FindCommand(String input) {
        super(input);
    }

    @Override
    protected void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String[] strArr = input.split(" ");
        String keyword = strArr[1];
        TaskList matchingList = tasks.find(keyword);
        ui.printWithFormat("", "find", matchingList);
    }
}
