package duke.command;

import duke.other.DateValidator;
import duke.other.DukeException;
import duke.task.TaskList;

import duke.other.Storage;
import duke.other.Ui;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;


/**
 * Represents a Command(i.e. ListCommand, DeleteCommand, ByeCommand). All Command extends this class.
 */
public abstract class Command {
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy/M/d");
    private static final DateValidator DATE_VALIDATOR = new DateValidator(DATE_FORMATTER);

    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm");
    private static final DateValidator TIME_VALIDATOR = new DateValidator(TIME_FORMATTER);
    public boolean isExit;

    public Command() {
        isExit = false;
    }

    public abstract String execute(TaskList taskList, Ui ui);

    public boolean isExit() {
        return isExit;
    }

    public boolean isValidDate(String s) {
        return DATE_VALIDATOR.isValidDate(s);
    }

    public boolean isValidTime(String s) {
        return TIME_VALIDATOR.isValidTime(s);
    }

    public DateTimeFormatter getDateFormatter() {
        return DATE_FORMATTER;
    }

    public DateTimeFormatter getTimeFormatter() {
        return TIME_FORMATTER;
    }

    public LocalDate parseValidDate(String s) {
        return LocalDate.parse(s, getDateFormatter());
    }

    public LocalTime parseValidTime(String s) {
        return LocalTime.parse(s, getTimeFormatter());
    }
}
