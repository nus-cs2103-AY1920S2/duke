package duke.pack;

import java.util.Comparator;

/**
 * Represents a comparator to sort according to time.
 */
public class TimeSorter implements Comparator<Task> {
    /**
     * Creates a TimeSorter object.
     */
    public TimeSorter() {
    }

    @Override
    public int compare(Task a, Task b) {
        String dateA = a.getDateToCompare();
        String dateB = b.getDateToCompare();

        return dateA.compareTo(dateB);
    }
}
