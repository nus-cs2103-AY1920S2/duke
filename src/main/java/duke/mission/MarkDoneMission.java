package duke.mission;

import duke.util.Ui;
import duke.util.Storage;
import duke.util.TaskList;


public class MarkDoneMission extends Mission {
    public MarkDoneMission(String input) {
        super(input);
    }

    @Override
    public String run(Ui ui, Storage storage, TaskList tasks) {
        String[] words = input.split(" ");
        String str;
        try {
            str = ui.showMarkDone(tasks.getTask(Integer.valueOf(words[1]) - 1));
            storage.save(tasks);
        } catch (IndexOutOfBoundsException e) {
            str = ui.showException("OOP!!! The number of tasks you have is only "
                    + tasks.getTaskNumber());
        }
        assert str != null : "Incomplete MarkDone Mission";
        return str;
    }
}
