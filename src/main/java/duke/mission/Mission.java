package duke.mission;

import duke.util.Ui;
import duke.util.Storage;
import duke.util.TaskList;

public abstract class Mission {
    public String input;

    public Mission(String input) {
        this.input = input;
    }

    public Mission() {
        this.input = null;
    }

    public abstract String run(Ui ui, Storage storage, TaskList tasks);
}
