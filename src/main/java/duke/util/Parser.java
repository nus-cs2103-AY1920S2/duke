package duke.util;

import duke.command.AddCommand;
import duke.command.ArchiveAddCommand;
import duke.command.ArchiveDeleteCommand;
import duke.command.ArchiveListCommand;
import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.ReminderCommand;
import duke.command.NoteAddCommand;
import duke.command.NoteDeleteCommand;
import duke.command.NoteListCommand;
import duke.command.UnarchiveCommand;
import duke.exception.DukeInvalidArgumentFormatException;
import duke.exception.DukeInvalidDateFormatException;
import duke.exception.DukeUnknownKeywordException;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Optional;

/*
 * Parser
 *
 * CS2103 AY19/20 Semester 2
 * Individual Project
 * Duke Project
 *
 * 28 Jan 2020
 *
 */

/**
 * <p>Parser class describes the behaviour of a
 * parser that parses commands entered by the client.</p>
 * @author Mario Lorenzo
 */

public class Parser {
    private static HashMap<String, Keyword> VALID_KEYWORDS = new HashMap<>() {
        {
            put("list", Keyword.LIST);
            put("done", Keyword.DONE);
            put("todo", Keyword.TODO);
            put("deadline", Keyword.DEADLINE);
            put("event", Keyword.EVENT);
            put("delete", Keyword.DELETE);
            put("find", Keyword.FIND);
            put("bye", Keyword.BYE);
            put("reminder", Keyword.REMINDER);
            put("archive-list", Keyword.ARCHIVE_LIST);
            put("archive-add", Keyword.ARCHIVE_ADD);
            put("archive-delete", Keyword.ARCHIVE_DELETE);
            put("unarchive", Keyword.UNARCHIVE);
            put("note-list", Keyword.NOTE_LIST);
            put("note-add", Keyword.NOTE_ADD);
            put("note-delete", Keyword.NOTE_DELETE);
        }
    };

    /**
     * Constructs a Parser instance.
     */

    public Parser() {

    }

    /**
     * Parses the command string entered by the client and create a Command
     * instance. If the command keyword is a valid keyword, it will return an
     * Argument instance. Otherwise, it will throw an exception.
     * @param commandString The command string entered by the client.
     * @return An Argument instance, initialized by the constructor.
     * @throws DukeUnknownKeywordException If the command keyword (the first word) is invalid.
     * @throws DukeInvalidDateFormatException If the date is invalid.
     * @throws DukeInvalidArgumentFormatException If the format of the command is invalid.
     */

    public Command parse(String commandString, IList<Task> taskList, IList<Task> archiveList, IList<Note> noteList)
            throws DukeUnknownKeywordException, DukeInvalidArgumentFormatException, DukeInvalidDateFormatException {
        String commandStr = getCommandKeyword(commandString);

        /*
         If optional_command is empty, it means that the command is not found.
         Therefore, it will throw the exception to inform the client.
         */

        Optional<Keyword> optionalKeyword = getOptionalKeyword(commandStr);
        Keyword keyword = optionalKeyword.orElseThrow(() ->
                new DukeUnknownKeywordException("☹ OOPS!!! I'm sorry, "
                        + "but I don't know what that means :-("));
        String details = getDetails(commandString);

        return validateCommand(keyword, details, taskList, archiveList, noteList);
    }

    /**
     * Gets the keyword from the user input.
     * @param commandString The user input.
     * @return The command keyword.
     */

    private String getCommandKeyword(String commandString) {
        String[] splittedCommands = commandString.split(" ");
        return splittedCommands[0];
    }

    /**
     * Gets the details from the user input.
     * @param commandString The user input.
     * @return The details or other particulars from the input.
     */

    private String getDetails(String commandString) {
        String[] splittedCommands = commandString.split(" ");
        return splittedCommands.length > 1
                ? commandString.split(" ", 2)[1]
                : "";
    }

    /**
     * Validates the command and returns the corresponding Command instance if
     * the command string is valid.
     * @param keyword The keyword of the command.
     * @param details The details of the command.
     * @param taskList The list of tasks.
     * @param archiveList The list of archived tasks.
     * @param noteList The list of notes.
     * @return The corresponding Command instance.
     * @throws DukeInvalidArgumentFormatException If the command is in invalid format.
     * @throws DukeInvalidDateFormatException If the date is in invalid format.
     */
    
