package duke.command;

import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

public class HeyCommand extends Command {
    @Override
    public boolean isExitCommand() {
        return false;
    }

    @Override
    public String execute(TaskList tasklist, Ui ui, Storage storage) {
        String greetings = "Hi Stan! This is Eric! Give me some things to do so I can lose some weight!\n";
        return greetings;
    }
}
