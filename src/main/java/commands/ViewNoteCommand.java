package commands;

import storage.Storage;
import tasklist.TaskList;
import ui.Ui;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class ViewNoteCommand extends Command {

    public String noteType;

    public ViewNoteCommand(String noteType) {
        this.noteType = noteType;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        if (!noteType.equals("all")) { //view school note
            String msg = "Your " + noteType + " notes: \n";
            File file = new File("data/notes/" + noteType + ".txt");
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line;
            while((line = br.readLine()) != null){
                msg += line + "\n";
            }
            return msg;
        } else { //view all notes
            String msg = "These are the notes you have: \n";
            File folder = new File("data/notes/");
            File[] listOfFiles = folder.listFiles();
            for (File file : listOfFiles) {
                if (file.isFile()) {
                    msg += file.getName() + "\n";
                }
            }
            return msg;
        }

    }

    @Override
    public boolean isExit() {
        return false;
    }
}