    private Command validateCommand(Keyword keyword, String details, IList<Task> taskList,
                                    IList<Task> archiveList, IList<Note> noteList) throws
            DukeInvalidArgumentFormatException, DukeInvalidDateFormatException {
        Command command;
        switch (keyword) {
        case LIST:
            command = checkValidListArgument(details);
            break;
        case DONE:
            command = checkValidDoneArgument(details, taskList);
            break;
        case DELETE:
            command = checkValidDeleteArgument(details, taskList);
            break;
        case FIND:
            command = checkValidFindArgument(details);
            break;
        case TODO:
            command = checkValidTodoArgument(details);
            break;
        case DEADLINE:
            command = checkValidDeadlineArgument(details);
            break;
        case BYE:
            command = checkValidByeArgument(details);
            break;
        case REMINDER:
            command = checkValidReminderArgument(details);
            break;
        case ARCHIVE_LIST:
            command = checkValidArchiveListArgument(details);
            break;
        case ARCHIVE_ADD:
            command = checkValidArchiveAddArgument(details, taskList);
            break;
        case ARCHIVE_DELETE:
            command = checkValidArchiveDeleteArgument(details, archiveList);
            break;
        case UNARCHIVE:
            command = checkValidUnarchiveArgument(details, archiveList);
            break;
        case NOTE_LIST:
            command = checkValidNoteListArgument(details);
            break;
        case NOTE_ADD:
            command = checkValidNoteAddArgument(details);
            break;
        case NOTE_DELETE:
            command = checkValidNoteDeleteArgument(details, noteList);
            break;
        default:
            command = checkValidEventArgument(details);
            break;
        }
        return command;
    }

    /**
     * Returns the Keyword enums of the corresponding
     * command string provided. Optional instances are used to handle cases
     * where the command string entered by the client is invalid, thus returning
     * null value.
     * @param commandString The command provided by the client to be processed.
     * @return The corresponding Keyword enums, packed in form of an Optional instance.
     */

    private static Optional<Keyword> getOptionalKeyword(String commandString) {
        return Optional.ofNullable(VALID_KEYWORDS.get(commandString));
    }

    /**
     * Verifies whether the Argument instance
     * is a valid "list" argument. It will throw an exception if
     * the argument is not valid.
     * @param details The details of the command.
     * @throws DukeInvalidArgumentFormatException If the argument is not a valid argument.
     */

    private ListCommand checkValidListArgument(String details) throws DukeInvalidArgumentFormatException {
        if (!details.equals("")) {
            throw new DukeInvalidArgumentFormatException("☹ OOPS!!! "
                    + "The argument for 'list' command is invalid.");
        }
        return new ListCommand();
    }

    /**
     * Verifies whether the Argument instance
     * is a valid "done" argument. It will throw an exception if
     * the argument is not valid (i.e. if the argument is empty or
     * not a number).
     * @param details The details of the command.
     * @param taskList The list of the tasks.
     * @return The index of the done argument, if the argument is valid.
     * @throws DukeInvalidArgumentFormatException If the argument is not valid.
     */

    private DoneCommand checkValidDoneArgument(String details, IList<Task> taskList) throws
            DukeInvalidArgumentFormatException {
        int index = -1;
        try {
            index = Integer.parseInt(details);
        } catch (NumberFormatException e) {
            throw new DukeInvalidArgumentFormatException("☹ OOPS!!! "
                    + "The argument for 'done' command requires a number.");
        }

        if (index <= 0 || index > taskList.size()) {
            throw new DukeInvalidArgumentFormatException("☹ OOPS!!! The index given is out of bound.");
        }
        return new DoneCommand(index);
    }

    /**
     * Verifies whether the Argument instance
     * is a valid "delete" argument. It will throw an exception if
     * the argument is not valid (i.e. if the argument is empty or
     * not a number).
     * @param details The details of the command.
     * @param taskList The list of the tasks.
     * @return The index of the delete argument, if the argument is valid.
     * @throws DukeInvalidArgumentFormatException If the argument is not valid.
     */

