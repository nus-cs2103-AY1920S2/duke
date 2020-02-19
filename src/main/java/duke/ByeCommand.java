package duke;

public class ByeCommand extends Command {

    ByeCommand(String str) {
        super(str);
    }

    String execute(TaskList lst, Storage storage, Ui ui, int tasks) {
        storage.writeToFile(lst);
        return ui.saybye();
    }
}