/** Represents a Note which stores snippets of textual information */
public class Note extends Task {

    protected String note;

    /**
     * Constructor for Note object.
     *
     * @param desc note tag description.
     * @param note note description.
     */
    public Note(String desc, String note) {
        super(desc);
        this.note = note;
    }

    @Override
    public String toString() {
        return "[N] " + desc + " -> " + note;
    }
}