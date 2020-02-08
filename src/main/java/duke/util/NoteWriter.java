package duke.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

/*
 * NoteWriter
 *
 * CS2103 AY19/20 Semester 2
 * Individual Project
 * Duke Project
 *
 * 27 Jan 2020
 *
 */

/**
 * <p>NoteWriter class abstracts the output method
 * to write the note to the file whenever there is a
 * change.</p>
 * @author Mario Lorenzo
 */

public class NoteWriter implements IWriter<Note> {
    private String filename;

    public NoteWriter(String filename) {
        this.filename = filename;
    }

    /**
     * Writes the note to the file whenever there is a change
     * from the client.
     * @param note The note that would be written.
     * @param isAppendMode A boolean to determine whether the writer is in append mode.
     * @throws IOException If there is an error in I/O.
     */

    public void write(Note note, boolean isAppendMode) throws IOException {
        FileOutputStream fos;
        if (isAppendMode) {
            fos = new FileOutputStream(new File(filename), true);
        } else {
            fos = new FileOutputStream(new File(filename));
        }

        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(fos));
        String[] dataArray = new String[2];

        dataArray[0] = note.getText();
        dataArray[1] = note.getDate();

        String noteString = String.join(" | ", dataArray);
        if (isAppendMode) {
            writer.newLine();
        }

        writer.write(noteString);
        writer.close();
    }

    /**
     * Sets the file to blank.
     */

    public void setBlank() {
        try {
            FileOutputStream fos;
            fos = new FileOutputStream(new File(filename));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(fos));
            writer.close();

        } catch (IOException e) {
            System.err.println(e);
        }
    }
}
