package dukeproj.command;

import dukeproj.Storage;
import dukeproj.Ui;
import dukeproj.data.Calender;
import dukeproj.data.TaskList;
import dukeproj.exception.BadDateException;
import dukeproj.exception.BadDescriptionException;
import dukeproj.exception.DukeDescriptionException;

public abstract class Command {

    public abstract String execute(Ui ui, TaskList taskList, Storage storage, Calender calender)
            throws BadDescriptionException, DukeDescriptionException, BadDateException;

}
