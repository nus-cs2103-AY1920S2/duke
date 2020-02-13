package duke.util;

import duke.exception.DukeInvalidDateFormatException;
import duke.exception.DukeInvalidTaskFormatException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

/*
 * NoteReader
 *
 * CS2103 AY19/20 Semester 2
 * Individual Project
 * Duke Project
 *
 * 08 Feb 2020
 *
 */

/**
 * <p>NoteReader class abstracts the input method
 * from a file, parses the notes, and loads them
 * to a Duke instance.</p>
 * @author Mario Lorenzo
 */

public class NoteReader implements IReader<Note> {
    private String filename;
    private static final String TIMESTAMP_FORMAT = "dd-MM-yyyy HH:mm";

    /**
     * Constructs a NoteReader instance that is used
     * for reading the list of tasks from a file.
     * @param filename The filename where the list of notes is located.
     */

    public NoteReader(String filename) {
        this.filename = filename;
    }

    /**
     * Loads the list of tasks from the file.
     * @return The ArrayList containing the tasks loaded from the file.
     * @throws IOException From the InputStreamReader
     * @throws DukeInvalidTaskFormatException If there is an error in the format.
     * @throws DukeInvalidDateFormatException If there is an error in the date format.
     */

    public ArrayList<Note> load() throws IOException, DukeInvalidTaskFormatException,
            DukeInvalidDateFormatException {
        FileInputStream fis = new FileInputStream(new File(filename));
        BufferedReader reader = new BufferedReader(new InputStreamReader(fis));

        String line;
        ArrayList<Note> notes = new ArrayList<>();
        int counter = 1;
        while ((line = reader.readLine()) != null) {
            notes.add(parseNote(line, counter));
        }
        reader.close();

        return notes;
    }

    /**
     * Parses a line describing a single note from a file.
     * @param line The line describing the detail of a note.
     * @param counter The index of the line
     * @return The corresponding note of the line
     * @throws DukeInvalidTaskFormatException If the note is not properly formatted.
     * @throws DukeInvalidDateFormatException If there is an error in the date format.
     */

    private Note parseNote(String line, int counter) throws DukeInvalidTaskFormatException,
            DukeInvalidDateFormatException {
        String[] lineSplitted = line.split(" \\| ");

        try {
            LocalDateTime timestamp = LocalDateTime.parse(lineSplitted[0],
                    DateTimeFormatter.ofPattern(TIMESTAMP_FORMAT));
            String content = lineSplitted[1];

            return new Note(content, timestamp);
        } catch (DateTimeParseException e) {
            throw new DukeInvalidDateFormatException("OOPS! It seems that your date "
                    + "is not properly formatted. The date should be in form of 'dd-MM-yyyy HH:mm'");
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeInvalidTaskFormatException("OOPS! It seems that yout notes is lack of argument.", line,
                    counter);
        }
    }
}
