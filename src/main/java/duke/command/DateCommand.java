package duke.command;

import duke.task.Task;
import duke.task.TaskList;
import duke.task.Deadline;
import duke.task.Event;
import duke.interaction.Ui;
import duke.util.Storage;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents the Command for the "date" input by the user.
 * It displays all task items that fall on an input date.
 *
 * @author  Hardy Shein
 * @version 0.1
 */
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
            Ui.showLine();
            int count = 0;
            for (Task t: taskList.getList()) {
                if (t instanceof Deadline) {
                    Deadline d = (Deadline)t;
                    if (d.getDeadline().toLocalDate().isEqual(date)) {
                        count++;
                        Ui.printWithIndent(count + "." + d.toString());
                    }
                } else if (t instanceof Event) {
                    Event e = (Event)t;
                    if (e.getDatetime().toLocalDate().isEqual(date)) {
                        count++;
                        Ui.printWithIndent(count + "." + e.toString());
                    }
                }
            }
            Ui.printWithIndent("You have " + count + " thing" + (count != 1 ? "s" : "")
                    + " happening on: " + date.format(DateTimeFormatter.ofPattern("MMM d yyyy")));
            Ui.showLine();
        } catch (DateTimeParseException e) {
            Ui.showLine();
            Ui.printWithIndent("Please input a valid date. E.g. 2020-12-26");
            Ui.showLine();
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
