package duke.mission;

import duke.util.Ui;
import duke.util.Storage;
import duke.util.TaskList;

public class DeleteMission extends Mission {
    public DeleteMission(String input) {
        super(input);
    }

    @Override
    public String run(Ui ui, Storage storage, TaskList tasks) {
        String[] words = input.split(" ");
        String str;
        try {
            str = ui.showDelete(tasks.getTask(Integer.valueOf(words[1]) - 1), tasks);
            storage.save(tasks);
        } catch (IndexOutOfBoundsException e) {
            str = ui.showException("OOP!!! The number of tasks you have is only "
                    + tasks.getTaskNumber());
        }
        assert str != null : "Incomplete Delete Mission";
        return str;
    }
}