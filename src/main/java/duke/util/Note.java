package duke.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/*
 * Note
 *
 * CS2103 AY19/20 Semester 2
 * Individual Project
 * Duke Project
 *
 * 08 Feb 2020
 *
 */

/**
 * <p>Note class describes the behavior of the notes
 * to be entered by the client.</p>
 * @author Mario Lorenzo
 */

public class Note {
    private String text;
    private LocalDateTime timestamp;
    private static final String DATE_FORMAT = "dd-MM-yyyy HH:mm";

    /**
     * Constructs a Note instance.
     * @param text The contain of the note.
     */

    public Note(String text, LocalDateTime timestamp) {
        this.text = text;
        this.timestamp = timestamp;
    }

    /**
     * Gets the text of the note.
     * @return The note's content.
     */

    public String getText() {
        return this.text;
    }

    /**
     * Gets the date when the note is created.
     * @return The String format of the timestamp.
     */

    public String getDate() {
        return this.timestamp.format(DateTimeFormatter.ofPattern(DATE_FORMAT));
    }

    /**
     * Overrides the Object's toString method.
     * @return the String representation of the Note.
     */

    public String toString() {
        return this.text;
    }
}
