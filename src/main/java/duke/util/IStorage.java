package duke.util;

import duke.exception.DukeInvalidDateFormatException;
import duke.exception.DukeInvalidTaskFormatException;

import java.util.ArrayList;

/*
 * IStorage
 *
 * CS2103 AY19/20 Semester 2
 * Individual Project
 * Duke Project
 *
 * 08 Feb 2020
 *
 */

/**
 * <p>IStorage interface describes the behavior
 * of the storage that reads and writes the objects into files.
 * </p>
 * @author Mario Lorenzo
 */

public interface IStorage<T> {

    /**
     * Loads the content from the file.
     * @return The ArrayList of all the content loaded from the file.
     * @throws DukeInvalidDateFormatException If there is a not properly formatted date.
     * @throws DukeInvalidTaskFormatException If there is a content there is not properly formatted.
     */

    ArrayList<T> load() throws DukeInvalidDateFormatException, DukeInvalidTaskFormatException;

    /**
     * Writes the content to the file.
     * @param content The content that wants to be written.
     * @param isAppendMode Whether the file wants to be appended or resetted to blank.
     */

    void write(T content, boolean isAppendMode);

    /**
     * Rewrites the content to the file whenever there is a change.
     * @param contents The ArrayList of the content.
     */

    void rewriteToFile(ArrayList<T> contents);
}
