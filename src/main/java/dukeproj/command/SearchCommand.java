package dukeproj.command;

import dukeproj.Parser;
import dukeproj.Storage;
import dukeproj.Ui;
import dukeproj.data.Calender;
import dukeproj.data.TaskList;
import dukeproj.enums.SayType;
import dukeproj.exception.BadDateException;
import dukeproj.exception.DukeDescriptionException;

import java.time.LocalDate;

public class SearchCommand extends Command {
    private String description;

    @Override
    public String execute(Ui ui, TaskList taskList, Storage storage, Calender calender)
            throws DukeDescriptionException, BadDateException {
        if (description.isEmpty()) {
            throw new DukeDescriptionException("Empty Description");
        }
        LocalDate date = Parser.dateParser(description);
        return ui.say(SayType.SEARCH) + date.format(Parser.DATE_READ_FORMATTER)
                + ":\n" + calender.searchDate(date);
    }

    public SearchCommand(String description) {
        this.description = description;
    }
}
