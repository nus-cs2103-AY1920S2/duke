import java.time.LocalDateTime;

public class Note {

    String message;
    String noteTitle; // like a "preview" of the note.
    LocalDateTime messageDate; // the date when you took down the note
    int numModifications; // number of times this note has been modified.
    LocalDateTime lastModified; // the date when the Note was last modified.

    /**
     * Constructor for the note.
     * @param message Whatever you want to write in the note.
     */
    public Note(String message) {
        this.message = message;
        if (message.length() < 30) {
            this.noteTitle = message;
        }
        else {
            this.noteTitle = message.substring(0, 30);
        }
        this.messageDate = LocalDateTime.now();
        this.lastModified = this.messageDate;
    }

    /**
     * Getter method to return the contents of the Note.
     * @return Whatever was written previously in the Note.
     */
    public String getMessage() {
        return this.message;
    }

    /**
     * You can change the note title if you don't want it to be just a preview of the message.
     * Please note that note title can only be less than 30 characters.
     */
    public void setNoteTitle(String newTitle) {
        this.noteTitle = newTitle;
        numModifications++;
    }

    /**
     * Changes the message
     * @param newMessage The new message which you want to replace the original message with.
     */
    public void modify(String newMessage) {
        this.message = newMessage;
        this.lastModified = LocalDateTime.now();
        numModifications++;
    }

    /**
     * Getter method to return information about the Note.
     * @return Information about the Note including title, date of inception, number of modifications, date of last modification
     */
    public String getInformation() {
        String info = "Note title: " + noteTitle + "\n";
        info = info + "Inception date: " + messageDate + "\n";
        info = info + "There have been " + numModifications + " modifications since.\n";
        info = info + "Last modified date: " + lastModified + "\n";
        return info;
    }

}