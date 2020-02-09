package commands;

import exceptions.DukeException;
import handlers.Storage;
import handlers.Ui;

import tasks.Task;
import tasks.TaskList;

public class AddNoteCommand extends Command{
    protected String addNote;
    protected Task taskNote;
    protected String note;

    public AddNoteCommand(String command, String addNote, String note) {
        this.command  = command;
        this.addNote = addNote;
        this.note = note;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IndexOutOfBoundsException {
        int num = Integer.parseInt(this.addNote);
        try {
            if (num > tasks.numOfTasks()) {
                throw new DukeException("☹ OOPS!!! No task to be done found!");
            } else {
                this.taskNote = tasks.get(num - 1);
                this.taskNote.setNote(this.note);
                ui.clearResponse();
                ui.showAddedNote(this.taskNote);
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
