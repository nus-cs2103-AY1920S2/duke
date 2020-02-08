package duke.util;

import duke.exception.DukeInvalidDateFormatException;
import duke.exception.DukeInvalidTaskFormatException;

import java.io.IOException;
import java.util.ArrayList;

/*
 * IReader
 *
 * CS2103 AY19/20 Semester 2
 * Individual Project
 * Duke Project
 *
 * 08 Feb 2020
 *
 */

/**
 * <p>The IReader interface describes the behavior of a reader.</p>
 * @author Mario Lorenzo
 */

public interface IReader<T> {

    /**
     * Loads the tasks or notes from the file.
     * @return The array list of the file content.
     * @throws IOException If there is I/O issue.
     * @throws DukeInvalidTaskFormatException If the format of a task is invalid.
     * @throws DukeInvalidDateFormatException If the format date is invalid.
     */
    
    ArrayList<T> load() throws IOException, DukeInvalidTaskFormatException, DukeInvalidDateFormatException;
}
