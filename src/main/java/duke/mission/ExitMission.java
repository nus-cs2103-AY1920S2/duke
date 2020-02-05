package duke.mission;

import duke.util.Ui;
import duke.util.Storage;
import duke.util.TaskList;

public class ExitMission extends Mission {
    @Override
    public String run(Ui ui, Storage storage, TaskList tasks) {
        return ui.showExit();
    }
}
