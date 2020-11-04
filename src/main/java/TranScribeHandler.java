// Handles all the commands being passed to the TranScribe.

public class TranScribeHandler {

    TranScribe ts;

    /**
     * Constructor method for my TranScribeHandler.
     */
    public TranScribeHandler() {
        this.ts = new TranScribe();
    }

    // what are the possible commands you can handle with notes?
    /**
     * Handles all commands specific to the notes function.
     * @param commands Notes-function specific command.
     * @return Message explaining the notes command/what has been modified.
     * @throws DukeException
     */
    public String handle(String commands) throws DukeException {
        String[] split_str = commands.split(" ");
        if (split_str[0].equals("documentation")) {
            String docs = "The TranScribe is a portable communication device developed by TranStar. The display is " +
                    "capable of voice chat, email, station navigation and inventory management.\n";
            docs = docs + "Here are the things you can do with the TranScribe:\n";

            docs = docs + "View all notes: notes view\n";
            docs = docs + "Display a note: notes display <index of note>\n";
            docs = docs + "Modify note: notes modify <index of note> <new message>\n";
            docs = docs + "Add note: notes add <message>\n";
            docs = docs + "Delete note: notes delete <index of note>\n";
            docs = docs + "Change note title: notes change <index of note> <new note title>\n";

            docs = docs + "Welcome aboard Talos I, Dr. Yu.\n";
            return docs;
        }
        else if (split_str[0].equals("modify")) {
            if (commands.length() < 8) { // remember command doesn't take into account the 0th index.
                throw new DukeException("Notes parameters are empty.\n");
            }
            int index = Integer.parseInt(split_str[1]);
            String message = commands.substring(9);
            ts.getNote(index).modify(message);
            return "TranScribe updated. Your note has been modified with the following message:\n" + message + "\n";
        }
        else if (split_str[0].equals("add")) {
            String message = commands.substring(4);
            ts.addNewNote(message);
            return "TranScribe updated. Your note has been added with the following message:\n" + message + "\n";
        }
        else if (split_str[0].equals("delete")) {
            if (commands.length() < 8) {
                throw new DukeException("Notes parameters are empty.\n");
            }
            int index = Integer.parseInt(split_str[1]);
            String message = ts.getNote(index).getMessage();
            ts.deleteNote(index);
            return "TranScribe updated. Your note has been deleted with the following message:\n" + message + "\n";
        }
        else if (split_str[0].equals("display")) { // DISPLAYS ONE NOTE.
            if (commands.length() < 9) {
                throw new DukeException("Notes parameters are empty.\n");
            }
            int index = Integer.parseInt(split_str[1]);
            String note = ts.getNote(index).getMessage() + "\n" + ts.getNote(index).getInformation();
            return note;
        }
        else if (split_str[0].equals("view")) { // DISPLAYS ALL NOTES.
            return ts.displayNotes();
        }
        else if (split_str[0].equals("change")) {
            int index = Integer.parseInt(split_str[1]);
            String title = commands.substring(9);
            ts.getNote(index).setNoteTitle(title);
            return "TranScribe updated. Your note title has been changed to the following:\n" + title + "\n";
        }

        return "You can't do that with the TranScribe.\n";
    }
}
