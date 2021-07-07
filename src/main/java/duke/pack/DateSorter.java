package duke.pack;

import java.util.Comparator;

/**
 * Represents a comparator to sort according to date.
 */
public class DateSorter implements Comparator<Task> {
    /**
     * Creates a DateSorter object.
     */
    public DateSorter() {
    }

    @Override
    public int compare(Task a, Task b) {
        String timeA = a.getTimeToCompare();
        String timeB = b.getTimeToCompare();

        return timeA.compareTo(timeB);
    }
}
