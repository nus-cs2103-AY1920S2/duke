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

public class DateCommand extends Command {

    private String dateStr;

    public DateCommand(String dateStr) {
        this.dateStr = dateStr;
    }

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

    public boolean isExit() {
        return false;
    }
}
