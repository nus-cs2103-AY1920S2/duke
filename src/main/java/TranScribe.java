import java.util.ArrayList;

public class TranScribe {

    ArrayList<Note> notes;

    /**
     * Constructor method for the TranScribe
     */
    public TranScribe() {
        notes = new ArrayList<Note>();
    }

    /**
     * When you want to add a new note.
     */
    public void addNewNote(String message) {
        notes.add(new Note(message));
    }

    /**
     * When you want to get a Note so then maybe you can modify it and use other Note class methods on it.
     * @param index Index of the Note which you want to retrieve.
     * @return the specified Note object.
     */
    public Note getNote(int index) {
        return notes.get(index - 1); // should be minus one hor?
    }

    /**
     * Displays the notes
     * @return A string with all the notes, displayed.
     */
    public String displayNotes() {
        String display = "";
        for (int i = 1; i <= notes.size(); i++) {
            display = display + i + ". " + notes.get(i-1).getInformation();
        }
        return display;
    }

    /**
     * @param index Index at which we want to delete the note.
     */
    public void deleteNote(int index) {
        notes.remove(index - 1);
    }
}
