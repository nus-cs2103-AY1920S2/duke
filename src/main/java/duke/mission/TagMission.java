package duke.mission;

import duke.util.Ui;
import duke.util.Storage;
import duke.util.TaskList;


public class TagMission extends Mission {
    public TagMission(String input) {
        super(input);
    }

    @Override
    public String run(Ui ui, Storage storage, TaskList tasks) {
        String[] words = input.split(" ");
        String str;
        try {
            str = ui.showTag(tasks.getTask(Integer.parseInt(words[1]) - 1), words[2]);
            storage.save(tasks);
        } catch (IndexOutOfBoundsException e) {
            str = ui.showException("OOP!!! The number of tasks you have is only "
                    + tasks.getTaskNumber());
        }
        assert str != null : "Incomplete Tag Mission";
        return str;
    }
}
