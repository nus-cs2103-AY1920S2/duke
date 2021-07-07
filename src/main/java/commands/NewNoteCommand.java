package commands;

import storage.Storage;
import task.Note;
import tasklist.TaskList;
import ui.Ui;

import java.io.IOException;

public class NewNoteCommand extends Command {

    public Note note;

    public NewNoteCommand(Note note) {
        this.note = note;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        storage.newFile(this.note.getType());
        storage.saveNote(this.note.getType(), this.note.getDescription());
        String msg = "New note added!: \n" + "• " + this.note.getDescription();
        return msg;
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
