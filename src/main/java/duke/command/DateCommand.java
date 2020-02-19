package duke.command;

import duke.task.Task;
import duke.task.TaskList;
import duke.task.DatedTask;
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

    public DateCommand() {

    }

    /**
     * DateCommand constructor.
     *
     * @param dateStr of the date user wants to check.
     */
    public DateCommand(String dateStr) {
        this.dateStr = dateStr;
    }

    /**
     * Executes Date behaviour of finding all tasks on a given date.
     *
     * @param taskList to access collection of tasks.
     * @param storage to access save-load functionality.
     */
    public void execute(TaskList taskList, Storage storage) {
        String latestResponse = "";

        try {
            LocalDate date =  LocalDate.parse(dateStr);
            Ui.showLine();
            int count = 0;
            for (Task t: taskList.getList()) {
                if (!(t instanceof DatedTask)) {
                    continue;
                }
                DatedTask datedTask = (DatedTask)t;
                if (!datedTask.getDate().toLocalDate().isEqual(date)) {
                    continue;
                }
                count++;
                latestResponse += count + "." + datedTask.toString() + "\n";
                Ui.printWithIndent(count + "." + datedTask.toString());
            }

            latestResponse += "You have " + count + " thing" + (count != 1 ? "s" : "")
                    + " happening on: " + date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
            Ui.setLatestResponse(latestResponse);
            Ui.printWithIndent(latestResponse);
            Ui.showLine();
        } catch (DateTimeParseException e) {
            latestResponse = "Please input a valid date. E.g. 2020-12-26";
            Ui.showLine();
            Ui.printWithIndent(latestResponse);
            Ui.setLatestResponse(latestResponse);
            Ui.showLine();
        }
    }

    /**
     * Inform if command is an exit command.
     *
     * @return boolean indicating if command is an exit command.
     */
    public boolean isExit() {
        return false;
    }

    @Override
    public String toString() {
        return "date yyyy-MM-dd - Shows all tasks on the given date";
    }
}