    private DeleteCommand checkValidDeleteArgument(String details, IList<Task> taskList) throws
            DukeInvalidArgumentFormatException {
        int index = -1;
        try {
            index = Integer.parseInt(details);
        } catch (NumberFormatException e) {
            throw new DukeInvalidArgumentFormatException("☹ OOPS!!! "
                    + "The argument for 'delete' command requires a number.");
        }

        if (index <= 0 || index > taskList.size()) {
            throw new DukeInvalidArgumentFormatException("☹ OOPS!!! The index given is out of bound.");
        }
        return new DeleteCommand(index);
    }

    /**
     * Verifies whether the Argument instance
     * is a valid "todo" argument. It will throw an exception if
     * the argument is not valid (i.e. if the argument has no
     * description).
     * @param details The details of the command.
     * @return The description of the todo argument, if the argument is valid.
     * @throws DukeInvalidArgumentFormatException If the argument is not valid.
     */

    private AddCommand checkValidTodoArgument(String details) throws DukeInvalidArgumentFormatException {
        if (details.equals("")) {
            throw new DukeInvalidArgumentFormatException("☹ OOPS!!! "
                    + "The argument for 'todo' command requires a description");
        }
        return new AddCommand(new Todo(details));
    }

    /**
     * Verifies whether the Argument instance
     * is a valid "deadline" argument. It will throw an exception if
     * the argument is not valid (i.e. if the argument has no description,
     * or the due date, or the format of the command is not proper).
     * @param details The details of the command.
     * @return The Pair of String, regarding to the description and the due date.
     * @throws DukeInvalidArgumentFormatException If the argument is not valid.
     */

    private AddCommand checkValidDeadlineArgument(String details) throws DukeInvalidArgumentFormatException,
            DukeInvalidDateFormatException {
        String caption;
        String bySchedule;
        String[] detailsWithSchedule = details.split(" /by ");
        try {
            caption = detailsWithSchedule[0];
            bySchedule = detailsWithSchedule[1];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeInvalidArgumentFormatException("☹ OOPS!!! "
                    + "The argument for 'deadline' "
                    + "command requires a description and a due date.");
        }

        return new AddCommand(new Deadline(caption, bySchedule));
    }

    /**
     * Verifies whether the Argument instance
     * is a valid "event" argument. It will throw an exception if
     * the argument is not valid (i.e. if the argument has no description,
     * or the event date, or the format of the command is not proper).
     * @param details The details of the command.
     * @return The Pair of String, regarding to the description and the event date.
     * @throws DukeInvalidArgumentFormatException If the argument is not valid.
     */

    private AddCommand checkValidEventArgument(String details) throws DukeInvalidArgumentFormatException,
            DukeInvalidDateFormatException {
        String caption;
        String atSchedule;
        String[] detailsWithSchedule = details.split(" /at ");
        try {
            caption = detailsWithSchedule[0];
            atSchedule = detailsWithSchedule[1];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeInvalidArgumentFormatException("☹ OOPS!!! "
                    + "The argument for 'event' "
                    + "command requires a description and an event date.");
        }

        return new AddCommand(new Event(caption, atSchedule));
    }

    /**
     * Verifies that the command entered by the client is a valid
     * find command.
     * @param details The details of the command.
     * @return The FindCommand instance of the corresponding input.
     */

    private FindCommand checkValidFindArgument(String details) {
        return new FindCommand(details);
    }

    /**
     * Verifies that the command entered by the client is a valid bye command.
     * @param details The details of the command.
     * @return The ByeCommand instance of the corresponding input.
     */

    private ByeCommand checkValidByeArgument(String details) {
        return new ByeCommand();
    }

    /**
     * Verifies that the command entered by the client is a valid reminder command.
     * @param details The details of the command.
     * @return The ReminderCommand instance of the corresponding input.
     */

    private ReminderCommand checkValidReminderArgument(String details) {
        return new ReminderCommand();
    }
    /**
     * Verifies that the command entered by the client is a valid archive-list command.
     * @param details The details of the command.
     * @return The ArchiveListCommand instance of the corresponding input.
     */

    private ArchiveListCommand checkValidArchiveListArgument(String details) throws DukeInvalidArgumentFormatException {
        if (!details.equals("")) {
            throw new DukeInvalidArgumentFormatException("☹ OOPS!!! "
                    + "The argument for 'archive-list' command is invalid.");
        }
        return new ArchiveListCommand();
    }

