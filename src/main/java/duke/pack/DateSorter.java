package duke.pack;

import java.util.Comparator;

public class DateSorter implements Comparator<Task> {
    public DateSorter() {
    }

    @Override
    public int compare(Task a, Task b) {
        String timeA = a.getTimeToCompare();
        String timeB = b.getTimeToCompare();

        return timeA.compareTo(timeB);
    }
}
