package duke.mission;

import duke.util.Ui;
import duke.util.Storage;
import duke.util.TaskList;

public class FindMission extends Mission {
    public FindMission(String input) {
        super(input);
    }

    @Override
    public String run(Ui ui, Storage storage, TaskList tasks) {
        return ui.showFind(input.substring(5), tasks);
    }
}
