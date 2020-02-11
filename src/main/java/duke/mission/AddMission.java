package duke.mission;

import duke.util.Ui;
import duke.util.Storage;
import duke.util.TaskList;
import duke.task.Todo;
import duke.task.Deadline;
import duke.task.Event;

import java.time.LocalDate;

public class AddMission extends Mission {
    public AddMission(String input) {
        super(input);
    }

    @Override
    public String run(Ui ui, Storage storage, TaskList tasks) {
        String[] words = input.split(" ");
        String str;
        if (words[0].equals("todo")) {
            try {
                str = ui.showAdd(new Todo(input.substring(5)), tasks);
                storage.save(tasks);
            } catch (IndexOutOfBoundsException e) {
                str = ui.showException("OOPS!!! The description of a todo cannot be empty.");
            }
        } else if (words[0].equals("deadline")) {
            String[] ddlDetails = input.substring(9).split(" /by ");
            try {
                str = ui.showAdd(new Deadline(ddlDetails[0], LocalDate.parse(ddlDetails[1])), tasks);
                storage.save(tasks);
            } catch (Exception e) {
                str = ui.showException("Input time should be \" /by yyyy-mm-dd\"");
            }
        } else {
            String[] eventDetails = input.substring(6).split(" /at ");
            try {
                str = ui.showAdd(new Event(eventDetails[0], LocalDate.parse(eventDetails[1])), tasks);
                storage.save(tasks);
            } catch (Exception e) {
                str = ui.showException("Input time should be \" /at yyyy-mm-dd\"");
            }
        }
        assert str != null : "Incomplete Add Mission";
        return str;
    }
}
