package dukeproj.command;

import dukeproj.Duke;
import dukeproj.Storage;
import dukeproj.Ui;
import dukeproj.data.Calender;
import dukeproj.data.TaskList;
import dukeproj.enums.SayType;
import dukeproj.exception.BadDescriptionException;
import dukeproj.exception.DukeDescriptionException;
import dukeproj.tasks.Task;

public class DeleteCommand extends Command {
    private String description;

    @Override
    public String execute(Ui ui, TaskList taskList, Storage storage, Calender calender)
            throws BadDescriptionException, DukeDescriptionException {
        String output = "";
        try {
            if (description.isEmpty()) {
                throw new DukeDescriptionException("Empty Description");
            }

            int delete = Integer.parseInt(description);

            if (delete <= 0 || delete > taskList.getSize()) {
                throw new BadDescriptionException("Description for delete cannot be " + delete);
            }

            Task deletedTask = taskList.getTask(delete - 1);
            taskList.removeTask(delete - 1);
            calender.removeTask(deletedTask, deletedTask.getDate());
            storage.writeListIntoFile(taskList.getList());

            output = ui.say(SayType.DELETE) + "\n" + deletedTask;
        } catch (NumberFormatException e) {
            throw new BadDescriptionException("Non-Integer");
        } finally {
            return output;
        }
    }

    public DeleteCommand(String description) {
        this.description = description;
    }

}
