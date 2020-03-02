package logic;

import logic.command.CommandException;
import logic.command.CommandResult;
import logic.parser.exceptions.ParserException;

import java.text.ParseException;

/**
 * API of the Logic component.
 */
public interface Logic {
    /**
     * Executes the command and returns the result.
     *
     * @param commandText The command as entered by the user.
     * @return the result of the command execution.
     * @throws CommandException If an error occurs during command execution.
     * @throws ParseException   If an error occurs during parsing.
     */
    CommandResult execute(String commandText) throws CommandException, ParseException, ParserException;
    /**
     * Returns the Task List.
     *
     * @see seedu.address.model.Model#getAddressBook()
     */
    //ReadOnlyAddressBook getAddressBook();

    /** Returns an unmodifiable view of the filtered list of persons */
    //ObservableList<Task> getFilteredPersonList();

    /**
     * Returns the user prefs' address book file path.
     */
    //Path getAddressBookFilePath();

    /**
     * Returns the user prefs' GUI settings.
     */
    //GuiSettings getGuiSettings();

    /**
     * Set the user prefs' GUI settings.
     */
    //void setGuiSettings(GuiSettings guiSettings)
}
