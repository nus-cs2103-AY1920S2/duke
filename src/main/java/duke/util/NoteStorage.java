package duke.util;

import duke.exception.DukeInvalidDateFormatException;
import duke.exception.DukeInvalidTaskFormatException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
/*
 * NoteStorage
 *
 * CS2103 AY19/20 Semester 2
 * Individual Project
 * Duke Project
 *
 * 08 Feb 2020
 *
 */

/**
 * NoteStorage class abstracts the I/O method
 * of reading and writing notes from a file.
 * @author Mario Lorenzo
 */

public class NoteStorage implements IStorage<Note> {
    private NoteReader reader;
    private NoteWriter writer;

    /**
     * Constructs a NoteStorage instance.
     * @param filePath The file path where the file is located.
     */

    public NoteStorage(String filePath) {
        assert (new File(filePath)).exists() : "The storage file does not exist.";
        this.reader = new NoteReader(filePath);
        this.writer = new NoteWriter(filePath);
    }

    /**
     * Loads the notes from the file.
     * @return The ArrayList of all the notes loaded from the file.
     * @throws DukeInvalidDateFormatException If there is a not properly formatted date.
     * @throws DukeInvalidTaskFormatException If there is a task there is not properly formatted.
     */

    public ArrayList<Note> load() throws DukeInvalidDateFormatException, DukeInvalidTaskFormatException {
        ArrayList<Note> notes = new ArrayList<>();

        try {
            notes = reader.load();
        } catch (IOException e) {
            System.err.println(e);
        } catch (DukeInvalidTaskFormatException | DukeInvalidDateFormatException e) {
            throw e;
        }
        return notes;
    }

    /**
     * Writes the note to the file.
     * @param note The task that wants to be added to the file.
     * @param isApppendMode Whether the file wants to be appended or resetted to blank.
     */

    public void write(Note note, boolean isApppendMode) {
        try {
            this.writer.write(note, isApppendMode);
        } catch (IOException e) {
            System.err.println(e);
        }
    }

    /**
     * Rewrites the list of tasks to the file.
     * @param notes The ArrayList of notes.
     */

    public void rewriteToFile(ArrayList<Note> notes) {
        this.writer.setBlank();
        try {
            for (int i = 0; i < notes.size(); i++) {
                this.writer.write(notes.get(i), i != 0);
            }
        } catch (IOException e) {
            System.err.println(e);
        }
    }
}
