package duke.util;

/*
 * Keyword
 *
 * CS2103 AY19/20 Semester 2
 * Individual Project
 * Duke Project
 *
 * 21 Jan 2020
 *
 */

/**
 * The Command enum represents the instructions
 * that are allowed to be performed by Duke.
 * @author Mario Lorenzo
 */

public enum Keyword {
    /**
     * Keywords that can be used.
     * <li>LIST</li>
     * <li>DONE</li>
     * <li>TODO</li>
     * <li>DEADLINE</li>
     * <li>EVENT</li>
     * <li>DELETE</li>
     * <li>FIND</li>
     * <li>BYE</li>
     * <li>REMINDER</li>
     * <li>ARCHIVE_LIST</li>
     * <li>ARCHIVE_ADD</li>
     * <li>ARCHIVE_DELETE</li>
     * <li>UNARCHIVE</li>
     * <li>NOTE_LIST</li>
     * <li>NOTE_ADD</li>
     * <li>NOTE_DELETE</li>
     */

    LIST, DONE, TODO, DEADLINE, EVENT, DELETE, FIND, BYE, REMINDER, ARCHIVE_LIST, ARCHIVE_ADD, ARCHIVE_DELETE,
    UNARCHIVE, NOTE_LIST, NOTE_ADD, NOTE_DELETE;
}
