package duke.pack;

import java.util.Comparator;

public class TimeSorter implements Comparator<Task> {
    public TimeSorter() {
    }

    @Override
    public int compare(Task a, Task b) {
        String dateA = a.getDateToCompare();
        String dateB = b.getDateToCompare();

        return dateA.compareTo(dateB);
    }
}
