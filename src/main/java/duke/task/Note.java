package duke.task;

/**
 * Represents information about small snippets of information user wants to record.
 */
public class Note extends Task {

    public Note(String note) {
        super(note);
    }

    /**
     * Returns note snippet.
     * @return the relevant notes in the Note object.
     */
    public String getNote() {
        return this.identifier;
    }

    @Override
    public String toString() {
        return "Note: " + this.identifier;
    }
}
