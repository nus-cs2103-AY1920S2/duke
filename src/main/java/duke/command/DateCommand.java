package duke.command;

import duke.task.*;
import duke.interaction.Ui;
import duke.util.Storage;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateCommand extends Command {

    private String dateStr;

    /**
     * DateCommand constructor.
     * @param dateStr of the date user wants to check.
     */
    public DateCommand(String dateStr) {
        this.dateStr = dateStr;
    }

    /**
     * Executes Date behaviour of finding all tasks on a given date.
     * @param taskList to access collection of tasks.
     * @param storage to access save-load functionality.
     */
    public void execute(TaskList taskList, Storage storage) {
        try {
            LocalDate date =  LocalDate.parse(dateStr);
            Ui.ShowLine();
            int count = 0;
            for (Task t: taskList.getList()) {
                if (t instanceof Deadline) {
                    Deadline d = (Deadline)t;
                    if (d.getDeadline().toLocalDate().isEqual(date)) {
                        count++;
                        Ui.PrintWithIndent(count + "." + d.toString());
                    }
                } else if (t instanceof Event) {
                    Event e = (Event)t;
                    if (e.getDatetime().toLocalDate().isEqual(date)) {
                        count++;
                        Ui.PrintWithIndent(count + "." + e.toString());
                    }
                }
            }
            Ui.PrintWithIndent("You have " + count + " thing" + (count != 1 ? "s" : "")
                    + " happening on: " + date.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
            Ui.ShowLine();
        } catch (DateTimeParseException e) {
            Ui.ShowLine();
            Ui.PrintWithIndent("Please input a valid date. E.g. 2020-12-26");
            Ui.ShowLine();
        }
    }

    /**
     * Inform if command is an exit command.
     * @return boolean indicating if command is an exit command.
     */
    public boolean isExit() {
        return false;
    }
}
