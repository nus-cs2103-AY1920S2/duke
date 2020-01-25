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

    public boolean isExit() {
        return false;
    }
}
