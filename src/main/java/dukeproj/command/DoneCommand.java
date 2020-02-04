package dukeproj.command;

import dukeproj.Storage;
import dukeproj.Ui;
import dukeproj.data.Calender;
import dukeproj.data.TaskList;
import dukeproj.enums.SayType;
import dukeproj.exception.BadDateException;
import dukeproj.exception.BadDescriptionException;
import dukeproj.exception.DukeDescriptionException;

public class DoneCommand extends Command {
    private String description;

    @Override
    public String execute(Ui ui, TaskList taskList, Storage storage, Calender calender)
            throws BadDescriptionException, DukeDescriptionException {
        try {
            if (description.isEmpty()) {
                throw new DukeDescriptionException("Empty Description");
            }

            int done = Integer.parseInt(description);

            if (done <= 0 || done > taskList.getSize()) {
                throw new BadDescriptionException("Description for done cannot be "
                        + done);
            }

            taskList.getTask(done - 1).setDone(true);
            storage.writeListIntoFile(taskList.getList());

            return ui.say(SayType.DONE) + "\n  " + taskList.getTask(done - 1);
        } catch (NumberFormatException e) {
            throw new BadDescriptionException("Description for done cannot be Non-Integer");
        }
    }

    public DoneCommand(String description) {
        this.description = description;
    }
}
