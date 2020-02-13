import duke.command.Command;
import duke.exception.DukeException;
import duke.exception.DukeInvalidDateFormatException;
import duke.exception.DukeInvalidTaskFormatException;
import duke.util.ArchiveList;
import duke.util.Note;
import duke.util.NoteList;
import duke.util.NoteStorage;
import duke.util.Parser;
import duke.util.Storage;
import duke.util.Task;
import duke.util.TaskList;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/*
 * Duke
 *
 * CS2103 AY19/20 Semester 2
 * Individual Project
 * Duke Project
 *
 * 21 Jan 2020
 *
 */

/**
 * <p>The Duke class is the main class of the bot,
 * where the command processing happens.</p>
 * @author Mario Lorenzo
 */

public class Duke {
    private TaskList taskList;
    private ArchiveList archiveList;
    private Storage taskStorage;
    private Storage archiveStorage;
    private NoteStorage noteStorage;
    private NoteList noteList;
    private Parser parser;
    private static final String DATA_DIRECTORY = "./data";
    private static final String TASK_DIRECTORY = "./data/tasks.txt";
    private static final String ARCHIVE_DIRECTORY = "./data/archive.txt";
    private static final String NOTES_DIRECTORY = "./data/notes.txt";

    /**
     * Constructs the Duke instance that has a list that
     * stores tasks, as well as the list of all valid commands
     * that the Duke instance can perform, stored in a HashMap.
     */

    private Duke(TaskList taskList, ArchiveList archiveList,
                 Storage taskStorage, Storage archiveStorage, NoteList noteList,
                 NoteStorage noteStorage, Parser parser) {
        this.taskList = taskList;
        this.archiveList = archiveList;
        this.taskStorage = taskStorage;
        this.archiveStorage = archiveStorage;
        this.noteList = noteList;
        this.noteStorage = noteStorage;
        this.parser = parser;
    }

    /**
     * Creates a Duke instance with a factory method fashion.
     * A Duke instance with the list of tasks loaded will be created
     * if the file is formatted properly.
     * @return A Duke instance, loaded with the list of tasks.
     * @throws DukeInvalidTaskFormatException If the file is not formatted properly.
     * @throws DukeInvalidDateFormatException If the date of the deadline or event is not formatted properly.
     */

    public static Duke start() throws DukeInvalidTaskFormatException, DukeInvalidDateFormatException {
        setStorage();
        Storage taskStorage = new Storage(TASK_DIRECTORY);
        Storage archiveStorage = new Storage(ARCHIVE_DIRECTORY);
        NoteStorage noteStorage = new NoteStorage(NOTES_DIRECTORY);
        ArrayList<Task> tasks = new ArrayList<>();
        ArrayList<Task> archives = new ArrayList<>();
        ArrayList<Note> notes = new ArrayList<>();

        try {
            tasks = taskStorage.load();
            archives = archiveStorage.load();
            notes = noteStorage.load();
        } catch (DukeInvalidTaskFormatException | DukeInvalidDateFormatException e) {
            throw e;
        }

        TaskList taskList = new TaskList(tasks);
        ArchiveList archiveList = new ArchiveList(archives);
        NoteList noteList = new NoteList(notes);
        return new Duke(taskList, archiveList, taskStorage, archiveStorage, noteList, noteStorage, new Parser());
    }

    /**
     * Parses the input entered by the client.<br>
     *     The following are valid commands that Duke can process:
     *     <ul>
     *         <li><tt>list</tt> - lists all the tasks that Duke has stored.</li>
     *         <li><tt>done [index]</tt> - marks the task of a particular index as done.</li>
     *         <li><tt>delete [index]</tt> - deletes the task of a particular index.</li>
     *         <li><tt>todo [description]</tt> - adds the Todo task to the list.</li>
     *         <li><tt>deadline [description] /by [date]</tt> - adds the Deadline task to the list.</li>
     *         <li><tt>event [description] /at [date]</tt> - adds the Event task to the list.</li>
     *         <li><tt>find [keyword]</tt> - finds tasks using a keyword.</li>
     *         <li><tt>archive-list</tt> - lists all the archived tasks.</li>
     *         <li><tt>archive-add [index]</tt> - moves a task at a particular index to the archive.</li>
     *         <li><tt>archive-delete [index]</tt> - deletes an archived task at a particular index.</li>
     *     </ul>
     * @param commands The instruction provided by the client.
     */

    public String processCommand(String commands) {
        try {
            Command command = parser.parse(commands, taskList, archiveList, noteList);
            return command.execute(taskList, taskStorage, archiveList, archiveStorage, noteList, noteStorage);
        } catch (DukeException exc) {
            return exc.getMessage();
        }
    }

    /**
     * Sets up the storage for Duke to read and write the contents.
     */

    private static void setStorage() {
        createDataDirectory();
        createTaskFile();
        createArchiveFile();
        createNoteFile();
    }
    /**
     * Creates the data directory if it is not exist yet.
     */

    private static void createDataDirectory() {
        File file = new File(DATA_DIRECTORY);
        if (!file.exists()) {
            file.mkdir();
        }
    }

    /**
     * Creates the tasks.txt file inside the data directory if not exist yet.
     */

    private static void createTaskFile() {
        File file = new File(TASK_DIRECTORY);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    /**
     * Creates the archive.txt file inside the data directory if not exist yet.
     */

    private static void createArchiveFile() {
        File file = new File(ARCHIVE_DIRECTORY);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    /**
     * Creates the notes.txt file inside the data directory if not exist yet.
     */

    private static void createNoteFile() {
        File file = new File(NOTES_DIRECTORY);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}