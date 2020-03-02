package commands;

import exceptions.DukeException;
import handlers.Storage;
import handlers.Ui;
import tasks.Task;
import tasks.TaskList;

public class NoteCommand extends Command {
    protected String getNote;
    protected Task taskGetNote;

    public NoteCommand(String command, String getNote) {
        this.command = command;
        this.getNote = getNote;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IndexOutOfBoundsException {
        try {
            int num = Integer.parseInt(this.getNote);
            if (num > tasks.numOfTasks()) {
                throw new DukeException("☹ OOPS!!! No task to be done found!");
            } else {
                this.taskGetNote = tasks.get(num - 1);
                String note = this.taskGetNote.getNote();
                ui.clearResponse();
                ui.showGetNote(this.taskGetNote, note);
            }
        } catch (DukeException e) {
            ui.showError(e.getMessage());
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