    /**
     * Verifies that the command entered by the client is a valid archive-add command.
     * @param details The details of the command.
     * @param taskList The list of the archives.
     * @return The ArchiveAddCommand instance of the corresponding input.
     */

    private ArchiveAddCommand checkValidArchiveAddArgument(String details, IList<Task> taskList) throws
            DukeInvalidArgumentFormatException {
        int index = -1;
        try {
            index = Integer.parseInt(details);
        } catch (NumberFormatException e) {
            throw new DukeInvalidArgumentFormatException("☹ OOPS!!! "
                    + "The argument for 'done' command requires a number.");
        }

        if (index <= 0 || index > taskList.size()) {
            throw new DukeInvalidArgumentFormatException("☹ OOPS!!! The index given is out of bound.");
        }
        return new ArchiveAddCommand(index);
    }

    /**
     * Verifies that the command entered by the client is a valid archive-delete command.
     * @param details The details of the command.
     * @param taskList The list of the archives.
     * @return The ArchiveDeleteCommand instance of the corresponding input.
     */

    private ArchiveDeleteCommand checkValidArchiveDeleteArgument(String details, IList<Task> taskList) throws
            DukeInvalidArgumentFormatException {
        int index = -1;
        try {
            index = Integer.parseInt(details);
        } catch (NumberFormatException e) {
            throw new DukeInvalidArgumentFormatException("☹ OOPS!!! "
                    + "The argument for 'archive-delete' command requires a number.");
        }

        if (index <= 0 || index > taskList.size()) {
            throw new DukeInvalidArgumentFormatException("☹ OOPS!!! The index given is out of bound.");
        }
        return new ArchiveDeleteCommand(index);
    }

    /**
     * Verifies that the command entered by the client is a valid unarchive command.
     * @param details The details of the command.
     * @param taskList The list of the archives.
     * @return The UnarchiveCommand instance of the corresponding input.
     */

    private UnarchiveCommand checkValidUnarchiveArgument(String details, IList<Task> taskList) throws
            DukeInvalidArgumentFormatException {
        int index = -1;
        try {
            index = Integer.parseInt(details);
        } catch (NumberFormatException e) {
            throw new DukeInvalidArgumentFormatException("☹ OOPS!!! "
                    + "The argument for 'archive-delete' command requires a number.");
        }

        if (index <= 0 || index > taskList.size()) {
            throw new DukeInvalidArgumentFormatException("☹ OOPS!!! The index given is out of bound.");
        }
        return new UnarchiveCommand(index);
    }

    /**
     * Verifies that the command entered by the client is a valid
     * note-add command.
     * @param details The details of the command.
     * @return The NoteAddCommand instance of the corresponding input.
     */

    private NoteAddCommand checkValidNoteAddArgument(String details) {
        return new NoteAddCommand(new Note(details, LocalDateTime.now()));
    }

    /**
     * Verifies that the command entered by the client is a valid
     * note-list command.
     * @param details The details of the command.
     * @return The NoteListCommand instance of the corresponding input.
     */

    private NoteListCommand checkValidNoteListArgument(String details) throws DukeInvalidArgumentFormatException {
        if (!details.equals("")) {
            throw new DukeInvalidArgumentFormatException("☹ OOPS!!! "
                    + "The argument for 'list' command is invalid.");
        }
        return new NoteListCommand();
    }

    /**
     * Verifies that the command entered by the client is a valid
     * note-delete command.
     * @param details The details of the command.
     * @return The NoteDeleteCommand instance of the corresponding input.
     */
    private NoteDeleteCommand checkValidNoteDeleteArgument(String details, IList<Note> noteList) throws
            DukeInvalidArgumentFormatException {
        int index = -1;
        try {
            index = Integer.parseInt(details);
        } catch (NumberFormatException e) {
            throw new DukeInvalidArgumentFormatException("☹ OOPS!!! "
                    + "The argument for 'archive-delete' command requires a number.");
        }

        if (index <= 0 || index > noteList.size()) {
            throw new DukeInvalidArgumentFormatException("☹ OOPS!!! The index given is out of bound.");
        }
        return new NoteDeleteCommand(index);
    }
}