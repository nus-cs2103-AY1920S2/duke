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
    private Storage storage;
    private NoteStorage noteStorage;
    private NoteList noteList;
    private Parser parser;

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
        Storage taskStorage = new Storage("./data/tasks.txt");
        Storage archiveStorage = new Storage("./data/archive.txt");
        NoteStorage noteStorage = new NoteStorage("./data/notes.txt");
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
}