package duke.util;

/*
 * IWriter
 *
 * CS2103 AY19/20 Semester 2
 * Individual Project
 * Duke Project
 *
 * 08 Feb 2020
 *
 */

import java.io.IOException;

/**
 * <p>The IWriter interface describes the behavior of a writer.</p>
 * @author Mario Lorenzo
 */

public interface IWriter<T> {

    /**
     * Writes the tasks or notes to the file.
     * @param content The string format of a task or note.
     * @param isAppendMode The boolean of whether the file writing is in append mode.
     * @throws IOException If there is an I/O issue.
     */

    void write(T content, boolean isAppendMode) throws IOException;

    /**
     * Sets the file to blank.
     */

    void setBlank();
}
