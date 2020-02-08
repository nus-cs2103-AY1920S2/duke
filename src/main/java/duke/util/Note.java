package duke.util;

import java.time.LocalDateTime;

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

    /**
     * Constructs a Note instance.
     * @param text The contain of the note.
     */

    public Note(String text, LocalDateTime timestamp) {
        this.text = text;
        this.timestamp = timestamp;
    }
}
