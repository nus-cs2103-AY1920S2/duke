import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class TaskQueries {

    public List<Task> filterBySpecificDate(List<Task> tasks, String date) {
        return tasks.stream().filter(task -> task.getDate().equals(LocalDate.parse(date))).collect(Collectors.toList());
    }

    public List<Task> filterBySpecificYear(List<Task> tasks, int year) {
        return tasks.stream().filter(task -> task.getDate().getYear() == year).collect(Collectors.toList());
    }

    public List<Task> filterBySpecificMonth(List<Task> tasks, int month) {
        return tasks.stream().filter(task -> task.getDate().getMonthValue() == month).collect(Collectors.toList());
    }
}
