package duke;

import java.io.IOException;

public class EditDateCommand implements Command {

    @Override
    public String execute(String task, MyList taskList, Ui ui, Storage storage) throws IOException, DukeException {

        String[] split = task.split(" ", 4);

        if (split.length < 4) {
            throw new DukeException("Edit date command needs a task number and a new name");
        }

        int taskNumber = Integer.parseInt(split[2]);
        Task taskToEdit = taskList.getTask(taskNumber);
        String newDate = split[3];

        if (taskToEdit instanceof Todo) {
            throw new DukeException("Edit date command cannot be done for a todo task");
        } else if (taskToEdit instanceof Deadline) {
            ((Deadline) taskToEdit).setDateAndTime(newDate);
        } else {
            ((Event) taskToEdit).setDateAndTime(newDate);
        }

        return "The task date has been changed to " + newDate + "\n" + taskToEdit.toString();
    }

    @Override
    public boolean isExit() {
        return false;
    }

}
