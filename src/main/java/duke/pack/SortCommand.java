package duke.pack;

import java.util.Collections;

public class SortCommand extends Command {
    public SortCommand() {
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Collections.sort(tasks.getList(), new SortByDate());
        ui.showSort();
    }

    public String getResponse(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Collections.sort(tasks.getList(), new SortByDate());

        String resp = "I have sorted the tasks chronologically!";

        return resp;
    }

    public Boolean isExit() {
        return false;
    }
}
