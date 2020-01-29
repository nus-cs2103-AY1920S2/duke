import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class CalendarCommand extends Command {
    public static boolean run(TaskList taskList, String calendarDateString) {
        LocalDate calendarDate = LocalDate.parse(calendarDateString, DateTimeFormatter.ofPattern("d/M/yyyy"));
        List<String> calendarList = new ArrayList<>();
        for (Task task : taskList.getTasks()) {
            if (task.getClass().equals(Deadline.class)) {
                LocalDate taskDate = ((Deadline) task).by.toLocalDate();
                if (taskDate.equals(calendarDate)) {
                    calendarList.add(task.toString());
                }
            } else if (task.getClass().equals(Event.class)) {
                LocalDate taskDate = ((Event) task).at.toLocalDate();
                if (taskDate.equals(calendarDate)) {
                    calendarList.add(task.toString());
                }
            }
        }
        if (calendarList.size() == 0) {
            Ui.calendarNoItems();
        } else {
            Ui.calendarDisplayItems(calendarDate, taskList.printTaskList());
        }
        return true;
    }
}
