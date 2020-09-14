package dukeproj.command;

import dukeproj.Parser;
import dukeproj.Storage;
import dukeproj.Ui;
import dukeproj.data.Schedule;
import dukeproj.data.TaskList;
import dukeproj.enums.SayType;
import dukeproj.exception.BadDateException;
import dukeproj.exception.DukeDescriptionException;

import java.time.LocalDate;

/**
 * Represents a command to search for tasks happening on a certain date.
 */
public class ScheduleCommand extends Command {
    /** The date to be searched for. */
    private String date;

    /**
     * Executes the search command to search for tasks that are happening on the date specified by the description.
     *
     * @param ui The user interface of Duke, used to return Duke's response.
     * @param taskList Unused.
     * @param storage Unused.
     * @param schedule The schedule to search tasks from.
     * @return Duke's response in the form of a String.
     * @throws DukeDescriptionException If the date is empty.
     * @throws BadDateException If the date is in a wrong format.
     */
    @Override
    public String execute(Ui ui, TaskList taskList, Storage storage, Schedule schedule)
            throws DukeDescriptionException, BadDateException {
        if (date.isEmpty()) {
            throw new DukeDescriptionException("Empty Description");
        }
        LocalDate date = Parser.dateParser(this.date);
        return ui.say(SayType.SEARCH) + date.format(Parser.DATE_READ_FORMATTER)
                + ":\n" + schedule.searchDate(date);
    }

    /**
     * Constructs a search command.
     *
     * @param date Date to be searched for.
     */
    public ScheduleCommand(String date) {
        this.date = date;
    }
}
